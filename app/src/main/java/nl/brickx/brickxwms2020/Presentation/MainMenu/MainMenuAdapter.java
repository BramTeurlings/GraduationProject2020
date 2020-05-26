package nl.brickx.brickxwms2020.Presentation.MainMenu;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;
import nl.brickx.domain.Models.MainMenuRecyclerModel;

import java.util.List;
import java.util.Objects;

import nl.brickx.brickxwms2020.R;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuAdapter.InfoRecyclerViewholder> {

    MainMenuContract.Navigator navigator;

    private List<MainMenuRecyclerModel> data;
    private RecyclerView recyclerView;

    public MainMenuAdapter(List<MainMenuRecyclerModel> items, MainMenuContract.Navigator navigator) {
        data = items;
        this.navigator = navigator;
    }

    @Override
    public MainMenuAdapter.InfoRecyclerViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_menu_recycler_item, parent, false);
        return new InfoRecyclerViewholder(view);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        this.recyclerView = recyclerView;
    }

    @Override
    public void onBindViewHolder(@NonNull InfoRecyclerViewholder holder, int position) {
        try{
            holder.textView.setText(data.get(position).getTitle());
            holder.imageView.setImageDrawable(data.get(position).getImage());
            holder.primaryCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch(data.get(position).getIdentifier()){
                        case PRODUCT_INFO_ITEM:
                            navigator.navigateToProductInfo();
                            break;
                        case LOCATION_INFO_ITEM:
                            navigator.navigateToLocationInfo();
                            break;
                        case ORDER_PICK_ITEM:
                            navigator.navigateToOrderPickScreen();
                            break;
                        case STOCK_TRANSFER_ITEM:
                            navigator.navigateToStockTransfer();
                            break;
                        case STOCK_MUTATION_ITEM:
                            navigator.navigateToStockMutation();
                            break;
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
        TextView textView;
        ImageView imageView;
        MaterialCardView primaryCard;

        InfoRecyclerViewholder(final View infoView) {
            super(infoView);
            textView = infoView.findViewById(R.id.main_menu_item_text);
            imageView = infoView.findViewById(R.id.main_menu_item_image);
            primaryCard = infoView.findViewById(R.id.location_recycler_card);
        }
    }
}
