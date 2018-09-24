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
import sanaebadi.ir.tandorosti.SeasonedAdapter.PicklesAdapter;
import sanaebadi.ir.tandorosti.SeasonedAdapter.SugarAdapter;
import sanaebadi.ir.tandorosti.SeasonedModel.Pickles;
import sanaebadi.ir.tandorosti.SeasonedModel.Sugar;
import sanaebadi.ir.tandorosti.rest.ApiClient;
import sanaebadi.ir.tandorosti.rest.ApiInterface;


public class SugarFragment extends Fragment {

  private static final String TAG = "SugarFragment";


  private RecyclerView sugar_pro_list;
  private ProgressBar sugar_progress;

  private ApiInterface apiInterface;
  private Call<List<Sugar>> sugarCall;

  public SugarFragment() {
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
    View view = inflater.inflate(R.layout.fragment_sugar, container, false);

    sugar_pro_list = view.findViewById(R.id.sugar_pro_list);
    sugar_progress = view.findViewById(R.id.sugar_progress);

    //Recycler View

    sugar_pro_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    sugar_pro_list.setItemAnimator(new DefaultItemAnimator());
    sugar_pro_list.hasFixedSize();
    sugar_pro_list.setNestedScrollingEnabled(false);

   /* sugar_pro_list.setFitsSystemWindows(true);
    sugar_pro_list.setItemViewCacheSize(20);
    sugar_pro_list.setDrawingCacheEnabled(true);
    sugar_pro_list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);*/


    sugar_progress.setVisibility(View.VISIBLE);

    apiInterface = ApiClient.createApi();
    sugarCall = apiInterface.getSugarFragment();

    sugarCall.enqueue(new Callback<List<Sugar>>() {
      @Override
      public void onResponse(Call<List<Sugar>> call, Response<List<Sugar>> response) {

        sugar_progress.setVisibility(View.GONE);

        List<Sugar> sugarList = response.body();
        SugarAdapter sugarAdapter = new SugarAdapter(sugarList, getActivity());
        sugar_pro_list.setAdapter(sugarAdapter);
      }

      @Override
      public void onFailure(Call<List<Sugar>> call, Throwable t) {
        sugar_progress.setVisibility(View.GONE);

        Log.e(TAG, "SugarOnFailure: " + t.getMessage());
        Toast.makeText(getActivity(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();

      }
    });

    return view;
  }


}
