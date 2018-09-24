package sanaebadi.ir.tandorosti.SeasonedFragment;

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
import sanaebadi.ir.tandorosti.R;
import sanaebadi.ir.tandorosti.SeasonedAdapter.SugarAdapter;
import sanaebadi.ir.tandorosti.SeasonedAdapter.SweetsAdapter;
import sanaebadi.ir.tandorosti.SeasonedModel.Sugar;
import sanaebadi.ir.tandorosti.SeasonedModel.Sweets;
import sanaebadi.ir.tandorosti.rest.ApiClient;
import sanaebadi.ir.tandorosti.rest.ApiInterface;


public class SweetsFragment extends Fragment {

  private static final String TAG = "SweetsFragment";


  private RecyclerView sweets_pro_list;
  private ProgressBar sweet_progress;

  private ApiInterface apiInterface;
  private Call<List<Sweets>> sweetsCall;


  public SweetsFragment() {
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
    View view = inflater.inflate(R.layout.fragment_sweets, container, false);

    sweets_pro_list = view.findViewById(R.id.sweets_pro_list);
    sweet_progress = view.findViewById(R.id.sweets_progress);

    //Recycler View

    sweets_pro_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    sweets_pro_list.setItemAnimator(new DefaultItemAnimator());
    sweets_pro_list.hasFixedSize();
    sweets_pro_list.setNestedScrollingEnabled(false);

 /*   sweets_pro_list.setFitsSystemWindows(true);
    sweets_pro_list.setItemViewCacheSize(20);
    sweets_pro_list.setDrawingCacheEnabled(true);
    sweets_pro_list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);*/


    sweet_progress.setVisibility(View.VISIBLE);

    apiInterface = ApiClient.createApi();
    sweetsCall = apiInterface.getSweetsFragment();

    sweetsCall.enqueue(new Callback<List<Sweets>>() {
      @Override
      public void onResponse(Call<List<Sweets>> call, Response<List<Sweets>> response) {

        sweet_progress.setVisibility(View.GONE);

        List<Sweets> sweetsList = response.body();
        SweetsAdapter sweetsAdapter = new SweetsAdapter(sweetsList, getActivity());
        sweets_pro_list.setAdapter(sweetsAdapter);
      }

      @Override
      public void onFailure(Call<List<Sweets>> call, Throwable t) {

        sweet_progress.setVisibility(View.GONE);

        Log.e(TAG, "SweetsOnFailure: " + t.getMessage());
        Toast.makeText(getActivity(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
      }
    });

    return view;
  }


}
