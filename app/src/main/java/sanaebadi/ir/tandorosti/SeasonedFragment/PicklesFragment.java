package sanaebadi.ir.tandorosti.SeasonedFragment;

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
import sanaebadi.ir.tandorosti.SeasonedModel.Pickles;
import sanaebadi.ir.tandorosti.rest.ApiClient;
import sanaebadi.ir.tandorosti.rest.ApiInterface;

public class PicklesFragment extends Fragment {

  private static final String TAG = "PicklesFragment";


  private RecyclerView pickles_pro_list;
  private ProgressBar pickles_progress;

  private ApiInterface apiInterface;
  private Call<List<Pickles>> picklesCall;


  public PicklesFragment() {
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
    View view = inflater.inflate(R.layout.fragment_picklese, container, false);

    pickles_pro_list = view.findViewById(R.id.pickles_pro_list);
    pickles_progress = view.findViewById(R.id.pickles_progress);

    //Recycler View

    pickles_pro_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    pickles_pro_list.setItemAnimator(new DefaultItemAnimator());
    pickles_pro_list.hasFixedSize();
    pickles_pro_list.setNestedScrollingEnabled(false);

    /*pickles_pro_list.setFitsSystemWindows(true);
    pickles_pro_list.setItemViewCacheSize(20);
    pickles_pro_list.setDrawingCacheEnabled(true);
    pickles_pro_list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);*/


    pickles_progress.setVisibility(View.VISIBLE);

    apiInterface = ApiClient.createApi();
    picklesCall = apiInterface.getPickleseFragment();

    picklesCall.enqueue(new Callback<List<Pickles>>() {
      @Override
      public void onResponse(Call<List<Pickles>> call, Response<List<Pickles>> response) {

        pickles_progress.setVisibility(View.GONE);

        List<Pickles> picklesList = response.body();
        PicklesAdapter picklesAdapter = new PicklesAdapter(picklesList, getActivity());
        pickles_pro_list.setAdapter(picklesAdapter);
      }

      @Override
      public void onFailure(Call<List<Pickles>> call, Throwable t) {

        pickles_progress.setVisibility(View.GONE);

        Log.e(TAG, "PicklesOnFailure: " + t.getMessage());
        Toast.makeText(getActivity(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();

      }
    });

    return view;
  }


}
