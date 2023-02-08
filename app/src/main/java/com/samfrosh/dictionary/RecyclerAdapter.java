package com.samfrosh.dictionary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.viewHolder> {

    private java.util.List<Words> List ;

    public RecyclerAdapter(List<Words> list) {
        List = list;
    }

    @NonNull
    @Override
    public RecyclerAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.search_design,parent,false);
       viewHolder viewHolder = new viewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.viewHolder holder, int position) {
        holder.meaning.setText(List.get(position).getDefination());
        holder.partOfSpeech.setText(List.get(position).getPartofspeech());
        holder.example.setText(List.get(position).getExamples());
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
TextView partOfSpeech,meaning,example;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            partOfSpeech = itemView.findViewById(R.id.partofspeech);
            meaning = itemView.findViewById(R.id.meaningofword);
            example = itemView.findViewById(R.id.examplemeaning);
        }
    }
}
