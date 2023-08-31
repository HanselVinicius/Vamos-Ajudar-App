package br.got.vamosajudar.view.components;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.got.vamosajudar.R;
import br.got.vamosajudar.model.ong.Ong;

public class OngAdapter extends RecyclerView.Adapter<OngAdapter.OngViewHolder> {

    private static final String TAG = "ONG_ADAPTER";
    //talvez usar um dto???
    private final List<Ong> ongList;

    public OngAdapter(List<Ong> ongList) {

        this.ongList = ongList;
    }

    @NonNull
    @Override
    public OngViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_ong,parent,false);
        return new OngViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OngViewHolder holder, int position) {
            Ong ong = ongList.get(position);
            holder.ongTitle.setText(ong.getName());
            Picasso.get()
                    .load(ong.getImage())
                    .into(holder.ongImageView);
            holder.ongDescription.setText(ong.getDescription());
    }

    @Override
    public int getItemCount() {
        if (ongList == null) return 0;
        return this.ongList.size();
    }

    public static class OngViewHolder extends RecyclerView.ViewHolder {

        public ImageView ongImageView;
        public TextView ongTitle;
        public TextView ongDescription;

        public OngViewHolder(@NonNull View itemView) {
            super(itemView);
            this.ongImageView = itemView.findViewById(R.id.ongImageView);
            this.ongTitle = itemView.findViewById(R.id.ongTitleTextView);
            this.ongDescription = itemView.findViewById(R.id.ongDescriptionTextView);
        }
    }
}
