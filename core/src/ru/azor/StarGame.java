package ru.azor;

import com.badlogic.gdx.Game;

import ru.azor.screen.MenuScreen;

public class StarGame extends Game {

	@Override
	public void create () {
		setScreen(new MenuScreen(this));
	}

}
