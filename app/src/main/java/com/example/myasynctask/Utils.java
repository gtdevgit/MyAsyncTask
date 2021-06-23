package com.example.myasynctask;

import android.util.Log;

public class Utils {
    private static final String TAG = "Utils";

    public static Long executeLongAction(int durationMillis){
        Log.d(TAG, "executeLongAction() called");
        Long endTime = System.currentTimeMillis() + durationMillis;
        while (System.currentTimeMillis() < endTime) {
            // loop for nothing
        }
        Log.d(TAG, "executeLongAction() is finished.");
        return endTime;
    }
}
