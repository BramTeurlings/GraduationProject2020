package nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickFragment;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import nl.brickx.brickxwms2020.Presentation.Login.LoginContract;
import nl.brickx.brickxwms2020.R;
import nl.brickx.data.Dagger.DataContext;
import nl.brickx.domain.Models.AuthenticationResult;
import nl.brickx.domain.Models.Gson.ApiUserRightsEnum;
import nl.brickx.domain.Models.Gson.Authentication;
import nl.brickx.domain.Models.Gson.UserInfo.UserInfo;
import nl.brickx.domain.Models.OrderPickSerialStatusModel;
import nl.brickx.domain.Models.Permission;
import nl.brickx.domain.Models.User;
import nl.brickx.domain.Users.GetUserAuthenticationByApiKey;

import static android.content.ContentValues.TAG;

public class OrderPickFragmentPresenter implements OrderPickFragmentContract.Presenter {

    Context context;

    @Inject
    public OrderPickFragmentPresenter(@DataContext Context context){
        this.context = context;
    }

    @Override
    public void removeSerialnumber(OrderPickSerialStatusModel serialStatusModel) {

    }

    @Override
    public void startPresenting() {

    }
}
