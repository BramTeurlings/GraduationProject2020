package nl.brickx.brickxwms2020.Presentation.OrderPickLanding;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import nl.brickx.brickxwms2020.Presentation.MainMenu.MainMenuContract;
import nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickFragment.OrderPickFragment;
import nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickOverviewFragment.OrderPickOverviewFragment;
import nl.brickx.brickxwms2020.R;
import nl.brickx.domain.Models.OrderPickLandingRecyclerModel;
import nl.brickx.domain.Models.OrderPickLandingStatus;

public class OrderPickLandingActivity extends DaggerAppCompatActivity {

    private RecyclerView orderRecycler;

    @Inject
    OrderPickLandingContract.Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_pick_landing_page);

        orderRecycler = findViewById(R.id.order_pick_landing_recycler_view);

        //Todo: Remove mock code.
        List<OrderPickLandingRecyclerModel> data = new ArrayList<>();
        data.add(new OrderPickLandingRecyclerModel("asd", "Order 1", OrderPickLandingStatus.FREE));
        data.add(new OrderPickLandingRecyclerModel("asf", "Order 2", OrderPickLandingStatus.IN_PROGRESS));
        data.add(new OrderPickLandingRecyclerModel("asg", "Order 3", OrderPickLandingStatus.FREE));
        data.add(new OrderPickLandingRecyclerModel("ash", "Order 4", OrderPickLandingStatus.COMPLETED));
        data.add(new OrderPickLandingRecyclerModel("asj", "Order 5", OrderPickLandingStatus.ON_HOLD));
        data.add(new OrderPickLandingRecyclerModel("ask", "Order 6", OrderPickLandingStatus.ON_HOLD));

        OrderPickLandingAdapter adapter = new OrderPickLandingAdapter(data, navigator);
        orderRecycler.setAdapter(adapter);
        orderRecycler.setLayoutManager(new LinearLayoutManager(this));
    }

    public static Intent createIntent(Context context){
        return new Intent(context, OrderPickLandingActivity.class);
    }
}
