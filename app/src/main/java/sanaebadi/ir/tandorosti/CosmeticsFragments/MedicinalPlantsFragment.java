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
import sanaebadi.ir.tandorosti.CosmeticsAdapter.CosmeticAdapter;
import sanaebadi.ir.tandorosti.CosmeticsAdapter.MedicinalPlantsAdapter;
import sanaebadi.ir.tandorosti.CosmeticsModel.Cosmetic;
import sanaebadi.ir.tandorosti.CosmeticsModel.MedicinalPlants;
import sanaebadi.ir.tandorosti.R;
import sanaebadi.ir.tandorosti.rest.ApiClient;
import sanaebadi.ir.tandorosti.rest.ApiInterface;


public class MedicinalPlantsFragment extends Fragment {


  private static final String TAG = "MedicinalPlantsFragment";


  private RecyclerView medicinal_plants_pro_list;
  private ProgressBar medicinal_plants_progress;

  private ApiInterface apiInterface;
  private Call<List<MedicinalPlants>> medicinalPlantsCall;

  public MedicinalPlantsFragment() {
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
    View view = inflater.inflate(R.layout.fragment_medicinal_plants, container, false);


    medicinal_plants_pro_list = view.findViewById(R.id.medicinal_plants_pro_list);
    medicinal_plants_progress = view.findViewById(R.id.medicinal_plants_progress);

    //Recycler View

    medicinal_plants_pro_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    medicinal_plants_pro_list.setItemAnimator(new DefaultItemAnimator());
    medicinal_plants_pro_list.hasFixedSize();
    medicinal_plants_pro_list.setNestedScrollingEnabled(false);

   /* medicinal_plants_pro_list.setFitsSystemWindows(true);
    medicinal_plants_pro_list.setItemViewCacheSize(20);
    medicinal_plants_pro_list.setDrawingCacheEnabled(true);
    medicinal_plants_pro_list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);*/


    medicinal_plants_progress.setVisibility(View.VISIBLE);

    apiInterface = ApiClient.createApi();
    medicinalPlantsCall = apiInterface.getMedicinalPlantFragment();


    medicinalPlantsCall.enqueue(new Callback<List<MedicinalPlants>>() {
      @Override
      public void onResponse(Call<List<MedicinalPlants>> call, Response<List<MedicinalPlants>> response) {

        medicinal_plants_progress.setVisibility(View.GONE);

        List<MedicinalPlants> medicinalPlantsList = response.body();
        MedicinalPlantsAdapter medicinalPlantsAdapter = new MedicinalPlantsAdapter(medicinalPlantsList, getActivity());
        medicinal_plants_pro_list.setAdapter(medicinalPlantsAdapter);
      }

      @Override
      public void onFailure(Call<List<MedicinalPlants>> call, Throwable t) {

        medicinal_plants_progress.setVisibility(View.GONE);

        Log.e(TAG, "MedicinalPlantsOnFailure: " + t.getMessage());
        Toast.makeText(getActivity(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
      }
    });


    return view;
  }


}
