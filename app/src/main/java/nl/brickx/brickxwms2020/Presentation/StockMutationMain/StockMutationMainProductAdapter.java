package nl.brickx.brickxwms2020.Presentation.StockMutationMain;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

import nl.brickx.brickxwms2020.R;
import nl.brickx.domain.Models.LocationInfoRecyclerModel;

public class StockMutationMainProductAdapter extends RecyclerView.Adapter<StockMutationMainProductAdapter.InfoRecyclerViewholder>{

    private List<LocationInfoRecyclerModel> data;
    private LocationInfoRecyclerModel tempLocationModel;
    private String productScan;
    private RecyclerView recyclerView;


    public StockMutationMainProductAdapter(List<LocationInfoRecyclerModel> items) {
        data = items;
    }

    @Override
    public StockMutationMainProductAdapter.InfoRecyclerViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stock_mutation_product_recycler_item, parent, false);
        StockMutationMainProductAdapter.InfoRecyclerViewholder holder = new StockMutationMainProductAdapter.InfoRecyclerViewholder(view);
        return holder;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        this.recyclerView = recyclerView;
    }

    @Override
    public void onBindViewHolder(@NonNull StockMutationMainProductAdapter.InfoRecyclerViewholder holder, int position) {
        //Todo: Make a good catch and multiple tries.
        try {
            holder.productTextView.setText(String.valueOf(data.get(position).getProductName()));
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            holder.SKUTextView.setText(String.valueOf(data.get(position).getProductSKU()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class InfoRecyclerViewholder extends RecyclerView.ViewHolder {
        MaterialCardView cardView;
        TextView productTextView;
        TextView SKUTextView;

        InfoRecyclerViewholder(final View infoView) {
            super(infoView);
            cardView = infoView.findViewById(R.id.location_recycler_card);
            productTextView = infoView.findViewById(R.id.stock_mutation_product_content);
            SKUTextView = infoView.findViewById(R.id.stock_mutation_sku_content);
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
