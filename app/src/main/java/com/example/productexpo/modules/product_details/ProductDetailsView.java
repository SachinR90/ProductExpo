package com.example.productexpo.modules.product_details;

import com.example.productexpo.entities.Product;
import com.example.productexpo.modules.base.fragment.BaseFragmentView;

/**
 * Created on 9/17/2017.
 */

public interface ProductDetailsView extends BaseFragmentView {
    void updateContent(Product product);
}
