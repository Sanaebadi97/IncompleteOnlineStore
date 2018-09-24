package sanaebadi.ir.tandorosti.CosmeticsFragments;

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
import sanaebadi.ir.tandorosti.CosmeticsAdapter.MedicinalPlantsAdapter;
import sanaebadi.ir.tandorosti.CosmeticsAdapter.SanitaryAdapter;
import sanaebadi.ir.tandorosti.CosmeticsModel.MedicinalPlants;
import sanaebadi.ir.tandorosti.CosmeticsModel.Sanitary;
import sanaebadi.ir.tandorosti.R;
import sanaebadi.ir.tandorosti.rest.ApiClient;
import sanaebadi.ir.tandorosti.rest.ApiInterface;


public class SanitaryFragment extends Fragment {

  private static final String TAG = "SanitaryFragment";


  private RecyclerView sanitary_pro_list;
  private ProgressBar sanitary_progress;

  private ApiInterface apiInterface;
  private Call<List<Sanitary>> sanitaryCall;

  public SanitaryFragment() {
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
    View view = inflater.inflate(R.layout.fragment_sanitary, container, false);

    sanitary_pro_list = view.findViewById(R.id.sanitary_pro_list);
    sanitary_progress = view.findViewById(R.id.sanitary_progress);

    //Recycler View

    sanitary_pro_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    sanitary_pro_list.setItemAnimator(new DefaultItemAnimator());
    sanitary_pro_list.hasFixedSize();
    sanitary_pro_list.setNestedScrollingEnabled(false);
  /*  sanitary_pro_list.setFitsSystemWindows(true);
    sanitary_pro_list.setItemViewCacheSize(20);
    sanitary_pro_list.setDrawingCacheEnabled(true);
    sanitary_pro_list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);*/

    sanitary_progress.setVisibility(View.VISIBLE);

    apiInterface = ApiClient.createApi();
    sanitaryCall = apiInterface.getSanitaryFragment();

    sanitaryCall.enqueue(new Callback<List<Sanitary>>() {
      @Override
      public void onResponse(Call<List<Sanitary>> call, Response<List<Sanitary>> response) {

        sanitary_progress.setVisibility(View.GONE);

        List<Sanitary> sanitaryList = response.body();
        SanitaryAdapter sanitaryAdapter = new SanitaryAdapter(sanitaryList, getActivity());
        sanitary_pro_list.setAdapter(sanitaryAdapter);
      }

      @Override
      public void onFailure(Call<List<Sanitary>> call, Throwable t) {

        sanitary_progress.setVisibility(View.GONE);

        Log.e(TAG, "SanitaryOnFailure: " + t.getMessage());
        Toast.makeText(getActivity(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
      }
    });

    return view;
  }


}
