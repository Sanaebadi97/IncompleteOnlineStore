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
import sanaebadi.ir.tandorosti.OIiModel.Olive;
import sanaebadi.ir.tandorosti.OilAdapter.OliveAdapter;
import sanaebadi.ir.tandorosti.R;
import sanaebadi.ir.tandorosti.SeasonedAdapter.HoneyAndJamAdapter;
import sanaebadi.ir.tandorosti.SeasonedModel.HoneyAndJam;
import sanaebadi.ir.tandorosti.rest.ApiClient;
import sanaebadi.ir.tandorosti.rest.ApiInterface;


public class HoneyAndJamFragment extends Fragment {

  private static final String TAG = "HoneyAndJamFragment";


  private RecyclerView honey_jam_pro_list;
  private ProgressBar honey_jam_progress;

  private ApiInterface apiInterface;
  private Call<List<HoneyAndJam>> honeyAndJamCall;

  public HoneyAndJamFragment() {
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
    View view = inflater.inflate(R.layout.fragment_honey_and_jam, container, false);

    honey_jam_pro_list = view.findViewById(R.id.honey_jam_pro_list);
    honey_jam_progress = view.findViewById(R.id.honey_jam_progress);

    //Recycler View

    honey_jam_pro_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    honey_jam_pro_list.setItemAnimator(new DefaultItemAnimator());
    honey_jam_pro_list.hasFixedSize();
    honey_jam_pro_list.setNestedScrollingEnabled(false);

  /* honey_jam_pro_list.setFitsSystemWindows(true);
   honey_jam_pro_list.setItemViewCacheSize(20);
   honey_jam_pro_list.setDrawingCacheEnabled(true);
   honey_jam_pro_list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);*/


    honey_jam_progress.setVisibility(View.VISIBLE);

    apiInterface = ApiClient.createApi();
    honeyAndJamCall = apiInterface.getHoneyAndJamFragment();


    honeyAndJamCall.enqueue(new Callback<List<HoneyAndJam>>() {
      @Override
      public void onResponse(Call<List<HoneyAndJam>> call, Response<List<HoneyAndJam>> response) {

        honey_jam_progress.setVisibility(View.GONE);

        List<HoneyAndJam> honeyAndJamList = response.body();
        HoneyAndJamAdapter honeyAndJamAdapter = new HoneyAndJamAdapter(honeyAndJamList, getActivity());
        honey_jam_pro_list.setAdapter(honeyAndJamAdapter);
      }

      @Override
      public void onFailure(Call<List<HoneyAndJam>> call, Throwable t) {

        honey_jam_progress.setVisibility(View.GONE);

        Log.e(TAG, "HoneyAndJamOnFailure: " + t.getMessage());
        Toast.makeText(getActivity(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
      }
    });

    return view;
  }


}
