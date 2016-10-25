package com.somo.userPersonalisation;

import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by drcee on 27/09/2016.
 */

public interface UserService {

    public Optional<UserSettings> getUserSettingsById(String userId);

    public void updateUserSettings(UserSettings userSettings);
}
