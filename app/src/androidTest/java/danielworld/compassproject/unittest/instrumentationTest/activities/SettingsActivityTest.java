package danielworld.compassproject.unittest.instrumentationTest.activities;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.view.View;

import danielworld.compassproject.R;
import danielworld.compassproject.activities.MainActivity;
import danielworld.compassproject.activities.SettingsActivity;
import danielworld.compassproject.activities.SplashActivity;

/**
 * Copyright (C) 2014-2015 Daniel Park. op7773hons@gmail.com
 * </p>
 * This file is part of CompassProject (https://github.com/DanielWorld)
 * Created by danielpark on 2015-07-04.
 */
public class SettingsActivityTest extends ActivityInstrumentationTestCase2<SettingsActivity>{

    private Activity mActivity;
    private Context mContext;

    private View dmsRbtn, ddRbtn, dmsLayout, ddLayout;
    private Instrumentation mInstrumentation;

    public SettingsActivityTest(){
        super(SettingsActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mInstrumentation = getInstrumentation();
        mInstrumentation.setInTouchMode(true);

        mActivity = getActivity();
        mContext = getActivity();

        dmsRbtn = mActivity.findViewById(R.id.dms_rbtn);
        ddRbtn = mActivity.findViewById(R.id.dd_rbtn);

        dmsLayout = mActivity.findViewById(R.id.dms_layout);
        ddLayout = mActivity.findViewById(R.id.dd_layout);
    }

    public void testPreconditions(){
        assertNotNull("SettingsActivity is null",mActivity);
        assertNotNull("DMS radio button is null",dmsRbtn);
        assertNotNull("DD radio button is null",ddRbtn);
        assertNotNull("DMS layout is null",dmsLayout);
        assertNotNull("DD layout is null",ddLayout);
    }

    public void testComponentsExist(){
        View mainDecorView = mActivity.getWindow().getDecorView();
        ViewAsserts.assertOnScreen(mainDecorView, dmsRbtn);
        ViewAsserts.assertOnScreen(mainDecorView, ddRbtn);
    }

    public void testProceed(){
        // Dummy test
        Instrumentation.ActivityMonitor mActivityMonitor = mInstrumentation.addMonitor(MainActivity.class.getName(), null, false);

        TouchUtils.clickView(this, dmsRbtn); // Click dms button
        assertEquals(View.VISIBLE, dmsLayout.getVisibility());
        assertEquals(View.GONE, ddLayout.getVisibility());

        TouchUtils.clickView(this, ddRbtn); // Click dd button
        assertEquals(View.GONE, dmsLayout.getVisibility());
        assertEquals(View.VISIBLE, ddLayout.getVisibility());

        MainActivity aActivity = (MainActivity) mActivityMonitor.waitForActivityWithTimeout(20000);
//
        assertNotNull("MainActivity is null", aActivity);

        // Remove the Activity Monitor
        mInstrumentation.removeMonitor(mActivityMonitor);


    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
