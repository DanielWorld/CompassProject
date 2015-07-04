package danielworld.compassproject.unittest.androidTest;

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

        String dmsLatType = "N";
        String dmsDegLat = "32";
        String dmsMinLat = "19";
        String dmsSecLat = "47";

        String dmsLonType = "W";
        String dmsDegLon = "-132";
        String dmsMinLon = "39";
        String dmsSecLon = "25";

        String dddLatType = "S";
        String dddLat = "28.399283";

        String dddLonType = "E";
        String dddLon = "150.288393";

        mPref.setDMSLatType(dmsLatType);
        mPref.setDMSdegLat(dmsDegLat);
        mPref.setDMSminLat(dmsMinLat);
        mPref.setDMSsecLat(dmsSecLat);

        mPref.setDMSLonType(dmsLonType);
        mPref.setDMSdegLon(dmsDegLon);
        mPref.setDMSminLon(dmsMinLon);
        mPref.setDMSsecLon(dmsSecLon);

        mPref.setDDdLatType(dddLatType);
        mPref.setDDdLat(dddLat);

        mPref.setDDdLonType(dddLonType);
        mPref.setDDdLon(dddLon);

        assertEquals(dmsLatType, mPref.getDMSLatType());
        assertEquals(dmsDegLat, mPref.getDMSdegLat());
        assertEquals(dmsMinLat, mPref.getDMSminLat());
        assertEquals(dmsSecLat, mPref.getDMSsecLat());

        assertEquals(dmsLonType, mPref.getDMSLonType());
        assertEquals(dmsDegLon, mPref.getDMSdegLon());
        assertEquals(dmsMinLon, mPref.getDMSminLon());
        assertEquals(dmsSecLon, mPref.getDMSsecLon());

        assertEquals(dddLatType, mPref.getDDdLatType());
        assertEquals(dddLat, mPref.getDDdLat());

        assertEquals(dddLonType, mPref.getDDdLonType());
        assertEquals(dddLon, mPref.getDDdLon());
    }
}
