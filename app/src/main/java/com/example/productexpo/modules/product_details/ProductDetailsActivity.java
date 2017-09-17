package com.example.productexpo.modules.product_details;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.productexpo.R;
import com.example.productexpo.entities.Product;
import com.example.productexpo.modules.base.activity.BaseActivity;

import static com.example.productexpo.data.AppConstants.KEY_PRODUCT_DETAIL;

/**
 * Created on 9/17/2017.
 */

public class ProductDetailsActivity extends BaseActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
    }

    @Override
    public void initializeUIComponent() {
        //Handle toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Product product = getIntent().getParcelableExtra(KEY_PRODUCT_DETAIL);
        FragmentManager managerForFragment = getSupportFragmentManager();
        //load first fragment
        FragmentTransaction transaction = managerForFragment.beginTransaction();
        transaction.replace(R.id.fl_fragment_container, ProductDetailsFragment.newInstance(product), ProductDetailsFragment.class.getSimpleName());
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return (super.onOptionsItemSelected(menuItem));
    }
}
