package nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickOverviewFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import nl.brickx.brickxwms2020.Presentation.OrderPick.OrderPickActivity;
import nl.brickx.brickxwms2020.Presentation.OrderPick.OrderPickActivityContract;
import nl.brickx.brickxwms2020.Presentation.OrderPickLanding.OrderPickLandingAdapter;
import nl.brickx.brickxwms2020.R;
import nl.brickx.data.Dagger.DataContext;
import nl.brickx.domain.Models.OrderPickPickListModel;

public class OrderPickOverviewFragment extends Fragment implements OrderPickOverviewFragmentContract.View {

    public List<OrderPickPickListModel> data;
    private RecyclerView orderItemRecycler;
    private OrderPickActivity parent;
    TextView totalArticlesPickedTextView;
    ProgressBar progressBar;
    OrderPickOverviewAdapter adapter;

    //Todo: Not pretty, fix better callback.
    public OrderPickOverviewFragment(OrderPickActivity parent) {
        this.parent = parent;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.order_pick_end_page, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if(progressBar == null){
            progressBar = view.findViewById(R.id.loadingIcon);
            progressBar.setVisibility(View.VISIBLE);
        }

        if(orderItemRecycler == null){
            orderItemRecycler = view.findViewById(R.id.order_pick_end_recyclerView);
        }

        if(totalArticlesPickedTextView == null){
            totalArticlesPickedTextView = view.findViewById(R.id.order_pick_end_totals_text);
        }

        data = new ArrayList<>();
        adapter = new OrderPickOverviewAdapter(data);
        orderItemRecycler.setAdapter(adapter);
        orderItemRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @SuppressLint("SetTextI18n")
    public void onPickListDataReceived(List<OrderPickPickListModel> data){
        this.data = data;
        totalArticlesPickedTextView.setText("0" + getContext().getString(R.string.order_pick_overview_amount_picked_splitter) + data.size() + " Artikelen");
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void changeLoadingState(Boolean isLoading) {
        if(progressBar != null){
            if(isLoading){
                progressBar.setVisibility(View.VISIBLE);
            }else{
                progressBar.setVisibility(View.GONE);
            }
        }
    }

    public void updateAdapterData(){
        adapter.setData(this.data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setErrorMessage(String message) {
        Snackbar.make(this.getView(), message, Snackbar.LENGTH_LONG)
                .setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        parent.getPresenter().getDataForFragments(OrderPickActivity.getOrderName());
                    }
                }).setDuration(20000).show();
    }

    public void setAdapterNavigator(OrderPickActivityContract.Navigator navigator){
        adapter.setNavigator(navigator);
    }
}
