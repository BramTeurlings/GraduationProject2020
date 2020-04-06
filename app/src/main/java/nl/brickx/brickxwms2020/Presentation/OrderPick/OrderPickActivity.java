package nl.brickx.brickxwms2020.Presentation.OrderPick;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import dagger.android.support.DaggerAppCompatActivity;
import nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickFragment.OrderPickFragment;
import nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickOverviewFragment.OrderPickOverviewFragment;
import nl.brickx.brickxwms2020.R;

public class OrderPickActivity extends DaggerAppCompatActivity {

    BottomNavigationView bottomNavigationView;
    final OrderPickFragment orderPickFragment = new OrderPickFragment();
    final OrderPickOverviewFragment orderPickOverviewFragment = new OrderPickOverviewFragment();
    Fragment active = orderPickFragment;

    //Todo: put in presenter.
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    try{
                        switch (item.getItemId()){
                            case R.id.navigation_main:
                                getSupportFragmentManager().beginTransaction().hide(active).show(orderPickFragment).commit();
                                active = orderPickFragment;
                                break;
                            case R.id.navigation_end:
                                getSupportFragmentManager().beginTransaction().hide(active).show(orderPickOverviewFragment).commit();
                                active = orderPickOverviewFragment;
                                break;
                        }
                    }catch (NullPointerException e){
                        return false;
                    }
                    return true;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_pick_bottom_nav);
        bottomNavigationView = findViewById(R.id.order_pick_main_bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().add(R.id.order_pick_fragment_container, orderPickOverviewFragment).hide(orderPickOverviewFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.order_pick_fragment_container, orderPickFragment).commit();
    }

    public static Intent createIntent(Context context, String orderId){
        Intent orderPickIntent = new Intent(context, OrderPickActivity.class);
        orderPickIntent.putExtra("order_id", orderId);
        return orderPickIntent;
    }
}
