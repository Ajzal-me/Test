package io.github.Mystic.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import io.github.Mystic.Main;  // Import your game class
import io.github.Mystic.Mystic_Trails;

public class Lwjgl3Launcher {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

        // Configure window settings
        config.setTitle("Mystic Trails");   // Set the title
        config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
        //config.setWindowedMode(1920,1000);
        config.setResizable(true);         // Allow the window to be resized
        config.useVsync(true);
        // Launch the game
        new Lwjgl3Application(new Mystic_Trails(), config);
    }
}
