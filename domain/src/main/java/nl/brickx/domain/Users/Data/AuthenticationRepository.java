package nl.brickx.domain.Users.Data;

import java.util.List;

import io.reactivex.Observable;
import nl.brickx.domain.Models.MainMenuRecyclerModel;
import nl.brickx.domain.Models.MenuCategory;
import nl.brickx.domain.Models.Permission;

public interface AuthenticationRepository {

    Boolean authenticateUser(String apiKey);
    Observable<List<MainMenuRecyclerModel>> getMenuItemsByPermissionsAndCategories(List<Permission> permissions, MenuCategory category);
}
