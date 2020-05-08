package nl.brickx.brickxwms2020.Presentation.StockTransfer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

import javax.inject.Inject;

import nl.brickx.brickxwms2020.R;
import nl.brickx.domain.Models.ProductInfoRecyclerModel;

public class StockTransferAdapter extends RecyclerView.Adapter<StockTransferAdapter.InfoRecyclerViewholder> {

    private List<ProductInfoRecyclerModel> data;
    private RecyclerView recyclerView;
    private Context context;

    @Inject
    StockTransferContract.Navigator navigator;

    public StockTransferAdapter(List<ProductInfoRecyclerModel> data) {
        this.data = data;
    }

    @Override
    public StockTransferAdapter.InfoRecyclerViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.combined_info_recycler, parent, false);
        StockTransferAdapter.InfoRecyclerViewholder holder = new StockTransferAdapter.InfoRecyclerViewholder(view);
        this.context = parent.getContext();
        return holder;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        this.recyclerView = recyclerView;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull StockTransferAdapter.InfoRecyclerViewholder holder, int position) {
        //Todo: Make a good catch and multiple tries.
        try{
            //Make text bold:
            SpannableStringBuilder stringBuilder = new SpannableStringBuilder(context.getText(R.string.combined_property_content) + " " + data.get(position).getProperty());
            stringBuilder.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, context.getText(R.string.combined_property_content).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.propertyTextView.setText(stringBuilder);

            stringBuilder = new SpannableStringBuilder(context.getText(R.string.combined_property_value_content) + " " + data.get(position).getValue() + data.get(position).getUnit());
            stringBuilder.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, context.getText(R.string.combined_property_value_content).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.valueTextView.setText(stringBuilder);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class InfoRecyclerViewholder extends RecyclerView.ViewHolder {
        TextView propertyTextView;
        TextView valueTextView;
        MaterialCardView cardView;

        InfoRecyclerViewholder(final View infoView) {
            super(infoView);
            propertyTextView = infoView.findViewById(R.id.combined_property_recycler);
            valueTextView = infoView.findViewById(R.id.combined_property_recycler_value);
            cardView = infoView.findViewById(R.id.combined_property_item_card);
        }
    }

    public List<ProductInfoRecyclerModel> getData() {
        return data;
    }

    public void setData(List<ProductInfoRecyclerModel> data) {
        this.data = data;
    }
}
