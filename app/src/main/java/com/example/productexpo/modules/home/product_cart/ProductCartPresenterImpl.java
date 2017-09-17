package com.example.productexpo.modules.home.product_cart;

import android.graphics.PorterDuff;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;

import com.example.productexpo.R;
import com.example.productexpo.entities.Product;
import com.example.productexpo.entities.ProductResponse;
import com.example.productexpo.modules.base.fragment.BaseFragmentView;
import com.example.productexpo.modules.home.product_cart.cart.CartFragment;
import com.example.productexpo.modules.home.product_cart.product.ProductFragment;

import java.util.List;

/**
 * Created on 9/17/2017.
 */

public class ProductCartPresenterImpl implements ProductCartPresenter {
    BaseFragmentView productCartView;
    ProductCartInteractor interactor;

    private TabLayout tlProductCart;
    private ViewPager vpProductCart;

    public ProductCartPresenterImpl(BaseFragmentView productCartView) {
        this.productCartView = productCartView;
        interactor = new ProductCartInteractorImpl(this);
    }


    @Override
    public void handleViewPager(ViewPager viewPager) {
        this.vpProductCart = viewPager;
        VPAdapterProductCart adapterProductCart = new VPAdapterProductCart(productCartView.getChildManagerForFragment());
        adapterProductCart.add(0, ProductFragment.newInstance(), productCartView.getViewContext().getString(R.string.str_product_list));
        adapterProductCart.add(0, CartFragment.newInstance(), productCartView.getViewContext().getString(R.string.str_cart_list));
        this.vpProductCart.setAdapter(adapterProductCart);
        this.vpProductCart.setOffscreenPageLimit(2);
    }

    @Override
    public void handleTabLayout(TabLayout tabLayout) {
        this.tlProductCart = tabLayout;
        this.tlProductCart.clearOnTabSelectedListeners();
        this.tlProductCart.addOnTabSelectedListener(new TabLayoutSelectionListener());
        this.tlProductCart.setupWithViewPager(this.vpProductCart, false);
        this.tlProductCart.getTabAt(0).setIcon(R.drawable.vc_product_list);
        this.tlProductCart.getTabAt(1).setIcon(R.drawable.vc_shopping_cart);
    }

    @Override
    public void selectProductTab() {
        this.tlProductCart.getTabAt(0).select();
        setTabColor(this.tlProductCart.getTabAt(0), R.color.white);
        setTabColor(this.tlProductCart.getTabAt(1), R.color.colorPrimaryDark);
    }

    private void setTabColor(TabLayout.Tab tab, int color) {
        tab.getIcon().setColorFilter(ContextCompat.getColor(this.productCartView.getViewContext(), color), PorterDuff.Mode.SRC_IN);
    }

    @Override
    public void fetchProducts() {
        productCartView.showProgress(productCartView.getViewContext().getString(R.string.str_pls_wait));
        interactor.getProducts();
    }

    @Override
    public void onGetProductsReceived(ProductResponse productResponse) {
        productCartView.hideProgress();
        if (vpProductCart != null) {
            VPAdapterProductCart adapter = (VPAdapterProductCart) vpProductCart.getAdapter();
            ProductCartView productCartView = adapter.getRegisteredFragment(0);
            productCartView.updateProductList(productResponse.getProducts());
        }
    }

    @Override
    public void fetchCartProducts() {

    }

    @Override
    public void onCartProductReceived(List<Product> products) {

    }

    @Override
    public void showErrorMessage(int errorCode, String message) {
        productCartView.showOKDialog("", message, "", null);
    }


    private class TabLayoutSelectionListener implements TabLayout.OnTabSelectedListener {

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            try {
                vpProductCart.setCurrentItem(tab.getPosition());
                setTabColor(tab, R.color.white);
                if (tab.getPosition() == 0) {
                    //get products
                    setTabColor(tlProductCart.getTabAt(1), R.color.colorPrimaryDark);
                } else if (tab.getPosition() == 1) {
                    //get cart products from the shared preference
                    setTabColor(tlProductCart.getTabAt(0), R.color.colorPrimaryDark);
                }
            } catch (Exception ignored) {

            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
            try {
                vpProductCart.setCurrentItem(tab.getPosition());
                setTabColor(tab, R.color.white);
                if (tab.getPosition() == 0) {
                    //get products
                    setTabColor(tlProductCart.getTabAt(1), R.color.colorPrimaryDark);
                } else if (tab.getPosition() == 1) {
                    //get cart products from the shared preference
                    setTabColor(tlProductCart.getTabAt(0), R.color.colorPrimaryDark);
                }
            } catch (Exception ignored) {

            }
        }
    }
}
