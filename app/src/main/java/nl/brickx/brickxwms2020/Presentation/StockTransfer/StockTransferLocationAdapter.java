package nl.brickx.brickxwms2020.Presentation.StockTransfer;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import nl.brickx.brickxwms2020.R;
import nl.brickx.domain.Models.Gson.Serialnumbers.Serialnumbers;
import nl.brickx.domain.Models.LocationInfoRecyclerModel;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class StockTransferLocationAdapter extends RecyclerView.Adapter<StockTransferLocationAdapter.InfoRecyclerViewholder>{

    private List<LocationInfoRecyclerModel> data;
    private LocationInfoRecyclerModel tempLocationModel;
    private String productScan;
    private RecyclerView recyclerView;
    private StockTransferContract.Navigator navigator;
    private Context context;


    public StockTransferLocationAdapter(List<LocationInfoRecyclerModel> items, StockTransferContract.Navigator navigator, Context context) {
        data = items;
        this.navigator = navigator;
        this.context = context;
    }

    @Override
    public StockTransferLocationAdapter.InfoRecyclerViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_info_recycler_item, parent, false);
        return new StockTransferLocationAdapter.InfoRecyclerViewholder(view);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        this.recyclerView = recyclerView;
    }

    @Override
    public void onBindViewHolder(@NonNull StockTransferLocationAdapter.InfoRecyclerViewholder holder, int position) {
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

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(data.get(position).getSerialnumbersRequired()){
                    if(data.get(position).getAvailibleNumbers().size() > 0){
                        tempLocationModel = data.get(position);
                        tempLocationModel.setProductScan(productScan);
                        navigator.navigateToTransferScreen(tempLocationModel);
                    }else{
                        Toast.makeText(context, "Serienummers nog niet geladen, een moment aub.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    tempLocationModel = data.get(position);
                    tempLocationModel.setProductScan(productScan);
                    navigator.navigateToTransferScreen(tempLocationModel);
                }
            }
        });
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

    public void setSerialnumbers(int productId, int stockLocationId, List<String> serialnumbers){
        for(int i = 0; i < data.size(); i++){
            if(Objects.equals(stockLocationId, data.get(i).getStockLocationId())){
                data.get(i).setAvailibleNumbers(serialnumbers);
            }
        }
        this.notifyDataSetChanged();
    }
}
