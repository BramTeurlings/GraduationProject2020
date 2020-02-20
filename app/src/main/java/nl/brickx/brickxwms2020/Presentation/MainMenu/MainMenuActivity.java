package nl.brickx.brickxwms2020.Presentation.MainMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import nl.brickx.brickxwms2020.R;

public class MainMenuActivity extends AppCompatActivity {

    RecyclerView infoFeatureRecyclerView;
    MainMenuAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_page);

        infoFeatureRecyclerView = this.findViewById(R.id.infoRecyclerView);
        initRecyclerViews();
    }

    private void initRecyclerViews(){
        //Todo: Mock code
        List<MainMenuRecyclerModel> mockData = new ArrayList<>();
        mockData.add(new MainMenuRecyclerModel("Order Picken", getDrawable(R.drawable.orderpick_transparent)));
        mockData.add(new MainMenuRecyclerModel("Coli Inpakken", getDrawable(R.drawable.inpakken_coli_transparent)));
        mockData.add(new MainMenuRecyclerModel("Voorraad Transfer", getDrawable(R.drawable.transfer_transparent)));
        mockData.add(new MainMenuRecyclerModel("Batch Orderpick", getDrawable(R.drawable.pack_transparant)));
        mockData.add(new MainMenuRecyclerModel("Product Informatie", getDrawable(R.drawable.productscan_transparent)));
        mockData.add(new MainMenuRecyclerModel("Replenishment", getDrawable(R.drawable.replenish_transparent)));
        mockData.add(new MainMenuRecyclerModel("Product Locatie", getDrawable(R.drawable.scanlocation_transparent)));
        mockData.add(new MainMenuRecyclerModel("Voorraad Mutaties", getDrawable(R.drawable.stockmutation_transparent)));
        mockData.add(new MainMenuRecyclerModel("Voorraad", getDrawable(R.drawable.voorraad_transparent)));
        mockData.add(new MainMenuRecyclerModel("Order Picken", getDrawable(R.drawable.orderpick_transparent)));
        mockData.add(new MainMenuRecyclerModel("Coli Inpakken", getDrawable(R.drawable.inpakken_coli_transparent)));
        mockData.add(new MainMenuRecyclerModel("Voorraad Transfer", getDrawable(R.drawable.transfer_transparent)));
        mockData.add(new MainMenuRecyclerModel("Batch Orderpick", getDrawable(R.drawable.pack_transparant)));
        mockData.add(new MainMenuRecyclerModel("Product Informatie", getDrawable(R.drawable.productscan_transparent)));
        mockData.add(new MainMenuRecyclerModel("Replenishment", getDrawable(R.drawable.replenish_transparent)));
        mockData.add(new MainMenuRecyclerModel("Product Locatie", getDrawable(R.drawable.scanlocation_transparent)));
        mockData.add(new MainMenuRecyclerModel("Voorraad Mutaties", getDrawable(R.drawable.stockmutation_transparent)));
        mockData.add(new MainMenuRecyclerModel("Voorraad", getDrawable(R.drawable.voorraad_transparent)));

        adapter = new MainMenuAdapter(mockData);

        infoFeatureRecyclerView.setAdapter(adapter);
        infoFeatureRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
