package com.ft.restaurants.auth;

import com.ft.restaurants.core.User;
import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import static com.google.common.base.Optional.of;

/**
 * Created by Jorge on 6/16/2016.
 */
public class UserAuthenticator implements Authenticator<BasicCredentials, User> {

    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        if ("p@ssw0rd".equals(credentials.getPassword())) {
            return of(new User());
        } else {
            return Optional.absent();
        }
    }

}
