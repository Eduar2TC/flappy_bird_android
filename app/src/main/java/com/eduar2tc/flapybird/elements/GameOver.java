package com.eduar2tc.flapybird.elements;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.eduar2tc.flapybird.R;
import com.eduar2tc.flapybird.engine.Colors;
import com.eduar2tc.flapybird.engine.Display;

public class GameOver {
    private final Display display;
    private final Paint paint;
    private Bitmap bg;
    public GameOver(Display display ){
        this.display = display;
        this.paint = new Colors(this.display).getColorGameOver(); //Pass context to Colors
        this.bg = BitmapFactory.decodeResource(display.getContext().getResources(), R.drawable.bg_over);
        this.bg = Bitmap.createScaledBitmap( this.bg, 800, 400, false );
    }
    public void drawMessageOnCanvas(Canvas canvas){
        String gameOver = "Game over";
        int centerTextHorizontal = this.centerText( gameOver );
        canvas.drawText( gameOver, centerTextHorizontal, display.getHeight() / 2, paint );

    }
    private int centerText(String text){
        Rect limitText = new Rect();
        paint.getTextBounds(text, 0, text.length(), limitText);
        int centerHorizontal = this.display.getWidth() / 2 - ( limitText.right -limitText.left ) / 2;
        return centerHorizontal;
    }
    public void drawBg(Canvas canvas){
        canvas.drawBitmap( this.bg, (float)this.display.getWidth()/2 - (float) this.bg.getWidth()/2, (float)this.display.getHeight()/2 - (float) this.bg.getHeight() /2 , null );
    }

}
