package nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickFragment.OrderPickProductsFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import nl.brickx.domain.Models.OrderPickPickListModel;

public class ViewPagerFragmentAdapter extends FragmentStatePagerAdapter {

    //Todo: Insert data list here
    public List<OrderPickPickListModel> data = new ArrayList<>();
    private OrderPickProductFragment orderPickProductFragment;

    public ViewPagerFragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public void updateOrderState(OrderPickPickListModel data, int position){
        this.data.set(position, data);
        notifyDataSetChanged();
    }

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
