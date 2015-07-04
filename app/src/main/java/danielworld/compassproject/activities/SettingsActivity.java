package danielworld.compassproject.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import danielworld.compassproject.R;
import danielworld.compassproject.preference.CompassPreference;
import danielworld.compassproject.util.ValidationCheck;
import danielworld.compassproject.util.dialog.AlertDialogUtil;

/**
 * Copyright (C) 2014-2015 Daniel Park. op7773hons@gmail.com
 * </p>
 * This file is part of CompassProject (https://github.com/DanielWorld)
 * Created by danielpark on 2015-07-03.
 */
public class SettingsActivity extends Activity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener, DialogInterface.OnClickListener {

    private CompassPreference mPref;

    private RadioGroup rbtnGroup;
    private RadioButton dmsRbtn, ddRbtn;
    private RelativeLayout dmsLayout, ddLayout;
    private Button saveBtn, dmsLatType, dmsLonType, ddLatType, ddLonType;
    private EditText latDegDms, latMinDms, latSecDms, lonDegDms, lonMinDms, lonSecDms, latDegDD, lonDegDD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mPref = new CompassPreference(this); // Initialize shared preferences
        init(); // initialize components

        if (dmsRbtn.isChecked()) {
            dmsLayout.setVisibility(View.VISIBLE);
            ddLayout.setVisibility(View.GONE);
        } else if (ddRbtn.isChecked()) {
            dmsLayout.setVisibility(View.GONE);
            ddLayout.setVisibility(View.VISIBLE);
        }

        rbtnGroup.setOnCheckedChangeListener(this);
        dmsLatType.setOnClickListener(this);
        dmsLonType.setOnClickListener(this);
        ddLatType.setOnClickListener(this);
        ddLonType.setOnClickListener(this);

