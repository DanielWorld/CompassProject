package danielworld.compassproject.util.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import danielworld.compassproject.util.StringUtil;

/**
 * Show AlertDialog
 * <br><br>
 * Copyright (C) 2014-2015 Daniel Park. op7773hons@gmail.com
 * </p>
 * This file is part of CompassProject (https://github.com/DanielWorld)
 * Created by danielpark on 5/29/15.
 */
public class AlertDialogUtil {


    /**
     * Show alert dialog with neutral button <br>
     * neutral button listener returns -3
     *
     * @param context
     * @param title      the title of dialog
     * @param message    the message of dialog
     * @param buttonText the text of button in dialog
     * @param listener   -3 : neutral button listener
     */
    public static void showOneButtonDialog(Context context, String title, String message, String buttonText, DialogInterface.OnClickListener listener) {
        // IF title & message are both empty or null, then leave
        if (StringUtil.isNullorEmpty(title) && StringUtil.isNullorEmpty(message)) {
            return;
        }

        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setCancelable(false);

        if (listener != null) {
            // If button text is null or empty
            if (StringUtil.isNullorEmpty(buttonText))
                dialog.setNeutralButton("확인", listener);
            else
                dialog.setNeutralButton(buttonText, listener);
        }

        dialog.show();
    }

    /**
     * Show alert dialog with positive & negative button <br>
     * positive button listener returns -1 <br>
     * negative button listener returns -2 <br>
     *
     * @param context
     * @param title       the title of dialog
     * @param message     the message of dialog
     * @param buttonText1 the text of button in dialog
     * @param buttonText2 the text of another button in dialog
     * @param listener    -1 : positive button listener / -2 : negative button listener
     */
    public static void showMultiButtonDialog(Context context, String title, String message, String buttonText1, String buttonText2, DialogInterface.OnClickListener listener) {
        // IF title & message are both empty or null, then leave
        if (StringUtil.isNullorEmpty(title) && StringUtil.isNullorEmpty(message)) {
            return;
        }

        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setCancelable(false);

        if (listener != null) {
            // If buttonText1 is null or empty
            if (StringUtil.isNullorEmpty(buttonText1))
                dialog.setPositiveButton("확인", listener);
            else
                dialog.setPositiveButton(buttonText1, listener);

            // If buttonText2 is null or empty
            if (StringUtil.isNullorEmpty(buttonText2))
                dialog.setNegativeButton("취소", listener);
            else
                dialog.setNegativeButton(buttonText2, listener);
        }

        dialog.show();
    }


}
