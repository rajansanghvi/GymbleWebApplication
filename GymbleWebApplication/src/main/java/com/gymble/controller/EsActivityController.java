package com.gymble.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gymble.entity.EsActivity;
import com.gymble.enumeration.Status;
import com.gymble.exception.EsBusinessException;
import com.gymble.service.intf.EsActivityService;

@Controller
@RequestMapping("/activity")
public class EsActivityController {
    
    @Autowired
    private EsActivityService esActivityService;
    
    @RequestMapping(value = { "/active-activites" }, method = RequestMethod.GET)
    public String listActiveActivities(ModelMap modelMap)
    {
        try {
            List<EsActivity> activities = esActivityService.findAllByStatus(Status.ACTIVE);
            modelMap.addAttribute("activities", activities);
            return "activityList";
        }
        catch (EsBusinessException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage());
            return "welcome";
        }
    }
}
