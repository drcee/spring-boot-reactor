package com.somo.userPersonalisation;

import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by drcee on 29/09/2016.
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public Optional<UserSettings> getUserSettingsById(String userId) {
        return null;
    }

    @Override
    public void updateUserSettings(UserSettings userSettings) {

    }
}
