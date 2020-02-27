package nl.brickx.domain.Users;

import java.util.List;
import javax.inject.Inject;
import io.reactivex.Observable;
import nl.brickx.domain.Models.MainMenuRecyclerModel;
import nl.brickx.domain.Models.MenuCategory;
import nl.brickx.domain.Models.Permission;
import nl.brickx.domain.Users.Data.AuthenticationRepository;

import android.content.Context;

public class GetMenuItemsByPermissionsAndCategory {

    private Context context;
    private AuthenticationRepository authenticationRepository;

    @Inject
    GetMenuItemsByPermissionsAndCategory(AuthenticationRepository authenticationRepository){
        this.authenticationRepository = authenticationRepository;
    }

    public Observable<List<MainMenuRecyclerModel>> invoke(List<Permission> permissions, MenuCategory category) {
        return authenticationRepository.getMenuItemsByPermissionsAndCategories(permissions, category);
    }

}
