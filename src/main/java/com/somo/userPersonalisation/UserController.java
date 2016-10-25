package com.somo.userPersonalisation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Created by drcee on 29/09/2016.
 */
@RestController
public class UserController{

    private static Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userRepository;

    private final static UserSettings USER_SETTINGS = new UserSettings(null,null);

    @Autowired
    public UserController(UserService userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(path="/userSettings/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    Mono<UserSettings> getUserSettings(@PathVariable("id") final String userId) throws Exception {
       return Mono.just(
               userRepository.getUserSettingsById(userId).orElse(USER_SETTINGS));
    }

    @RequestMapping(path = "/userSettings/create", method = RequestMethod.POST)
    public Mono<UserSettings> updateUserSettings(@RequestBody Mono<UserSettings> userSettingsFuture) {
        return userSettingsFuture.doOnNext(
                settings -> userRepository.updateUserSettings(settings));
    }
}
