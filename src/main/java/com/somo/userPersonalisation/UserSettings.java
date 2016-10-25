package com.somo.userPersonalisation;

/**
 * Created by drcee on 29/09/2016.
 */
public class UserSettings {

    private final String userId;
    private final String backGroundStyle;

    public UserSettings(String userId,
                        String backGroundStyle) {
        this.userId = userId;
        this.backGroundStyle = backGroundStyle;
    }

    public String getUserId() {
        return userId;
    }

    public String getBackGroundStyle() {
        return backGroundStyle;
    }
}
