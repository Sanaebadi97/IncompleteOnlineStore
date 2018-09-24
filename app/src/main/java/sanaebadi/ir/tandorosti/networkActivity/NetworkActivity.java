package sanaebadi.ir.tandorosti.networkActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import sanaebadi.ir.tandorosti.DrawerActivity.Beans;
import sanaebadi.ir.tandorosti.R;
import sanaebadi.ir.tandorosti.activity.MainActivity;
import sanaebadi.ir.tandorosti.app.BaseActivity;

public class NetworkActivity extends BaseActivity {

 /* @BindView(R.id.btn_network_setting)
  Button btn_network_setting;*/

  @BindView(R.id.btn_try_again)
  Button btn_try_again;

  @BindView(R.id.coordinatorLayout)
  CoordinatorLayout coordinatorLayout;

  private String mainAct;
  private String beansAct;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_network);


    ButterKnife.bind(this);


   /* btn_network_setting.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
      }
    });*/

    //get Intent
    Intent mainIntent = getIntent();
    mainAct = mainIntent.getStringExtra("MainAct");


    btn_try_again.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        if (isNetworkAvailable()) {

            startActivity(new Intent(NetworkActivity.this, MainActivity.class));
            NetworkActivity.this.finish();

        } else {

          Snackbar snackbar = Snackbar
            .make(coordinatorLayout, R.string.snack_error, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.snack_setting, new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
              }
            });

          ViewCompat.setLayoutDirection(snackbar.getView(), ViewCompat.LAYOUT_DIRECTION_RTL);

          snackbar.show();
        }
      }
    });
  }

  private boolean isNetworkAvailable() {
    ConnectivityManager connectivityManager
      = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
  }

  //Disable Back Button
  public void onBackPressed() {

  }
}
