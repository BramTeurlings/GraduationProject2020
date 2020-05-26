package nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickFragment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.Objects;
import nl.brickx.brickxwms2020.R;
import static android.content.ContentValues.TAG;
import nl.brickx.domain.Models.OrderPickSerialStatusModel;


public class OrderPickStatusSerialNumbersAdapter extends RecyclerView.Adapter<OrderPickStatusSerialNumbersAdapter.InfoRecyclerViewholder>{

    private List<OrderPickSerialStatusModel> data;
    private Context context;
    private OrderPickFragmentContract.Presenter presenter;

    OrderPickStatusSerialNumbersAdapter(List<OrderPickSerialStatusModel> data) {
        this.data = data;
    }

    @Override
    public OrderPickStatusSerialNumbersAdapter.InfoRecyclerViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_pick_status_serial_number_recycler_item, parent, false);
        OrderPickStatusSerialNumbersAdapter.InfoRecyclerViewholder holder = new OrderPickStatusSerialNumbersAdapter.InfoRecyclerViewholder(view);
        this.context = parent.getContext();
        return holder;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderPickStatusSerialNumbersAdapter.InfoRecyclerViewholder holder, int position) {
        try{
            holder.serialnumberText.setText(String.valueOf(data.get(position).getSerialnumber()));
            if(data.get(position).getAvailible()){
                holder.serialnumberText.setTextColor(context.getResources().getColor(R.color.status_free));
            }else{
                holder.serialnumberText.setTextColor(context.getResources().getColor(R.color.status_completed));
            }

            holder.deleteButtonCardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(presenter != null) {
                        presenter.removeSerialnumber(data.get(position));
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
        CardView deleteButtonCardview;
        TextView serialnumberText;

        InfoRecyclerViewholder(final View infoView) {
            super(infoView);
            parentCardView = infoView.findViewById(R.id.order_pick_end_recycler_item_card);
            deleteButtonCardview = infoView.findViewById(R.id.order_pick_serial_numbers_recycler_item_delete);
            serialnumberText = infoView.findViewById(R.id.order_pick_serial_numbers_recycler_item_product_serial_number);
        }
    }

    public void setData(List<OrderPickSerialStatusModel> data) {
        this.data = data;
    }

    public void setPresenter(OrderPickFragmentContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
