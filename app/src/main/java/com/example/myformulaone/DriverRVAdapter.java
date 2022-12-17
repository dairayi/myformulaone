package com.example.myformulaone;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class DriverRVAdapter extends ListAdapter<DriverModal, DriverRVAdapter.ViewHolder> {
    //creating a variable for on item click listner.
    private OnItemClickListener listener;

    //creating a constructor class for our adapter class.
    DriverRVAdapter() {
        super(DIFF_CALLBACK);
    }

    //creating a call back for item of recycler view.
    private static final DiffUtil.ItemCallback<DriverModal> DIFF_CALLBACK = new DiffUtil.ItemCallback<DriverModal>() {
        @Override
        public boolean areItemsTheSame(DriverModal oldItem, DriverModal newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(DriverModal oldItem, DriverModal newItem) {
            //below line is to check the course name, description and course duration.
            return oldItem.getDriverCode().equals(newItem.getDriverCode()) &&
                    oldItem.getDriverNumber().equals(newItem.getDriverNumber()) &&
                    oldItem.getDriverName().equals(newItem.getDriverName()) &&
                    oldItem.getDriverNationality().equals(newItem.getDriverNationality()) &&
                    oldItem.getDriverTeam().equals(newItem.getDriverTeam());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //below line is use to inflate our layout file for each item of our recycler view.
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.driver_rv_item, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //below line of code is use to set data to each item of our recycler view.
        DriverModal model = getDriverAt(position);
        holder.driverCodeTV.setText(model.getDriverCode());
        holder.driverNumberTV.setText(model.getDriverNumber());
        holder.driverNameTV.setText(model.getDriverName());
        holder.driverNationalityTV.setText(model.getDriverNationality());
        holder.driverTeamTV.setText(model.getDriverTeam());
    }

    //creating a method to get course modal for a specific position.
    public DriverModal getDriverAt(int position) {
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //view holder class to create a variable for each view.
        TextView driverCodeTV, driverNumberTV, driverNameTV, driverNationalityTV, driverTeamTV;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            //initializing each view of our recycler view.
            driverCodeTV = itemView.findViewById(R.id.idTVDriverCode);
            driverNumberTV = itemView.findViewById(R.id.idTVDriverNumber);
            driverNameTV = itemView.findViewById(R.id.idTVDriverName);
            driverNationalityTV = itemView.findViewById(R.id.idTVDriverNationality);
            driverTeamTV = itemView.findViewById(R.id.idTVDriverTeam);
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
        void onItemClick(DriverModal model);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
