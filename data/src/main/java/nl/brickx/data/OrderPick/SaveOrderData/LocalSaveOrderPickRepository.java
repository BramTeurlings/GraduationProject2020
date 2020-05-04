package nl.brickx.data.OrderPick.SaveOrderData;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import io.reactivex.Observable;
import nl.brickx.data.Dagger.DataContext;
import nl.brickx.data.User.LocalUserRepositoryService;
import nl.brickx.domain.Models.GsonOrderPickPickList;
import nl.brickx.domain.Models.OrderPickPickListModel;
import nl.brickx.domain.OrderPick.Main.Data.OrderPickRepository;
import retrofit2.Retrofit;

public class LocalSaveOrderPickRepository implements OrderPickRepository.SaveOrderPickProgress, OrderPickRepository.GetOrderPickProgress {

    @Inject
    Gson gson;

    @Inject
    Retrofit retrofit;

    @Inject
    LocalUserRepositoryService localUserRepositoryService;

    final static String storageName = "ORDER_STORAGE";
    final static String TAG = "SharedPreferences: ";

    Context context;

    private SharedPreferences sharedPreferences;
    private ObjectMapper mapper = new ObjectMapper();
    private List<GsonOrderPickPickList> tempGetResult = new ArrayList<>();

    @Inject
    LocalSaveOrderPickRepository(@DataContext Context context) {
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences(storageName, Context.MODE_PRIVATE);
    }

    @Override
    public Boolean saveUserDataSharedPref(List<OrderPickPickListModel> data) {
        List<GsonOrderPickPickList> orderPickSaves = new ArrayList<>();
        try{
            if(getOrderPickData().blockingFirst() != null){
                orderPickSaves = getOrderPickData().blockingFirst();
            }
        }catch (NullPointerException e){
            Log.i(TAG, "Unable to read order data from storage");
        }

        //Todo: Go through list and check for doubles
        try{
            for(int i = 0; i < orderPickSaves.size(); i++){
                for(int i2 = 0; i < orderPickSaves.get(i).getOrders().size(); i2++){
                    try{
                        if(orderPickSaves.get(i).getOrders().get(i2).getPickslipNumber().equals(data.get(0).getPickslipNumber())){
                            orderPickSaves.remove(i);
                            i--;
                        }
                        break;
                    }catch (Exception e){
                        Log.i(TAG, "Order is empty, unable to read pickslip number.");
                    }
                }
            }
        }catch (Exception e){
            Log.i(TAG, "Unable to write order data to storage.");
        }

        orderPickSaves.add(new GsonOrderPickPickList(data));
        try{
            String jsonUser = mapper.writeValueAsString(orderPickSaves);
            sharedPreferences.edit().putString(storageName, jsonUser).apply();
        }catch (Exception e){
            Log.e(TAG, e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Observable<List<GsonOrderPickPickList>> getOrderPickData() {
        String jsonOrders = sharedPreferences.getString(storageName, "");
        try {
            tempGetResult = mapper.readValue(jsonOrders, new TypeReference<List<GsonOrderPickPickList>>(){});
            return Observable.just(tempGetResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
