package nl.brickx.brickxwms2020.Presentation.LocationInfo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import nl.brickx.brickxwms2020.Presentation.MainMenu.MainMenuAdapter;
import nl.brickx.brickxwms2020.Presentation.ProductInfo.ProductInfoActivity;
import nl.brickx.brickxwms2020.R;
import nl.brickx.domain.Models.MenuCategory;
import nl.brickx.domain.Models.Permission;

public class LocationInfoActivity extends DaggerAppCompatActivity {

    RecyclerView recyclerView;
    LocationInfoAdapter recyclerViewAdapter;

    @Inject
    LocationInfoPresenter presenter;

    public static Intent createIntent(Context context){
        return new Intent(context, LocationInfoActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_info_page);
        recyclerView = this.findViewById(R.id.location_info_recycler_view);
        initRecyclerViews();
    }

    private void initRecyclerViews(){
        //Todo: Get data from domain layer and data layer and bind to the correct adapter.
        recyclerViewAdapter = new LocationInfoAdapter(presenter.getProductsByLocation("mockLocationCode"), "mockLocationCode");

        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
