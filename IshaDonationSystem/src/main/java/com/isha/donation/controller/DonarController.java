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

import com.isha.donation.entity.Donar;
import com.isha.donation.service.DonarService;
import com.isha.donation.utils.Utils;

@Controller
public class DonarController {

    @Autowired
    private DonarService mDonarService;
    private Utils mUtils = new Utils();

    @RequestMapping(value = "/ShowAllDonar", method = RequestMethod.GET)
    @ResponseBody
    public Object index() {
        return mDonarService.findAll();
    }

    @RequestMapping(value = "/createDonar", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String create(@RequestBody Donar donar) {
        String donarId = "";
        try {
            donar.setCreateDonardate(mUtils.getCurrentTime());
            donar.setDonarComments(donar.getCreatorName() + " : added.");
            mDonarService.save(donar);
            donarId = String.valueOf(donar.getDonarId());
        } catch (Exception ex) {
            return "Error creating the Donar: " + ex.toString();
        }
        return "Donar succesfully created with id = " + donarId;
    }

    @RequestMapping("/updateDonar/{id}")
    @ResponseBody
    public String updateUser(@RequestBody Donar donar, @PathVariable Long id) {
        try {
            donar.setDonarUpdatedDate(mUtils.getCurrentTime());
            donar.setDonarId(id);
            mDonarService.save(donar);
        } catch (Exception ex) {
            return "Error updating the Donar: " + ex.toString();
        }
        return "Donar succesfully updated!";
    }

    @RequestMapping(value = "/searchDonar", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Donar getByVolunteerPhoneNo(@RequestBody String searchString) {
        try {
            JSONObject json = new JSONObject(searchString);
            Donar donar = mDonarService.findByMobileNumber(json.getString("mobileNumber"));
            if (donar != null) {
                return donar;
            }
        } catch (Exception ex) {
            return null; // here returning null means No donar found for entered
                            // search
        }
        return null; // here returning null means No donar found for entered
                        // search
    }
}
