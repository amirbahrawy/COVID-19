package com.example.android.covid_19.ui.world;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.android.covid_19.R;
import com.example.android.covid_19.model.WorldModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class WorldAdapter extends RecyclerView.Adapter<WorldAdapter.WorldViewHolder> implements Filterable {

    private List<WorldModel> wordList=new ArrayList<>();
    private List<WorldModel> wordListCopy=new ArrayList<>();


    public void setWorldList(List<WorldModel> worldList) {
        this.wordList = worldList;
        wordListCopy=new ArrayList<>(worldList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WorldViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new WorldViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WorldViewHolder holder, int position) {
       String flag=wordList.get(position).getCountryInfo().getFlag();
        Picasso.get().load(flag).into(holder.flag);
        holder.country.setText(wordList.get(position).getCountry());
        holder.confirmed.setText("Confirmed: "+wordList.get(position).getCases());
        holder.deaths.setText("Deaths: "+wordList.get(position).getDeaths());
        holder.recovered.setText("Recovered: "+wordList.get(position).getRecovered());
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    @Override
    public Filter getFilter() {
        return worldFilter;

    }
    private Filter worldFilter =new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<WorldModel> filterList=new ArrayList<>();
            if(constraint==null||constraint.length()==0)
                    filterList.addAll(wordListCopy);
            else{
                String filterPattren=constraint.toString().toLowerCase().trim();
                for(WorldModel worldModel:wordListCopy){
                    if(worldModel.getCountry().toLowerCase().contains(filterPattren))
                            filterList.add(worldModel);
                }
            }
            FilterResults filterResults=new FilterResults();
            filterResults.values=filterList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            wordList.clear();
            wordList.addAll((List)results.values);
            notifyDataSetChanged();
        }
    } ;
    public class WorldViewHolder extends RecyclerView.ViewHolder {
        CircleImageView flag;
        TextView country,confirmed,deaths,recovered;
        public WorldViewHolder(@NonNull View itemView) {
            super(itemView);
            flag=itemView.findViewById(R.id.flag);
            country=itemView.findViewById(R.id.country);
            confirmed=itemView.findViewById(R.id.confirmed);
            deaths=itemView.findViewById(R.id.deathes);
            recovered=itemView.findViewById(R.id.recovered);
        }
    }
}
