package sanaebadi.ir.tandorosti.BeansFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sanaebadi.ir.tandorosti.R;
import sanaebadi.ir.tandorosti.BeansAdapter.RiceAdapter;
import sanaebadi.ir.tandorosti.BeansModel.Rice;
import sanaebadi.ir.tandorosti.rest.ApiClient;
import sanaebadi.ir.tandorosti.rest.ApiInterface;


public class RiceFragment extends Fragment {

  private static final String TAG = "RiceFragment";

  private RecyclerView rice_pro_list;
  private ProgressBar rice_progress;
  private View snackView;

  private ApiInterface apiInterface;
  private Call<List<Rice>> riceCall;


  public RiceFragment() {
    // Required empty public constructor
  }


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_rice, container, false);

    rice_pro_list = view.findViewById(R.id.rice_pro_list);
    rice_progress = view.findViewById(R.id.rice_progress);

    //View For Snack Bar
    snackView = getActivity().findViewById(android.R.id.content);

    //Recycler View

    rice_pro_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    rice_pro_list.setItemAnimator(new DefaultItemAnimator());
    rice_pro_list.hasFixedSize();
    rice_pro_list.setNestedScrollingEnabled(false);

   /* rice_pro_list.setFitsSystemWindows(true);
    rice_pro_list.setItemViewCacheSize(20);
    rice_pro_list.setDrawingCacheEnabled(true);
    rice_pro_list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);*/

    rice_progress.setVisibility(View.VISIBLE);

    apiInterface = ApiClient.createApi();
    riceCall = apiInterface.getRiceFragment();

    riceCall.enqueue(new Callback<List<Rice>>() {
      @Override
      public void onResponse(Call<List<Rice>> call, Response<List<Rice>> response) {

        rice_progress.setVisibility(View.GONE);

        List<Rice> riceList = response.body();
        RiceAdapter riceAdapter = new RiceAdapter(riceList, getActivity());
        rice_pro_list.setAdapter(riceAdapter);
      }

      @Override
      public void onFailure(Call<List<Rice>> call, Throwable t) {

        rice_progress.setVisibility(View.GONE);

        Log.e(TAG, "RiceOnFailure: " + t.getMessage());
       // Toast.makeText(getActivity(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();

        Snackbar snackbar = Snackbar
          .make(snackView, "خطا در اتصال به اینترنت ", Snackbar.LENGTH_INDEFINITE)
          .setAction("تنظیمات", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
            }
          });

        ViewCompat.setLayoutDirection(snackbar.getView(), ViewCompat.LAYOUT_DIRECTION_RTL);

        snackbar.show();



      }

    });

    return view;
  }


}
