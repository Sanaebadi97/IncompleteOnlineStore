package sanaebadi.ir.tandorosti.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sanaebadi.ir.tandorosti.DrawerActivity.Beans;
import sanaebadi.ir.tandorosti.adapter.BestProAdapter;
import sanaebadi.ir.tandorosti.adapter.NavListAdapter;
import sanaebadi.ir.tandorosti.adapter.NewAdapter;
import sanaebadi.ir.tandorosti.adapter.ViewPagerAdapter;
import sanaebadi.ir.tandorosti.app.BaseActivity;
import sanaebadi.ir.tandorosti.model.BestPro;
import sanaebadi.ir.tandorosti.R;
import sanaebadi.ir.tandorosti.model.NavList;
import sanaebadi.ir.tandorosti.model.NewPro;
import sanaebadi.ir.tandorosti.networkActivity.NetworkActivity;
import sanaebadi.ir.tandorosti.rest.ApiClient;
import sanaebadi.ir.tandorosti.rest.ApiInterface;

import static sanaebadi.ir.tandorosti.R.id.action_nav;

public class MainActivity extends BaseActivity {

  private static final String TAG = "MainActivity";

  private ViewPagerAdapter viewPagerAdapter;
  private List<NavList> navLists = new ArrayList<>();
  private ApiInterface apiInterface;
  private Call<List<NewPro>> newProCall;
  private Call<List<BestPro>> bestProCall;
  private NavListAdapter navListAdapter;
  private int dotsCount;
  private ImageView[] dots;


  public DrawerLayout drawer_layout;

  //get Views From Xml With Butter Knife Lib
  /*@BindView(R.id.drawer_layout)
  DrawerLayout drawer_layout;*/

 /* @BindView(R.id.fab_bg_layout)
  View fab_bg_layout;

  @BindView(R.id.fab_lay_about_us)
  LinearLayout fab_lay_about_us;

  @BindView(R.id.fab_lay_contact_us)
  LinearLayout fab_lay_contact_us;

  @BindView(R.id.fab_lay_setting)
  LinearLayout fab_lay_setting;


  @BindView(R.id.fab)
  FloatingActionButton fab;

  @BindView(R.id.fab_about_us)
  FloatingActionButton fab_about_us;

  @BindView(R.id.fab_contact_us)
  FloatingActionButton fab_contact_us;

  @BindView(R.id.fab_setting)
  FloatingActionButton fab_setting;*/

  @BindView(R.id.toolbar)
  Toolbar toolbar;

  @BindView(R.id.view_pager)
  ViewPager view_pager;

  @BindView(R.id.layoutDots)
  LinearLayout layoutDots;

  @BindView(R.id.best_pro_list)
  RecyclerView best_pro_list;

  @BindView(R.id.new_pro_list)
  RecyclerView new_pro_list;

  @BindView(R.id.drawer_list)
  RecyclerView drawer_list;

  @BindView(R.id.main_progress)
  ProgressBar main_progress;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


    //Handle Network
    if (isNetworkAvailable()) {

      //Toast.makeText(this, "You are Connect To Network", Toast.LENGTH_SHORT).show();
    } else {
      // Toast.makeText(this, "You are offline", Toast.LENGTH_SHORT).show();
      Intent intent = new Intent(this, NetworkActivity.class);
      intent.putExtra("MainAct", Beans.class.getSimpleName());
      startActivity(intent);
      MainActivity.this.finish();

    }

    // bind the view using butterKnife
    ButterKnife.bind(this);
    drawer_layout = findViewById(R.id.drawer_layout);

    //Toolbar Settings
    setSupportActionBar(toolbar);
    toolbar.setTitle("");




  /*  //Fab Settings

    fab_lay_about_us.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(MainActivity.this, AboutUsActivity.class));

      }
    });

    fab_lay_contact_us.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(MainActivity.this, ContactUsActivity.class));

      }
    });

    fab_lay_setting.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(MainActivity.this, SettingActivity.class));

      }
    });
*/

    //****************************************************************************

  /*  //Custom Fab On ClickListener

    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (!isFABOpen) {
          showFABMenu();
        } else {
          closeFABMenu();
        }
      }
    });

    fab_bg_layout.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        closeFABMenu();
      }
    });
*/

    //  **************************** START DOTS *****************************

    //Dots Of View Pager Swipe Settings

    viewPagerAdapter = new ViewPagerAdapter();

