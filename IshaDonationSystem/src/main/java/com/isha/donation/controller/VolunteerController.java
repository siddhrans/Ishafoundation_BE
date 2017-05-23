package com.isha.donation.controller;

import org.json.JSONObject;
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
import com.isha.donation.utils.Utils;

@Controller
public class VolunteerController {

    @Autowired
    private VolunteerService mVolunteerService;
    private Utils mUtils = new Utils();

    @RequestMapping(value = "/ShowAllVolunteer", method = RequestMethod.GET)
    @ResponseBody
    public Object index() {
        return mVolunteerService.findAll();
    }

    @RequestMapping(value = "/createVolunteer", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String create(@RequestBody Volunteer volunteer) {
        String volunteerId = "";
        try {
            volunteer.setCreateDate(mUtils.getCurrentTime());
            volunteer.setComments(volunteer.getName() + " : added.");
            mVolunteerService.save(volunteer);
            volunteerId = String.valueOf(volunteer.getId());
        } catch (Exception ex) {
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
        } catch (Exception ex) {
            return "Error deleting the Volunteer:" + ex.toString();
        }
        return "Volunteer succesfully deleted!";
    }

    @RequestMapping(value = "/login", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getByVolunteerPhoneNo(@RequestBody String userCredentials) {
        try {
            JSONObject json = new JSONObject(userCredentials);
            Volunteer volunteer = mVolunteerService.findByPhoneNo(json.getString("phoneNo"));
            if (volunteer != null) {
                return mUtils.checkSignInCredentials(volunteer, json.getString("password"));
            }
        } catch (Exception ex) {
            return "Error login ..! validate inputs";
        }
        return "Volunteer not exist :(";
    }

    @RequestMapping("/updateVolunteer/{id}")
    @ResponseBody
    public String updateUser(@RequestBody Volunteer volunteer, @PathVariable Long id) {
        try {
            volunteer.setUpdateDate(mUtils.getCurrentTime());
            volunteer.setId(id);
            mVolunteerService.save(volunteer);
        } catch (Exception ex) {
            return "Error updating the Volunteer: " + ex.toString();
        }
        return "Volunteer succesfully updated!";
    }
}
