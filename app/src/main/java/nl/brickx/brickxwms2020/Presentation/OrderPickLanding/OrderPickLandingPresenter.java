package nl.brickx.brickxwms2020.Presentation.OrderPickLanding;

import android.content.Context;

import javax.inject.Inject;

import nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickFragment.OrderPickFragmentContract;
import nl.brickx.data.Dagger.DataContext;

public class OrderPickLandingPresenter implements OrderPickLandingContract.Presenter {

    Context context;

    @Inject
    public OrderPickLandingPresenter(@DataContext Context context){
        this.context = context;
    }

    @Override
    public void getOrdersToPick() {

    }
}
