package com.example.productexpo.modules.home.product_cart.product;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.productexpo.R;
import com.example.productexpo.entities.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.view.LayoutInflater.from;

/**
 * Created on 9/17/2017.
 */

public class RVAdapterProductList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Product> productList;
    private LayoutInflater inflater;
    private Context context;

    public RVAdapterProductList(Context context, ArrayList<Product> productList) {
        this.productList = productList;
        this.context = context;
        this.inflater = from(this.context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.row_product, parent, false);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Product product = productList.get(position);
        ProductViewHolder rootHolder = (ProductViewHolder) holder;

        Picasso.with(context).load(product.getProductImg()).into(rootHolder.ivHomeGridMenuImage);
        rootHolder.tvRowProductName.setText(product.getProductName());
        rootHolder.tvRowProductPrice.setText("Price : \u20B9 " + product.getPrice());
        rootHolder.tvRowProductVendorName.setText("Vendor: " + product.getVendorName());
        rootHolder.tvRowProductVendorAddress.setText("Address: " + product.getVendorAddress());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void replaceList(List<Product> productList) {
        if (productList != null) {
            notifyItemRangeRemoved(0, productList.size());
            this.productList.clear();
            this.productList.addAll(productList);
            notifyItemRangeInserted(0, productList.size());
        }
    }

    public ArrayList<Product> getData() {
        return this.productList;
    }

    private static class ProductViewHolder extends RecyclerView.ViewHolder {
        private AppCompatImageView ivHomeGridMenuImage;
        private AppCompatTextView tvRowProductName;
        private AppCompatTextView tvRowProductPrice;
        private AppCompatTextView tvRowProductVendorName;
        private AppCompatTextView tvRowProductVendorAddress;
        private AppCompatButton btnAddCart;
        private View llHomeGridParent;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ivHomeGridMenuImage = (AppCompatImageView) itemView.findViewById(R.id.iv_product_image);
            tvRowProductName = (AppCompatTextView) itemView.findViewById(R.id.tv_row_product_name);
            tvRowProductPrice = (AppCompatTextView) itemView.findViewById(R.id.tv_row_product_price);
            tvRowProductVendorName = (AppCompatTextView) itemView.findViewById(R.id.tv_row_product_vendor_name);
            tvRowProductVendorAddress = (AppCompatTextView) itemView.findViewById(R.id.tv_row_product_vendor_address);
            llHomeGridParent = itemView.findViewById(R.id.ll_home_grid_parent);
            btnAddCart = (AppCompatButton) itemView.findViewById(R.id.btn_add_cart);

        }
    }
}