    dotsCount = viewPagerAdapter.getCount();
    dots = new ImageView[dotsCount];


    for (int i = 0; i < dotsCount; i++) {
      dots[i] = new ImageView(this);
      dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dots));
      LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT);

      params.setMargins(8, 0, 8, 0);

      layoutDots.addView(dots[i], params);

    }

    dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dots));

    view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      @Override
      public void onPageSelected(int position) {
        for (int i = 0; i < dotsCount; i++) {
          dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dots));
        }

        dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dots));
      }

      @Override
      public void onPageScrollStateChanged(int state) {

      }
    });


    //  **************************** FINISH DOTS *****************************

    //get data for new product recycler
    getNewPro();

    //New Product RecyclerView Settings
    new_pro_list.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
    new_pro_list.setItemAnimator(new DefaultItemAnimator());
    new_pro_list.setNestedScrollingEnabled(false);
    new_pro_list.hasFixedSize();
//    new_pro_list.setFitsSystemWindows(true);
//    new_pro_list.setItemViewCacheSize(20);
//    new_pro_list.setDrawingCacheEnabled(true);
//    new_pro_list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);

    //get data for best product recycler

    getBestPro();
    //BestPro RecyclerView Settings

    best_pro_list.setLayoutManager(new LinearLayoutManager(this,
      LinearLayoutManager.HORIZONTAL, true));
    best_pro_list.setItemAnimator(new DefaultItemAnimator());
    best_pro_list.hasFixedSize();
    best_pro_list.setNestedScrollingEnabled(false);
    //  best_pro_list.setFitsSystemWindows(true);


    //View Pager Settings
    viewPagerAdapter = new ViewPagerAdapter(this);
    view_pager.setAdapter(viewPagerAdapter);
    view_pager.setCurrentItem(0);


    //init Navigation View RecyclerView
    navListAdapter = new NavListAdapter(this, navLists);
    drawer_list.setHasFixedSize(true);
    drawer_list.setLayoutManager(new LinearLayoutManager(this,
      LinearLayoutManager.VERTICAL, false));
    drawer_list.setAdapter(navListAdapter);

    navListAdapter.activity = this;
    initNavRecycler();
  }

  //***************************************************************************

  //init Nav RecyclerView
  private void initNavRecycler() {

    NavList navList = new NavList(R.string.nav_home, R.drawable.ic_home_white_24dp);
    navLists.add(navList);

    navList = new NavList(R.string.nav_news, R.drawable.ic_autorenew_white_24dp);
    navLists.add(navList);

    navList = new NavList(R.string.nav_beverages, R.drawable.ic_local_bar_blue_grey_300_24dp);
    navLists.add(navList);

    navList = new NavList(R.string.nav_junk_food, R.drawable.ic_popcorn_white_24dp);
    navLists.add(navList);

    navList = new NavList(R.string.nav_seasoned, R.drawable.ic_food_variant_white_24dp);
    navLists.add(navList);

    navList = new NavList(R.string.nav_beans_and_cereals, R.drawable.ic_rice_white_24dp);
    navLists.add(navList);

    navList = new NavList(R.string.nav_oil, R.drawable.ic_water_white_24dp);
    navLists.add(navList);

    navList = new NavList(R.string.nav_cosmetics, R.drawable.ic_brush_white_24dp);
    navLists.add(navList);

    navList = new NavList(R.string.nav_clothing, R.drawable.tshirt);
    navLists.add(navList);

    navListAdapter.notifyDataSetChanged();

  }

//********************************************************************

  //init BestPro Recycler View and Retrofit
  private void getBestPro() {

    apiInterface = ApiClient.createApi();
    bestProCall = apiInterface.getBestPro();

    bestProCall.enqueue(new Callback<List<BestPro>>() {
      @Override
      public void onResponse(Call<List<BestPro>> call, Response<List<BestPro>> response) {

        List<BestPro> bestProList = response.body();
        BestProAdapter bestProAdapter = new BestProAdapter(MainActivity.this, bestProList);
        best_pro_list.setAdapter(bestProAdapter);
      }

      @Override
      public void onFailure(Call<List<BestPro>> call, Throwable t) {
        Log.e(TAG, "bestOnFailure: " + t.getMessage());
        //   Toast.makeText(MainActivity.this, "خطا در اتصال به سرور", Toast.LENGTH_SHORT).show();


      }
    });

  }

