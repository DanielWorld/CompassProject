package danielworld.compassproject.util;

/**
 * Latitude / Longitude conversion <br>
 *
 * Latitude : (-90 ~ +90) :::  Longitude (-180 ~ +180)
 *
 * <br><br>
 * Copyright (C) 2014-2015 Daniel Park. op7773hons@gmail.com
 * </p>
 * This file is part of CompassProject (https://github.com/DanielWorld)
 * Created by danielpark on 2015. 7. 1..
 */
public class EarthDegreeConverter {

    /**
     * Convert DMS to D.d format class
     */
    public static class DMStoDDclass {

        private static int Degree;

        /**
         * Convert DMS to DD
         *
         * @param deg
         * @param min
         * @param sec it can be numeric number
         */
        public static synchronized double DMStoDD(int deg, int min, double sec, String type) {

            if(type.equals("N") || type.equals("E")) {

                return DMmtoDD(DMStoDMm(deg, min, sec));
            }
            else if(type.equals("S") || type.equals("W")){
                return DMmtoDD(DMStoDMm(-deg, min, sec));
            }
            else{
                return 0.0f;
            }
        }

        /**
         * Convert DMS to DMm
         * <br>
         * Save Degree and return Minutes.m
         *
         * @param deg degree
         * @param min minute
         * @param sec second
         * @return
         */
        private static double DMStoDMm(int deg, int min, double sec) {
            Degree = deg;
            return min + (sec / 60);
        }

        /**
         * Convert DMm to DD
         *
         * @param Mm
         * @return
         */
        private static double DMmtoDD(double Mm) {
            if(Degree > 0){
                return Degree + (Mm / 60);
            }
            else{
                return Degree - (Mm / 60);
            }

        }
    }

    /**
     * Convert D.d to DMS format class
     */
    public static class DDtoDMSclass {

        public static synchronized DDtoDMSclass DDtoDMS(double dd){
            DMmtoDMS(DDtoDMm(dd));

            return new DDtoDMSclass();
        }

        private static void DMmtoDMS(double Mm){
            Minute = (int) Math.abs(Mm);
            Second = (Mm - Minute) * 60;
        }

        private static double DDtoDMm(double dd){
            Degree = (int) Math.abs(dd);

            double Mm = 0.0f;
            if(dd < 0){
                Mm = -(dd + Degree) * 60;
            }
            else{
                Mm = (dd - Degree) * 60;
            }
            return Mm;
        }

        private static int Degree;
        private static int Minute;
        private static double Second;

        public int getDegree() {
            return Degree;
        }

        public double getMinute() {
            return Minute;
        }

        public double getSecond() {
            return Second;
        }

        @Override
        public String toString() {
            return Degree + " " + Minute + " " + Second;
        }
    }

    /**
     * Calculate between two points
     * @param latitude1
     * @param longitude1
     * @param latitude2
     * @param longitude2
     * @return
     */
    public static double distance(double latitude1, double longitude1, double latitude2, double longitude2){

        final int R = 6371;; // Radius of the earth ( km )

        double lat1 = Math.toRadians(latitude1);
        double lat2 = Math.toRadians(latitude2);

        double latDistance = Math.toRadians(latitude2 - latitude1);
        double longDistance = Math.toRadians(longitude2 - longitude1);

        double result = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(lat1) * Math.cos(lat2) * Math.sin(longDistance / 2) * Math.sin(longDistance / 2);

        double result2 = 2 * Math.atan2(Math.sqrt(result), Math.sqrt(1-result));

        return R * result2;
    }


    /**
     * Get angle between two points
     * @return
     */
    public static double getAngle(double current_lat, double current_lon, double des_lat, double des_lon){

        // Normal formula isn't working, try to trick this.

        double latDistance = Math.toRadians(current_lat - des_lat);
        double lonDistance = Math.toRadians(current_lon - des_lon);

        boolean isArrowAbove;
        boolean isArrowRight;

        if(latDistance > 0){
            // arrow directs under 0
            isArrowAbove = false;
        }
        else{
            // arrow directs above 0
            latDistance = - latDistance;
            isArrowAbove = true;
        }

        if(lonDistance > 0){
            // arrow directs Left
            isArrowRight = false;
        }
        else{
            // arrow directs Right
            lonDistance = - lonDistance;
            isArrowRight = true;
        }

        double bearing = Math.atan2(latDistance , lonDistance);

        if(isArrowAbove && isArrowRight){
            return bearing;
        }
        else if(isArrowAbove && !isArrowRight){
            return (90 - bearing) + 90;
        }
        else if(!isArrowAbove && isArrowRight){
            return 360 - bearing;
        }
        else if(!isArrowAbove && !isArrowRight){
            return 180 + bearing;
        }

        return 0.0f;

    }
}
