package danielworld.compassproject.customView;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import danielworld.compassproject.R;
import danielworld.compassproject.util.ValidationCheck;
import danielworld.compassproject.util.dialog.AlertDialogUtil;


/**
 * Copyright (C) 2014-2015 Daniel Park, op7773hons@gmail.com
 * <p/>
 * This file is part of CompassSensor (https://github.com/NamgyuWorld)
 * Created by danielpark on 2015. 7. 1..
 */
public class TopView extends RelativeLayout implements View.OnClickListener, DialogInterface.OnClickListener {

    TextView locationView;
    EditText ed1, ed2, ed3, ed4, ed5, ed6;
    Button btn;

    public TopView(Context context) {
        super(context);
        init(context);
    }

    public TopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TopView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){

        View v = LayoutInflater.from(context).inflate(R.layout.view_top, null);
        locationView = (TextView) v.findViewById(R.id.locate_view);
        ed1 = (EditText) v.findViewById(R.id.editText1);
        ed2 = (EditText) v.findViewById(R.id.editText2);
        ed3 = (EditText) v.findViewById(R.id.editText3);
        ed4 = (EditText) v.findViewById(R.id.editText4);
        ed5 = (EditText) v.findViewById(R.id.editText5);
        ed6 = (EditText) v.findViewById(R.id.editText6);

        btn = (Button) v.findViewById(R.id.button);
        btn.setOnClickListener(this);

        addView(v);
    }

    /**
     * Set text string to textview
     * @param str
     */
    public void setText(String str){
        locationView.setText(str);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button){
            //...
            // Check validation
            if(!ValidationCheck.checkLatitudeDegree(ed1.getText().toString())) {
                AlertDialogUtil.showOneButtonDialog(v.getContext(), "에러", "N Deg의 경우 -90 ~ 90사이의 숫자만 받을 수 있습니다.\n재입력해주세요", null, this);
                // clear text
                ed1.setText("");
                // Focus on ed1
                ed1.requestFocus();
            }
            else if(!ValidationCheck.checkMinute(ed2.getText().toString())) {
                AlertDialogUtil.showOneButtonDialog(v.getContext(), "에러", "N Min의 경우 0 ~ 59사이의 숫자만 받을 수 있습니다.\n재입력해주세요", null, this);
                // clear text
                ed2.setText("");
                // Focus on ed2
                ed2.requestFocus();
            }
            else if(!ValidationCheck.checkSecond(ed3.getText().toString())) {
                AlertDialogUtil.showOneButtonDialog(v.getContext(), "에러", "N Sec의 경우 0 ~ 59사이의 숫자만 받을 수 있습니다.\n재입력해주세요", null, this);
                // clear text
                ed3.setText("");
                // Focus on ed3
                ed3.requestFocus();
            }
            else if(!ValidationCheck.checkLongitudeDegree(ed4.getText().toString())) {
                AlertDialogUtil.showOneButtonDialog(v.getContext(), "에러", "W Deg의 경우 -180 ~ 180사이의 숫자만 받을 수 있습니다.\n재입력해주세요", null, this);
                // clear text
                ed4.setText("");
                // Focus on ed4
                ed4.requestFocus();
            }
            else if(!ValidationCheck.checkMinute(ed5.getText().toString())) {
                AlertDialogUtil.showOneButtonDialog(v.getContext(), "에러", "W Min의 경우 0 ~ 59사이의 숫자만 받을 수 있습니다.\n재입력해주세요", null, this);
                // clear text
                ed5.setText("");
                // Focus on ed5
                ed5.requestFocus();
            }
            else if(!ValidationCheck.checkSecond(ed6.getText().toString())) {
                AlertDialogUtil.showOneButtonDialog(v.getContext(), "에러", "W Sec의 경우 0 ~ 59사이의 숫자만 받을 수 있습니다.\n재입력해주세요", null, this);
                // clear text
                ed6.setText("");
                // Focus on ed6
                ed6.requestFocus();
            }
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(which == -3) {
            dialog.dismiss();
        }
    }
}
