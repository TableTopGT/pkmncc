package org.tabletop.pkmncc;

import java.io.IOException;
import java.io.InputStream;

import org.tabletop.pkmncc.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;

public class Game extends Activity{
    /** Called when the activity is first created. */
	public MediaPlayer battleMusic;
	public Bitmap battleGround;
	public AssetManager assetManager;
	public InputStream inputStream;
	public Paint dialogBoxPaint;
	public enum State {START, TURN, END};
	public State gameState = State.START;
	public RectF dialogBoxRect;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Sets Window to fullscreen and gets rid of top bar on Android device
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        // Sets the current view to the RenderView of "this"
        setContentView(new RenderView(this));
        
        // Setup Battle Music
        battleMusic = MediaPlayer.create(this, R.raw.intro);
        battleMusic.start();
        battleMusic.setLooping(true);
        
        // Setup Paint types
        dialogBoxPaint = new Paint();
        dialogBoxPaint.setARGB(5, 100, 50, 0);
//        dialogBoxPaint.setColor(Color.RED);
//        dialogBoxPaint.setStrokeWidth(5);
//        dialogBoxPaint.setAntiAlias(false);
        dialogBoxPaint.setStyle(Paint.Style.FILL);
        
        // Setup Asset stream
        assetManager = this.getAssets();
    	try {
			inputStream = assetManager.open("images/battlebackground.png");
			battleGround = BitmapFactory.decodeStream(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
    }
    
    class RenderView extends View {
    	// The Constructor here calls a super function for View
    	public RenderView(Context context) {
    	super(context);
    	}
    	
    	// Called to draw things
    	protected void onDraw(Canvas canvas) {
    	// to be implemented
    		int width = canvas.getWidth();  // Width of canvas
    		int height = canvas.getHeight(); // Height of canvas
    		
    		switch(gameState){
    		case START:
        		// Re-scale background to Canvas resolution, goes off screen, canvas is
        		// NOT 1280 x 800 because of the tablet's bar at the bottom
        		battleGround = Bitmap.createScaledBitmap(battleGround, width, height, false);
        		// Draw the background
        		canvas.drawBitmap(battleGround, 0, 0, null);
        		
        		// Setup the DialogBox (not finished) and draw it
        		dialogBoxRect = new RectF();
        		dialogBoxRect.set((float)(width/2)-300, (float)(height/2)+200, (float)(width/2)+300, (float)(height/2)-200);
//        		canvas.drawRoundRect(dialogBoxRect, 2, 2, dialogBoxPaint);  << Not Working, won't draw, used regular Rect instead
        		canvas.drawRect(dialogBoxRect, dialogBoxPaint);

        		invalidate();  // <----------THIS REDRAWS EVERYTHING OVER AND OVER
    			break;
    		case TURN:
    			break;
    		case END:
    			break;
    		}
    	}
    }
 
    
    // Will fix these later
	@Override
	public void onPause(){
		super.onPause();
		battleMusic.stop();
	}
	
/*	@Override
	public void onStop(){
		super.onStop();
		battleMusic.stop();
		battleMusic.release();
	}*/
	
	@Override
	public void onResume(){
		super.onResume();
		battleMusic.start();
	}
}