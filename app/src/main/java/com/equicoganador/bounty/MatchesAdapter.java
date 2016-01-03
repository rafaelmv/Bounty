package com.equicoganador.bounty;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;

/**
 * Created by alex on 3/01/16.
 */
public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.ViewHolder> {

    List<String[]> matches;

    private void sendDismissScore(final String matchId, Context context){

        String url = new UrlClass().baseUrl + matchId;

        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            response.toString();
                        }catch (Exception e){

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("******Error", error.toString());
                        Log.d("******matchid", matchId);

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("match_id", matchId);
                params.put("score", "0");
                return params;
            }
        };
        queue.add(stringRequest);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pending_matches, parent, false);
        ViewHolder pvh = new ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final int current_position = position;

        final Context context = holder.matchUserImage.getContext();
        final String []current = matches.get(position);
        Picasso.with(context)
                .load(current[0])
                .resize(200, 200)
                .centerCrop()
                .into(holder.matchUserImage);
        holder.matchUsername.setText(current[1]);
        holder.matchUserMoney.setText(current[2]);

        holder.dismisMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDismissScore(current[3], context);

                matches.remove(current_position);
                notifyItemRemoved(current_position);
                notifyItemRangeChanged(current_position, matches.size());
            }
        });

        holder.acceptMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String theUserId = ((UserProfile)context).userId;

                Intent intent = new Intent(context, GameActivity.class);
                intent.putExtra("matchId", current[3]);
                intent.putExtra("username", current[1]);
                intent.putExtra("image", current[0]);
                intent.putExtra("theUser", theUserId);
                context.startActivity(intent);
            }
        });
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

        Button dismisMatch;
        Button acceptMatch;

        public ViewHolder(View itemView) {
            super(itemView);
            matchesCardView = (CardView)itemView.findViewById(R.id.matches_card_view);

            matchUserImage = (ImageView)itemView.findViewById(R.id.match_user_image);
            matchUsername = (TextView)itemView.findViewById(R.id.match_username);
            matchUserMoney = (TextView)itemView.findViewById(R.id.match_user_money);

            dismisMatch = (Button)itemView.findViewById(R.id.dismis_btn);
            acceptMatch = (Button)itemView.findViewById(R.id.acept_btn);

        }
    }

    MatchesAdapter(List<String[]> matches){
        this.matches = matches;
    }
}
