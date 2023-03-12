package com.theelitedevelopers.bunkies.modules.account_setup.profile;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.theelitedevelopers.bunkies.databinding.DropDownRvItemBinding;

import java.util.List;

/**
 * @author Victor
 * @created 11/03/2022 - 12:55 AM
 * @project Bunkies
 */
public class BottomSheetDialogRvAdapter extends RecyclerView.Adapter<BottomSheetDialogRvAdapter.BottomSheetDialogViewHolder> {
    private List<String> listOfItems;
    private ItemClicked listener;

    public BottomSheetDialogRvAdapter(List<String> listOfItems) {
        this.listOfItems = listOfItems;
    }

    @NonNull
    @Override
    public BottomSheetDialogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DropDownRvItemBinding binding = DropDownRvItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new BottomSheetDialogViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BottomSheetDialogViewHolder holder, int position) {
        holder.bind(listOfItems.get(position));
    }

    @Override
    public int getItemCount() {
        return listOfItems.size();
    }

    public class BottomSheetDialogViewHolder extends RecyclerView.ViewHolder {
        private DropDownRvItemBinding binding;

        public BottomSheetDialogViewHolder(DropDownRvItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(String item) {
            binding.textView.setText(item);

            binding.getRoot().setOnClickListener(v -> listener.itemClicked(item));
        }
    }

    public void setOnItemCLickListener(ItemClicked listener) {
        this.listener = listener;
    }

    public interface ItemClicked {
        void itemClicked(String item);
    }
}
