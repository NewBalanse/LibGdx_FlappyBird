package com.mygdx.game.GameSetting;

/**
 * Created by NewBalanse on 07.12.2017.
 */

public class GameCount {
    float TotalCount;

    GameSetting gameSetting;

    public GameCount() {
        gameSetting = new GameSetting();
    }

    public void setTotalCount(float totalCount) {
        TotalCount = totalCount;
    }

    public float getTotalCount() {
        return TotalCount;
    }

}
