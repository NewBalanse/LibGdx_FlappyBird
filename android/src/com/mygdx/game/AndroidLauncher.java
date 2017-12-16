package com.mygdx.game;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.game.DataBase.DBHelper;

public class AndroidLauncher extends AndroidApplication {
	DBHelper helper;
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		helper = new DBHelper(this);
		SQLiteDatabase db = helper.getWritableDatabase();

		initialize(new GameController(), config);
	}

}
