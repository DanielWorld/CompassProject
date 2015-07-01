package danielworld.compassproject.customView;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import danielworld.compassproject.R;


/**
 * Copyright (C) 2014-2015 Daniel Park, op7773hons@gmail.com
 * <p/>
 * This file is part of CompassSensor (https://github.com/NamgyuWorld)
 * Created by danielpark on 2015. 7. 1..
 */
public class TopView extends RelativeLayout{

    TextView locationView;

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
        addView(v);
    }

    /**
     * Set text string to textview
     * @param str
     */
    public void setText(String str){
        locationView.setText(str);
    }
}
