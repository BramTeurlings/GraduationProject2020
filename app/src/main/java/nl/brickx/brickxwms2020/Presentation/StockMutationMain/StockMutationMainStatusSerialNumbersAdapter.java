package nl.brickx.brickxwms2020.Presentation.StockMutationMain;

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

import nl.brickx.brickxwms2020.R;
import nl.brickx.domain.Models.OrderPickSerialStatusModel;

public class StockMutationMainStatusSerialNumbersAdapter extends RecyclerView.Adapter<StockMutationMainStatusSerialNumbersAdapter.InfoRecyclerViewholder>{

    private List<OrderPickSerialStatusModel> data;
    private RecyclerView recyclerView;
    private Context context;
    private StockMutationMainContract.Presenter presenter;
    private Handler handler;

    StockMutationMainStatusSerialNumbersAdapter(List<OrderPickSerialStatusModel> data) {
        this.data = data;
    }

    @Override
    public StockMutationMainStatusSerialNumbersAdapter.InfoRecyclerViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_pick_status_serial_number_recycler_item, parent, false);
        StockMutationMainStatusSerialNumbersAdapter.InfoRecyclerViewholder holder = new StockMutationMainStatusSerialNumbersAdapter.InfoRecyclerViewholder(view);
        this.context = parent.getContext();
        return holder;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public void onBindViewHolder(@NonNull StockMutationMainStatusSerialNumbersAdapter.InfoRecyclerViewholder holder, int position) {
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
                        presenter.updateSerialAmountText();
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

    public void setPresenter(StockMutationMainContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
