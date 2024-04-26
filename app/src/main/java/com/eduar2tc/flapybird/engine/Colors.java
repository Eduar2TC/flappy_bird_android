package com.eduar2tc.flapybird.engine;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;

import com.eduar2tc.flapybird.R;

public class Colors {
    private Display display;

    public Colors(Display display){
    this.display = display;
    }
    public static Paint getColor(){
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        return paint;
    }
    public static Paint getPipeColor() {
        Paint paint = new Paint();
        paint.setColor(Color.DKGRAY);
        return paint;
    }
    public static Paint getScoreColor(){
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(100);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setShadowLayer( 3, 5, 5, Color.DKGRAY );
        return paint;
    }
    public Paint getColorGameOver(){
        Paint paint = new Paint();
        Typeface typeface = ResourcesCompat.getFont(this.display.getContext(), R.font.arcade);
        //paint.setColor(Color.argb(255, 188, 188, 188) );
        paint.setColor(Color.argb(255, 255, 155, 0) );
        paint.setTextSize(150);
        paint.setTypeface( typeface );
        paint.setShadowLayer(3, 10, 10, Color.WHITE);
        return paint;
    }
}
