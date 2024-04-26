package com.eduar2tc.flapybird.engine;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.eduar2tc.flapybird.R;

public class Parallax {
    private int xPosition = 0;
    private int xGrassPosition = 0;
    private int newHeight = 0;
    private int newWidth = 0;
    private int widthScreen;
    private Bitmap background;
    private Bitmap grass;

    public Parallax( Display display ){
        float ratio = (float)display.getWidth() / display.getHeight();
        this.widthScreen = display.getWidth();
        newHeight = display.getHeight();
        newWidth = (int) (ratio * display.getHeight());
        this.background = BitmapFactory.decodeResource(display.getContext().getResources(), R.drawable.bg);
        this.background = Bitmap.createScaledBitmap( this.background, display.getWidth(), display.getHeight(), false);
        this.grass = BitmapFactory.decodeResource( display.getContext().getResources(), R.drawable.grass);
        this.grass = Bitmap.createScaledBitmap( this.grass, (int) (this.grass.getWidth() * ratio), (int) (grass.getHeight() * 0.25), false );

    }
    public void drawBackgroundOnCanvas( Canvas canvas ){
        this.xPosition -= 3;
        if( this.xPosition < -this.background.getWidth() ){
            this.xPosition = 0;
        }
        canvas.drawBitmap(this.background, xPosition, 0, null);

        if( this.xPosition < this.widthScreen ){
            canvas.drawBitmap(this.background, xPosition + this.background.getWidth(), 0, null);
        }
    }
    public void drawGrassBackground(Canvas canvas){
        this.xGrassPosition -= 8;
        if( xGrassPosition < -this.grass.getWidth() ){
            this.xGrassPosition = 0;
        }
        canvas.drawBitmap( this.grass, this.xGrassPosition, this.newHeight - this.grass.getHeight(), null );
        if( this.xGrassPosition < this.widthScreen ){
            canvas.drawBitmap( this.grass, this.xGrassPosition + this.grass.getWidth(), this.newHeight - this.grass.getHeight(), null );
        }
    }
}