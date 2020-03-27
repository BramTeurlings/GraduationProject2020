package nl.brickx.brickxwms2020.Presentation.OrderPick;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import nl.brickx.domain.Models.OrderPickViewPagerModel;

public class ViewPagerFragmentAdapter extends FragmentStatePagerAdapter {

    //Todo: Insert data list here
    public OrderPickViewPagerModel data;

    public ViewPagerFragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public OrderPickViewPagerModel getData(int position){
        return new OrderPickViewPagerModel("ProductName" + String.valueOf(position), "Location" + String.valueOf(position), "Warehouse" + String.valueOf(position), (double)position);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        OrderPickProductFragment orderPickProductFragment = new OrderPickProductFragment();
        Bundle bundle = new Bundle();
        data = getData(position);
        bundle.putSerializable("data", data);
        orderPickProductFragment.setArguments(bundle);
        return orderPickProductFragment;
    }

    @Override
    public int getCount() {
        //Todo: Count data list
        return 6;
    }
}
