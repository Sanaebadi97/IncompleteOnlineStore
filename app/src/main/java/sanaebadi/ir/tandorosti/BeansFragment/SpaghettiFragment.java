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
import sanaebadi.ir.tandorosti.BeansAdapter.SpaghettiAdapter;
import sanaebadi.ir.tandorosti.BeansModel.Spaghetti;
import sanaebadi.ir.tandorosti.rest.ApiClient;
import sanaebadi.ir.tandorosti.rest.ApiInterface;


public class SpaghettiFragment extends Fragment {

  private static final String TAG = "SpaghettiFragment";


  private RecyclerView spaghetti_pro_list;
  private ProgressBar spaghetti_progress;
  private View snackView;

  private ApiInterface apiInterface;
  private Call<List<Spaghetti>> spaghettiCall;


  public SpaghettiFragment() {
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
    View view = inflater.inflate(R.layout.fragment_spaghetti, container, false);


    spaghetti_pro_list = view.findViewById(R.id.spaghetti_pro_list);
    spaghetti_progress = view.findViewById(R.id.spaghetti_progress);

    //View For Snack Bar
    snackView = getActivity().findViewById(android.R.id.content);

    //Recycler View

    spaghetti_pro_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    spaghetti_pro_list.setItemAnimator(new DefaultItemAnimator());
    spaghetti_pro_list.hasFixedSize();
    spaghetti_pro_list.setNestedScrollingEnabled(false);

    /*spaghetti_pro_list.setFitsSystemWindows(true);
    spaghetti_pro_list.setItemViewCacheSize(20);
    spaghetti_pro_list.setDrawingCacheEnabled(true);
    spaghetti_pro_list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);*/

    spaghetti_progress.setVisibility(View.VISIBLE);

    apiInterface = ApiClient.createApi();
    spaghettiCall = apiInterface.getSpaghettiFragment();


    spaghettiCall.enqueue(new Callback<List<Spaghetti>>() {
      @Override
      public void onResponse(Call<List<Spaghetti>> call, Response<List<Spaghetti>> response) {

        spaghetti_progress.setVisibility(View.GONE);

        List<Spaghetti> spaghettiList = response.body();
        SpaghettiAdapter spaghettiAdapter = new SpaghettiAdapter(spaghettiList, getActivity());
        spaghetti_pro_list.setAdapter(spaghettiAdapter);
      }

      @Override
      public void onFailure(Call<List<Spaghetti>> call, Throwable t) {

        spaghetti_progress.setVisibility(View.GONE);

        Log.e(TAG, "SpaghettiOnFailure: " + t.getMessage());
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
