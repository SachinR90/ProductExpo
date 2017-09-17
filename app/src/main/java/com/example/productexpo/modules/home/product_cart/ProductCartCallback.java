package com.example.productexpo.modules.home.product_cart;

import com.example.productexpo.entities.Product;
import com.example.productexpo.modules.base.fragment.BaseFragmentView;

import java.util.List;

/**
 * Acts a bridge between Cart-Product and ViewPager.<br>
 * Updates Products list in  Cart and Product Fragment.
 * Created on 9/17/2017.
 */
public interface ProductCartCallback extends BaseFragmentView {
    void updateProductList(List<Product> listOfProducts);

    /**
     * show products gallery
     *
     * @param product's gallery
     */
    void showProductGallery(Product product);
}
