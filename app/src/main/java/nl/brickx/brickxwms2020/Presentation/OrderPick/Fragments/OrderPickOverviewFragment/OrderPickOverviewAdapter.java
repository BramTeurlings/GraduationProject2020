package nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickOverviewFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import nl.brickx.brickxwms2020.Presentation.OrderPickLanding.OrderPickLandingContract;
import nl.brickx.brickxwms2020.R;
import nl.brickx.domain.Models.OrderPickLandingRecyclerModel;
import nl.brickx.domain.Models.OrderPickLandingStatus;
import nl.brickx.domain.Models.OrderPickPickListModel;

public class OrderPickOverviewAdapter extends RecyclerView.Adapter<OrderPickOverviewAdapter.InfoRecyclerViewholder>{

    private List<OrderPickPickListModel> data;
    private RecyclerView recyclerView;
    private Context context;
    private OrderPickLandingContract.Navigator navigator;

    OrderPickOverviewAdapter(List<OrderPickPickListModel> data, OrderPickLandingContract.Navigator navigator) {
        this.data = data;
        this.navigator = navigator;
    }

    @Override
    public OrderPickOverviewAdapter.InfoRecyclerViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_pick_landing_page_recycler_item, parent, false);
        OrderPickOverviewAdapter.InfoRecyclerViewholder holder = new OrderPickOverviewAdapter.InfoRecyclerViewholder(view);
        this.context = parent.getContext();
        return holder;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderPickOverviewAdapter.InfoRecyclerViewholder holder, int position) {
        //Todo: Make a good catch and multiple tries.
        try{
            holder.orderLineProductNameTextView.setText(data.get(position).getProductName());
            holder.orderLineProductSKUTextView.setText(data.get(position).getProductSku());
            String amountText = "0 / 0";
            amountText = data.get(position).getQuantityPicked() + context.getString(R.string.order_pick_overview_amount_picked_splitter) + data.get(position).getQuantityRequired();
            holder.orderLineAmountPickedTextView.setText(amountText);
            if(data.get(position).getQuantityRequired() == data.get(position).getQuantityPicked() && data.get(position).getLocationScanned()){
                holder.orderLineStatusImage.setBackgroundColor(context.getResources().getColor(R.color.status_free));
            }else{
                holder.orderLineStatusImage.setBackgroundColor(context.getResources().getColor(R.color.status_completed));
            }

            holder.parentCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("CLICK! - " + position);
                    //Todo: Navigate to main fragment with this item on top.
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class InfoRecyclerViewholder extends RecyclerView.ViewHolder {
        CardView parentCardView;
        ImageView orderLineStatusImage;
        TextView orderLineProductNameTextView;
        TextView orderLineProductSKUTextView;
        TextView orderLineAmountPickedTextView;

        InfoRecyclerViewholder(final View infoView) {
            super(infoView);
            parentCardView = infoView.findViewById(R.id.order_pick_end_recycler_item_card);
            orderLineStatusImage = infoView.findViewById(R.id.order_pick_end_status_icon);
            orderLineProductNameTextView = infoView.findViewById(R.id.order_pick_end_recycler_item_product_name_text);
            orderLineProductSKUTextView = infoView.findViewById(R.id.order_pick_end_recycler_item_product_sku_text);
            orderLineAmountPickedTextView = infoView.findViewById(R.id.order_pick_end_recycler_item_amount_text);
        }
    }

    public void setData(List<OrderPickPickListModel> data) {
        this.data = data;
    }
}
