package com.example.train1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private List<ListModel> listModels;
    private OnQuizListItemClicked onQuizListItemClicked;

    public ListAdapter(OnQuizListItemClicked onQuizListItemClicked){
        this.onQuizListItemClicked = onQuizListItemClicked;
    }

    public void setListModels(List<ListModel> listModels) {
        this.listModels = listModels;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_item, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.list_tittle.setText(listModels.get(position).getQuiz_name());
        holder.list_description.setText(listModels.get(position).getQuiz_description());
        holder.list_difficulty.setText(listModels.get(position).getQuiz_level());


        String imageUrl = listModels.get(position).getQuiz_image();

        Glide.with(holder.itemView.getContext())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.bg_load)
                .into(holder.list_image);

    }

    @Override
    public int getItemCount() {
        if (listModels == null) {
            return 0;
        }else {
            return listModels.size();
        }
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView list_image;
        TextView list_tittle;
        TextView list_description;
        TextView list_difficulty;
        Button list_button;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            list_image = itemView.findViewById(R.id.quiz_image);
            list_tittle = itemView.findViewById(R.id.quiz_name);
            list_description = itemView.findViewById(R.id.quiz_description);
            list_difficulty = itemView.findViewById(R.id.quiz_level);
            list_button = itemView.findViewById(R.id.button_play);

            list_button.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            onQuizListItemClicked.onItemClicked(getAdapterPosition());
        }

    }

    public interface OnQuizListItemClicked{
        void onItemClicked(int position);
    }
}
