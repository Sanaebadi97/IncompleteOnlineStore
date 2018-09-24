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
import sanaebadi.ir.tandorosti.OIiModel.Sesame;
import sanaebadi.ir.tandorosti.OilAdapter.PeanutAdapter;
import sanaebadi.ir.tandorosti.OilAdapter.SesameAdapter;
import sanaebadi.ir.tandorosti.R;
import sanaebadi.ir.tandorosti.rest.ApiClient;
import sanaebadi.ir.tandorosti.rest.ApiInterface;


public class SesameFragment extends Fragment {

  private static final String TAG = "SesameFragment";

  private RecyclerView sesame_pro_list;
  private ProgressBar sesame_progress;

  private ApiInterface apiInterface;
  private Call<List<Sesame>> sesameCall;

  public SesameFragment() {
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
    View view = inflater.inflate(R.layout.fragment_sesame, container, false);

    sesame_pro_list = view.findViewById(R.id.sesame_pro_list);
    sesame_progress = view.findViewById(R.id.sesame_progress);

    //Recycler View

    sesame_pro_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    sesame_pro_list.setItemAnimator(new DefaultItemAnimator());
    sesame_pro_list.hasFixedSize();
    sesame_pro_list.setNestedScrollingEnabled(false);

   /* sesame_pro_list.setFitsSystemWindows(true);
    sesame_pro_list.setItemViewCacheSize(20);
    sesame_pro_list.setDrawingCacheEnabled(true);
    sesame_pro_list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);*/

    sesame_progress.setVisibility(View.VISIBLE);

    apiInterface = ApiClient.createApi();
    sesameCall = apiInterface.getSesameFragment();

    sesameCall.enqueue(new Callback<List<Sesame>>() {
      @Override
      public void onResponse(Call<List<Sesame>> call, Response<List<Sesame>> response) {

        sesame_progress.setVisibility(View.GONE);

        List<Sesame> sesameList = response.body();
        SesameAdapter sesameAdapter = new SesameAdapter(sesameList, getActivity());
        sesame_pro_list.setAdapter(sesameAdapter);
      }

      @Override
      public void onFailure(Call<List<Sesame>> call, Throwable t) {

        sesame_progress.setVisibility(View.GONE);

        Log.e(TAG, "SesameOnFailure: " + t.getMessage());
        Toast.makeText(getActivity(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
      }
    });


    return view;
  }


}
