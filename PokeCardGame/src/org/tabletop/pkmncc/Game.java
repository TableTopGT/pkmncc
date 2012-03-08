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
import android.graphics.Paint;
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
	public Paint defaultPaint;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(new RenderView(this));
        
        // Setup Battle Music
        battleMusic = MediaPlayer.create(this, R.raw.intro);
        battleMusic.start();
        battleMusic.setLooping(true);
        
        // Setup Paint types
        defaultPaint = new Paint();
        
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
    	public RenderView(Context context) {
    	super(context);
    	}
    	protected void onDraw(Canvas canvas) {
    	// to be implemented
    		int width = canvas.getWidth();  // Width of canvas
    		int height = canvas.getHeight(); // Height of canvas
    		
    		// Re-scale background to Canvas resolution, goes off screen, canvas is
    		// NOT 1280 x 800 because of the tablet's bar at the bottom
    		battleGround = Bitmap.createScaledBitmap(battleGround, width, height, false);
    		// Draw the background
    		canvas.drawBitmap(battleGround, 0, 0, null);
    		

    		invalidate();  // <----------THIS REDRAWS EVERYTHING OVER AND OVER
    	}
    }

}