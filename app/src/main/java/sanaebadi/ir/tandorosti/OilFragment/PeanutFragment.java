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
import sanaebadi.ir.tandorosti.OIiModel.Olive;
import sanaebadi.ir.tandorosti.OIiModel.Peanut;
import sanaebadi.ir.tandorosti.OilAdapter.OliveAdapter;
import sanaebadi.ir.tandorosti.OilAdapter.PeanutAdapter;
import sanaebadi.ir.tandorosti.R;
import sanaebadi.ir.tandorosti.rest.ApiClient;
import sanaebadi.ir.tandorosti.rest.ApiInterface;


public class PeanutFragment extends Fragment {

  private static final String TAG = "PeanutFragment";


  private RecyclerView peanut_pro_list;
  private ProgressBar peanut_progress;

  private ApiInterface apiInterface;
  private Call<List<Peanut>> peanutCall;


  public PeanutFragment() {
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
    View view = inflater.inflate(R.layout.fragment_peanut, container, false);

    peanut_pro_list = view.findViewById(R.id.peanut_pro_list);
    peanut_progress = view.findViewById(R.id.peanut_progress);

    //Recycler View

    peanut_pro_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    peanut_pro_list.setItemAnimator(new DefaultItemAnimator());
    peanut_pro_list.hasFixedSize();
    peanut_pro_list.setNestedScrollingEnabled(false);

    /*peanut_pro_list.setFitsSystemWindows(true);
    peanut_pro_list.setItemViewCacheSize(20);
    peanut_pro_list.setDrawingCacheEnabled(true);
    peanut_pro_list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);*/

    peanut_progress.setVisibility(View.VISIBLE);


    apiInterface = ApiClient.createApi();
    peanutCall = apiInterface.getPeanutFragment();

    peanutCall.enqueue(new Callback<List<Peanut>>() {
      @Override
      public void onResponse(Call<List<Peanut>> call, Response<List<Peanut>> response) {

        peanut_progress.setVisibility(View.GONE);


        List<Peanut> peanutList = response.body();
        PeanutAdapter peanutAdapter = new PeanutAdapter(peanutList, getActivity());
        peanut_pro_list.setAdapter(peanutAdapter);
      }

      @Override
      public void onFailure(Call<List<Peanut>> call, Throwable t) {
        peanut_progress.setVisibility(View.GONE);

        Log.e(TAG, "PeanutOnFailure: " + t.getMessage());
        Toast.makeText(getActivity(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
      }
    });

    return view;
  }


}
