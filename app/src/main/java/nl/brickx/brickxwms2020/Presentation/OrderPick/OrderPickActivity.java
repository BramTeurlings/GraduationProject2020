package nl.brickx.brickxwms2020.Presentation.OrderPick;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import dagger.android.support.DaggerAppCompatActivity;
import nl.brickx.brickxwms2020.Presentation.LocationInfo.LocationInfoActivity;
import nl.brickx.brickxwms2020.R;

public class OrderPickActivity extends DaggerAppCompatActivity {

    ViewPager imageViewPager;
    ViewPagerFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_pick_main_page);
        imageViewPager = findViewById(R.id.order_pick_viewpager);
        TabLayout tabLayout = findViewById(R.id.order_pick_tablayout_dots);
        tabLayout.setupWithViewPager(imageViewPager, true);
        adapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        imageViewPager.setAdapter(adapter);
    }

    public static Intent createIntent(Context context){
        return new Intent(context, OrderPickActivity.class);
    }
}
