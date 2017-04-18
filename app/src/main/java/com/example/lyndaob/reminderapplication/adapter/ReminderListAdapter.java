package com.example.lyndaob.reminderapplication.adapter;

import com.google.firebase.database.Query;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.lyndaob.reminderapplication.databinding.ReminderListItemBinding;
import com.example.lyndaob.reminderapplication.models.Reminder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

/**
 * ReminderApplication2
 * Michael Obi
 * 17 04 2017 8:50 PM
 */

public class ReminderListAdapter extends FirebaseRecyclerAdapter<Reminder, ReminderListAdapter.ViewHolder> {

    /**
     * @param modelClass      Firebase will marshall the data at a location into an instance of a
     *                        class that you provide
     * @param modelLayout     This is the layout used to represent a single item in the list. You
     *                        will be responsible for populating an instance of the corresponding
     *                        view with the data from an instance of modelClass.
     * @param viewHolderClass The class that hold references to all sub-views in an instance
     *                        modelLayout.
     * @param ref             The Firebase location to watch for data changes. Can also be a slice
     *                        of a location, using some combination of {@code limit()}, {@code
     *                        startAt()}, and {@code endAt()}.
     */
    public ReminderListAdapter(Class<Reminder> modelClass, int modelLayout, Class<ViewHolder> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    @Override
    protected void populateViewHolder(ViewHolder viewHolder, Reminder reminder, int position) {
        viewHolder.bind(reminder);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ReminderListItemBinding binding = DataBindingUtil.inflate(
                layoutInflater, viewType, parent, false);
        return new ViewHolder(binding);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ReminderListItemBinding binding;

        public ViewHolder(ReminderListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Reminder reminder) {
            binding.setReminder(reminder);
            binding.executePendingBindings();
        }
    }
}
