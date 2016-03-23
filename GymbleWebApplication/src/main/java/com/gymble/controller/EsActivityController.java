package com.gymble.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gymble.entity.EsActivity;
import com.gymble.enumeration.Status;
import com.gymble.exception.EsBusinessException;
import com.gymble.service.intf.EsActivityService;

@RestController
public class EsActivityController {
	@Autowired
    private EsActivityService esActivityService;
	  @RequestMapping(value = { "/list-activities/" }, method = RequestMethod.GET)
	    public ResponseEntity<List<EsActivity>> listActivities()
	    {
	        try {
	            List<EsActivity> activities = esActivityService.findAllByStatus(Status.ALL);
	            return new ResponseEntity<List<EsActivity>>(activities, HttpStatus.OK);
	        }
	        catch (EsBusinessException e) {
	            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage());
	            return new ResponseEntity<List<EsActivity>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	    }
}
