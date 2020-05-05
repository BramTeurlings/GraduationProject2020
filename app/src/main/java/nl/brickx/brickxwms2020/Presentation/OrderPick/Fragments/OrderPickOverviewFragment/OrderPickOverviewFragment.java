package nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickOverviewFragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import nl.brickx.brickxwms2020.Presentation.OrderPick.OrderPickActivity;
import nl.brickx.brickxwms2020.Presentation.OrderPick.OrderPickActivityContract;
import nl.brickx.brickxwms2020.Presentation.OrderPickLanding.OrderPickLandingAdapter;
import nl.brickx.brickxwms2020.R;
import nl.brickx.data.Dagger.DataContext;
import nl.brickx.domain.Models.OrderPickPickListModel;
import nl.brickx.domain.Users.UserDataManager;

public class OrderPickOverviewFragment extends DaggerFragment implements OrderPickOverviewFragmentContract.View {

    public List<OrderPickPickListModel> data;
    private RecyclerView orderItemRecycler;
    private OrderPickActivity parent;
    private OrderPickActivityContract.Navigator navigator;
    MaterialButton bottomButton;
    TextView totalArticlesPickedTextView;
    ProgressBar progressBar;
    OrderPickOverviewAdapter adapter;

    @Inject
    OrderPickOverviewFragmentContract.Presenter presenter;

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

        if(bottomButton == null){
            bottomButton = view.findViewById(R.id.order_pick_end_page_button);
        }

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Todo: Call close order pick.
                        presenter.closeOrderPick(data);
                        navigator.navigateToOrderPickLanding();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        DialogInterface.OnShowListener dialogOnShowListener = new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {

                Context context = getContext();
                Window view = ((AlertDialog)dialog).getWindow();

                view.setBackgroundDrawableResource(R.color.true_white);
                Button negButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_NEGATIVE);
                negButton.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                negButton.setTextColor(context.getResources().getColor(R.color.true_white));

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0,0,15,0);
                negButton.setLayoutParams(params);
            }
        };

        bottomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Todo: Handle closing order pick.
                if(bottomButton.getText().equals(getContext().getString(R.string.order_pick_end_button_end))){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("Weet je zeker dat je de orderpick wil afronden?").setPositiveButton("Ja", dialogClickListener)
                            .setNegativeButton("Nee", dialogClickListener);
                    AlertDialog dialog = builder.create();
                    dialog.setOnShowListener(dialogOnShowListener);
                    dialog.show();
                }else{
                    navigator.navigateToOrder(0);
                }
            }
        });

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

    public void updateBottomButtonText(){
        if(bottomButton.getText() != getString(R.string.order_pick_end_button_end)){
            bottomButton.setText(String.valueOf(getContext().getString(R.string.order_pick_end_button_end)));
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
        this.navigator = navigator;
        adapter.setNavigator(navigator);
    }
}
