package sanaebadi.ir.tandorosti.app;


import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import sanaebadi.ir.tandorosti.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class AppController extends Application {

  @SuppressLint("StaticFieldLeak")
  public static Context context;

  @Override
  public void onCreate() {
    super.onCreate();
    context = getApplicationContext();

    CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
      .setDefaultFontPath("fonts/iransans.ttf")
      .setFontAttrId(R.attr.fontPath)
      .build()
    );
  }
}
