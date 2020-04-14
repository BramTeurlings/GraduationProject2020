package nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.DaggerFragment;
import nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickProductsFragments.ViewPagerFragmentAdapter;
import nl.brickx.brickxwms2020.R;
import nl.brickx.domain.Models.OrderPickPickListModel;

public class OrderPickFragment extends DaggerFragment implements OrderPickFragmentContract.View {

    public List<OrderPickPickListModel> data;
    ViewPager imageViewPager;
    ViewPagerFragmentAdapter adapter;
    TabLayout tabLayout;
    FloatingActionButton plusButton;
    ProgressBar progressBar;

    @Inject
    OrderPickFragmentContract.Presenter presenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.order_pick_main_page, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if(imageViewPager == null){
            imageViewPager = view.findViewById(R.id.order_pick_viewpager);
        }

        if(tabLayout == null) {
            tabLayout = view.findViewById(R.id.order_pick_tablayout_dots);
            tabLayout.setupWithViewPager(imageViewPager, true);
        }

        if(adapter == null){
            adapter = new ViewPagerFragmentAdapter(getFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            imageViewPager.setAdapter(adapter);
        }

        if(plusButton == null){
            plusButton = view.findViewById(R.id.order_pick_main_product_pick_plus_item_fab);

            plusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    data.get(imageViewPager.getCurrentItem()).setQuantityMet(true);
                    adapter.data = data;
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }

    public void onPickListDataReceived(List<OrderPickPickListModel> data){
        this.data = data;
        adapter.data = data;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void changeLoadingState(Boolean isLoading) {

    }

    @Override
    public void setErrorMessage(String message) {

    }
}
