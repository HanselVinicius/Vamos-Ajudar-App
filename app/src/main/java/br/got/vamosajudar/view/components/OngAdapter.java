package br.got.vamosajudar.view.components;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.got.vamosajudar.R;
import br.got.vamosajudar.model.ong.Ong;

public class OngAdapter extends RecyclerView.Adapter<OngAdapter.OngViewHolder> {

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

    }

    @Override
    public int getItemCount() {
        if (ongList == null) return 0;
        return this.ongList.size();
    }

    public static class OngViewHolder extends RecyclerView.ViewHolder {

        public OngViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}