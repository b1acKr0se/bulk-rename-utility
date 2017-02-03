package io.b1ackr0se.bulkrenameutility.ui.filechooser;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.b1ackr0se.bulkrenameutility.R;
import io.b1ackr0se.bulkrenameutility.data.model.Item;


public class FileAdapter extends RecyclerView.Adapter<FileAdapter.FileViewHolder> {
    private List<Item> list;
    private OnItemClickListener onItemClickListener;

    public FileAdapter(List<Item> items) {
        list = items;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public FileAdapter.FileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FileViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_file, parent, false));
    }

    @Override
    public void onBindViewHolder(FileAdapter.FileViewHolder holder, int position) {
        final Item item = list.get(position);
        holder.bind(item);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClicked(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class FileViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image) ImageView imageView;
        @BindView(R.id.name) TextView name;
        @BindView(R.id.checkbox) CheckBox checkBox;

        private Item item;

        public FileViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Item i) {
            item = i;
            if (item.isDirectory) {
                imageView.setImageResource(R.drawable.ic_folder);
            } else {
                imageView.setImageResource(R.drawable.ic_file);
            }

            name.setText(item.name);

            checkBox.setChecked(item.isChecked);
        }
    }

    public interface OnItemClickListener {
        void onItemClicked(Item item);
    }
}
