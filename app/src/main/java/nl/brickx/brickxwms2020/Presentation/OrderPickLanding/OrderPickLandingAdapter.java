package nl.brickx.brickxwms2020.Presentation.OrderPickLanding;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

import nl.brickx.brickxwms2020.R;
import nl.brickx.domain.Models.OrderPickLandingRecyclerModel;
import nl.brickx.domain.Models.OrderPickLandingStatus;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class OrderPickLandingAdapter extends RecyclerView.Adapter<OrderPickLandingAdapter.InfoRecyclerViewholder>{

    private List<OrderPickLandingRecyclerModel> data;
    private RecyclerView recyclerView;
    private Context context;
    private OrderPickLandingContract.Navigator navigator;

    OrderPickLandingAdapter(List<OrderPickLandingRecyclerModel> data, OrderPickLandingContract.Navigator navigator) {
        this.data = data;
        this.navigator = navigator;
    }

    @Override
    public OrderPickLandingAdapter.InfoRecyclerViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_pick_landing_page_recycler_item, parent, false);
        OrderPickLandingAdapter.InfoRecyclerViewholder holder = new OrderPickLandingAdapter.InfoRecyclerViewholder(view);
        this.context = parent.getContext();
        return holder;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderPickLandingAdapter.InfoRecyclerViewholder holder, int position) {
        //Todo: Make a good catch and multiple tries.
        try{
            holder.orderNameTextView.setText(data.get(position).getOrderName());
            DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
            holder.orderDateTextView.setText(df.format(data.get(position).getOrderDate()));

            switch (data.get(position).getOrderStatus()){
                case FREE:
                    holder.orderStatusTextView.setTextColor(context.getResources().getColor(R.color.status_free));
                    holder.orderStatusTextView.setText(R.string.order_pick_status_free);
                    break;
                case IN_PROGRESS:
                    holder.orderStatusTextView.setTextColor(context.getResources().getColor(R.color.status_in_progress));
                    holder.orderStatusTextView.setText(R.string.order_pick_status_busy);
                    break;
                case ON_HOLD:
                    holder.orderStatusTextView.setTextColor(context.getResources().getColor(R.color.status_on_hold));
                    holder.orderStatusTextView.setText(R.string.order_pick_status_on_hold);
                    break;
                case COMPLETED:
                    holder.orderStatusTextView.setTextColor(context.getResources().getColor(R.color.status_completed));
                    holder.orderStatusTextView.setText(R.string.order_pick_status_completed);
                    break;
            }

            holder.parentCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("CLICK! - " + position);
                    if(data.get(position).getOrderStatus() != OrderPickLandingStatus.COMPLETED || data.get(position).getOrderStatus() != OrderPickLandingStatus.IN_PROGRESS){
                        navigator.navigateToOrder(context, data.get(position).getOrderName());
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
        TextView orderNameTextView;
        TextView orderDateTextView;
        TextView orderStatusTextView;

        InfoRecyclerViewholder(final View infoView) {
            super(infoView);
            parentCardView = infoView.findViewById(R.id.order_pick_landing_recycler_item_card);
            orderNameTextView = infoView.findViewById(R.id.order_pick_landing_recycler_item_order_number);
            orderDateTextView = infoView.findViewById(R.id.order_pick_landing_recycler_item_date);
            orderStatusTextView = infoView.findViewById(R.id.order_pick_landing_recycler_item_image);
        }
    }

    public void setData(List<OrderPickLandingRecyclerModel> data) {
        this.data = data;
    }
}
