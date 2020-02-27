package nl.brickx.brickxwms2020.Presentation.Models;

import nl.brickx.domain.Models.User;

public class AuthenticationResult {

    User user;
    Boolean isAuthenticated;

    public AuthenticationResult(User user) {
        this.user = user;
    }

    public AuthenticationResult(User user, Boolean isAuthenticated) {
        this.user = user;
        this.isAuthenticated = isAuthenticated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        isAuthenticated = authenticated;
    }
}
