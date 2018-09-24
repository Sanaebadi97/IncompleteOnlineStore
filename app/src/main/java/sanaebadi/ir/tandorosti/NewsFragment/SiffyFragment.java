package sanaebadi.ir.tandorosti.NewsFragment;

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
import sanaebadi.ir.tandorosti.NewsAdapter.SiffyAdapter;
import sanaebadi.ir.tandorosti.NewsModel.Siffy;
import sanaebadi.ir.tandorosti.rest.ApiClient;
import sanaebadi.ir.tandorosti.rest.ApiInterface;


public class SiffyFragment extends Fragment {

  private static final String TAG = "SiffyFragment";


  private RecyclerView siffy_pro_list;
  private ProgressBar siffy_progress;

  private ApiInterface apiInterface;
  private Call<List<Siffy>> siffyCall;


  public SiffyFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_siffy, container, false);

    siffy_pro_list = view.findViewById(R.id.siffy_pro_list);
    siffy_progress = view.findViewById(R.id.siffy_progress);

    //Recycler View

    siffy_pro_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    siffy_pro_list.setItemAnimator(new DefaultItemAnimator());
    siffy_pro_list.hasFixedSize();
    siffy_pro_list.setNestedScrollingEnabled(false);

   /* siffy_pro_list.setFitsSystemWindows(true);
    siffy_pro_list.setItemViewCacheSize(20);
    siffy_pro_list.setDrawingCacheEnabled(true);
    siffy_pro_list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);*/


    siffy_progress.setVisibility(View.VISIBLE);

    apiInterface = ApiClient.createApi();
    siffyCall = apiInterface.getSiffyFragment();

    siffyCall.enqueue(new Callback<List<Siffy>>() {
      @Override
      public void onResponse(Call<List<Siffy>> call, Response<List<Siffy>> response) {

        siffy_progress.setVisibility(View.GONE);

        List<Siffy> siffysList = response.body();
        SiffyAdapter siffyAdapter = new SiffyAdapter(siffysList, getActivity());
        siffy_pro_list.setAdapter(siffyAdapter);
      }

      @Override
      public void onFailure(Call<List<Siffy>> call, Throwable t) {

        siffy_progress.setVisibility(View.GONE);

        Log.e(TAG, "SiffyOnFailure: " + t.getMessage());
        Toast.makeText(getActivity(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
      }
    });

    return view;
  }


}
