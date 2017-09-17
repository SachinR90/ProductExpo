package com.example.productexpo.modules.home.product_cart.cart;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.productexpo.R;
import com.example.productexpo.customviews.SquareImageView;
import com.example.productexpo.entities.Product;

import java.util.List;

import static android.view.LayoutInflater.from;

/**
 * Created on 9/17/2017.
 */

public class RVAdapterCartList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Product> listOfCart;
    private LayoutInflater inflater;

    public RVAdapterCartList(Context context, List<Product> listOfCart) {
        this.context = context;
        this.listOfCart = listOfCart;
        this.inflater = from(this.context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.row_cart, parent, false);
        return new CartViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listOfCart.size();
    }

    private static class CartViewHolder extends RecyclerView.ViewHolder {
        private SquareImageView ivCartImage;
        private AppCompatTextView tvProdName;
        private AppCompatTextView tvVendorName;
        private AppCompatTextView tvProdPrice;
        private AppCompatTextView tvVendorAddress;

        public CartViewHolder(View itemView) {
            super(itemView);
            ivCartImage = (SquareImageView) itemView.findViewById(R.id.iv_cart_image);
            tvProdName = (AppCompatTextView) itemView.findViewById(R.id.tv_prod_name);
            tvVendorName = (AppCompatTextView) itemView.findViewById(R.id.tv_vendor_name);
            tvProdPrice = (AppCompatTextView) itemView.findViewById(R.id.tv_prod_price);
            tvVendorAddress = (AppCompatTextView) itemView.findViewById(R.id.tv_vendor_address);
        }
    }
}
