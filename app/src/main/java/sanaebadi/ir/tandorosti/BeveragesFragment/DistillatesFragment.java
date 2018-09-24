package sanaebadi.ir.tandorosti.BeveragesFragment;

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
import sanaebadi.ir.tandorosti.R;
import sanaebadi.ir.tandorosti.BeveragesAdapter.DistillatesAdapter;
import sanaebadi.ir.tandorosti.BeveragesModel.Distillates;
import sanaebadi.ir.tandorosti.rest.ApiClient;
import sanaebadi.ir.tandorosti.rest.ApiInterface;


public class DistillatesFragment extends Fragment {

  private static final String TAG = "DistillatesFragment";

  private RecyclerView distillates_pro_list;
  private ProgressBar distillates_progress;

  private ApiInterface apiInterface;
  private Call<List<Distillates>> distillatesCall;


  public DistillatesFragment() {
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

    View view = inflater.inflate(R.layout.fragment_distillates, container, false);

    distillates_pro_list = view.findViewById(R.id.distillates_pro_list);
    distillates_progress = view.findViewById(R.id.distillates_progress);

    //Recycler View

    distillates_pro_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    distillates_pro_list.setItemAnimator(new DefaultItemAnimator());
    distillates_pro_list.hasFixedSize();
    distillates_pro_list.setNestedScrollingEnabled(false);

 /*   distillates_pro_list.setFitsSystemWindows(true);
    distillates_pro_list.setItemViewCacheSize(20);
    distillates_pro_list.setDrawingCacheEnabled(true);
    distillates_pro_list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);*/

    distillates_progress.setVisibility(View.VISIBLE);

    apiInterface = ApiClient.createApi();
    distillatesCall = apiInterface.getDistillatesFragment();

    distillatesCall.enqueue(new Callback<List<Distillates>>() {
      @Override
      public void onResponse(Call<List<Distillates>> call, Response<List<Distillates>> response) {

        distillates_progress.setVisibility(View.GONE);

        List<Distillates> distillatesList = response.body();
        DistillatesAdapter distillatesAdapter = new DistillatesAdapter(distillatesList, getActivity());
        distillates_pro_list.setAdapter(distillatesAdapter);
      }

      @Override
      public void onFailure(Call<List<Distillates>> call, Throwable t) {

        distillates_progress.setVisibility(View.GONE);

        Log.e(TAG, "DistillatesOnFailure: " + t.getMessage());
        Toast.makeText(getActivity(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
      }
    });

    return view;
  }


}



