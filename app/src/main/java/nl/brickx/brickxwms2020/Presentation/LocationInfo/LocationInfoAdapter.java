package nl.brickx.brickxwms2020.Presentation.LocationInfo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import nl.brickx.brickxwms2020.R;
import nl.brickx.domain.Models.LocationInfoRecyclerModel;

public class LocationInfoAdapter extends RecyclerView.Adapter<LocationInfoAdapter.InfoRecyclerViewholder>{

    private List<LocationInfoRecyclerModel> data;
    private RecyclerView recyclerView;

    public LocationInfoAdapter(List<LocationInfoRecyclerModel> items) {
        data = items;
    }

    @Override
    public LocationInfoAdapter.InfoRecyclerViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_info_recycler_item, parent, false);
        LocationInfoAdapter.InfoRecyclerViewholder holder = new LocationInfoAdapter.InfoRecyclerViewholder(view);
        return holder;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        this.recyclerView = recyclerView;
    }

    @Override
    public void onBindViewHolder(@NonNull LocationInfoAdapter.InfoRecyclerViewholder holder, int position) {
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
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class InfoRecyclerViewholder extends RecyclerView.ViewHolder {
        TextView codeTextView;
        TextView descriptionTextView;
        TextView stockAmountTextView;
        TextView locationTextView;

        InfoRecyclerViewholder(final View infoView) {
            super(infoView);
            codeTextView = infoView.findViewById(R.id.location_info_code_content);
            locationTextView = infoView.findViewById(R.id.location_info_name_text);
            descriptionTextView = infoView.findViewById(R.id.location_info_name_content);
            stockAmountTextView = infoView.findViewById(R.id.location_info_stock_amount_content);
        }
    }

    public void setData(List<LocationInfoRecyclerModel> data) {
        this.data = data;
    }
}
