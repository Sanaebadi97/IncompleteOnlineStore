package sanaebadi.ir.tandorosti.DrawerActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import sanaebadi.ir.tandorosti.NewsFragment.DairyFragment;
import sanaebadi.ir.tandorosti.OilFragment.OliveFragment;
import sanaebadi.ir.tandorosti.OilFragment.PeanutFragment;
import sanaebadi.ir.tandorosti.OilFragment.SesameFragment;
import sanaebadi.ir.tandorosti.OilFragment.SunFlowerFragment;
import sanaebadi.ir.tandorosti.R;
import sanaebadi.ir.tandorosti.adapter.TabLayViewPagerAdapter;
import sanaebadi.ir.tandorosti.app.BaseActivity;
import sanaebadi.ir.tandorosti.networkActivity.NetworkActivity;


@SuppressLint("Registered")
public class Oil extends BaseActivity {

  @BindView(R.id.toolbar)
  android.support.v7.widget.Toolbar toolbar;

  @BindView(R.id.tabs)
  TabLayout tabs;

  @BindView(R.id.view_pager)
  ViewPager view_pager;

  @BindView(R.id.img_toolbar_back)
  ImageView img_back;

  @BindView(R.id.txt_toolbar_title)
  TextView txt_title;

  @BindView(R.id.img_search)
  ImageView img_search;

  @BindView(R.id.img_basket)
  ImageView img_basket;

  private TabLayViewPagerAdapter adapter;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.layout_oil);

    //Handle Network
   /* if (isNetworkAvailable()) {

      //Toast.makeText(this, "You are Connect To Network", Toast.LENGTH_SHORT).show();
    } else {
      // Toast.makeText(this, "You are offline", Toast.LENGTH_SHORT).show();
      startActivity(new Intent(this, NetworkActivity.class));
      Oil.this.finish();

    }*/

    // bind the view using butterKnife
    ButterKnife.bind(this);

    //Toolbar Settings
    setSupportActionBar(toolbar);
    toolbar.setTitle("");

    setupViewpager(view_pager);

    tabs.setupWithViewPager(view_pager);

    view_pager.setCurrentItem(3);

    Typeface face = Typeface.createFromAsset(getAssets(), "fonts/iransans.ttf");
    for (int i = 0; i < tabs.getTabCount(); i++)

    {
      //noinspection ConstantConditions
      TextView tv = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
      tv.setTypeface(face);
      tabs.getTabAt(i).setCustomView(tv);

    }

    img_back.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });
  }

  private void setupViewpager(ViewPager viewPager) {
    adapter = new TabLayViewPagerAdapter(getSupportFragmentManager());
    adapter.addFragment(new PeanutFragment(), "روغن بادام زمینی");
    adapter.addFragment(new OliveFragment(), "روغن زیتون");
    adapter.addFragment(new SesameFragment(), "روغن کنجد");
    adapter.addFragment(new SunFlowerFragment(), "روغن آفتابگردان");
    viewPager.setAdapter(adapter);

  }

  //Handle Network

  private boolean isNetworkAvailable() {
    ConnectivityManager connectivityManager
      = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
  }

  @Override
  protected void onResume() {
    super.onResume();
  }
}
