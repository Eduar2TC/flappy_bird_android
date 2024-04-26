package com.eduar2tc.flapybird.elements;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;

import com.eduar2tc.flapybird.R;
import com.eduar2tc.flapybird.engine.Colors;
import com.eduar2tc.flapybird.engine.Display;
import com.eduar2tc.flapybird.engine.Parallax;

public class Pipe {
    private static  final int PIPE_HEIGHT = 500;
    private static final int PIPE_WIDTH = 200;

    private Display display;
    private int pipeBottomHeight;
    private int pipeTopHeight;
    private int position;
    final Paint paint = Colors.getPipeColor();
    private Bitmap pipeBottom;
    private Bitmap pipeTop;
    float ratio;

    public Pipe( Display display, int position ){
        ratio = (float) display.getWidth() / display.getHeight();
        this.display = display;
        //Bottom pipe
        this.pipeBottomHeight = display.getHeight() - PIPE_HEIGHT - this.randomValue();
        this.position = position;
        //Top pipe
        this.pipeTopHeight = 0 + PIPE_HEIGHT + this.randomValue();
        this.pipeBottom = BitmapFactory.decodeResource( display.getContext().getResources(), R.drawable.pipe);
        this.pipeTop = BitmapFactory.decodeResource( display.getContext().getResources(), R.drawable.pipe);
        this.pipeBottom = Bitmap.createScaledBitmap( this.pipeBottom, PIPE_WIDTH, this.pipeBottomHeight, false );
        this.pipeTop = Bitmap.createScaledBitmap( this.pipeTop, PIPE_WIDTH, this.pipeTopHeight, false );
    }

    private int randomValue() {
        return (int) (Math.random() * 350 );
    }

    public void drawPipeTopOnCanvas(Canvas canvas) {
        /*Paint strokePaint = new Paint();
        Path path = new Path();

        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setColor(Color.BLACK);
        strokePaint.setStrokeWidth(5);
        //Parameters , Initial positionXY on screen , Height pipe
        canvas.drawRect( this.position, 0, this.position + PIPE_WIDTH, this.pipeTopHeight, this.paint);
        path.moveTo(this.position, 0);
        path.lineTo(this.position, this.pipeTopHeight);
        path.lineTo(this.position + PIPE_WIDTH, this.pipeTopHeight );
        path.lineTo(this.position + PIPE_WIDTH, 0);
        //path.close();//closed loop
        canvas.drawPath(path, strokePaint);*/

        canvas.save();
        canvas.rotate( 180, this.position, this.pipeTopHeight / 2.f  ); //Rotate in middle image
        canvas.drawBitmap( this.pipeTop, this.position - PIPE_WIDTH, 0 , null );
        canvas.restore();
    }

    //Draw pipes
    public void drawPipeBottomOnCanvas( Canvas canvas ){
        /*Paint strokePaint = new Paint();
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setColor(Color.BLACK);
        strokePaint.setStrokeWidth(5);

        //Parameters Initial Position XY on screen, SizeScreen - Height of pipe, PositionXPipe + width Pipe, Screen Size, Paint object
        //Parameters( positionXYWidth, Height, positionXYHeight  )
        canvas.drawRect(  this.position , this.pipeBottomHeight, this.position + PIPE_WIDTH, display.getHeight() , this.paint );
        Path path = new Path();
        Path gradientPath = new Path();
        path.moveTo(this.position, this.display.getHeight() );    //Set the starting point
        path.lineTo(this.position, this.pipeBottomHeight);      //The end of the first line is also the beginning of the second line.
        path.lineTo(this.position + PIPE_WIDTH, pipeBottomHeight);
        path.lineTo(this.position + PIPE_WIDTH, this.display.getHeight() );
        paint.setShader(new LinearGradient( this.position, 0, this.position + PIPE_WIDTH - 110, 0, Color.DKGRAY, Color.argb(255, 81, 185, 87), Shader.TileMode.MIRROR));
        canvas.drawPath(gradientPath, paint);
        //path.close();//closed loop
        canvas.drawPath(path, strokePaint);*/
        canvas.drawBitmap( this.pipeBottom, this.position, this.pipeBottomHeight, null );
    }

    public void move() {
        this.position -= 8;
    }

    public boolean outOfLefScreen() {
        if( position + PIPE_WIDTH < 0 ){
            return true;
        }
        return false;
        //return this.position + PIPE_WIDTH < 0;
    }

    public int getPosition() {
        return this.position;
    }

    public boolean detectHorizontalCollision(Bird bird) {
        if( this.position - bird.getX() < bird.getRadius() ){
            return true;
        }
        return false;
    }

    public boolean detectVerticalCollision(Bird bird) {
        //return true if top bird is < superior pipe height and if bird position > height pipe bottom
        if( bird.getHeight() - bird.getRadius() < this.pipeTopHeight || bird.getHeight() + bird.getRadius() > this.pipeBottomHeight ){
            return true;
        }
        return false;
    }
}
