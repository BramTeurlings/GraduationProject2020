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

public class StockTransferMainLocationFromAdapter extends RecyclerView.Adapter<StockTransferMainLocationFromAdapter.InfoRecyclerViewholder>{

    private List<LocationInfoRecyclerModel> data;
    private LocationInfoRecyclerModel tempLocationModel;
    private String productScan;
    private RecyclerView recyclerView;


    public StockTransferMainLocationFromAdapter(List<LocationInfoRecyclerModel> items) {
        data = items;
    }

    @Override
    public StockTransferMainLocationFromAdapter.InfoRecyclerViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_info_recycler_item, parent, false);
        StockTransferMainLocationFromAdapter.InfoRecyclerViewholder holder = new StockTransferMainLocationFromAdapter.InfoRecyclerViewholder(view);
        return holder;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        this.recyclerView = recyclerView;
    }

    @Override
    public void onBindViewHolder(@NonNull StockTransferMainLocationFromAdapter.InfoRecyclerViewholder holder, int position) {
        //Todo: Make a good catch and multiple tries.
        try{
            holder.codeTextView.setText(data.get(position).getWarehouseName());
            holder.stockAmountTextView.setText(data.get(position).getProductStock().toString());
            if(data.get(position).getProductName() != null){
                holder.locationTextView.setText(R.string.location_info_product_text);
                holder.descriptionTextView.setText(data.get(position).getProductName());
            }else{
                holder.descriptionTextView.setText(data.get(position).getLocation());
            }
        }catch (Exception e){
            Log.e(TAG, Objects.requireNonNull(e.getLocalizedMessage()));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class InfoRecyclerViewholder extends RecyclerView.ViewHolder {
        MaterialCardView cardView;
        TextView codeTextView;
        TextView descriptionTextView;
        TextView stockAmountTextView;
        TextView locationTextView;

        InfoRecyclerViewholder(final View infoView) {
            super(infoView);
            cardView = infoView.findViewById(R.id.location_recycler_card);
            codeTextView = infoView.findViewById(R.id.location_info_code_content);
            locationTextView = infoView.findViewById(R.id.location_info_name_text);
            descriptionTextView = infoView.findViewById(R.id.location_info_name_content);
            stockAmountTextView = infoView.findViewById(R.id.location_info_stock_amount_content);
        }
    }

    public void setData(List<LocationInfoRecyclerModel> data, String productScan) {
        this.data = data;
        this.productScan = productScan;
    }
}
