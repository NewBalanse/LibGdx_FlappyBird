package com.mygdx.game.GameSetting;


public class GameSetting {
    int TotalCount;
    FileStream fileStream;

    public int getTotalCount() {
        return TotalCount;
    }

    public GameSetting() {
        fileStream = new FileStream();
        ReturnTotalCount();
    }

    public GameSetting(String filePath) {
        fileStream = new FileStream(filePath);
        ReturnTotalCount();
    }

    private void ReturnTotalCount() {
        String result;
        result = fileStream.WriteFile();
        if (result.equals(""))
            TotalCount = 0;
        else
            TotalCount = Integer.parseInt(result);

    }

    public void SaveCount(int NewBestCount) {
        fileStream.SaveFile(NewBestCount);
    }
}
