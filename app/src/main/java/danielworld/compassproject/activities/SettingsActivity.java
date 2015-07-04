package danielworld.compassproject.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import danielworld.compassproject.R;
import danielworld.compassproject.preference.CompassPreference;

/**
 * Copyright (C) 2014-2015 Daniel Park. op7773hons@gmail.com
 * </p>
 * This file is part of CompassProject (https://github.com/DanielWorld)
 * Created by danielpark on 2015-07-03.
 */
public class SettingsActivity extends Activity implements RadioGroup.OnCheckedChangeListener {

    private CompassPreference mPref;

    private RadioGroup rbtnGroup;
    private RadioButton dmsRbtn, ddRbtn;
    private RelativeLayout dmsLayout, ddLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mPref = new CompassPreference(this); // Initialize shared preferences
        init(); // initialize components

        if(dmsRbtn.isChecked()){
            dmsLayout.setVisibility(View.VISIBLE);
            ddLayout.setVisibility(View.GONE);
        } else if(ddRbtn.isChecked()){
            dmsLayout.setVisibility(View.GONE);
            ddLayout.setVisibility(View.VISIBLE);
        }

        rbtnGroup.setOnCheckedChangeListener(this);
    }

    /* Initialize components */
    private void init() {
        rbtnGroup = (RadioGroup) findViewById(R.id.rbtn_group); // the group of dms, dd radio button
        dmsRbtn = (RadioButton) findViewById(R.id.dms_rbtn); // enable dms input field
        ddRbtn = (RadioButton) findViewById(R.id.dd_rbtn); // enable dd.dddddd input field

        dmsLayout = (RelativeLayout) findViewById(R.id.dms_layout); // dms input field layout
        ddLayout = (RelativeLayout) findViewById(R.id.dd_layout); // dd input field layout
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (group.getId()){
            case R.id.rbtn_group:
                if(checkedId == R.id.dms_rbtn){
                    dmsLayout.setVisibility(View.VISIBLE);
                    ddLayout.setVisibility(View.GONE);
                } else if(checkedId == R.id.dd_rbtn){
                    dmsLayout.setVisibility(View.GONE);
                    ddLayout.setVisibility(View.VISIBLE);
                }
                break;
        }
    }
}
