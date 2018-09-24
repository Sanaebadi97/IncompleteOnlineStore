package sanaebadi.ir.tandorosti.BeansFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sanaebadi.ir.tandorosti.R;
import sanaebadi.ir.tandorosti.BeansAdapter.GrainAdapter;
import sanaebadi.ir.tandorosti.BeansModel.Grain;
import sanaebadi.ir.tandorosti.rest.ApiClient;
import sanaebadi.ir.tandorosti.rest.ApiInterface;


public class GrainFragment extends Fragment {

  private static final String TAG = "GrainFragment";


  private RecyclerView grain_pro_list;
  private ProgressBar grain_progress;
  private View snackView;

  private ApiInterface apiInterface;
  private Call<List<Grain>> grainCall;


  public GrainFragment() {
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
    View view = inflater.inflate(R.layout.fragment_grain, container, false);

    grain_pro_list = view.findViewById(R.id.grain_pro_list);
    grain_progress = view.findViewById(R.id.grain_progress);

    //View For Snack Bar
    snackView = getActivity().findViewById(android.R.id.content);

    //Recycler View

    grain_pro_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    grain_pro_list.setItemAnimator(new DefaultItemAnimator());
    grain_pro_list.hasFixedSize();
    grain_pro_list.setNestedScrollingEnabled(false);
  /*  grain_pro_list.setFitsSystemWindows(true);
    grain_pro_list.setItemViewCacheSize(20);
    grain_pro_list.setDrawingCacheEnabled(true);
    grain_pro_list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);*/

    grain_progress.setVisibility(View.VISIBLE);

    apiInterface = ApiClient.createApi();
    grainCall = apiInterface.getGrainFragment();

    grainCall.enqueue(new Callback<List<Grain>>() {
      @Override
      public void onResponse(Call<List<Grain>> call, Response<List<Grain>> response) {

        grain_progress.setVisibility(View.GONE);

        List<Grain> grainList = response.body();
        GrainAdapter grainAdapter = new GrainAdapter(grainList, getActivity());
        grain_pro_list.setAdapter(grainAdapter);
      }

      @Override
      public void onFailure(Call<List<Grain>> call, Throwable t) {

        grain_progress.setVisibility(View.GONE);

        Log.e(TAG, "GrainOnFailure: " + t.getMessage());
        Toast.makeText(getActivity(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();

      }
    });


    return view;
  }



}
