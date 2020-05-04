package nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickFragment.OrderPickProductsFragments;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.transition.Visibility;
import androidx.viewpager.widget.ViewPager;

import nl.brickx.brickxwms2020.R;
import nl.brickx.domain.Models.OrderPickPickListModel;

public class OrderPickProductFragment extends Fragment {
    ViewPagerFragmentAdapter adapter;
    ViewPager viewPager;
    TextView productNameText;
    TextView productNameTitleText;
    TextView locationText;
    TextView warehouseText;
    TextView warehouseTitleText;
    TextView amountToPickText;
    TextView amountToPickTitleText;
    TextView orderInfoTitle;
    ImageView imageView;
    ProgressBar loadingIcon;

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
        orderInfoTitle = view.findViewById(R.id.order_pick_title);
        productNameTitleText = view.findViewById(R.id.order_pick_product_name_text);
        warehouseTitleText = view.findViewById(R.id.order_pick_product_warehouse_text);
        amountToPickTitleText = view.findViewById(R.id.order_pick_product_amount_text);
        imageView = view.findViewById(R.id.order_pick_product_image);
        loadingIcon = view.findViewById(R.id.order_pick_product_loading_icon);

        Bundle args = getArguments();
        assert args != null;
        OrderPickPickListModel data = (OrderPickPickListModel)args.getSerializable("data");
        assert data != null;
        productNameText.setText(data.getProductName());
        locationText.setText(data.getProductLocation());
        warehouseText.setText(data.getWarehouseName());
        amountToPickText.setText(String.valueOf(data.getQuantityRequired()));

        if(data.getImageLoaded()){
            loadingIcon.setVisibility(View.INVISIBLE);
        }

        if(data.getImage() != null){
            imageView.setImageDrawable(data.getImage());
        }

        //If quantity met, make text green.
        if(data.getQuantityMet() || data.getQuantityRequired() == data.getScannedSerialNumbers().size() && data.getLocationScanned()){
            changeOrderTitle(getString(R.string.order_pick_info_title_done));
            data.setQuantityMet(true);
            amountToPickText.setTextColor(getContext().getResources().getColor(R.color.status_free));
        }

        //If location is scanned
        if(data.getLocationScanned()){
            locationText.setTextColor(getContext().getResources().getColor(R.color.status_free));
            productNameTitleText.setAlpha(1);
            amountToPickTitleText.setAlpha(1);
            warehouseTitleText.setAlpha(1);
            productNameText.setAlpha(1);
            warehouseText.setAlpha(1);
            amountToPickText.setAlpha(1);
            if(!data.getQuantityMet()){
                changeOrderTitle(getString(R.string.order_pick_info_title_scan_right_amount));
                amountToPickText.setTextColor(getContext().getResources().getColor(R.color.status_completed));
            }
        }else{
            locationText.setTextColor(getContext().getResources().getColor(R.color.status_completed));
            changeOrderTitle(getString(R.string.order_pick_info_title_scan_location));
        }
    }

    private void changeOrderTitle(String newTitle){
        orderInfoTitle.setText(String.valueOf(newTitle));
    }
}
