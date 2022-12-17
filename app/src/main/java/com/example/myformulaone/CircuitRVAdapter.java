package com.example.myformulaone;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class CircuitRVAdapter extends ListAdapter<CircuitModal, CircuitRVAdapter.ViewHolder> {
    //creating a variable for on item click listner.
    private OnItemClickListener listener;

    //creating a constructor class for our adapter class.
    CircuitRVAdapter() {
        super(DIFF_CALLBACK);
    }

    //creating a call back for item of recycler view.
    private static final DiffUtil.ItemCallback<CircuitModal> DIFF_CALLBACK = new DiffUtil.ItemCallback<CircuitModal>() {
        @Override
        public boolean areItemsTheSame(CircuitModal oldItem, CircuitModal newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(CircuitModal oldItem, CircuitModal newItem) {
            return oldItem.getCircuitName().equals(newItem.getCircuitName()) &&
                    oldItem.getCircuitLocation().equals(newItem.getCircuitLocation()) &&
                    oldItem.getCircuitLng().equals(newItem.getCircuitLng()) &&
                    oldItem.getCircuitLat().equals(newItem.getCircuitLat());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //below line is use to inflate our layout file for each item of our recycler view.
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.circuit_rv_item, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //below line of code is use to set data to each item of our recycler view.
        CircuitModal model = getCircuitAt(position);
        holder.circuitNameTV.setText(model.getCircuitName());
        holder.circuitLocationTV.setText(model.getCircuitLocation());
        holder.circuitLngTV.setText(model.getCircuitLng());
        holder.circuitLatTV.setText(model.getCircuitLat());
    }

    //creating a method to get course modal for a specific position.
    public CircuitModal getCircuitAt(int position) {
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //view holder class to create a variable for each view.
        TextView circuitNameTV, circuitLocationTV, circuitLngTV, circuitLatTV;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            //initializing each view of our recycler view.
            circuitNameTV = itemView.findViewById(R.id.idTVCircuitName);
            circuitLocationTV = itemView.findViewById(R.id.idTVCircuitLocation);
            circuitLngTV = itemView.findViewById(R.id.idTVCircuitLng);
            circuitLatTV = itemView.findViewById(R.id.idTVCircuitLat);
            //adding on click listner for each item of recycler view.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //inside on click listner we are passing position to our item of recycler view.
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(CircuitModal model);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
