package io.github.Mystic;

import com.badlogic.gdx.Game;

public class Mystic_Trails extends Game {

    @Override
    public void create() {

        setScreen(new Gamemain(this));
    }

    @Override
    public void render() {

        super.render();
    }
}