        saveBtn.setOnClickListener(this);
    }

    /* Initialize components */
    private void init() {
        mPref = new CompassPreference(this);

        rbtnGroup = (RadioGroup) findViewById(R.id.rbtn_group); // the group of dms, dd radio button
        dmsRbtn = (RadioButton) findViewById(R.id.dms_rbtn); // enable dms input field
        ddRbtn = (RadioButton) findViewById(R.id.dd_rbtn); // enable dd.dddddd input field

        dmsLayout = (RelativeLayout) findViewById(R.id.dms_layout); // dms input field layout
        ddLayout = (RelativeLayout) findViewById(R.id.dd_layout); // dd input field layout

        dmsLatType = (Button) findViewById(R.id.latitude_type_dms); // dms lat button type ( N or S )
        dmsLonType = (Button) findViewById(R.id.longitude_type_dms); // dms lon button type ( E or W )
        ddLatType = (Button) findViewById(R.id.latitude_type_dd);   // dd lat button type ( N or S )
        ddLonType = (Button) findViewById(R.id.longitude_type_dd);  // dd lon button type ( E or W )

        latDegDms = (EditText) findViewById(R.id.latitude_deg_dms);
        latMinDms = (EditText) findViewById(R.id.latitude_min_dms);
        latSecDms = (EditText) findViewById(R.id.latitude_sec_dms);

        lonDegDms = (EditText) findViewById(R.id.longitude_deg_dms);
        lonMinDms = (EditText) findViewById(R.id.longitude_min_dms);
        lonSecDms = (EditText) findViewById(R.id.longitude_sec_dms);

        latDegDD = (EditText) findViewById(R.id.latitude_degree_dd);
        lonDegDD = (EditText) findViewById(R.id.longitude_degree_dd);

        saveBtn = (Button) findViewById(R.id.save_button); // dms or dd input save button
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (group.getId()) {
            case R.id.rbtn_group:
                if (checkedId == R.id.dms_rbtn) {
                    dmsLayout.setVisibility(View.VISIBLE);
                    ddLayout.setVisibility(View.GONE);
                } else if (checkedId == R.id.dd_rbtn) {
                    dmsLayout.setVisibility(View.GONE);
                    ddLayout.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save_button:
                if(dmsRbtn.isChecked()){    // dms radio button is clicked
                    // Check validation
                    if(!ValidationCheck.checkLatitudeDegree(latDegDms.getText().toString())){
                        AlertDialogUtil.showOneButtonDialog(v.getContext(), "에러", "N Deg의 경우 0 ~ 90사이의 숫자만 받을 수 있습니다.\n재입력해주세요", null, this);
                        latDegDms.setText("");  // clear text
                        latDegDms.requestFocus();   // focus
                    } else if(!ValidationCheck.checkLatitudeDegree(latMinDms.getText().toString())){
                        AlertDialogUtil.showOneButtonDialog(v.getContext(), "에러", "N Min의 경우 0 ~ 59사이의 숫자만 받을 수 있습니다.\n재입력해주세요", null, this);
                        latMinDms.setText("");  // clear text
                        latMinDms.requestFocus();   // focus
                    } else if(!ValidationCheck.checkLatitudeDegree(latSecDms.getText().toString())){
                        AlertDialogUtil.showOneButtonDialog(v.getContext(), "에러", "N Sec의 경우 0 ~ 59사이의 숫자만 받을 수 있습니다.\n재입력해주세요", null, this);
                        latSecDms.setText("");  // clear text
                        latSecDms.requestFocus();   // focus
                    } else if(!ValidationCheck.checkLatitudeDegree(lonDegDms.getText().toString())){
                        AlertDialogUtil.showOneButtonDialog(v.getContext(), "에러", "N Deg의 경우 0 ~ 90사이의 숫자만 받을 수 있습니다.\n재입력해주세요", null, this);
                        lonDegDms.setText("");  // clear text
                        lonDegDms.requestFocus();   // focus
                    } else if(!ValidationCheck.checkLatitudeDegree(lonMinDms.getText().toString())){
                        AlertDialogUtil.showOneButtonDialog(v.getContext(), "에러", "N Min의 경우 0 ~ 59사이의 숫자만 받을 수 있습니다.\n재입력해주세요", null, this);
                        lonMinDms.setText("");  // clear text
                        lonMinDms.requestFocus();   // focus
                    } else if(!ValidationCheck.checkLatitudeDegree(lonSecDms.getText().toString())){
                        AlertDialogUtil.showOneButtonDialog(v.getContext(), "에러", "N Sec의 경우 0 ~ 59사이의 숫자만 받을 수 있습니다.\n재입력해주세요", null, this);
                        lonSecDms.setText("");  // clear text
                        lonSecDms.requestFocus();   // focus
                    } else {
                        // Test is passed then save current DMS
                    }
                }
                else if(ddRbtn.isChecked()){    // dd radio button is clicked
                    if(!ValidationCheck.checkLatitudeDegree(latDegDD.getText().toString())){
                        AlertDialogUtil.showOneButtonDialog(v.getContext(), "에러", "N Deg의 경우 0 ~ 90사이의 숫자만 받을 수 있습니다.\n재입력해주세요", null, this);
                        latDegDD.setText("");  // clear text
                        latDegDD.requestFocus();   // focus
                    } else if(!ValidationCheck.checkLatitudeDegree(lonDegDD.getText().toString())){
                        AlertDialogUtil.showOneButtonDialog(v.getContext(), "에러", "N Min의 경우 0 ~ 59사이의 숫자만 받을 수 있습니다.\n재입력해주세요", null, this);
                        lonDegDD.setText("");  // clear text
                        lonDegDD.requestFocus();   // focus
                    } else {
                        // Test is passed then save current DD.dddddd
                    }
                }

                break;
            case R.id.latitude_type_dms:
                if (dmsLatType.getText().equals("N")) {
                    dmsLatType.setText("S");
                } else {
                    dmsLatType.setText("N");
                }
                break;
            case R.id.longitude_type_dms:
                if (dmsLonType.getText().equals("E")) {
                    dmsLonType.setText("W");
                } else {
                    dmsLonType.setText("E");
                }
                break;
            case R.id.latitude_type_dd:
                if (ddLatType.getText().equals("N")) {
                    ddLatType.setText("S");
                } else {
                    ddLatType.setText("N");
                }
                break;
            case R.id.longitude_type_dd:
                if (ddLonType.getText().equals("E")) {
                    ddLonType.setText("W");
                } else {
                    ddLonType.setText("E");
                }
                break;
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(which == -3)
            dialog.dismiss();
    }
}
