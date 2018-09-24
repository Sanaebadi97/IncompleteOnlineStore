package sanaebadi.ir.tandorosti.OilFragment;

import android.content.Context;
import android.net.Uri;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sanaebadi.ir.tandorosti.OIiModel.Peanut;
import sanaebadi.ir.tandorosti.OIiModel.SunFlower;
import sanaebadi.ir.tandorosti.OilAdapter.SunFlowerAdapter;
import sanaebadi.ir.tandorosti.R;
import sanaebadi.ir.tandorosti.rest.ApiClient;
import sanaebadi.ir.tandorosti.rest.ApiInterface;


public class SunFlowerFragment extends Fragment {

  private static final String TAG = "SunFlowerFragment";


  private RecyclerView sun_flower_pro_list;
  private ProgressBar sun_flower_progress;

  private ApiInterface apiInterface;
  private Call<List<SunFlower>> sunFlowerCall;


  public SunFlowerFragment() {
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
    View view = inflater.inflate(R.layout.fragment_sun_flower, container, false);

    sun_flower_pro_list = view.findViewById(R.id.sun_flower_pro_list);
    sun_flower_progress = view.findViewById(R.id.sun_flower_progress);

    //Recycler View

    sun_flower_pro_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    sun_flower_pro_list.setItemAnimator(new DefaultItemAnimator());
    sun_flower_pro_list.hasFixedSize();
    sun_flower_pro_list.setNestedScrollingEnabled(false);

   /* sun_flower_pro_list.setFitsSystemWindows(true);
    sun_flower_pro_list.setItemViewCacheSize(20);
    sun_flower_pro_list.setDrawingCacheEnabled(true);
    sun_flower_pro_list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);*/


    sun_flower_progress.setVisibility(View.VISIBLE);

    apiInterface = ApiClient.createApi();
    sunFlowerCall = apiInterface.getSunFlowrsFragment();


    sunFlowerCall.enqueue(new Callback<List<SunFlower>>() {
      @Override
      public void onResponse(Call<List<SunFlower>> call, Response<List<SunFlower>> response) {

        sun_flower_progress.setVisibility(View.GONE);

        List<SunFlower> sunFlowerList = response.body();
        SunFlowerAdapter sunFlowerAdapter = new SunFlowerAdapter(sunFlowerList, getActivity());
        sun_flower_pro_list.setAdapter(sunFlowerAdapter);
      }

      @Override
      public void onFailure(Call<List<SunFlower>> call, Throwable t) {

        sun_flower_progress.setVisibility(View.GONE);

        Log.e(TAG, "SunFlowerOnFailure: " + t.getMessage());
        Toast.makeText(getActivity(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
      }
    });


    return view;
  }


}
