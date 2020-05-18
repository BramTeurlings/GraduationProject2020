package nl.brickx.brickxwms2020.Presentation.MainMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import nl.brickx.brickxwms2020.R;
import nl.brickx.domain.Models.MainMenuRecyclerModel;
import nl.brickx.domain.Models.MenuCategory;
import nl.brickx.domain.Models.Permission;
import nl.brickx.domain.Models.User;

public class MainMenuActivity extends DaggerAppCompatActivity {

    User user;

    @Inject
    MainMenuContract.Presenter presenter;

    @Inject
    MainMenuContract.Navigator navigator;

    TextView activityHeaderText;
    TextView infoHeaderText;
    TextView maintenanceHeaderText;
    TextView usernameText;
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
        readIntentExtra(getIntent());

        activityHeaderText = this.findViewById(R.id.main_menu_activities_text);
        infoHeaderText = this.findViewById(R.id.main_menu_info_text);
        maintenanceHeaderText = this.findViewById(R.id.main_menu_maintenance_text);
        activityFeatureRecyclerView = this.findViewById(R.id.activitiesRecyclerView);
        infoFeatureRecyclerView = this.findViewById(R.id.infoRecyclerView);
        maintenanceFeatureRecyclerView = this.findViewById(R.id.maintenanceRecyclerView);
        usernameText = this.findViewById(R.id.main_menu_login_username_text);

        usernameText.setText(presenter.getUserData().getUsername());
        initRecyclerViews();
    }

    public static Intent createIntent(Context context){
        return new Intent(context, MainMenuActivity.class);
    }

    private void readIntentExtra(Intent intent){
        if(intent.getStringExtra("message") != null){
            Toast.makeText(getApplicationContext(), intent.getStringExtra("message"), Toast.LENGTH_SHORT).show();
        }
    }

    private void initRecyclerViews(){
//        //Todo: Mock premissions
//        List<Permission> permissions = new ArrayList<>();
//        permissions.add(Permission.BATCH_PICK);
//        permissions.add(Permission.GENERATE_LABEL);
//        permissions.add(Permission.INCOMING_GOODS);
//        permissions.add(Permission.LOCATION_INFO);
//        permissions.add(Permission.ORDER_PICK);
//        permissions.add(Permission.PRODUCT_INFO);
//        permissions.add(Permission.SSCC);
//        permissions.add(Permission.REPLENISHMENT);
//        permissions.add(Permission.STOCK_COUNT);
//        permissions.add(Permission.STOCK_MUTATION);
//        permissions.add(Permission.STOCK_TRANSFER);

        user = presenter.getUserData();

        //Todo: Get data from domain layer and data layer and bind to the correct adapter.
        activityAdapter = new MainMenuAdapter(presenter.getMenuContentByCategory(user.getPermissions(), MenuCategory.ACTIVITIES), navigator);
        infoAdapter = new MainMenuAdapter(presenter.getMenuContentByCategory(user.getPermissions(), MenuCategory.INFO), navigator);
        maintenanceAdapter = new MainMenuAdapter(presenter.getMenuContentByCategory(user.getPermissions(), MenuCategory.MAINTENANCE), navigator);

        activityFeatureRecyclerView.setAdapter(activityAdapter);
        activityFeatureRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        infoFeatureRecyclerView.setAdapter(infoAdapter);
        infoFeatureRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        maintenanceFeatureRecyclerView.setAdapter(maintenanceAdapter);
        maintenanceFeatureRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ShowCategoryHeaders();
    }

    public void ShowCategoryHeaders(){
        if(activityAdapter.getItemCount() < 1){
            activityHeaderText.setVisibility(View.GONE);
        }else{
            activityHeaderText.setVisibility(View.VISIBLE);
        }

        if(infoAdapter.getItemCount() < 1){
            infoHeaderText.setVisibility(View.GONE);
        }else{
            infoHeaderText.setVisibility(View.VISIBLE);
        }

        if(maintenanceAdapter.getItemCount() < 1){
            maintenanceHeaderText.setVisibility(View.GONE);
        }else{
            maintenanceHeaderText.setVisibility(View.VISIBLE);
        }
    }

}
