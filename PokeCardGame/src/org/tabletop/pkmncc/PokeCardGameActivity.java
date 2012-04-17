package org.tabletop.pkmncc;

import org.tabletop.pkmncc.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.View;
import android.view.View.OnClickListener;

public class PokeCardGameActivity extends Activity implements OnClickListener {
	private MediaPlayer music;
	private WakeLock wakeLock;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Set up click listeners for all the buttons
        View beginButton = findViewById(R.id.begin_button);
        beginButton.setOnClickListener(this);
        View exitButton = findViewById(R.id.exit_button);
        exitButton.setOnClickListener(this);
        
        // Setup Music
        music = MediaPlayer.create(this, R.raw.title);
        music.setLooping(true);

        // Setup wakelock so screen does not dim
       PowerManager powerManager = (PowerManager)this.getSystemService(Context.POWER_SERVICE);
       wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "My Lock");
    }

	@Override
    public void onResume() {
    	super.onResume();
        wakeLock.acquire();
		if (music != null)
			music.start();
    }

    @Override
	protected void onPause() {
		super.onPause();
		wakeLock.release();
		if (music != null) {
			music.pause();
		}
	}

    public void onClick(View v) {
    	switch(v.getId()) {
    	case R.id.begin_button:
    		Intent beginGame = new Intent(this, Game.class);
    		startActivity(beginGame);
    		break;
    	case R.id.exit_button:
    		music.stop();
    		music.release();
    		music = null;
    		finish();
    		break;
        }
    }
}