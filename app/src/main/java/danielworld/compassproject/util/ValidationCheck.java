package danielworld.compassproject.util;

import android.widget.EditText;

import java.util.regex.Pattern;

/**
 * Copyright (C) 2014-2015 Daniel Park. op7773hons@gmail.com
 * </p>
 * This file is part of CompassProject (https://github.com/DanielWorld)
 * Created by danielpark on 2015. 7. 2..
 */
public class ValidationCheck {


    private static final String LATITUDE_PATTERN = "^[0-9]{1,2}$";
    private static final String LONGITUDE_PATTERN = "^[0-9]{1,3}$";

    private static final String MINUTE_PATTERN = "^[0-9]{1,2}$";
    private static final String SECOND_PATTERN = "^[0-9.]{1,25}$";

    private static final String DDd_PATTERN = "^[0-9.-]{1,15}$";


    /**
     * Check Latitude degree
     * @param degree
     * @return
     */
    public static boolean checkLatitudeDegree(String degree){

        Pattern pattern = Pattern.compile(LATITUDE_PATTERN);

        if(pattern.matcher(degree).matches()){
            try{
                int deg = Integer.parseInt(degree);

                if(deg >= 0 && deg <= 90){
                    return true;
                }
                return false;
            }catch (Exception e){
                return false;
            }
        }else{
            return false;
        }
    }

    /**
     * Check Longitude degree
     * @param degree
     * @return
     */
    public static boolean checkLongitudeDegree(String degree){

        Pattern pattern = Pattern.compile(LONGITUDE_PATTERN);

        if(pattern.matcher(degree).matches()){
            try{
                int deg = Integer.parseInt(degree);

                if(deg >= 0 && deg <= 180){
                    return true;
                }
                return false;

            }catch (Exception e){
                return false;
            }
        }else{
            return false;
        }
    }

    public static boolean checkMinute(String minute){
        Pattern pattern = Pattern.compile(MINUTE_PATTERN);

        if(pattern.matcher(minute).matches()){
            try{
                int deg = Integer.parseInt(minute);

                if(deg >= 0 && deg < 60){
                    return true;
                }
                return false;
            }catch (Exception e){
                return false;
            }
        }else{
            return false;
        }
    }

    public static boolean checkSecond(String second){
        Pattern pattern = Pattern.compile(SECOND_PATTERN);

        if(pattern.matcher(second).matches()){
            try{
                double deg = Double.parseDouble(second);

                if(deg >= 0 && deg < 60){
                    return true;
                }
                return false;
            }catch (Exception e){
                return false;
            }
        }else{
            return false;
        }
    }

    public static boolean checkLatitudeDDd(String input){
        // Latitude is -90 ~ 90 but you're not going to allow -90 I guess...
        Pattern pattern = Pattern.compile(DDd_PATTERN);

        if(pattern.matcher(input).matches()){
            try{
                double ddd = Double.parseDouble(input);
                if(ddd >= -90 && ddd <= 90)
                    return true;

                return false;
            }catch (Exception e){
                return false;
            }
        }
        else{
            return false;
        }
    }

    public static boolean checkLongitudeDDd(String input){
        // Latitude is -90 ~ 90 but you're not going to allow -90 I guess...
        Pattern pattern = Pattern.compile(DDd_PATTERN);

        if(pattern.matcher(input).matches()){
            try{
                double ddd = Double.parseDouble(input);
                if(ddd >= -180 && ddd <= 180)
                    return true;

                return false;
            }catch (Exception e){
                return false;
            }
        }
        else{
            return false;
        }
    }

}
