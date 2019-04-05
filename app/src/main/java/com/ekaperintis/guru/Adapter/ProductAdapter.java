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
import com.ekaperintis.guru.Model.Product;
import com.ekaperintis.guru.R;
import com.ekaperintis.guru.Teacher_Learning_Detail;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    //this context we will use to inflate the layout
    private Context mCtx;
   // public View view;               // <----- here
    //we are storing all the products in a list
    private List<Product> productList;

    //getting the context and product list with constructor
    public ProductAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_teacher_learning, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, final int position) {
        //getting the product of the specified position
        final Product product = productList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(product.getTitle());
        holder.textViewShortDesc.setText(product.getShortdesc());
        holder.textViewRating.setText(String.valueOf(product.getRating()));
        holder.textViewPrice.setText(String.valueOf(product.getPrice()));

        //holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage()));

        //holder.imageView.setImageURI(product.getImage_url().toString());
        //holder.imageView.setImageBitmap(getBitmapFromURL(product.getImage_url()));

        Picasso.with(mCtx)
                .load(AppConfig.HOST_IMAGE + product
                        .getImage_url())
                .into(holder.imageView);


        holder.textViewRating2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Log.i("W4K","Click-"+position);
                mCtx.startActivity(new Intent(mCtx, Teacher_Learning_Detail.class));
                */

                Intent intent=new Intent(mCtx, Teacher_Learning_Detail.class);
                //intent.putExtra("url",results.get(position).getUrl().toString());

                intent.putExtra("title",product.getTitle());
                intent.putExtra("description",product.getShortdesc());
                intent.putExtra("imageurl", AppConfig.HOST_IMAGE + product.getImage_url());

                mCtx.startActivity(intent);

            }
        });


        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mCtx, Teacher_Learning_Detail.class);
                //intent.putExtra("url",results.get(position).getUrl().toString());

                intent.putExtra("title",product.getTitle());
                intent.putExtra("description",product.getShortdesc());
                intent.putExtra("imageurl", AppConfig.HOST_IMAGE + product.getImage_url());

                mCtx.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice, textViewRating2;
        ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageView = itemView.findViewById(R.id.imageView);
            textViewRating2 = itemView.findViewById(R.id.textViewRating2);
        }
    }
}