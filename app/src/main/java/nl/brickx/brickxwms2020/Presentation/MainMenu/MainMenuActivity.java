package nl.brickx.brickxwms2020.Presentation.MainMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import nl.brickx.brickxwms2020.R;
import nl.brickx.domain.Models.MainMenuRecyclerModel;
import nl.brickx.domain.Models.MenuCategory;
import nl.brickx.domain.Models.Permission;

public class MainMenuActivity extends DaggerAppCompatActivity {

    @Inject
    MainMenuContract.Presenter presenter;

    @Inject
    MainMenuContract.Navigator navigator;

    RecyclerView activityFeatureRecyclerView;
    RecyclerView infoFeatureRecyclerView;
    RecyclerView maintenanceFeatureRecyclerView;
    MainMenuAdapter activityAdapter;
    MainMenuAdapter infoAdapter;
    MainMenuAdapter maintenanceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_page);

        activityFeatureRecyclerView = this.findViewById(R.id.activitiesRecyclerView);
        infoFeatureRecyclerView = this.findViewById(R.id.infoRecyclerView);
        maintenanceFeatureRecyclerView = this.findViewById(R.id.maintenanceRecyclerView);
        initRecyclerViews();
    }

    private void initRecyclerViews(){
        //Todo: Mock premissions
        List<Permission> permissions = new ArrayList<>();
        permissions.add(Permission.BATCH_PICK);
        permissions.add(Permission.GENERATE_LABEL);
        permissions.add(Permission.INCOMING_GOODS);
        permissions.add(Permission.LOCATION_INFO);
        permissions.add(Permission.ORDER_PICK);
        permissions.add(Permission.PRODUCT_INFO);
        permissions.add(Permission.SSCC);
        permissions.add(Permission.REPLENISHMENT);
        permissions.add(Permission.STOCK_COUNT);
        permissions.add(Permission.STOCK_MUTATION);
        permissions.add(Permission.STOCK_TRANSFER);

        //Todo: Get data from domain layer and data layer and bind to the correct adapter.
        activityAdapter = new MainMenuAdapter(presenter.getMenuContentByCategory(permissions, MenuCategory.ACTIVITIES), navigator);
        infoAdapter = new MainMenuAdapter(presenter.getMenuContentByCategory(permissions, MenuCategory.INFO), navigator);
        maintenanceAdapter = new MainMenuAdapter(presenter.getMenuContentByCategory(permissions, MenuCategory.MAINTENANCE), navigator);

        activityFeatureRecyclerView.setAdapter(activityAdapter);
        activityFeatureRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        infoFeatureRecyclerView.setAdapter(infoAdapter);
        infoFeatureRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        maintenanceFeatureRecyclerView.setAdapter(maintenanceAdapter);
        maintenanceFeatureRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
