package com.example.noiseboxentree;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class CartItemRecViewAdapter extends RecyclerView.Adapter<CartItemRecViewAdapter.ViewHolder> {

    private ArrayList<NoiseBox> noiseBoxes = new ArrayList<>();
    private Context mContext;
    private DatabaseHelper databaseHelper;

    public CartItemRecViewAdapter(Context mContext) {
        this.mContext = mContext;
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

        holder.imgCIDelete.setOnClickListener(view -> {
            databaseHelper.deleteOne(noiseBoxes.get(position));
            noiseBoxes.remove(position);
            notifyDataSetChanged();
        });
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
