package sanaebadi.ir.tandorosti.fullPhotoActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


import butterknife.BindView;
import butterknife.ButterKnife;
import sanaebadi.ir.tandorosti.R;

public class FullBestProPhoto extends AppCompatActivity {

  private static final String TAG = "FullBestProPhoto";

  @BindView(R.id.img_clear)
  ImageView img_clear;

  @BindView(R.id.img_full_pro)
  ImageView img_full_pro;

  private String pro_image;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_full_pro_photo);

    ButterKnife.bind(this);

    Intent intent = getIntent();
    pro_image = intent.getStringExtra("PRO_IMAGE");


    Glide.with(this).clear(img_full_pro);

    Glide.with(this).load(pro_image)
      .into(img_full_pro);


    img_clear.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });
  }

  @Override
  protected void onResume() {
    super.onResume();
  }
}
