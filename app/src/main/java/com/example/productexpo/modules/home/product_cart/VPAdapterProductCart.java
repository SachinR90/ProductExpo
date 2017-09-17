package com.example.productexpo.modules.home.product_cart;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 9/17/2017.
 */

public class VPAdapterProductCart extends FragmentStatePagerAdapter {
    // Sparse array to keep track of registered fragments in memory
    private SparseArray<ProductCartView> registeredFragments = new SparseArray<>();
    private List<Fragment> listOfFragments = new ArrayList<>(0);
    private List<String> pageTitles = new ArrayList<>(0);
    public VPAdapterProductCart(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        return listOfFragments.get(position);
    }

    @Override
    public int getCount() {
        return listOfFragments.size();
    }

    public void add(int pos, Fragment fragment, String title) {
        listOfFragments.add(fragment);
        registeredFragments.put(pos, (ProductCartView) fragment);
        pageTitles.add(title);
    }

    // Register the fragment when the item is instantiated
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, (ProductCartView) fragment);
        return fragment;
    }

    // Unregister when the item is inactive
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    // Returns the fragment for the position (if instantiated)
    public ProductCartView getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitles.get(position);
    }
}
