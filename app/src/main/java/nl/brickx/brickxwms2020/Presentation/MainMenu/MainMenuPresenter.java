package nl.brickx.brickxwms2020.Presentation.MainMenu;

import java.util.List;
import java.util.Observable;

import javax.inject.Inject;

import nl.brickx.domain.Models.MainMenuRecyclerModel;
import nl.brickx.domain.Models.MenuCategory;
import nl.brickx.domain.Models.Permission;
import nl.brickx.domain.Users.GetMenuItemsByPermissionsAndCategory;

public class MainMenuPresenter implements MainMenuContract.Presenter {

    private GetMenuItemsByPermissionsAndCategory getMenuItemsByPermissionsAndCategory;

    @Inject
    MainMenuPresenter(GetMenuItemsByPermissionsAndCategory getMenuItemsByPermissionsAndCategory){
        this.getMenuItemsByPermissionsAndCategory = getMenuItemsByPermissionsAndCategory;
    }


    @Override
    public List<MainMenuRecyclerModel> getMenuContentByCategory(List<Permission> permissions, MenuCategory category) {
        return getMenuItemsByPermissionsAndCategory.invoke(permissions, category).blockingFirst();
    }
}