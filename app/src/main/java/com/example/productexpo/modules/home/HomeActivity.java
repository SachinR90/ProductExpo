package com.example.productexpo.modules.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.productexpo.R;
import com.example.productexpo.application.ProductExpoApp;
import com.example.productexpo.entities.Product;
import com.example.productexpo.modules.base.ExitActivity;
import com.example.productexpo.modules.base.activity.BaseActivity;
import com.example.productexpo.modules.product_details.ProductDetailsActivity;
import com.example.productexpo.modules.product_details.ProductDetailsFragment;

import static com.example.productexpo.data.AppConstants.KEY_PRODUCT_DETAIL;

/**
 * Created on 9/17/2017.
 */

public class HomeActivity extends BaseActivity implements HomeView {
    HomePresenter presenter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (savedInstanceState == null) {
            presenter = new HomePresenterImpl(this);
            presenter.showProductCartFragment();
        } else {

        }
    }

    @Override
    public void initializeUIComponent() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    @Override
    public void loadProductDetails(Product product) {
        ProductDetailsFragment displayFrag = (ProductDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.details_frag);
        if (displayFrag == null) {
            // DisplayFragment (Fragment B) is not in the layout (handset layout),
            // so start DisplayActivity (Activity B)
            // and pass it the info about the selected item
            Intent intent = new Intent(this, ProductDetailsActivity.class);
            intent.putExtra(KEY_PRODUCT_DETAIL, product);
            startActivity(intent);
        } else {
            // DisplayFragment (Fragment B) is in the layout (tablet layout),
            // so tell the fragment to update
            displayFrag.updateContent(product);
        }
    }

    @Override
    public void onBackPressed() {
        //Checking for fragment count on backstack
        showYesNoDialog(getString(R.string.str_msg_title_exit_application), getString(R.string.str_msg_exit_application), getString(R.string.str_yes), getString(R.string.str_no)
                , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ExitActivity.exitApplication(ProductExpoApp.getAppContext().getApplicationContext());
                    }
                }, null);
    }
}