//*************************************************************************
  //Recycler View and Retrofit

  private void getNewPro() {

    main_progress.setVisibility(View.VISIBLE);

    apiInterface = ApiClient.createApi();
    newProCall = apiInterface.getNewPro();

    newProCall.enqueue(new Callback<List<NewPro>>() {
      @Override
      public void onResponse(Call<List<NewPro>> call, Response<List<NewPro>> response) {

        main_progress.setVisibility(View.GONE);

        List<NewPro> newProList = response.body();

        NewAdapter newAdapter = new NewAdapter(newProList, MainActivity.this);
        new_pro_list.setAdapter(newAdapter);


      }

      @Override
      public void onFailure(Call<List<NewPro>> call, Throwable t) {

        main_progress.setVisibility(View.GONE);
        Log.e(TAG, "newOnFailure: " + t.getMessage());
        //    Toast.makeText(MainActivity.this, "خطا در اتصال به سرور", Toast.LENGTH_SHORT).show();


      }
    });

  }

  //***********************************************************************

  @Override
  public void onBackPressed() {
    if (drawer_layout.isDrawerOpen(GravityCompat.END)) {
      drawer_layout.closeDrawer(GravityCompat.END);
      /*if (isFABOpen) {
        closeFABMenu();
        invalidateOptionsMenu();*/
    } else {
      super.onBackPressed();

    }

  }

  //***********************************************************************

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  //***********************************************************************
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == action_nav) {
      drawer_layout.openDrawer(GravityCompat.END);
      invalidateOptionsMenu();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }


  //Manage Network Connection

 /* private boolean haveNetworkConnection() {
    boolean haveConnectedWifi = false;
    boolean haveConnectedMobile = false;

    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    assert cm != null;
    NetworkInfo[] netInfo = cm.getAllNetworkInfo();
    for (NetworkInfo ni : netInfo) {
      if (ni.getTypeName().equalsIgnoreCase("WIFI"))
        if (ni.isConnected())
          haveConnectedWifi = true;
      if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
        if (ni.isConnected())
          haveConnectedMobile = true;
    }
    return haveConnectedWifi || haveConnectedMobile;
  }*/


  private boolean isNetworkAvailable() {
    ConnectivityManager connectivityManager
      = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
  }

  //Hide Toolbar Icons When Drawer Opened

 /* @Override
  public boolean onPrepareOptionsMenu(Menu menu) {
    boolean drawerOpen = ui.drawer_layout.isDrawerOpen(ui.nav_list_view);
    menu.findItem(R.id.img_search).setVisible(!drawerOpen);
    menu.findItem(R.id.img_basket).setVisible(!drawerOpen);
    return super.onPrepareOptionsMenu(menu);
  }*/

  //******************************************************************************************

  //Custom Fab Methods


 /* private void showFABMenu() {
    isFABOpen = true;
    fab_lay_about_us.setVisibility(View.VISIBLE);
    fab_lay_contact_us.setVisibility(View.VISIBLE);
    fab_lay_setting.setVisibility(View.VISIBLE);
    fab_bg_layout.setVisibility(View.VISIBLE);

    fab.animate().rotationBy(180);
    fab_lay_about_us.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
    fab_lay_contact_us.animate().translationY(-getResources().getDimension(R.dimen.standard_100));
    fab_lay_setting.animate().translationY(-getResources().getDimension(R.dimen.standard_145));
  }

  private void closeFABMenu() {
    isFABOpen = false;
    fab_bg_layout.setVisibility(View.GONE);
    fab.animate().rotationBy(-180);
    fab_lay_about_us.animate().translationY(0);
    fab_lay_contact_us.animate().translationY(0);
    fab_lay_setting.animate().translationY(0).setListener(new Animator.AnimatorListener() {
      @Override
      public void onAnimationStart(Animator animator) {

      }

      @Override
      public void onAnimationEnd(Animator animator) {
        if (!isFABOpen) {
          fab_lay_about_us.setVisibility(View.GONE);
          fab_lay_contact_us.setVisibility(View.GONE);
          fab_lay_setting.setVisibility(View.GONE);
        }

      }

      @Override
      public void onAnimationCancel(Animator animator) {

      }

      @Override
      public void onAnimationRepeat(Animator animator) {

      }
    });
  }*/
  //********************************************************************************


 /* @Override
  protected void onResume() {
    super.onResume();
  }*/
}
