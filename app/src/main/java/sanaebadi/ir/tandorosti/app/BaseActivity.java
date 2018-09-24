package sanaebadi.ir.tandorosti.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

@SuppressLint("Registered")
public abstract class BaseActivity extends AppCompatActivity {

  @Override
  public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {

    if (Build.VERSION.SDK_INT >= 17)
      getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

    super.onCreate(savedInstanceState, persistentState);

  }


  @Override
  protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
  }


  @Override
  protected void onResume() {
    super.onResume();
  }
}
