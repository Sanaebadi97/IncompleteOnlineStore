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
import sanaebadi.ir.tandorosti.BeansAdapter.BreadAdapter;
import sanaebadi.ir.tandorosti.BeansModel.Bread;
import sanaebadi.ir.tandorosti.rest.ApiClient;
import sanaebadi.ir.tandorosti.rest.ApiInterface;


public class BreadFragment extends Fragment {

  private static final String TAG = "BreadFragment";


  private RecyclerView bread_pro_list;
  private ProgressBar bread_progress;
  private View snackView;

  private ApiInterface apiInterface;
  private Call<List<Bread>> breadCall;

  public BreadFragment() {
    // Required empty public constructor
  }


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_bread, container, false);

    bread_pro_list = view.findViewById(R.id.bread_pro_list);
    bread_progress = view.findViewById(R.id.bread_progress);


    //View For Snack Bar
    snackView = getActivity().findViewById(android.R.id.content);

    //Recycler View

    bread_pro_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    bread_pro_list.setItemAnimator(new DefaultItemAnimator());
    bread_pro_list.hasFixedSize();
    bread_pro_list.setNestedScrollingEnabled(false);
  /*  bread_pro_list.setFitsSystemWindows(true);
    bread_pro_list.setItemViewCacheSize(20);
    bread_pro_list.setDrawingCacheEnabled(true);
    bread_pro_list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);*/

    bread_progress.setVisibility(View.VISIBLE);

    apiInterface = ApiClient.createApi();
    breadCall = apiInterface.getBreadFragment();

    breadCall.enqueue(new Callback<List<Bread>>() {
      @Override
      public void onResponse(Call<List<Bread>> call, Response<List<Bread>> response) {

        bread_progress.setVisibility(View.GONE);

        List<Bread> breadList = response.body();
        BreadAdapter dairyAdapter = new BreadAdapter(breadList, getActivity());
        bread_pro_list.setAdapter(dairyAdapter);
      }

      @Override
      public void onFailure(Call<List<Bread>> call, Throwable t) {

        bread_progress.setVisibility(View.GONE);

        Log.e(TAG, "breadOnFailure: " + t.getMessage());
     //   Toast.makeText(getActivity(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();


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
