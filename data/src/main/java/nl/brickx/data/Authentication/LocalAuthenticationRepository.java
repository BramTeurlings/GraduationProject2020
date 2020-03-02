package nl.brickx.data.Authentication;

import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import nl.brickx.data.Dagger.DataContext;
import nl.brickx.data.R;
import nl.brickx.domain.Models.Gson.Authentication;
import nl.brickx.domain.Models.MainMenuRecyclerModel;
import nl.brickx.domain.Models.MenuCategory;
import nl.brickx.domain.Models.MenuItemIdentifier;
import nl.brickx.domain.Models.Permission;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class LocalAuthenticationRepository implements nl.brickx.domain.Users.Data.AuthenticationRepository {

    private Context context;

    @Inject
    LocalAuthenticationRepository(@DataContext Context context){
        this.context = context;
    }

    @Override
    public Flowable<Authentication> authenticateUser(String apiKey) {

        //Todo: Async RXJava API call. How Tf does this work?
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        LocalAuthenticationRepositoryService localAuthenticationRepositoryService = retrofit.create(LocalAuthenticationRepositoryService.class);

        Flowable<Authentication> authenticationSingle = localAuthenticationRepositoryService.validateApiKey(1);
        authenticationSingle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FlowableSubscriber<Authentication>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                    }


                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Authentication authentication) {
                        Log.i("API INFO: ", "Data received: " + authentication.getTitle());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return authenticationSingle;
    }

    @Override
    public Observable<List<MainMenuRecyclerModel>> getMenuItemsByPermissionsAndCategories(List<Permission> permissions, MenuCategory category) {

        List<MainMenuRecyclerModel> availibleFeatures = new ArrayList<>();

            //Todo: Make it return the right things per
            for(int i = 0; i < permissions.size(); i++){
                if(permissions.get(i) == Permission.BATCH_PICK && category == MenuCategory.ACTIVITIES){
                    availibleFeatures.add(new MainMenuRecyclerModel("Batch Orderpick", context.getDrawable(R.drawable.inpakken_coli_transparent), MenuItemIdentifier.BATCH_PICK_ITEM));
                }
                else if(permissions.get(i) == Permission.GENERATE_LABEL && category == MenuCategory.ACTIVITIES){
                    availibleFeatures.add(new MainMenuRecyclerModel("Label Genereren", context.getDrawable(R.drawable.productscan_transparent), MenuItemIdentifier.GENERATE_LABEL_ITEM));
                }
                else if(permissions.get(i) == Permission.ORDER_PICK && category == MenuCategory.ACTIVITIES){
                    availibleFeatures.add(new MainMenuRecyclerModel("Orderpick", context.getDrawable(R.drawable.orderpick_transparent), MenuItemIdentifier.ORDER_PICK_ITEM));
                }
                else if(permissions.get(i) == Permission.INCOMING_GOODS && category == MenuCategory.ACTIVITIES){
                    availibleFeatures.add(new MainMenuRecyclerModel("Inkomende Goederen", context.getDrawable(R.drawable.pack_transparant), MenuItemIdentifier.INCOMING_GOODS_ITEM));
                }
                else if(permissions.get(i) == Permission.LOCATION_INFO && category == MenuCategory.INFO){
                    availibleFeatures.add(new MainMenuRecyclerModel("Locatie Informatie", context.getDrawable(R.drawable.scanlocation_transparent), MenuItemIdentifier.LOCATION_INFO_ITEM));
                }
                else if(permissions.get(i) == Permission.PRODUCT_INFO && category == MenuCategory.INFO){
                    availibleFeatures.add(new MainMenuRecyclerModel("Product Informatie", context.getDrawable(R.drawable.productscan_transparent), MenuItemIdentifier.PRODUCT_INFO_ITEM));
                }
                else if(permissions.get(i) == Permission.REPLENISHMENT && category == MenuCategory.MAINTENANCE){
                    availibleFeatures.add(new MainMenuRecyclerModel("Replenishment", context.getDrawable(R.drawable.replenish_transparent), MenuItemIdentifier.REPLENISHMENT_ITEM));
                }
                else if(permissions.get(i) == Permission.SSCC && category == MenuCategory.ACTIVITIES){
                    availibleFeatures.add(new MainMenuRecyclerModel("SSCC", context.getDrawable(R.drawable.inpakken_coli_transparent), MenuItemIdentifier.SSCC_ITEM));
                }
                else if(permissions.get(i) == Permission.STOCK_COUNT && category == MenuCategory.MAINTENANCE){
                    availibleFeatures.add(new MainMenuRecyclerModel("Voorraad Tellen", context.getDrawable(R.drawable.orderpick_transparent), MenuItemIdentifier.STOCK_COUNT_ITEM));
                }
                else if(permissions.get(i) == Permission.STOCK_MUTATION && category == MenuCategory.MAINTENANCE){
                    availibleFeatures.add(new MainMenuRecyclerModel("Voorraad Mutatie", context.getDrawable(R.drawable.stockmutation_transparent), MenuItemIdentifier.STOCK_MUTATION_ITEM));
                }
                else if(permissions.get(i) == Permission.STOCK_TRANSFER && category == MenuCategory.MAINTENANCE){
                    availibleFeatures.add(new MainMenuRecyclerModel("Voorraad Transfer", context.getDrawable(R.drawable.transfer_transparent), MenuItemIdentifier.STOCK_TRANSFER_ITEM));
                }
            }

        return Observable.just(availibleFeatures);
    }
}
