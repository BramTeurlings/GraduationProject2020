package nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickFragment;

import android.content.Context;
import android.util.Log;

import javax.inject.Inject;

import nl.brickx.data.Dagger.DataContext;
import nl.brickx.domain.Models.OrderPickSerialStatusModel;

import static android.content.ContentValues.TAG;

public class OrderPickFragmentPresenter implements OrderPickFragmentContract.Presenter {

    Context context;

    @Inject
    OrderPickFragmentContract.View view;

    @Inject
    public OrderPickFragmentPresenter(@DataContext Context context){
        this.context = context;
    }

    @Override
    public void removeSerialnumber(OrderPickSerialStatusModel serialStatusModel) {
        try{
            view.getData().get(view.getCurrentViewPagerIndex()).getScannedSerialNumbers().remove(serialStatusModel.getSerialnumber());
            view.getData().get(view.getCurrentViewPagerIndex()).setQuantityPicked(view.getData().get(view.getCurrentViewPagerIndex()).getQuantityPicked() - 1);
            view.updateSerialnumbers(view.getData());
        }catch(Exception e){
            Log.i(TAG, "Unable to remove serialnumber.");
        }
    }

    @Override
    public void startPresenting() {

    }
}
