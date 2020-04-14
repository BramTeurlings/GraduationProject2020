package nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickProductsFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import nl.brickx.domain.Models.OrderPickPickListModel;
import nl.brickx.domain.Models.OrderPickViewPagerModel;

public class ViewPagerFragmentAdapter extends FragmentStatePagerAdapter {

    //Todo: Insert data list here
    public List<OrderPickPickListModel> data = new ArrayList<>();
    private OrderPickProductFragment orderPickProductFragment;

    public ViewPagerFragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

//    public OrderPickViewPagerModel getData(int position){
//        OrderPickPickListModel tempModel = new OrderPickPickListModel();
//        tempModel = data.get(position);
//        ("ProductName" + String.valueOf(position), "Location" + String.valueOf(position), "Warehouse" + String.valueOf(position), (double)position);
//        tempModel.setProductName("ProductName"tempModel.getProductName());
//    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        orderPickProductFragment = new OrderPickProductFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", data.get(position));
        orderPickProductFragment.setArguments(bundle);
        return orderPickProductFragment;
    }

    @Override
    public int getCount() {
        //Todo: Count data list
        return data.size();
    }
}
