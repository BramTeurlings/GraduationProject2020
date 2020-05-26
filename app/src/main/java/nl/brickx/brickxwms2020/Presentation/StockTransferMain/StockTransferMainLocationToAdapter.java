package nl.brickx.brickxwms2020.Presentation.StockTransferMain;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;
import java.util.Objects;

import nl.brickx.brickxwms2020.R;
import nl.brickx.domain.Models.LocationInfoRecyclerModel;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class StockTransferMainLocationToAdapter extends RecyclerView.Adapter<StockTransferMainLocationToAdapter.InfoRecyclerViewholder>{

    private List<LocationInfoRecyclerModel> data;
    private LocationInfoRecyclerModel tempLocationModel;
    private String productScan;
    private RecyclerView recyclerView;


    public StockTransferMainLocationToAdapter(List<LocationInfoRecyclerModel> items) {
        data = items;
    }

    @Override
    public StockTransferMainLocationToAdapter.InfoRecyclerViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stock_transfer_to_location_recycler_item, parent, false);
        StockTransferMainLocationToAdapter.InfoRecyclerViewholder holder = new StockTransferMainLocationToAdapter.InfoRecyclerViewholder(view);
        return holder;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        this.recyclerView = recyclerView;
    }

    @Override
    public void onBindViewHolder(@NonNull StockTransferMainLocationToAdapter.InfoRecyclerViewholder holder, int position) {
        //Todo: Make a good catch and multiple tries.
        try {
            holder.locationTextView.setText(String.valueOf(data.get(position).getLocationTag()));
        }catch (Exception e){
            Log.e(TAG, Objects.requireNonNull(e.getLocalizedMessage()));
        }

        holder.deleteCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Todo: Highlight location
                data.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class InfoRecyclerViewholder extends RecyclerView.ViewHolder {
        MaterialCardView cardView;
        MaterialCardView deleteCard;
        TextView locationTextView;

        InfoRecyclerViewholder(final View infoView) {
            super(infoView);
            cardView = infoView.findViewById(R.id.stock_transfer_to_location_recycler_card);
            deleteCard = infoView.findViewById(R.id.stock_transfer_to_location_recycler_item_delete);
            locationTextView = infoView.findViewById(R.id.stock_transfer_recycler_item_location_content);
        }
    }

    public void setData(List<LocationInfoRecyclerModel> data, String productScan) {
        this.data = data;
        this.productScan = productScan;
    }

    public void addItem(LocationInfoRecyclerModel model){
        this.data.add(model);
        notifyDataSetChanged();
    }
}
