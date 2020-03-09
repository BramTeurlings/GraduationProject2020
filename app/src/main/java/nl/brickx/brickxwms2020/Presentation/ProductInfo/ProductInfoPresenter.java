package nl.brickx.brickxwms2020.Presentation.ProductInfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;
import nl.brickx.domain.Models.ProductInfoHolder;
import nl.brickx.domain.Models.User;
import nl.brickx.domain.Product.Info.GetProductInfoByScan;
import nl.brickx.domain.Users.UserDataManager;

public class ProductInfoPresenter implements ProductInfoContract.Presenter {

    private MediatorLiveData<ProductInfoHolder> productInfo = new MediatorLiveData<>();

    private UserDataManager userDataManager;
    private GetProductInfoByScan getProductInfoByScan;

    @Inject
    ProductInfoPresenter(UserDataManager userDataManager, GetProductInfoByScan getProductInfoByScan){
        this.userDataManager = userDataManager;
        this.getProductInfoByScan = getProductInfoByScan;
    }

    @Override
    public void getProductInfoByScan(String scan) {
        try{

            final LiveData<ProductInfoHolder> source = LiveDataReactiveStreams.fromPublisher(getProductInfoByScan.invoke(scan, getUserData().getApiKey()).subscribeOn(Schedulers.io()));
            productInfo.addSource(source, new Observer<ProductInfoHolder>() {
                @Override
                public void onChanged(ProductInfoHolder productInfoHolder) {
                    productInfo.setValue(productInfoHolder);
                    productInfo.removeSource(source);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public User getUserData() {
        return userDataManager.GetUserData();
    }

    @Override
    public LiveData<ProductInfoHolder> observeProductInfo() {
        return productInfo;
    }
}
