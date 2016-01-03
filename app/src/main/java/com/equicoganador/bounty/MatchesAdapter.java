package com.equicoganador.bounty;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by alex on 3/01/16.
 */
public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.ViewHolder> {

    List<String[]> matches;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pending_matches, parent, false);
        ViewHolder pvh = new ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Context context = holder.matchUserImage.getContext();
        String []current = matches.get(position);
        Picasso.with(context)
                .load(current[0])
                .resize(200, 200)
                .centerCrop()
                .into(holder.matchUserImage);
        holder.matchUsername.setText(current[1]);
        holder.matchUserMoney.setText(current[2]);

    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView matchesCardView;
        ImageView matchUserImage;
        TextView matchUsername;
        TextView matchUserMoney;

        public ViewHolder(View itemView) {
            super(itemView);
            matchesCardView = (CardView)itemView.findViewById(R.id.matches_card_view);

            matchUserImage = (ImageView)itemView.findViewById(R.id.match_user_image);
            matchUsername = (TextView)itemView.findViewById(R.id.match_username);
            matchUserMoney = (TextView)itemView.findViewById(R.id.match_user_money);

        }
    }

    MatchesAdapter(List<String[]> matches){
        this.matches = matches;
    }
}
