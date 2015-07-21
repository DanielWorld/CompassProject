package danielworld.compassproject.customView;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.namgyuworld.utility.Logger;

import danielworld.compassproject.R;
import danielworld.compassproject.util.ResizeImageView;
import danielworld.compassproject.util.ResolutionUtil;

/**
 * Copyright (C) 2014-2015 Daniel Park. op7773hons@gmail.com
 * </p>
 * This file is part of CompassProject (https://github.com/DanielWorld)
 * Created by danielpark on 2015. 7. 1..
 */
public class MainView extends RelativeLayout implements SensorEventListener {

    private final String TAG = getClass().getSimpleName();
    private final Logger LOG = Logger.getInstance();

    private Context mContext;

    private SensorManager mSensorManager;
    private Sensor gsensor;
    private Sensor msensor;

    private float[] mGData = new float[3];
    private float[] mMData = new float[3];

    private float[] mR = new float[9];
    private float[] mOrientation = new float[3];
    private float mCurrentDegree = 0f;

    private int mCount;
    private int mCount2;
    private int mCount3;

    ImageView compassView, canvasView;

    public MainView(Context context) {
        super(context);
        init(context);
    }

    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MainView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MainView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        mContext = context;

        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);

        View v = LayoutInflater.from(context).inflate(R.layout.view_main, null);

        compassView = (ImageView) v.findViewById(R.id.compass_view);
        canvasView = (ImageView) v.findViewById(R.id.canvas_view);

        // Resize canvasView
        ResizeImageView.resizeImageView(canvasView, ResolutionUtil.displayWidth(context), ResolutionUtil.displayWidth(context));

        Bitmap bitmap = Bitmap.createBitmap(ResolutionUtil.displayWidth(context), ResolutionUtil.displayHeight(context), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        // Draw transparent screen
        Paint transPainter = new Paint();
        transPainter.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        canvas.drawRect(0, 0, bitmap.getWidth(), bitmap.getHeight(), transPainter);

        Paint drawPaint = new Paint();
        drawPaint.reset();
        drawPaint.setFlags(Paint.DITHER_FLAG);
        drawPaint.setAntiAlias(true);
        drawPaint.setSubpixelText(true);
        drawPaint.setColor(Color.GREEN);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        drawPaint.setStrokeWidth(10);
        canvas.drawLines(new float[]{bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2, 0}, drawPaint);

        canvasView.setImageBitmap(bitmap);

        // invoke canvasView
        canvasView.invalidate();

        addView(v);
    }

    public void onResume() {
        gsensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        msensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        // Accelerometer sensor
        if (gsensor != null) {
            mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            mSensorManager.registerListener(this, gsensor, SensorManager.SENSOR_DELAY_GAME);
        } else {
            LOG.e(TAG, "g sensor is null");
        }

        // Magnetic sensor
        if (msensor != null) {
            mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
            mSensorManager.registerListener(this, msensor, SensorManager.SENSOR_DELAY_GAME);
        } else {
            LOG.e(TAG, "m sensor is null");
        }
    }

    public void onPause() {
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
    /**
     * X axis is horizontal and points to the right <br>
     * Y axis is vertical and points up <br>
     * Z axis points towards the outside of the front face of the screen
     *
     * event.values <br>
     *     - values[0] : x
     *     - values[1] : y
     *     - values[2] : z
     */

        synchronized (this) {

            float[] aData;
            float[] mData;

            int type = event.sensor.getType();
            if (type == Sensor.TYPE_ACCELEROMETER) {
                aData = mGData;
                for (int i = 0; i < 3; i++)
                    aData[i] = event.values[i];
                if (mCount++ > 200) {
                    LOG.d(TAG, "a_x: " + (aData[0]) +
                            "  a_y: " + (aData[1]) +
                            "  a_z: " + (aData[2]));

                    // http://developer.android.com/reference/android/hardware/SensorEvent.html
                    // According to this reference, when the device is sitting on a table, Acceleration minus Gz on the z-axis is from 9.30 ~ 9.85
                    if (aData[2] < 9.30 || aData[2] > 9.90) {
                        Toast.makeText(mContext, "스마트폰을 수평으로 놔주세요!", Toast.LENGTH_SHORT).show();
                    }
                    mCount = 0;
                }
            } else if (type == Sensor.TYPE_MAGNETIC_FIELD) {
                mData = mMData;

                for (int i = 0; i < 3; i++)
                    mData[i] = event.values[i];

                if (mCount2++ > 200) {
                    LOG.d(TAG, "m_x: " + (mData[0]) +
                            "  m_y: " + (mData[1]) +
                            "  m_z: " + (mData[2]));
                    mCount2 = 0;
                }
            }

            if (mCount3++ > 30) {

                SensorManager.getRotationMatrix(mR, null, mGData, mMData);
                SensorManager.getOrientation(mR, mOrientation);
                float azimuthInRadians = mOrientation[0];
                float azimuthInDegress = (float) (Math.toDegrees(azimuthInRadians) + 360) % 360;
                RotateAnimation ra = new RotateAnimation(
                        mCurrentDegree,
                        -azimuthInDegress,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF,
                        0.5f);

                ra.setDuration(250);

                ra.setFillAfter(true);

                if(bearing != 0.0f) {
                    //**** 15.07.02 additional function *****
                    RotateAnimation ra2 = new RotateAnimation(
                            mCurrentDegree + bearing,
                            -azimuthInDegress + bearing,
                            Animation.RELATIVE_TO_SELF, 0.5f,
                            Animation.RELATIVE_TO_SELF,
                            0.5f);

                    ra2.setDuration(250);

                    ra2.setFillAfter(true);

                    // 15.07.02 added canvas view
                    canvasView.startAnimation(ra2);
                }

                compassView.startAnimation(ra);
                mCurrentDegree = -azimuthInDegress;

                mCount3 = 0;
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    /**
     * Set angle (bearing)
     * @param obj
     */
    public void setBearingDirection(Object obj){
        bearing = Float.parseFloat(obj.toString());
    }
    float bearing = 0.0f;
}
