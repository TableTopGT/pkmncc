package org.tabletop.pokemon;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;

public class PokemonCardColliseumActivity extends AndroidApplication {
        public void onCreate (Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                initialize(new Game(), false);
        }
}