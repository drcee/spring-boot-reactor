package com.somo.userPersonalisation;

import java.util.Optional;

/**
 * Created by drcee on 27/09/2016.
 */

public interface UserRepository {
ÂØ
    public Optional<UserSettings> getUserSettingsById(String userId);

    public void updateUserSettings(UserSettings userSettings);
}
