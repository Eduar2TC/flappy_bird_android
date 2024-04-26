package com.eduar2tc.flapybird.elements;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.eduar2tc.flapybird.R;
import com.eduar2tc.flapybird.engine.Display;

public class Play {
    private final Display display;
    private final Bitmap play;
    private final Bitmap[] play_frames = new Bitmap[3];
    private int actualFrame = 0;
    private final int marginTop;
    private int alpha = 255;
    public boolean playClicked = false;

    public Play(Display display ){
        this.display = display;
        this.marginTop = 80;
        this.play = BitmapFactory.decodeResource(this.display.getContext().getResources(), R.drawable.play);
        for( int i = 0; i < this.play_frames.length; i++ ){
            this.play_frames[i] = BitmapFactory.decodeResource( this.display.getContext().getResources(), this.display.getContext().getResources().getIdentifier("play_frame_"+i, "drawable", this.display.getContext().getPackageName() ) );
        }
    }
    //Initial background button
    public void drawPlayOnCanvas(Canvas canvas){
        Paint paint = new Paint();
        paint.setAlpha(this.alpha);
        canvas.drawBitmap(this.play, this.display.getWidth()/2 - this.play.getWidth()/2,  (this.display.getHeight()/2 - this.play.getHeight()/2) + marginTop, paint);
    }
    //Effect click on button
    public void clickAnimation( Canvas canvas ){
        this.alpha = 0;
        canvas.drawBitmap( this.play_frames[this.actualFrame], this.display.getWidth()/2 - this.play.getWidth()/2,  (this.display.getHeight()/2 - this.play.getHeight()/2) + marginTop, null );
        this.actualFrame++;
        if( this.actualFrame == this.play_frames.length ){
            this.actualFrame = this.play_frames.length - 1;
        }
    }
    public void rotateEffect( Canvas canvas ){
        Matrix matrix = new Matrix();
        matrix.postRotate(90, (this.play.getWidth() / 2), (this.play.getHeight() / 2)); //rotate it
        matrix.postTranslate( this.display.getWidth()/2 - this.play.getWidth()/2, (this.display.getHeight()/2 - this.play.getHeight()/2) + marginTop ); //move it into x, y position
        canvas.drawBitmap( this.play_frames[this.actualFrame], matrix, null );
        this.actualFrame++;
        if( this.actualFrame == this.play_frames.length ){
            this.actualFrame = 0;
        }
    }
}
