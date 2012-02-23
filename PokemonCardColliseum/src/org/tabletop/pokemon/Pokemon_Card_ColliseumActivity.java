package org.tabletop.pokemon;

import android.app.Activity;
import android.os.Bundle;

//public class Pokemon_Card_ColliseumActivity extends Activity {
    /** Called when the activity is first created. */
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
//    }
//}

import com.badlogic.gdx.backends.android.AndroidApplication;

public class Pokemon_Card_ColliseumActivity extends AndroidApplication {
        public void onCreate (android.os.Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                initialize(new Game(), false);
        }
}