package sanaebadi.ir.tandorosti.NewsDetailsActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sanaebadi.ir.tandorosti.R;
import sanaebadi.ir.tandorosti.adapter.SameProAdapter;
import sanaebadi.ir.tandorosti.app.BaseActivity;
import sanaebadi.ir.tandorosti.fullPhotoActivity.FullBestProPhoto;
import sanaebadi.ir.tandorosti.manage.ExpandableTextView;
import sanaebadi.ir.tandorosti.model.SamePro;
import sanaebadi.ir.tandorosti.rest.ApiClient;
import sanaebadi.ir.tandorosti.rest.ApiInterface;

import static sanaebadi.ir.tandorosti.app.AppController.context;

public class VegetablesDetailsActivity extends BaseActivity {

  private static final String TAG = "VegetablesDetailsActivi";

  @BindView(R.id.txt_see_more)
  ExpandableTextView txt_see_more;

  @BindView(R.id.same_pro_list)
  RecyclerView same_pro_list;


  @BindView(R.id.txt_price)
  TextView txt_price;

  @BindView(R.id.toolbar)
  Toolbar toolbar;

  @BindView(R.id.app_bar_layout)
  AppBarLayout app_bar_layout;

  @BindView(R.id.collapsing_toolbar)
  CollapsingToolbarLayout collapsing_toolbar;

  @BindView(R.id.img_toolbar_back)
  ImageView img_toolbar_back;

  @BindView(R.id.txt_toolbar_title)
  TextView txt_toolbar_title;

  @BindView(R.id.txt_pro_name)
  TextView txt_pro_name;

  @BindView(R.id.img_pro_photo)
  ImageView img_pro_photo;

  @BindView(R.id.img_share)
  ImageView img_share;

  @BindView(R.id.img_fav)
  ImageView img_fav;


  private ApiInterface apiInterface;
  private Call<List<SamePro>> semeCall;
  private Typeface face;
  private boolean appBarExpanded = true;
  private boolean isPlay = false;




  //Intent Views

  private String pro_image;
  private String pro_name;
  private String pro_desc;
  private int pro_price;



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_vegetables_details);

    ButterKnife.bind(this);
    setSupportActionBar(toolbar);
    toolbar.setTitle("");


    //get Intent From New Activity

    Intent intent = getIntent();

    pro_image = intent.getStringExtra("PRO_IMAGE");
    pro_name = intent.getStringExtra("PRO_NAME");
    pro_desc = intent.getStringExtra("PRO_DESC");
    pro_price = intent.getIntExtra("PRO_PRICE", 0);


    //*****************************************************************


    //Finish Activity
    img_toolbar_back.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });


    //****************************************************************************
    //Expandable TextView
    txt_see_more.setText(pro_desc);

    //**************************************************************************


    //Put Intent Data On Views

    Glide.with(this).clear(img_pro_photo);

    Glide.with(this).load(pro_image)
      .into(img_pro_photo);

    txt_pro_name.setText(pro_name);


    //Put ... On Toolbar Title

    if (pro_name.length() > 16) {

      pro_name = pro_name.substring(0, 16) + "...";
      txt_toolbar_title.setText(pro_name);

    } else {
      txt_toolbar_title.setText(pro_name);
    }


    //***************************************************************
    //Separator Price Number ","

    txt_price.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
      }
    });


    face = Typeface.createFromAsset(getAssets(), "fonts/bzar.ttf");
    txt_price.setTypeface(face);

    NumberFormat formatter = new DecimalFormat("#,###,###,###");
    txt_price.setText(" " + formatter.format(pro_price));

    //*********************************************************************

    //*******************************************************************
    //get data for same product recycler
    getSamePro();

    //Same Product RecyclerView Settings
    same_pro_list.setLayoutManager(new LinearLayoutManager(VegetablesDetailsActivity.this,
      LinearLayoutManager.HORIZONTAL, true));
    same_pro_list.setItemAnimator(new DefaultItemAnimator());
    same_pro_list.hasFixedSize();
    same_pro_list.setFitsSystemWindows(true);
    same_pro_list.setItemViewCacheSize(20);
    same_pro_list.setDrawingCacheEnabled(true);
    same_pro_list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);

    //*******************************************************************

    //CollapsingToolbarLayout

    collapsing_toolbar.setTitle(getString(R.string.toolbar_title));

    Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
      R.drawable.image);

    Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
      @SuppressWarnings("ResourceType")
      @Override
      public void onGenerated(Palette palette) {
        int vibrantColor = palette.getVibrantColor(R.color.colorPrimary);
        collapsing_toolbar.setContentScrimColor(vibrantColor);
        collapsing_toolbar.setStatusBarScrimColor(R.color.colorPrimaryDark);
      }
    });


    app_bar_layout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
      @Override
      public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        Log.d(TAG, "onOffsetChanged: verticalOffset: " + verticalOffset);

        //  Vertical offset == 0 indicates appBar is fully expanded.
        if (Math.abs(verticalOffset) > 200) {
          appBarExpanded = false;
          invalidateOptionsMenu();
        } else {
          appBarExpanded = true;
          invalidateOptionsMenu();
        }
      }
    });
  }
  //******************************************************************

  //********************************************************************
  //init Same Pro Recycler View and Retrofit
  private void getSamePro() {

    apiInterface = ApiClient.createApi();
    semeCall = apiInterface.getSamePro();

    semeCall.enqueue(new Callback<List<SamePro>>() {
      @Override
      public void onResponse(Call<List<SamePro>> call, Response<List<SamePro>> response) {

        List<SamePro> sameProList = response.body();
        SameProAdapter sameProAdapter = new SameProAdapter(VegetablesDetailsActivity.this, sameProList);
        same_pro_list.setAdapter(sameProAdapter);
      }

      @Override
      public void onFailure(Call<List<SamePro>> call, Throwable t) {
        Log.e(TAG, "bestOnFailure: " + t.getMessage());
        Toast.makeText(VegetablesDetailsActivity.this, "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
      }
    });

    img_pro_photo.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(VegetablesDetailsActivity.this, FullBestProPhoto.class);
        intent.putExtra("PRO_IMAGE", pro_image);
        startActivity(intent);
      }
    });

    //Share Page With

    img_share.setOnClickListener(new View.OnClickListener() {
      @RequiresApi(api = Build.VERSION_CODES.O)
      @Override
      public void onClick(View v) {
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        String subject = txt_pro_name.getText().toString() + "\n  را در ارمغان تندرستی ببین!";
        String object = "https://play.google.com/store/apps/details?id=com.lenovo.anyshare.gps&hl=en";
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        sendIntent.putExtra(Intent.EXTRA_TEXT, object);
        startActivity(sendIntent);
      }
    });

    //Favorite Doing ...

    img_fav.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (isPlay) {

          img_fav.setColorFilter(ContextCompat.getColor(context, R.color.nav_icon_tint),
            PorterDuff.Mode.MULTIPLY);

        } else {
          img_fav.setColorFilter(ContextCompat.getColor(context, android.R.color.holo_red_dark),
            PorterDuff.Mode.MULTIPLY);
        }

        isPlay = !isPlay; // reverse
      }
    });



  }


  //*********************************************************************


  @Override
  protected void onResume() {
    super.onResume();
  }
}
