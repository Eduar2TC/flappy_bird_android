package com.eduar2tc.flapybird.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.eduar2tc.flapybird.R;
import com.eduar2tc.flapybird.engine.Colors;
import com.eduar2tc.flapybird.engine.Display;
import com.eduar2tc.flapybird.engine.Sound;

public class Bird {
    private final int x;
    private final int radius;
    private int height;
    private final Paint paint;
    private final Display display;
    private final Sound sound;
    private float velocity = 0;

    public Bird( Display display ){
        this.display = display;
        this.x = 100;
        this.radius = 80;
        this.height = 200;
        this.paint = Colors.getColor();
        this.sound = new Sound( display.getContext() );
    }
    public void drawOnCanvas(Canvas canvas){
        canvas.drawCircle(this.x, this.height, this.radius, this.paint);
    }
    //Falling bird
    public void fallDown() {
        final float dt = 0.63f;
        final float acceleration = 3.8f;
        //Check bird collision with bottom screen
        boolean checkBirdIsInBottom = this.height + this.radius > display.getHeight();
        if(!checkBirdIsInBottom){
            velocity += acceleration *dt;
            this.height += velocity *dt;
        }else{
            System.out.println("Pajaro abajo");
        }

    }
    //Effect jump
    public void flap(){
        if( this.height > this.radius ){
            this.velocity = -30;
            this.sound.play( Sound.flap );
        }else {
            System.out.println("Pajaro en parte sup");
        }
    }
    public int getHeight(){
        return this.height;
    }
    public int getRadius(){
        return this.radius;
    }

    public int getX() {
        return this.x;
    }
}
