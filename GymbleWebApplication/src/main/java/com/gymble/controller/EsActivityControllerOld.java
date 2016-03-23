package com.gymble.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gymble.entity.EsActivity;
import com.gymble.entity.EsStudent;
import com.gymble.enumeration.Status;
import com.gymble.exception.EsBadDataException;
import com.gymble.exception.EsBusinessException;
import com.gymble.exception.EsDataValidationException;
import com.gymble.service.intf.EsActivityService;

@Controller
@RequestMapping("/activities")
public class EsActivityControllerOld {
    
    @Autowired
    private EsActivityService esActivityService;
    
    @RequestMapping(value = { "/list-active-activities" }, method = RequestMethod.GET)
    public String listActiveActivities(ModelMap modelMap)
    {
        try {
            List<EsActivity> activities = esActivityService.findAllByStatus(Status.ACTIVE);
            modelMap.addAttribute("activities", activities);
            modelMap.addAttribute("status", "Active");
            return "listActivities";
        }
        catch (EsBusinessException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage());
            return "welcome";
        }
    }
    
    @RequestMapping(value = { "/list-inactive-activities" }, method = RequestMethod.GET)
    public String listInactiveActivities(ModelMap modelMap)
    {
        try {
            List<EsActivity> activities = esActivityService.findAllByStatus(Status.INACTIVE);
            modelMap.addAttribute("activities", activities);
            modelMap.addAttribute("status", "Inactive");
            return "listActivities";
        }
        catch (EsBusinessException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage());
            return "welcome";
        }
    }
    
    @RequestMapping(value = {"/create-activity"}, method = RequestMethod.GET)
    public String createActivity(ModelMap modelMap)
    {
    	if(!modelMap.containsAttribute("activity"))
    	{
	        EsActivity esActivity = new EsActivity();
	        modelMap.addAttribute("activity", esActivity);
    	}
        return "createActivity";
    }
    
    @RequestMapping(value = {"/create-activity"}, method = RequestMethod.POST)
    public String saveEmployee(@ModelAttribute("activity")@Valid EsActivity activity, BindingResult bindingResult, ModelMap modelMap, RedirectAttributes attr)
    {
    	if(bindingResult.hasErrors())
    	{
    		attr.addFlashAttribute("org.springframework.validation.BindingResult.activity", bindingResult);
    		attr.addFlashAttribute("activity", activity);
    		return "redirect:/activities/create-activity";
    	}
    	
    	EsActivity dbActivity = null;
    	try {
			dbActivity = esActivityService.GetActivityByName(activity.getName());
		} catch (EsBadDataException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage());
            FieldError nameError = new FieldError("activity", "name", "Name of the Activity can not be left blank or empty");
            bindingResult.addError(nameError);
            
            attr.addFlashAttribute("org.springframework.validation.BindingResult.activity", bindingResult);
    		attr.addFlashAttribute("activity", activity);
            
    		return "redirect:/activities/create-activity";
		} catch (EsBusinessException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage());
            FieldError nameError = new FieldError("activity", "name", "General Error while creating an activity");
            bindingResult.addError(nameError);
            
            attr.addFlashAttribute("org.springframework.validation.BindingResult.activity", bindingResult);
    		attr.addFlashAttribute("activity", activity);
            
    		return "redirect:/activities/create-activity";
		}
    	
    	if(dbActivity != null)
    	{
    		FieldError nameError = new FieldError("activity", "name", "Another activity with the same name already exists");
            bindingResult.addError(nameError);
            
            attr.addFlashAttribute("org.springframework.validation.BindingResult.activity", bindingResult);
    		attr.addFlashAttribute("activity", activity);
    		return "redirect:/activities/create-activity";
    	}
    	
    	try {
			Long id = esActivityService.create(activity, "admin");
			if(id > 0)
			{
				return "redirect:/activities/list-active-activities";
			}
			else
			{
				FieldError nameError = new FieldError("activity", "name", "General Error while creating an activity");
	            bindingResult.addError(nameError);
	            
	            attr.addFlashAttribute("org.springframework.validation.BindingResult.activity", bindingResult);
	    		attr.addFlashAttribute("activity", activity);
	    		return "redirect:/activities/create-activity";
			}
		} catch (EsDataValidationException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage());
            FieldError nameError = new FieldError("activity", "name", "Data validation error. Please contact admin");
            bindingResult.addError(nameError);
            
            attr.addFlashAttribute("org.springframework.validation.BindingResult.activity", bindingResult);
    		attr.addFlashAttribute("activity", activity);
    		return "redirect:/activities/create-activity";
		} catch (EsBusinessException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage());
            FieldError nameError = new FieldError("activity", "name", "General Error while creating an activity");
            bindingResult.addError(nameError);
            
            attr.addFlashAttribute("org.springframework.validation.BindingResult.activity", bindingResult);
    		attr.addFlashAttribute("activity", activity);
    		return "redirect:/activities/create-activity";
		}
    }
    
    @RequestMapping(value = {"/edit-activity/{code}"}, method = RequestMethod.GET)
    public String editActivity(@PathVariable String code, ModelMap modelMap)
    {
    	if(!modelMap.containsAttribute("activity"))
    	{
	        EsActivity esActivity = null;
			try {
				esActivity = esActivityService.findByCode(code);
				if(esActivity == null)
				{
					return "redirect:/activities/list-active-activities";
				}
				
			} catch (EsBadDataException e) {
				Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage());
				return "redirect:/activities/list-active-activities";
			} catch (EsBusinessException e) {
				Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage());
	    		return "redirect:/activities/list-active-activities";
			}
	        modelMap.addAttribute("activity", esActivity);
    	}
        return "editActivity";
    }
    
    @RequestMapping(value = {"/edit-activity/{code}"}, method = RequestMethod.POST)
    public String updateEmployee(@ModelAttribute("activity")@Valid EsActivity activity, BindingResult bindingResult, ModelMap modelMap, @PathVariable String code, RedirectAttributes attr)
    {
    	if(bindingResult.hasErrors())
    	{
    		attr.addFlashAttribute("org.springframework.validation.BindingResult.activity", bindingResult);
    		attr.addFlashAttribute("activity", activity);
    		attr.addFlashAttribute("code", activity.getCode());
    		return "redirect:/activities/edit-activity{code}";
    	}
    	
    	EsActivity dbActivity = null;
    	try {
			dbActivity = esActivityService.GetActivityByName(activity.getName());
		} catch (EsBadDataException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage());
            FieldError nameError = new FieldError("activity", "name", "Name of the Activity can not be left blank or empty");
            bindingResult.addError(nameError);
            
            attr.addFlashAttribute("org.springframework.validation.BindingResult.activity", bindingResult);
    		attr.addFlashAttribute("activity", activity);
    		attr.addFlashAttribute("code", activity.getCode());
    		return "redirect:/activities/edit-activity{code}";
		} catch (EsBusinessException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage());
            FieldError nameError = new FieldError("activity", "name", "General Error while creating an activity");
            bindingResult.addError(nameError);
            
            attr.addFlashAttribute("org.springframework.validation.BindingResult.activity", bindingResult);
    		attr.addFlashAttribute("activity", activity);
    		attr.addFlashAttribute("code", activity.getCode());
    		return "redirect:/activities/edit-activity{code}";
		}
    	
    	if(dbActivity != null)
    	{
    		FieldError nameError = new FieldError("activity", "name", "Another activity with the same name already exists");
            bindingResult.addError(nameError);
            
            attr.addFlashAttribute("org.springframework.validation.BindingResult.activity", bindingResult);
    		attr.addFlashAttribute("activity", activity);
    		attr.addFlashAttribute("code", activity.getCode());
    		return "redirect:/activities/edit-activity{code}";
    	}
    	
    	try {
			esActivityService.edit(activity, "admin");
		
			return "redirect:/activities/list-active-activities";
		} catch (EsDataValidationException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage());
            FieldError nameError = new FieldError("activity", "name", "Data validation error. Please contact admin");
            bindingResult.addError(nameError);
            
            attr.addFlashAttribute("org.springframework.validation.BindingResult.activity", bindingResult);
    		attr.addFlashAttribute("activity", activity);
    		attr.addFlashAttribute("code", activity.getCode());
    		return "redirect:/activities/edit-activity{code}";
		} catch (EsBusinessException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage());
            FieldError nameError = new FieldError("activity", "code", "General Error while updating an activity");
            bindingResult.addError(nameError);
            
            attr.addFlashAttribute("org.springframework.validation.BindingResult.activity", bindingResult);
    		attr.addFlashAttribute("activity", activity);
    		attr.addFlashAttribute("code", activity.getCode());
    		return "redirect:/activities/edit-activity{code}";
		} catch (EsBadDataException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage());
            FieldError nameError = new FieldError("activity", "code", "Activity you are trying to update is inactive");
            bindingResult.addError(nameError);
            
            attr.addFlashAttribute("org.springframework.validation.BindingResult.activity", bindingResult);
    		attr.addFlashAttribute("activity", activity);
    		attr.addFlashAttribute("code", activity.getCode());
    		return "redirect:/activities/edit-activity{code}";
		}
    }
}
