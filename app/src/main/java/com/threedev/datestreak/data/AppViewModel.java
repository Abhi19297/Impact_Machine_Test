package com.threedev.datestreak.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;

import com.threedev.datestreak.BR;
import com.threedev.datestreak.R;
import com.threedev.datestreak.data.model.DateStreakModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;



public class AppViewModel extends BaseObservable {

    private final Context context;
    DateStreakModel model;
    private int addDate=0;
    private static String datePattern = "E, dd MMM yyyy";

    @Bindable
    public String getDate () {
        return model.getDate();
    }

    public void setDate(String date) {
        model.setDate(date);
        notifyPropertyChanged(BR.date);
    }

    @Bindable
    public String getStreak () {
        return model.getStreak();
    }

    public void setStreak(String streak) {
        model.setStreak(streak);
        notifyPropertyChanged(BR.streak);
    }
    // constructor of ViewModel class
    public AppViewModel(Context context) {
        // instantiating object of
        // model class
        model = new DateStreakModel(getCurrentDate(),String.valueOf(addDate));
        this.context=context;
    }


    public void onButtonNextDateClicked() {

        setDate(increaseDate(getDate(),1));
        addDate++;
        setStreak(String.valueOf(addDate));
    }
    public void onButtonSkipDateClicked() {

        setDate(increaseDate(getDate(),2));
        addDate=0;
        setStreak(String.valueOf(addDate));
    }
    public void onButtonResetDateClicked() {

        showResetDialog();
    }
    private String getCurrentDate() {
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat(datePattern);
        Date date = new Date();
        return dateFormat.format(date);
    }
    public static String increaseDate(String myDate, int noOfDate){
        if( myDate == null || myDate.isEmpty()  ||  !isValidDate(myDate)  || noOfDate <=0 )
        { return ""; }
        else {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat df = new SimpleDateFormat(datePattern);
            Date d = null;
            try {
                d = df.parse(myDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar cal = Calendar.getInstance();
            assert d != null;
            cal.setTime(d);
            cal.add(Calendar.DATE, noOfDate);
            return df.format(cal.getTime());
        }
    }


    private void showResetDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setMessage(R.string.ResetText);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setDate(getCurrentDate());
                addDate=0;
                setStreak(String.valueOf(addDate));
                dialog.dismiss();
            }
        });
        alertDialogBuilder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }



    private static boolean isValidDate(String myDate){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(myDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

}
