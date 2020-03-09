package nl.brickx.domain.Users.Data;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import nl.brickx.domain.Models.Gson.Authentication;
import nl.brickx.domain.Models.Gson.UserInfo.UserInfo;
import nl.brickx.domain.Models.MainMenuRecyclerModel;
import nl.brickx.domain.Models.MenuCategory;
import nl.brickx.domain.Models.Permission;
import nl.brickx.domain.Models.User;

public interface AuthenticationRepository {

    interface Api{
        Flowable<Authentication> authenticateUser(String apiKey);
    }

    interface Categories {
        Observable<List<MainMenuRecyclerModel>> getMenuItemsByPermissionsAndCategories(List<Permission> permissions, MenuCategory category);
    }

    interface GetUserDataFromApi {
        Flowable<UserInfo> getUserData(String apiKey);
    }

    interface SaveUserDataSharedPref {
        Boolean saveUserDataSharedPref(User user);
    }

    interface GetUserDataSharedPref {
        User getuserDataSharedPref();
    }
}
