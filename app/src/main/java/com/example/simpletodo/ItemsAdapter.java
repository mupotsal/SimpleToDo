package com.example.simpletodo;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    public interface OnLongClickListener{
        void onItemLongClicked(int position);
    }
    List<String> items;
    OnLongClickListener longClickListener;

    public ItemsAdapter(List<String> items,OnLongClickListener longClickListener) {
        this.items = items;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Use the layout inflate to inflate a view
        // Wrap it inside a view Holder and return it
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(todoView);
    }

    // responsible for binding data to a particular view holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Grabe the item at the position
        String item = items.get(position);
        // Bind the item into the specified view holder
        holder.bind(item);

    }

    @Override
    // This method tells the R View how many items are there.
    public int getItemCount() {
        return items.size();
    }



     //Container to provide easy access to views that represent each row of the List
  class ViewHolder extends RecyclerView.ViewHolder{
       TextView tvItem;
      public ViewHolder(@NonNull View itemView){
          super(itemView);
          tvItem = itemView.findViewById(android.R.id.text1);
      }

         public void bind(String item) {
          tvItem.setText(item);
          tvItem.setOnLongClickListener(new View.OnLongClickListener() {
              @Override
              public boolean onLongClick(View v) {
                  // remove the item from the recycler view
                  //notify the listener which item was long clicked
                  longClickListener.onItemLongClicked(getAdapterPosition());
                  return true;
              }
          });
         }
     }
}

