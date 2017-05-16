package com.isha.donation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.isha.donation.entity.Volunteer;
import com.isha.donation.service.VolunteerService;

@Controller
public class VolunteerController {

    @Autowired
    private VolunteerService mVolunteerService;

    @RequestMapping(value = "/volunteer", method = RequestMethod.GET)		
    @ResponseBody
    public Object index(){ 
        return mVolunteerService.findAll();
    }

    @RequestMapping(value = "/createVolunteer", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)	  
    @ResponseBody
    public String create(@RequestBody Volunteer volunteer) {
        String volunteerId = "";
      try {
          mVolunteerService.save(volunteer);
          volunteerId = String.valueOf(volunteer.getId());
      }
      catch (Exception ex) {
        return "Error creating the user: " + ex.toString();
      }
      return "Voluteer succesfully created with id = " + volunteerId;
    }

    @RequestMapping("/deleteVolunteer/{id}")
    @ResponseBody
    public String delete(@PathVariable long id) {
      try {
         Volunteer user = mVolunteerService.findById(id);
         mVolunteerService.delete(user);
      }
      catch (Exception ex) {
        return "Error deleting the Volunteer:" + ex.toString();
      }
      return "Volunteer succesfully deleted!";
    }

    @RequestMapping("/get-voluteer-by-email")
    @ResponseBody
    public String getByEmail(String email) {
      String volunteerId = "";
     try {
          Volunteer volunteer = mVolunteerService.findByEmail(email);
          volunteerId = String.valueOf(volunteer.getId());
      }
      catch (Exception ex) {
          return "Volunteer not found";
      }
          return "The Volunteer id is: " + volunteerId;
    }

    @RequestMapping("/updateVolunteer/{id}")
    @ResponseBody
    public String updateUser(@RequestBody Volunteer volunteer,@PathVariable Long id) {
        try {
            volunteer.setId(id);
            mVolunteerService.save(volunteer);
        }
        catch (Exception ex) {
          return "Error updating the Volunteer: " + ex.toString();
        }
        return "Volunteer succesfully updated!";
     }
}
