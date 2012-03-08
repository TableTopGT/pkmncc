package org.tabletop.pkmncc;

import org.tabletop.poke.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
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
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Setup Battle Music
        battleMusic = MediaPlayer.create(this, R.raw.intro);
        battleMusic.start();
        battleMusic.setLooping(true);
    }
    
    class RenderView extends View {
    	public RenderView(Context context) {
    	super(context);
    	}
    	protected void onDraw(Canvas canvas) {
    	// to be implemented
    		int width = canvas.getWidth();  // Width of canvas
    		int height = canvas.getHeight(); // Height of canvas

    		invalidate();  // <----------THIS REDRAWS EVERYTHING OVER AND OVER
    	}
    }

}