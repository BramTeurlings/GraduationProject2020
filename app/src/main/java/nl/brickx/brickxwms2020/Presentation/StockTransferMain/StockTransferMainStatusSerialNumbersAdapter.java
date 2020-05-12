package nl.brickx.brickxwms2020.Presentation.StockTransferMain;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nl.brickx.brickxwms2020.Presentation.OrderPick.Fragments.OrderPickFragment.OrderPickFragmentContract;
import nl.brickx.brickxwms2020.R;
import nl.brickx.domain.Models.OrderPickSerialStatusModel;

public class StockTransferMainStatusSerialNumbersAdapter extends RecyclerView.Adapter<StockTransferMainStatusSerialNumbersAdapter.InfoRecyclerViewholder>{

    private List<OrderPickSerialStatusModel> data;
    private RecyclerView recyclerView;
    private Context context;
    private StockTransferMainContract.Presenter presenter;
    private Handler handler;

    StockTransferMainStatusSerialNumbersAdapter(List<OrderPickSerialStatusModel> data) {
        this.data = data;
    }

    @Override
    public StockTransferMainStatusSerialNumbersAdapter.InfoRecyclerViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_pick_status_serial_number_recycler_item, parent, false);
        StockTransferMainStatusSerialNumbersAdapter.InfoRecyclerViewholder holder = new StockTransferMainStatusSerialNumbersAdapter.InfoRecyclerViewholder(view);
        this.context = parent.getContext();
        return holder;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public void onBindViewHolder(@NonNull StockTransferMainStatusSerialNumbersAdapter.InfoRecyclerViewholder holder, int position) {
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
            e.printStackTrace();
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

    public void setPresenter(StockTransferMainContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
