package com.example.myasynctask;

import android.os.AsyncTask;
import android.util.Log;

import java.lang.ref.WeakReference;

public class MyAsyncTask extends AsyncTask<Void, Void, Long> {

    private static final String TAG = "MyAsyncTask";

    public interface Listeners {
        void onPreExecute();
        void doInBackground();
        void onPostExecute(Long taskDuration);
    }

    private final WeakReference<Listeners> callback;
    private Long taskStartTime;
    private int durationMillis;

    public MyAsyncTask(Listeners callback, int durationMillis) {
        this.callback = new WeakReference<>(callback);
        this.durationMillis = durationMillis;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.callback.get().onPreExecute();
        Log.d(TAG, "onPreExecute() called");
    }

    @Override
    protected Long doInBackground(Void... voids) {
        this.callback.get().doInBackground();
        Log.d(TAG, "doInBackground()");
        taskStartTime = System.currentTimeMillis();
        Utils.executeLongAction(this.durationMillis);
        return (System.currentTimeMillis() - taskStartTime);
    }

    @Override
    protected void onPostExecute(Long aLong) {
        super.onPostExecute(aLong);
        this.callback.get().onPostExecute(aLong);
        Log.d(TAG, "onPostExecute() called with: aLong = [" + aLong + "]");
    }
}
