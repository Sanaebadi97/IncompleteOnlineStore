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
import sanaebadi.ir.tandorosti.SeasonedAdapter.SpiceAdapter;
import sanaebadi.ir.tandorosti.SeasonedModel.Pickles;
import sanaebadi.ir.tandorosti.SeasonedModel.Spice;
import sanaebadi.ir.tandorosti.rest.ApiClient;
import sanaebadi.ir.tandorosti.rest.ApiInterface;


public class SpiceFragment extends Fragment {

  private static final String TAG = "SpiceFragment";


  private RecyclerView spice_pro_list;
  private ProgressBar spice_progress;

  private ApiInterface apiInterface;
  private Call<List<Spice>> spiceCall;

  public SpiceFragment() {
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
    View view = inflater.inflate(R.layout.fragment_spice, container, false);


    spice_pro_list = view.findViewById(R.id.spice_pro_list);
    spice_progress = view.findViewById(R.id.spice_progress);

    //Recycler View

    spice_pro_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    spice_pro_list.setItemAnimator(new DefaultItemAnimator());
    spice_pro_list.hasFixedSize();
    spice_pro_list.setNestedScrollingEnabled(false);

   /* spice_pro_list.setFitsSystemWindows(true);
    spice_pro_list.setItemViewCacheSize(20);
    spice_pro_list.setDrawingCacheEnabled(true);
    spice_pro_list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);*/

    spice_progress.setVisibility(View.VISIBLE);

    apiInterface = ApiClient.createApi();
    spiceCall = apiInterface.getSpiceFragment();


    spiceCall.enqueue(new Callback<List<Spice>>() {
      @Override
      public void onResponse(Call<List<Spice>> call, Response<List<Spice>> response) {

        spice_progress.setVisibility(View.GONE);


        List<Spice> spiceList = response.body();
        SpiceAdapter spiceAdapter = new SpiceAdapter(spiceList, getActivity());
        spice_pro_list.setAdapter(spiceAdapter);
      }

      @Override
      public void onFailure(Call<List<Spice>> call, Throwable t) {

        spice_progress.setVisibility(View.GONE);

        Log.e(TAG, "SpiceOnFailure: " + t.getMessage());
        Toast.makeText(getActivity(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();

      }
    });

    return view;
  }


}
