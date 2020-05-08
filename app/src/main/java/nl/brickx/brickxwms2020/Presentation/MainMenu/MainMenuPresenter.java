package nl.brickx.brickxwms2020.Presentation.MainMenu;

import java.util.List;
import java.util.Observable;

import javax.inject.Inject;

import nl.brickx.domain.Models.MainMenuRecyclerModel;
import nl.brickx.domain.Models.MenuCategory;
import nl.brickx.domain.Models.Permission;
import nl.brickx.domain.Models.User;
import nl.brickx.domain.Users.GetMenuItemsByPermissionsAndCategory;
import nl.brickx.domain.Users.UserDataManager;

public class MainMenuPresenter implements MainMenuContract.Presenter {

    private GetMenuItemsByPermissionsAndCategory getMenuItemsByPermissionsAndCategory;
    private UserDataManager userDataManager;

    @Inject
    MainMenuPresenter(GetMenuItemsByPermissionsAndCategory getMenuItemsByPermissionsAndCategory, UserDataManager userDataManager){
        this.getMenuItemsByPermissionsAndCategory = getMenuItemsByPermissionsAndCategory;
        this.userDataManager = userDataManager;
    }


    @Override
    public List<MainMenuRecyclerModel> getMenuContentByCategory(List<Permission> permissions, MenuCategory category) {
        //Todo: Do proper RXjava without blocking.
        return getMenuItemsByPermissionsAndCategory.invoke(permissions, category).blockingFirst();
    }

    @Override
    public User getUserData() {
        return userDataManager.GetUserData();
    }
}
