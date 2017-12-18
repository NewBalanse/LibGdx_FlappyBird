package com.mygdx.game.GameSetting;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Created by NewBalanse on 17.12.2017.
 */

public class FileStream {
    String filePath;
    FileHandle fileHandle;

    public FileStream(String filePath) {
        this.filePath = filePath;
    }

    public FileStream() {
        filePath = "CountFlappyBird.txt";
    }

    public String WriteFile() {
        fileHandle = Gdx.files.external(filePath);
        String text = "";
        boolean isExists = fileHandle.exists();
        if (isExists) {
            fileHandle = Gdx.files.internal(filePath);
            text = fileHandle.readString();
        }
        return text;
    }
    public void SaveFile(int SaveCount){
        fileHandle = Gdx.files.external(filePath);
        if(!fileHandle.exists())
            fileHandle.mkdirs();

        fileHandle = Gdx.files.local(filePath);
        String temp = String.valueOf(SaveCount);
        fileHandle.writeString(temp,false);
    }

}
