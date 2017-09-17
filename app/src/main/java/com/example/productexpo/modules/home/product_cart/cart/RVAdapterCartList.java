package com.example.productexpo.modules.home.product_cart.cart;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.productexpo.R;
import com.example.productexpo.customviews.CustomOnTouchListener;
import com.example.productexpo.customviews.IndeterminateCircularProgressDrawable;
import com.example.productexpo.customviews.ItemClickListenerRV;
import com.example.productexpo.customviews.SquareImageView;
import com.example.productexpo.data.AppConstants;
import com.example.productexpo.entities.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.view.LayoutInflater.from;

/**
 * Created on 9/17/2017.
 */

@SuppressWarnings("unchecked")
public class RVAdapterCartList extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private Context context;
    private List<Product> listOfCart;
    private LayoutInflater inflater;
    private ItemClickListenerRV clickListener;

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
        Product product = listOfCart.get(position);
        CartViewHolder rootHolder = (CartViewHolder) holder;
        IndeterminateCircularProgressDrawable drawable =
                new IndeterminateCircularProgressDrawable(context
                        , 3.0f
                        , ContextCompat.getColor(context, R.color.colorPrimary)
                        , ContextCompat.getColor(context, R.color.colorAccent)
                );

        Picasso.with(context).load(product.getProductImg()).placeholder(drawable).into(rootHolder.ivCartImage);
        rootHolder.tvProdName.setText(product.getProductName());
        rootHolder.tvProdPrice.setText("\u20B9 " + product.getPrice());
        rootHolder.tvVendorName.setText("Vendor: " + product.getVendorName());
        rootHolder.tvVendorAddress.setText("Address: " + product.getVendorAddress());

        rootHolder.btnCallVendor.setOnClickListener(this);
        rootHolder.btnCallVendor.setOnTouchListener(new CustomOnTouchListener());

        rootHolder.btnRemoveCart.setOnClickListener(this);
        rootHolder.btnRemoveCart.setOnTouchListener(new CustomOnTouchListener());

        rootHolder.llHolder.setOnClickListener(this);
        rootHolder.llHolder.setOnTouchListener(new CustomOnTouchListener());

        rootHolder.btnCallVendor.setTag(AppConstants.TAG_KEY, position);
        rootHolder.btnRemoveCart.setTag(AppConstants.TAG_KEY, position);
        rootHolder.llHolder.setTag(AppConstants.TAG_KEY, position);

    }

    @Override
    public int getItemCount() {
        return listOfCart.size();
    }

    public void replaceList(List<Product> productList) {
        if (productList != null) {
            notifyItemRangeRemoved(0, productList.size());
            this.listOfCart.clear();
            this.listOfCart.addAll(productList);
            notifyItemRangeInserted(0, productList.size());
        }
    }

    public void removeProduct(int index) {
        if (listOfCart != null) {
            listOfCart.remove(index);
           notifyDataSetChanged();
        }
    }

    public ArrayList<Product> getData() {
        return (ArrayList<Product>) this.listOfCart;
    }

    @Override
    public void onClick(View v) {
        int pos;
        if (clickListener != null) {
            pos = (int) v.getTag(AppConstants.TAG_KEY);
            switch (v.getId()) {
                case R.id.btn_remove_cart:
                case R.id.btn_call_vendor:
                case R.id.ll_holder:
                    clickListener.onItemClick(v, pos, listOfCart.get(pos), null);
                    break;
            }
        }
    }

    public void setClickListener(ItemClickListenerRV clickListener) {
        this.clickListener = clickListener;
    }

    private static class CartViewHolder extends RecyclerView.ViewHolder {
        private SquareImageView ivCartImage;
        private AppCompatTextView tvProdName;
        private AppCompatTextView tvVendorName;
        private AppCompatTextView tvProdPrice;
        private AppCompatTextView tvVendorAddress;
        private AppCompatButton btnCallVendor;
        private AppCompatButton btnRemoveCart;
        private View llHolder;

        public CartViewHolder(View itemView) {
            super(itemView);
            ivCartImage = (SquareImageView) itemView.findViewById(R.id.iv_cart_image);
            tvProdName = (AppCompatTextView) itemView.findViewById(R.id.tv_prod_name);
            tvVendorName = (AppCompatTextView) itemView.findViewById(R.id.tv_vendor_name);
            tvProdPrice = (AppCompatTextView) itemView.findViewById(R.id.tv_prod_price);
            tvVendorAddress = (AppCompatTextView) itemView.findViewById(R.id.tv_vendor_address);
            btnCallVendor = (AppCompatButton) itemView.findViewById(R.id.btn_call_vendor);
            btnRemoveCart = (AppCompatButton) itemView.findViewById(R.id.btn_remove_cart);
            llHolder = itemView.findViewById(R.id.ll_holder);
        }
    }
}
