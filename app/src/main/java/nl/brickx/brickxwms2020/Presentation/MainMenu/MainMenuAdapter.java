package nl.brickx.brickxwms2020.Presentation.MainMenu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import nl.brickx.domain.Models.MainMenuRecyclerModel;
import java.util.List;

import nl.brickx.brickxwms2020.R;

public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuAdapter.InfoRecyclerViewholder> {
    private List<MainMenuRecyclerModel> data;

    public MainMenuAdapter(List<MainMenuRecyclerModel> items) {
        data = items;
    }

    @Override
    public MainMenuAdapter.InfoRecyclerViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_menu_recycler_item, parent, false);
        InfoRecyclerViewholder holder = new InfoRecyclerViewholder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull InfoRecyclerViewholder holder, int position) {
        //Todo: Make a good catch and multiple tries.
        try{
            holder.textView.setText(data.get(position).getTitle());
            holder.imageView.setImageDrawable(data.get(position).getImage());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class InfoRecyclerViewholder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView textView;
        ImageView imageView;

        InfoRecyclerViewholder(final View infoView) {
            super(infoView);
            textView = infoView.findViewById(R.id.main_menu_item_text);
            imageView = infoView.findViewById(R.id.main_menu_item_image);
        }
    }
}
