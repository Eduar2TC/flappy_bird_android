package com.eduar2tc.flapybird.engine;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class Display {
    private final DisplayMetrics metrics;
    private final Context context;
    public Display(Context context){
        WindowManager windowManager = (WindowManager) context.getSystemService( Context.WINDOW_SERVICE );
        android.view.Display display = windowManager.getDefaultDisplay();
        metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        this.context = context;
    }
    //return height window
    public int getHeight(){
        return metrics.heightPixels;
    }
    public int getWidth(){
        return metrics.widthPixels;
    }

    public Context getContext() {
        return context;
    }
}
