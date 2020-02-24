package nl.brickx.domain.Users;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import nl.brickx.data.dagger.DataContext;
import nl.brickx.domain.Models.MainMenuRecyclerModel;
import nl.brickx.domain.Models.MenuCategory;
import nl.brickx.domain.Models.Permission;
import nl.brickx.domain.R;

import android.content.Context;

public class GetMenuItemsByPermissionsAndCategory {

    private Context context;

    @Inject
    public GetMenuItemsByPermissionsAndCategory(@DataContext Context context){
        this.context = context;
    }

    public Observable<List<MainMenuRecyclerModel>> invoke(List<Permission> permissions, MenuCategory category){
        List<MainMenuRecyclerModel> availibleFeatures = new ArrayList<>();

        //Todo: Make it return the right things per
        for(int i = 0; i < permissions.size(); i++){
            if(permissions.get(i) == Permission.BATCH_PICK && category == MenuCategory.ACTIVITIES){
                availibleFeatures.add(new MainMenuRecyclerModel("Batch Orderpick", context.getDrawable(R.drawable.inpakken_coli_transparent)));
            }
            else if(permissions.get(i) == Permission.GENERATE_LABEL && category == MenuCategory.ACTIVITIES){
                availibleFeatures.add(new MainMenuRecyclerModel("Label Genereren", context.getDrawable(R.drawable.productscan_transparent)));
            }
            else if(permissions.get(i) == Permission.ORDER_PICK && category == MenuCategory.ACTIVITIES){
                availibleFeatures.add(new MainMenuRecyclerModel("Orderpick", context.getDrawable(R.drawable.orderpick_transparent)));
            }
            else if(permissions.get(i) == Permission.INCOMING_GOODS && category == MenuCategory.ACTIVITIES){
                availibleFeatures.add(new MainMenuRecyclerModel("Inkomende Goederen", context.getDrawable(R.drawable.pack_transparant)));
            }
            else if(permissions.get(i) == Permission.LOCATION_INFO && category == MenuCategory.INFO){
                availibleFeatures.add(new MainMenuRecyclerModel("Locatie Informatie", context.getDrawable(R.drawable.scanlocation_transparent)));
            }
            else if(permissions.get(i) == Permission.PRODUCT_INFO && category == MenuCategory.INFO){
                availibleFeatures.add(new MainMenuRecyclerModel("Product Informatie", context.getDrawable(R.drawable.productscan_transparent)));
            }
            else if(permissions.get(i) == Permission.REPLENISHMENT && category == MenuCategory.MAINTENANCE){
                availibleFeatures.add(new MainMenuRecyclerModel("Replenishment", context.getDrawable(R.drawable.replenish_transparent)));
            }
            else if(permissions.get(i) == Permission.SSCC && category == MenuCategory.ACTIVITIES){
                availibleFeatures.add(new MainMenuRecyclerModel("SSCC", context.getDrawable(R.drawable.inpakken_coli_transparent)));
            }
            else if(permissions.get(i) == Permission.STOCK_COUNT && category == MenuCategory.MAINTENANCE){
                availibleFeatures.add(new MainMenuRecyclerModel("Voorraad Tellen", context.getDrawable(R.drawable.orderpick_transparent)));
            }
            else if(permissions.get(i) == Permission.STOCK_MUTATION && category == MenuCategory.MAINTENANCE){
                availibleFeatures.add(new MainMenuRecyclerModel("Voorraad Mutatie", context.getDrawable(R.drawable.stockmutation_transparent)));
            }
            else if(permissions.get(i) == Permission.STOCK_TRANSFER && category == MenuCategory.MAINTENANCE){
                availibleFeatures.add(new MainMenuRecyclerModel("Voorraad Transfer", context.getDrawable(R.drawable.transfer_transparent)));
            }
        }

        return Observable.just(availibleFeatures);
    }

}
