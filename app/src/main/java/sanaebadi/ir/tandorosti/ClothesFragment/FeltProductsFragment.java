package sanaebadi.ir.tandorosti.ClothesFragment;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import sanaebadi.ir.tandorosti.ClothesAdapter.FeltProductsAdapter;
import sanaebadi.ir.tandorosti.ClothesModel.FeltProducts;
import sanaebadi.ir.tandorosti.CosmeticsAdapter.CosmeticAdapter;
import sanaebadi.ir.tandorosti.CosmeticsModel.Cosmetic;
import sanaebadi.ir.tandorosti.DrawerActivity.Clothing;
import sanaebadi.ir.tandorosti.R;
import sanaebadi.ir.tandorosti.activity.MainActivity;
import sanaebadi.ir.tandorosti.networkActivity.NetworkActivity;
import sanaebadi.ir.tandorosti.rest.ApiClient;
import sanaebadi.ir.tandorosti.rest.ApiInterface;

public class FeltProductsFragment extends Fragment {

  private static final String TAG = "FeltProductsFragment";


  private RecyclerView felt_products_pro_list;
  private ProgressBar felt_pro_progress;

  private ApiInterface apiInterface;
  private Call<List<FeltProducts>> feltProductsCall;


  public FeltProductsFragment() {
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
    View view = inflater.inflate(R.layout.fragment_felt_products, container, false);


    felt_products_pro_list = view.findViewById(R.id.felt_products_pro_list);
    felt_pro_progress = view.findViewById(R.id.felt_pro_progress);


    //Recycler View

    felt_products_pro_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    felt_products_pro_list.setItemAnimator(new DefaultItemAnimator());
    felt_products_pro_list.hasFixedSize();
    felt_products_pro_list.setNestedScrollingEnabled(false);
   /* felt_products_pro_list.setFitsSystemWindows(true);
    felt_products_pro_list.setItemViewCacheSize(20);
    felt_products_pro_list.setDrawingCacheEnabled(true);
    felt_products_pro_list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);*/


    felt_pro_progress.setVisibility(View.VISIBLE);

    apiInterface = ApiClient.createApi();
    feltProductsCall = apiInterface.getFeltProductsFragment();

    feltProductsCall.enqueue(new Callback<List<FeltProducts>>() {
      @Override
      public void onResponse(Call<List<FeltProducts>> call, Response<List<FeltProducts>> response) {

        felt_pro_progress.setVisibility(View.GONE);

        List<FeltProducts> feltProductsList = response.body();
        FeltProductsAdapter feltProductsAdapter = new FeltProductsAdapter(feltProductsList, getActivity());
        felt_products_pro_list.setAdapter(feltProductsAdapter);
      }

      @Override
      public void onFailure(Call<List<FeltProducts>> call, Throwable t) {

        felt_pro_progress.setVisibility(View.GONE);

        Log.e(TAG, "FeltProductsOnFailure: " + t.getMessage());
        Toast.makeText(getActivity(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
      }
    });


    return view;
  }


}
