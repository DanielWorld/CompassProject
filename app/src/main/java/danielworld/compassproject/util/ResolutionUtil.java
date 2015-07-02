package danielworld.compassproject.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;

/**
 * Get device width & height
 * <br><br>
 * Copyright (C) 2014-2015 Daniel Park, op7773hons@gmail.com
 * <p/>
 * This file is part of CompassProject (https://github.com/NamgyuWorld)
 * Created by danielpark on 2015. 7. 2..
 */
public class ResolutionUtil {

    /**
     * Get current device resolution's width
     * @param mActivity activity
     * @return device resolution's width
     */
    public static int displayWidth(Activity mActivity) {
        // Get Display resolution
        Display display = mActivity.getWindowManager().getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= 13) {
            // Available only equal or higher than API 13
            Point size = new Point();
            display.getSize(size);
            return size.x;
        } else {
            return display.getWidth();
        }
    }

    /**
     * Get current device resolution's width
     * @param context
     * @return device resolution's width
     */
    public static int displayWidth(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= 13) {
            // Available only equal or higher than API 13
            Point size = new Point();
            display.getSize(size);
            return size.x;
        } else {
            return display.getWidth();
        }
    }

    /**
     * Get current device resolution's height
     * @return device resolution's height
     */
    public static int displayHeight(Activity mActivity) {
        // Get Display resolution
        Display display = mActivity.getWindowManager().getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= 13) {
            // Available only equal or higher than API 13
            Point size = new Point();
            display.getSize(size);
            return size.y;
        } else {
            return display.getHeight();
        }
    }

    /**
     * Get current device resolution's height
     * @param context
     * @return device resolution's height
     */
    public static int displayHeight(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= 13) {
            // Available only equal or higher than API 13
            Point size = new Point();
            display.getSize(size);
            return size.y;
        } else {
            return display.getHeight();
        }
    }
}
