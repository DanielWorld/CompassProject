package danielworld.compassproject.util;

/**
 * Latitude / Longitude conversion
 * <br><br>
 * Copyright (C) 2014-2015 Daniel Park, op7773hons@gmail.com
 * <p>
 * This file is part of CompassProject (https://github.com/NamgyuWorld)
 * Created by danielpark on 2015. 7. 1..
 */
public class EarthDegreeConverter {

    private static int Degree;
    private static double Minute;
    private static double Second;

    /**
     * Convert DMS to DD
     * @param deg
     * @param min
     * @param sec it can be numeric number
     */
    public static double DMStoDD(int deg, int min, double sec){

        return DMmtoDD(DMStoDMm(deg, min, sec));
    }

    /**
     * Convert DMS to DMm
     * <br>
     * Save Degree and return Minutes.m
     * @param deg degree
     * @param min minute
     * @param sec second
     * @return
     */
    private static double DMStoDMm(int deg, int min, double sec){
        Degree = deg;
        return min + (sec / 60);
    }

    /**
     * Convert DMm to DD
     * @param Mm
     * @return
     */
    private static double DMmtoDD(double Mm){
        return Degree + (Mm / 60);
    }
}
