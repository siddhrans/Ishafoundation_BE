package com.isha.donation.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.isha.donation.entity.Volunteer;

public class Utils {

    public String getCurrentTime() {
        String time = "";
        time = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss", Locale.US).format(new Date());
        return time;
    }

    public String checkSignInCredentials(Volunteer volunteer, String password) {
        if (volunteer.getPassword().equals(password)) {
            if (volunteer.getStatus().equalsIgnoreCase("active")) {
                return "Login Successful :)";
            } else {
                return "Login failed : Volunteer status is : "+volunteer.getStatus();
            }
        }
        return "Login failed : Wrong Password";
    }
}
