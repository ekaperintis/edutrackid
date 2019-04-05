package com.ekaperintis.guru.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ekaperintis.guru.App.AppConfig;
import com.ekaperintis.guru.Model.Information;
import com.ekaperintis.guru.R;
import com.ekaperintis.guru.Teacher_Learning_Detail;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InformationAdapter extends RecyclerView.Adapter<InformationAdapter.ProductViewHolder> {

    private Context mCtx;
    private List<Information> productList;

    public InformationAdapter(Context mCtx, List<Information> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public InformationAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_share_information, null);
        return new InformationAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InformationAdapter.ProductViewHolder holder, final int position) {

        final Information product = productList.get(position);

        holder.textViewTitle.setText(product.getTitle());
        //holder.textViewShortDesc.setText(product.getShortdesc());

        //holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage()));

        Picasso.with(mCtx)
                .load(AppConfig.HOST_IMAGE + product
                        .getImage())
                .into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mCtx, Teacher_Learning_Detail.class);
                intent.putExtra("title", product.getTitle());
                intent.putExtra("description", product.getShortdesc());
                //intent.putExtra("imageurl", product.getImage_url());
                mCtx.startActivity(intent);
            }
        });

        holder.textViewRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mCtx, Teacher_Learning_Detail.class);
                intent.putExtra("title", product.getTitle());
                intent.putExtra("description", product.getShortdesc());
                mCtx.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewRead;
        ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewRead = itemView.findViewById(R.id.textViewRead);
            //textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}