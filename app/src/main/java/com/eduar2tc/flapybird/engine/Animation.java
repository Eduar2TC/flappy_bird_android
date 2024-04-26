package com.eduar2tc.flapybird.engine;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.eduar2tc.flapybird.elements.Bird;

import java.util.concurrent.TimeUnit;

public class Animation {
    private final Bitmap[] flying = new Bitmap[8];
    public final Bitmap[] hit = new Bitmap[2];
    private int actualFrame;
    public int actualFrameHit;
    private final Bird bird;

    public Animation( Display display, Bird bird ){
        Context context = display.getContext();
        this.bird = bird;
        this.actualFrameHit = 0;
        //Initialize bitmaps
        for( int i = 0; i < this.flying.length; i++ ){
            this.flying[i] = BitmapFactory.decodeResource( context.getResources(), context.getResources().getIdentifier("bird_flying_frame_"+i, "drawable", context.getPackageName() ) );
        }
        for( int i = 0; i < this.hit.length; i++ ){
            this.hit[i] = BitmapFactory.decodeResource( context.getResources(), context.getResources().getIdentifier( "bird_hit_frame_" + i, "drawable", context.getPackageName() ) );
        }
        //Resize bitmaps
        for( int i = 0; i < this.flying.length; i++ ){
            this.flying[i] = Bitmap.createScaledBitmap(this.flying[i], this.bird.getRadius() * 2, bird.getRadius() * 2, false );
        }
        for( int i = 0; i < this.hit.length; i++ ){
            this.hit[i] = Bitmap.createScaledBitmap(this.hit[i], this.bird.getRadius() * 2, bird.getRadius() * 2, false );
        }
    }
    public Bitmap getBitmapFrame(String type){
        if(type.equals("flying")){
            return this.flying[ this.actualFrame ];
        }else if ( type.equals("hit") ) {
            return this.hit[ this.actualFrameHit ];
        }
        return null;
    }
    public int getPositionFrame(){
        return this.actualFrame;
    }
    public void resetPositionFrame(){
        this.actualFrame = 0;
    }
    public void setNextFrame(){
        this.actualFrame++;
    }
    public int getTotalFrames(String type){
        if( type.equals("flying") ){
            return this.flying.length;
        }else if( type.equals( "hit" ) ){
            return this.hit.length;
        }
        return 0;
    }
    public void drawAnimationBirdOnCanvas( Canvas canvas ){
        canvas.drawBitmap( this.getBitmapFrame("flying"), this.bird.getX() - bird.getRadius(), bird.getHeight() - bird.getRadius(), null );
        this.setNextFrame();
        if( this.getPositionFrame() == this.getTotalFrames("flying") ){
            this.resetPositionFrame();
        }
    }
    /*public boolean drawHitOnCanvas(Canvas canvas) {
        //Return status of game
        canvas.drawBitmap( this.getBitmapFrame("hit"), this.bird.getX() - bird.getRadius(), bird.getHeight() - bird.getRadius(), null );
        this.actualFrameHit++;
        if( this.actualFrameHit == this.getTotalFrames("hit") ){
            this.actualFrameHit = 0;
            return false;
        }
        return true;
    }*/
    public void drawHitOnCanvas(Canvas canvas) {
        //Return status of game
        canvas.drawBitmap( this.getBitmapFrame("hit"), this.bird.getX() - bird.getRadius(), bird.getHeight() - bird.getRadius(), null );
        this.actualFrameHit++;
        if( this.actualFrameHit == this.getTotalFrames("hit") ) {
            this.actualFrameHit = this.hit.length - 1;
        }
    }
    //TODO: rotate bird when this fall down
    public void rotateBirdOnFallDown(Canvas canvas){
        //angle progress
        int degress = 0;
       for (int i = 0; i < 90; i++) {
           canvas.save();
           canvas.rotate(degress, this.bird.getX(), this.bird.getHeight());
           canvas.drawBitmap(this.getBitmapFrame("flying"), this.bird.getX() - bird.getRadius(), bird.getHeight() - bird.getRadius(), null);
           canvas.restore();
           degress += 10;
       }
    }
}
