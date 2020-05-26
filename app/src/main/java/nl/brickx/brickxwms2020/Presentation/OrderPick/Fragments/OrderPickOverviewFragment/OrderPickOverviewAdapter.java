package nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickOverviewFragment;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.Objects;

import nl.brickx.brickxwms2020.Presentation.OrderPick.OrderPickActivityContract;
import nl.brickx.brickxwms2020.R;
import nl.brickx.domain.Models.OrderPickPickListModel;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class OrderPickOverviewAdapter extends RecyclerView.Adapter<OrderPickOverviewAdapter.InfoRecyclerViewholder>{

    private List<OrderPickPickListModel> data;
    private RecyclerView recyclerView;
    private Context context;
    private OrderPickActivityContract.Navigator navigator;
    private Handler handler;

    OrderPickOverviewAdapter(List<OrderPickPickListModel> data) {
        this.data = data;
    }

    @Override
    public OrderPickOverviewAdapter.InfoRecyclerViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_pick_end_page_recycler_item, parent, false);
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
        try{
            holder.orderLineProductNameTextView.setText(data.get(position).getProductName());
            holder.orderLineProductSKUTextView.setText(data.get(position).getProductSku());
            String amountText = "0 / 0";
            amountText = data.get(position).getQuantityPicked() + context.getString(R.string.order_pick_overview_amount_picked_splitter) + data.get(position).getQuantityRequired();
            holder.orderLineAmountPickedTextView.setText(amountText);
            if(data.get(position).getQuantityRequired() == data.get(position).getQuantityPicked() || data.get(position).getQuantityRequired() == data.get(position).getScannedSerialNumbers().size() && data.get(position).getLocationScanned()){
                holder.statusCardView.setBackgroundColor(context.getResources().getColor(R.color.status_free_transparent));
            }else{
                holder.statusCardView.setBackgroundColor(context.getResources().getColor(R.color.status_completed_transparent));
            }

            holder.parentCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Todo: Navigate to main fragment with this item on top. I don't like the way this works yet, execution is slow.
                    if(navigator != null) {
                        navigator.navigateToOrder(position);
                    }
                }
            });
        }catch (Exception e){
            Log.e(TAG, Objects.requireNonNull(e.getLocalizedMessage()));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class InfoRecyclerViewholder extends RecyclerView.ViewHolder {
        CardView parentCardView;
        CardView statusCardView;
        ImageView orderLineStatusImage;
        TextView orderLineProductNameTextView;
        TextView orderLineProductSKUTextView;
        TextView orderLineAmountPickedTextView;

        InfoRecyclerViewholder(final View infoView) {
            super(infoView);
            parentCardView = infoView.findViewById(R.id.order_pick_end_recycler_item_card);
            statusCardView = infoView.findViewById(R.id.order_pick_end_recycler_item_tertiary_card);
            orderLineStatusImage = infoView.findViewById(R.id.order_pick_end_status_icon);
            orderLineProductNameTextView = infoView.findViewById(R.id.order_pick_end_recycler_item_product_name_text);
            orderLineProductSKUTextView = infoView.findViewById(R.id.order_pick_end_recycler_item_product_sku_text);
            orderLineAmountPickedTextView = infoView.findViewById(R.id.order_pick_end_recycler_item_amount_text);
        }
    }

    public void setData(List<OrderPickPickListModel> data) {
        this.data = data;
    }

    public void setNavigator(OrderPickActivityContract.Navigator navigator) {
        this.navigator = navigator;
    }
}
