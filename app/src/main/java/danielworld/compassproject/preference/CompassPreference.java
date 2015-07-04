package danielworld.compassproject.preference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by danielpark on 2015-07-04.
 */
public class CompassPreference {

    private SharedPreferences mPrefs;

    private final String PREF_NAME = "compass_pref";
    private final String KEY_FOR_DMS_DEG_LAT = "key_dms_deg_lat";
    private final String KEY_FOR_DMS_MIN_LAT = "key_dms_min_lat";
    private final String KEY_FOR_DMS_SEC_LAT = "key_dms_sec_lat";
    private final String KEY_FOR_DMS_DEG_LON = "key_dms_deg_lon";
    private final String KEY_FOR_DMS_MIN_LON = "key_dms_min_lon";
    private final String KEY_FOR_DMS_SEC_LON = "key_dms_sec_lon";

    private final String KEY_FOR_DDd_LAT = "key_DDd_deg_lat";
    private final String KEY_FOR_DDd_LON = "key_DDd_deg_lon";

    public CompassPreference(Context context){
        mPrefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    /* Set degree in DMS format */
    public void setDMSdegLat(String degree){
        mPrefs.edit().putString(KEY_FOR_DMS_DEG_LAT, degree).commit();
    }
    /* Get degree in DMS format */
    public String getDMSdegLat(){
        return mPrefs.getString(KEY_FOR_DMS_DEG_LAT, null);
    }
    /* Set minute in DMS format */
    public void setDMSminLat(String minute){
        mPrefs.edit().putString(KEY_FOR_DMS_MIN_LAT, minute).commit();
    }
    /* Get minute in DMS format */
    public String getDMSminLat(){
        return mPrefs.getString(KEY_FOR_DMS_MIN_LAT, null);
    }
    /* Set second in DMS format */
    public void setDMSsecLat(String second){
        mPrefs.edit().putString(KEY_FOR_DMS_SEC_LAT, second).commit();
    }
    /* Get second in DMS format */
    public String getDMSsecLat(){
        return mPrefs.getString(KEY_FOR_DMS_SEC_LAT, null);
    }


    /* Set degree in DMS format */
    public void setDMSdegLon(String degree){
        mPrefs.edit().putString(KEY_FOR_DMS_DEG_LON, degree).commit();
    }
    /* Get degree in DMS format */
    public String getDMSdegLon(){
        return mPrefs.getString(KEY_FOR_DMS_DEG_LON, null);
    }
    /* Set minute in DMS format */
    public void setDMSminLon(String minute){
        mPrefs.edit().putString(KEY_FOR_DMS_MIN_LON, minute).commit();
    }
    /* Get minute in DMS format */
    public String getDMSminLon(){
        return mPrefs.getString(KEY_FOR_DMS_MIN_LON, null);
    }
    /* Set second in DMS format */
    public void setDMSsecLon(String second){
        mPrefs.edit().putString(KEY_FOR_DMS_SEC_LON, second).commit();
    }
    /* Get second in DMS format */
    public String getDMSsecLon(){
        return mPrefs.getString(KEY_FOR_DMS_SEC_LON, null);
    }


    /* Set ddd in DD.dddddd format */
    public void setDDdLat(String ddd){
        mPrefs.edit().putString(KEY_FOR_DDd_LAT, ddd).commit();
    }
    /* Get ddd in DD.dddddd format */
    public String getDDdLat(){
        return mPrefs.getString(KEY_FOR_DDd_LAT, null);
    }
    /* Set ddd in DD.dddddd format */
    public void setDDdLon(String ddd){
        mPrefs.edit().putString(KEY_FOR_DDd_LON, ddd).commit();
    }
    /* Get ddd in DD.dddddd format */
    public String getDDdLon(){
        return mPrefs.getString(KEY_FOR_DDd_LON, null);
    }

}
