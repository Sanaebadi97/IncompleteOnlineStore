package sanaebadi.ir.tandorosti.fabActivity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import sanaebadi.ir.tandorosti.R;
import sanaebadi.ir.tandorosti.app.BaseActivity;

public class AboutUsActivity extends BaseActivity {


  @BindView(R.id.img_toolbar_back)
  ImageView img_toolbar_back;

  @BindView(R.id.toolbar)
  Toolbar toolbar;



  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_about_us);

    ButterKnife.bind(this);
    setSupportActionBar(toolbar);
    toolbar.setTitle("");


    img_toolbar_back.setOnClickListener(new View.OnClickListener() {
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
