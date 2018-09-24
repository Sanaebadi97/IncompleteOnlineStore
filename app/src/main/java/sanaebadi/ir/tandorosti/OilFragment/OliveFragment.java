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
import sanaebadi.ir.tandorosti.NewsAdapter.DairyAdapter;
import sanaebadi.ir.tandorosti.NewsModel.Dairy;
import sanaebadi.ir.tandorosti.OIiModel.Olive;
import sanaebadi.ir.tandorosti.OilAdapter.OliveAdapter;
import sanaebadi.ir.tandorosti.R;
import sanaebadi.ir.tandorosti.rest.ApiClient;
import sanaebadi.ir.tandorosti.rest.ApiInterface;


public class OliveFragment extends Fragment {

  private static final String TAG = "OliveFragment";


  private RecyclerView olive_pro_list;
  private ProgressBar olive_progress;

  private ApiInterface apiInterface;
  private Call<List<Olive>> oliveCall;


  public OliveFragment() {
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
    View view = inflater.inflate(R.layout.fragment_olive, container, false);

    olive_pro_list = view.findViewById(R.id.olive_pro_list);
    olive_progress = view.findViewById(R.id.olive_progress);

    //Recycler View

    olive_pro_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    olive_pro_list.setItemAnimator(new DefaultItemAnimator());
    olive_pro_list.hasFixedSize();
    olive_pro_list.setNestedScrollingEnabled(false);

  /*  olive_pro_list.setFitsSystemWindows(true);
    olive_pro_list.setItemViewCacheSize(20);
    olive_pro_list.setDrawingCacheEnabled(true);
    olive_pro_list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);*/


    olive_progress.setVisibility(View.VISIBLE);

    apiInterface = ApiClient.createApi();
    oliveCall = apiInterface.getOliveFragment();


    oliveCall.enqueue(new Callback<List<Olive>>() {
      @Override
      public void onResponse(Call<List<Olive>> call, Response<List<Olive>> response) {

        olive_progress.setVisibility(View.GONE);

        List<Olive> oliveList = response.body();
        OliveAdapter oliveAdapter = new OliveAdapter(oliveList, getActivity());
        olive_pro_list.setAdapter(oliveAdapter);
      }

      @Override
      public void onFailure(Call<List<Olive>> call, Throwable t) {
        olive_progress.setVisibility(View.GONE);

        Log.e(TAG, "oliveOnFailure: " + t.getMessage());
        Toast.makeText(getActivity(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
      }
    });

    return view;
  }


}
