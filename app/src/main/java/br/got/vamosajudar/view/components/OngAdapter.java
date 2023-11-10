package br.got.vamosajudar.view.components;

import static br.got.vamosajudar.view.activity.OngDetailActivity.IS_FROM_USER;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

import br.got.vamosajudar.R;
import br.got.vamosajudar.model.ong.Ong;
import br.got.vamosajudar.view.activity.LoginActivity;
import br.got.vamosajudar.view.activity.OngDetailActivity;

public class OngAdapter extends RecyclerView.Adapter<OngAdapter.OngViewHolder> {

    private static final String TAG = "ONG_ADAPTER";
    //talvez usar um dto???
    private final List<Ong> ongList;

    private final Context context;

    public final static String ONG = "ONG";




    public OngAdapter(List<Ong> ongList, Context context) {
        this.ongList = ongList;
        this.context = context;
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
                    .load(ong.getImageLink())
                    .into(holder.ongImageView);
            holder.ongDescription.setText(ong.getDescription());

            if (ong.getVerified()){
                holder.ongTitle.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.verified_star,0);
            }else {
                holder.ongTitle.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

            }
            onClicks(holder,ong);
    }

    private void onClicks(OngViewHolder holder,Ong ong){
        var it = new Intent(context, OngDetailActivity.class);
        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        it.putExtra(ONG,ong);
        it.putExtra(IS_FROM_USER,false);
        holder.itemView.setOnClickListener(l->{
            //abre a tela de detalhamento
            context.startActivity(it);

        });
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
