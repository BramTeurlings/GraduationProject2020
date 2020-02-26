package nl.brickx.brickxwms2020.Presentation.MainMenu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import nl.brickx.domain.Models.MainMenuRecyclerModel;
import nl.brickx.domain.Models.MenuItemIdentifier;
import java.util.List;

import javax.inject.Inject;

import nl.brickx.brickxwms2020.R;

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
        InfoRecyclerViewholder holder = new InfoRecyclerViewholder(view);
        return holder;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        this.recyclerView = recyclerView;
    }

    @Override
    public void onBindViewHolder(@NonNull InfoRecyclerViewholder holder, int position) {
        //Todo: Make a good catch and multiple tries.
        try{
            holder.textView.setText(data.get(position).getTitle());
            holder.imageView.setImageDrawable(data.get(position).getImage());
            holder.primaryCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("CLICK! - " + position);
                    if(data.get(position).getIdentifier() == MenuItemIdentifier.PRODUCT_INFO_ITEM){
                        //Todo: Fix injection.
                        navigator.navigateToProductInfo();
                    }else if(data.get(position).getIdentifier() == MenuItemIdentifier.LOCATION_INFO_ITEM){
                        navigator.navigateToLocationInfo();
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
        TextView textView;
        ImageView imageView;
        MaterialCardView primaryCard;

        InfoRecyclerViewholder(final View infoView) {
            super(infoView);
            textView = infoView.findViewById(R.id.main_menu_item_text);
            imageView = infoView.findViewById(R.id.main_menu_item_image);
            primaryCard = infoView.findViewById(R.id.main_menu_item_card);
        }
    }
}
