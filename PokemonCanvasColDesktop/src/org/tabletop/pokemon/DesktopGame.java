package org.tabletop.pokemon;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class DesktopGame {
        public static void main (String[] args) {
                new LwjglApplication(new Game(), "Main_Game", 1280, 800, false);
        }
}