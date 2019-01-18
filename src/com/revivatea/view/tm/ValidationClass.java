package com.revivatea.view.tm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationClass {

    public static boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean isValidPhoneNumber(String number){

        Pattern pattern = Pattern.compile("\\d{3}-\\d{7}");
        Matcher matcher = pattern.matcher(number);

        if (matcher.matches()) {
            return true;
        }
        else
        {
           return false;
        }
    }
    public static boolean validateNIC(String nic){
        if(!(nic.trim().matches("^[0-9]{9}[vV]$")))
        {
            return false;
        }
        return true;
    }

    public static boolean isDouble(String text){
        try {
            Double.parseDouble(text);

        }catch (Exception e){
            return false;
        }
        return true;
    }

    public static boolean NagativeCheck(double value){
        if(value<0){
            return true;
        }
        return false;
    }

    public static boolean isINT(String text){
        try {
            Integer.parseInt(text);

        }catch (Exception e){
            return false;
        }
        return true;
    }
}
