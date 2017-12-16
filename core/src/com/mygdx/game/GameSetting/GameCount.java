package com.mygdx.game.GameSetting;

/**
 * Created by NewBalanse on 07.12.2017.
 */

public class GameCount {
    int TotalCount;

    boolean GoldMedal;
    boolean SilverMedal;
    boolean BronzeMedal;

    GameSetting gameSetting;

    public GameCount() {
        gameSetting = new GameSetting();
        TotalCount = gameSetting.getTotalCount();
        switch (gameSetting.getTypeMedal()) {
            case 1:
                GoldMedal = true;
                break;
            case 2:
                SilverMedal = true;
                break;
            case 3:
                BronzeMedal = true;
                break;
            default:
                break;
        }
    }

    public void setTotalCount(int totalCount) {
        TotalCount = totalCount;
        if (TotalCount == 5)
            BronzeMedal = true;
        else if (TotalCount == 10)
            SilverMedal = true;
        else if (TotalCount >= 25)
            GoldMedal = true;
    }

    public int getTotalCount() {
        return TotalCount;
    }

    public boolean isGoldMedal() {
        return GoldMedal;
    }

    public boolean isSilverMedal() {
        return SilverMedal;
    }

    public boolean isBronzeMedal() {
        return BronzeMedal;
    }
}
