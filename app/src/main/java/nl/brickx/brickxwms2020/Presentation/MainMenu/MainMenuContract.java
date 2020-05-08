package nl.brickx.brickxwms2020.Presentation.MainMenu;

import java.util.List;

import nl.brickx.domain.Models.MainMenuRecyclerModel;
import nl.brickx.domain.Models.MenuCategory;
import nl.brickx.domain.Models.Permission;
import nl.brickx.domain.Models.User;

public interface MainMenuContract {

    interface Presenter {

        List<MainMenuRecyclerModel> getMenuContentByCategory(List<Permission> permissions, MenuCategory category);
        User getUserData();
    }

    interface Navigator {

        void navigateToProductInfo();
        void navigateToLocationInfo();
        void navigateToOrderPickScreen();
        void navigateToStockTransfer();
    }
}
