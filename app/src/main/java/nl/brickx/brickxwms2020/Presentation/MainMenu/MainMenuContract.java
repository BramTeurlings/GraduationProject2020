package nl.brickx.brickxwms2020.Presentation.MainMenu;

import java.util.List;

import nl.brickx.domain.Models.MainMenuRecyclerModel;
import nl.brickx.domain.Models.MenuCategory;
import nl.brickx.domain.Models.Permission;

public interface MainMenuContract {

    interface Presenter {

        List<MainMenuRecyclerModel> getMenuContentByCategory(List<Permission> permissions, MenuCategory category);
    }

    interface Navigator {

        //Todo: Navigate to screens.
    }
}
