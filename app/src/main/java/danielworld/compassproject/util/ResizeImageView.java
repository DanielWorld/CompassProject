package danielworld.compassproject.util;

import android.widget.ImageView;

/**
 * Change ImageView size
 * <br><br>
 * Copyright (C) 2014-2015 Daniel Park. op7773hons@gmail.com
 * </p>
 * This file is part of CompassProject (https://github.com/DanielWorld)
 * Created by danielpark on 2015. 7. 2..
 */
public class ResizeImageView {
    /**
     * 해당 ImageView 의 크기를 임의로 조정하는 method <br>
     * Resize ImageView by what you want.
     *
     * @param iv - ImageView variable
     * @param width - set width of ImageView
     * @param height - set height of ImageView
     */
    public static void resizeImageView(ImageView iv, int width, int height) {
//        iv.setLayoutParams(new ViewGroup.LayoutParams(width, height));
        iv.requestLayout();
        if(width != 0) {
            iv.getLayoutParams().width = width;
        }
        if(height != 0) {
            iv.getLayoutParams().height = height;
        }
    }
}
