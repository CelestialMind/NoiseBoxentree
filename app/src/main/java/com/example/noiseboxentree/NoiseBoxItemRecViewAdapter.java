package com.example.noiseboxentree;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noiseboxentree.fragments.CatalogFragment;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class NoiseBoxItemRecViewAdapter extends RecyclerView.Adapter<NoiseBoxItemRecViewAdapter.ViewHolder> {

    private ArrayList<NoiseBox> noiseBoxes = new ArrayList<>();
    private Context mContext;
    private String layoutName;
    private DatabaseHelper databaseHelper;

    public NoiseBoxItemRecViewAdapter(Context mContext, String layoutName) {
        this.mContext = mContext;
        this.layoutName = layoutName;
        databaseHelper = new DatabaseHelper(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtCIName.setText(noiseBoxes.get(position).getName());
        String size = noiseBoxes.get(position).getLength() + "x" + noiseBoxes.get(position).getWidth() + "x" + noiseBoxes.get(position).getHeight();
        holder.txtCISize.setText(size);
        if (noiseBoxes.get(position).isHavingSpAdvanced())
            holder.txtCIAdvSp.setText("Advanced soundproof");
        else
            holder.txtCIAdvSp.setText("Standard soundproof");
        if (noiseBoxes.get(position).isHavingFan())
            holder.txtCIFan.setText("With a fan");
        else
            holder.txtCIFan.setText("Without a fan");

        holder.txtCIPrepayment.setText("999");
        holder.txtCICost.setText("9999");

        switch (layoutName){
            case "activity_cart":
                holder.imgCIDelete.setOnClickListener(view -> {
                    databaseHelper.deleteOne(noiseBoxes.get(position), "CUSTOM_NOISE_BOX_TABLE");
                    noiseBoxes.remove(position);
                    notifyDataSetChanged();
                });
                break;
            case "fragment_catalog":
                holder.imgCIDelete.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return noiseBoxes.size();
    }

    public void setNoiseBoxes(ArrayList<NoiseBox> noiseBoxes) {
        this.noiseBoxes = noiseBoxes;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private MaterialCardView parent;
        private ImageView imgCIProduct, imgCIDelete;
        private TextView txtCIName, txtCISize, txtCIAdvSp, txtCIFan, txtCIPrepayment, txtCICost;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            initView();
        }

        private void initView(){
            parent = itemView.findViewById(R.id.parentCI);
            imgCIProduct = itemView.findViewById(R.id.imgCIProduct);
            txtCIName = itemView.findViewById(R.id.txtCIName);
            txtCISize = itemView.findViewById(R.id.txtCISize);
            txtCIAdvSp = itemView.findViewById(R.id.txtCIAdvSp);
            txtCIFan = itemView.findViewById(R.id.txtCIFan);
            txtCIPrepayment = itemView.findViewById(R.id.txtCIPrePayment);
            txtCICost = itemView.findViewById(R.id.txtCICost);
            imgCIDelete = itemView.findViewById(R.id.imgCIDelete);
        }

    }

}
