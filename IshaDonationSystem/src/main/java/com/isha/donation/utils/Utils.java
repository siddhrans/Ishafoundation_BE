package com.isha.donation.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {

     public String getCurrentTime() {
         String time = "";
         time = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss", Locale.US).format(new Date());
         return time;
     }
}
