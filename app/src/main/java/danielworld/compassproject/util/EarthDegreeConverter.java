package danielworld.compassproject.util;

/**
 * Latitude / Longitude conversion <br>
 *
 * Latitude : (-90 ~ +90) :::  Longitude (-180 ~ +180)
 *
 * <br><br>
 * Copyright (C) 2014-2015 Daniel Park, op7773hons@gmail.com
 * <p>
 * This file is part of CompassProject (https://github.com/NamgyuWorld)
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
        public static synchronized double DMStoDD(int deg, int min, double sec) {

            return DMmtoDD(DMStoDMm(deg, min, sec));
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
}
