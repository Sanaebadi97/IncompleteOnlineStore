package sanaebadi.ir.tandorosti.CosmeticsFragments;

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
import sanaebadi.ir.tandorosti.CosmeticsModel.Cosmetic;
import sanaebadi.ir.tandorosti.JUnkFoodAdapter.BiscuitsAdapter;
import sanaebadi.ir.tandorosti.JunkFoodModel.Biscuits;
import sanaebadi.ir.tandorosti.R;
import sanaebadi.ir.tandorosti.rest.ApiClient;
import sanaebadi.ir.tandorosti.rest.ApiInterface;


public class CosmeticsFragment extends Fragment {

  private static final String TAG = "CosmeticsFragment";


  private RecyclerView cosmetics_pro_list;
  private ProgressBar cosmetics_progress;

  private ApiInterface apiInterface;
  private Call<List<Cosmetic>> cosmeticCall;


  public CosmeticsFragment() {
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
    View view = inflater.inflate(R.layout.fragment_cosmetics, container, false);

    cosmetics_pro_list =view.findViewById(R.id.cosmetics_pro_list);
    cosmetics_progress =view.findViewById(R.id.cosmetics_progress);

    //Recycler View

    cosmetics_pro_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    cosmetics_pro_list.setItemAnimator(new DefaultItemAnimator());
    cosmetics_pro_list.hasFixedSize();
    cosmetics_pro_list.setNestedScrollingEnabled(false);
  /*  cosmetics_pro_list.setFitsSystemWindows(true);
    cosmetics_pro_list.setItemViewCacheSize(20);
    cosmetics_pro_list.setDrawingCacheEnabled(true);
    cosmetics_pro_list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);*/


    cosmetics_progress.setVisibility(View.VISIBLE);

    apiInterface = ApiClient.createApi();
    cosmeticCall = apiInterface.getCosmeticFragment();

    cosmeticCall.enqueue(new Callback<List<Cosmetic>>() {
      @Override
      public void onResponse(Call<List<Cosmetic>> call, Response<List<Cosmetic>> response) {

        cosmetics_progress.setVisibility(View.GONE);

        List<Cosmetic> cosmeticList = response.body();
        CosmeticAdapter cosmeticAdapter = new CosmeticAdapter(cosmeticList, getActivity());
        cosmetics_pro_list.setAdapter(cosmeticAdapter);
      }

      @Override
      public void onFailure(Call<List<Cosmetic>> call, Throwable t) {

        cosmetics_progress.setVisibility(View.GONE);

        Log.e(TAG, "CosmeticOnFailure: " + t.getMessage());
        Toast.makeText(getActivity(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
      }
    });


    return view;
  }


}
