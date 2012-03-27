package org.tabletop.pkmncc;

import org.tabletop.pkmncc.R;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.View;
import android.view.View.OnClickListener;

public class PokeCardGameActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	public MediaPlayer music;
	
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
        music.start();
        music.setLooping(true);
        
        // Setup wakelock so screen does not dim  **ERROR DOES NOT WORK YET
       /* PowerManager powerManager = (PowerManager)this.getSystemService(this.POWER_SERVICE);
        WakeLock wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "My Lock");
        wakeLock.acquire();*/
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	music.start();
    };
    
    public void onClick(View v) {
    	switch(v.getId()) {
    	case R.id.begin_button:
    		music.stop();
    		//music.release();
    		Intent beginGame = new Intent(this, Game.class);
    		startActivity(beginGame);
    		break;
    	case R.id.exit_button:
    		music.stop();
    		music.release();
    		finish();
    		break;
        }
    }
}