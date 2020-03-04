package nl.brickx.domain.Users;

import javax.inject.Inject;

import nl.brickx.domain.Models.User;
import nl.brickx.domain.Users.Data.AuthenticationRepository;

public class UserDataManager {

    private AuthenticationRepository.SaveUserDataSharedPref saveUserDataSharedPref;
    private AuthenticationRepository.GetUserDataSharedPref getUserDataSharedPref;

    @Inject
    public UserDataManager(AuthenticationRepository.SaveUserDataSharedPref saveUserDataSharedPref, AuthenticationRepository.GetUserDataSharedPref getUserDataSharedPref) {
        this.saveUserDataSharedPref = saveUserDataSharedPref;
        this.getUserDataSharedPref = getUserDataSharedPref;
    }

    public Boolean SaveUserData(User user){
        return saveUserDataSharedPref.saveUserDataSharedPref(user);
    }

    public User GetUserData(){
        return getUserDataSharedPref.getuserDataSharedPref();
    }
}
