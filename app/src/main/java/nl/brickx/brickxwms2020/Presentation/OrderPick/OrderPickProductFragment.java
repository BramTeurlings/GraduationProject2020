package nl.brickx.brickxwms2020.Presentation.OrderPick;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import nl.brickx.brickxwms2020.R;
import nl.brickx.domain.Models.OrderPickViewPagerModel;

public class OrderPickProductFragment extends Fragment {
    ViewPagerFragmentAdapter adapter;
    ViewPager viewPager;
    TextView productNameText;
    TextView locationText;
    TextView warehouseText;
    TextView amountToPickText;
    ImageView imageView;

    public OrderPickProductFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.order_pick_view_pager_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        productNameText = view.findViewById(R.id.order_pick_product_name_content);
        locationText = view.findViewById(R.id.order_pick_product_location_content);
        warehouseText = view.findViewById(R.id.order_pick_product_warehouse_content);
        amountToPickText = view.findViewById(R.id.order_pick_product_amount_content);

        Bundle args = getArguments();
        assert args != null;
        OrderPickViewPagerModel data = (OrderPickViewPagerModel)args.getSerializable("data");
        assert data != null;
        productNameText.setText(data.getProductName());
        locationText.setText(data.getLocation());
        warehouseText.setText(data.getWarehouse());
        amountToPickText.setText(String.valueOf(data.getAmountToPick().intValue()));
    }
}
