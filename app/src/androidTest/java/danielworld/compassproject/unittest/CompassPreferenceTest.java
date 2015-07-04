package danielworld.compassproject.unittest;

import android.content.Context;
import android.test.AndroidTestCase;

import danielworld.compassproject.preference.CompassPreference;

/**
 * Created by danielpark on 2015-07-04.
 */
public class CompassPreferenceTest extends AndroidTestCase{

    Context mContext;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mContext = getContext();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testSharedPreferences(){
        CompassPreference mPref = new CompassPreference(mContext);

        String dmsDegLat = "32";
        String dmsMinLat = "19";
        String dmsSecLat = "47";

        String dmsDegLon = "-132";
        String dmsMinLon = "39";
        String dmsSecLon = "25";

        String dddLat = "28.399283";
        String dddLon = "150.288393";

        mPref.setDMSdegLat(dmsDegLat);
        mPref.setDMSminLat(dmsMinLat);
        mPref.setDMSsecLat(dmsSecLat);

        mPref.setDMSdegLon(dmsDegLon);
        mPref.setDMSminLon(dmsMinLon);
        mPref.setDMSsecLon(dmsSecLon);

        mPref.setDDdLat(dddLat);
        mPref.setDDdLon(dddLon);

        assertEquals(dmsDegLat, mPref.getDMSdegLat());
        assertEquals(dmsMinLat, mPref.getDMSminLat());
        assertEquals(dmsSecLat, mPref.getDMSsecLat());

        assertEquals(dmsDegLon, mPref.getDMSdegLon());
        assertEquals(dmsMinLon, mPref.getDMSminLon());
        assertEquals(dmsSecLon, mPref.getDMSsecLon());

        assertEquals(dddLat, mPref.getDDdLat());
        assertEquals(dddLon, mPref.getDDdLon());
    }
}
