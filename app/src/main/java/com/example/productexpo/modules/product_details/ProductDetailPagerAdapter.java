package com.example.productexpo.modules.product_details;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.productexpo.R;
import com.example.productexpo.entities.Product;
import com.example.productexpo.utils.AppUtils;
import com.squareup.picasso.Picasso;

import static android.view.LayoutInflater.from;

public class ProductDetailPagerAdapter extends PagerAdapter {
    LayoutInflater layoutInflater;
    private Product product;
    private Context context;

    public ProductDetailPagerAdapter(Context context, Product product) {
        this.product = product;
        layoutInflater = from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        if (product == null) {
            return 0;
        }
        return product.getProductGallery().size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.item_vp_product_details, container, false);
        if (product != null && !AppUtils.isCollectionEmpty(product.getProductGallery())) {
            ImageView imageView = (ImageView) itemView.findViewById(R.id.iv_product_gallery);
            Picasso.with(context).load(product.getProductGallery().get(position))
                    .into(imageView);
            container.addView(itemView);
        }
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}