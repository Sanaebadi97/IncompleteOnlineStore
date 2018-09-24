package sanaebadi.ir.tandorosti.ClothesFragment;

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
import sanaebadi.ir.tandorosti.ClothesAdapter.FeltProductsAdapter;
import sanaebadi.ir.tandorosti.ClothesAdapter.KidsAdapter;
import sanaebadi.ir.tandorosti.ClothesModel.FeltProducts;
import sanaebadi.ir.tandorosti.ClothesModel.Kids;
import sanaebadi.ir.tandorosti.R;
import sanaebadi.ir.tandorosti.rest.ApiClient;
import sanaebadi.ir.tandorosti.rest.ApiInterface;

public class KidsFragment extends Fragment {

  private static final String TAG = "KidsFragment";


  private RecyclerView kids_pro_list;
  private ProgressBar kids_progress;

  private ApiInterface apiInterface;
  private Call<List<Kids>> kidsCall;


  public KidsFragment() {
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
    View view = inflater.inflate(R.layout.fragment_kids, container, false);

    kids_pro_list = view.findViewById(R.id.kids_pro_list);
    kids_progress = view.findViewById(R.id.kids_progress);

    //Recycler View

    kids_pro_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    kids_pro_list.setItemAnimator(new DefaultItemAnimator());
    kids_pro_list.hasFixedSize();
    kids_pro_list.setNestedScrollingEnabled(false);
 /*   kids_pro_list.setFitsSystemWindows(true);
    kids_pro_list.setItemViewCacheSize(20);
    kids_pro_list.setDrawingCacheEnabled(true);
    kids_pro_list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);*/


    kids_progress.setVisibility(View.VISIBLE);

    apiInterface = ApiClient.createApi();
    kidsCall = apiInterface.getKidsFragment();


    kidsCall.enqueue(new Callback<List<Kids>>() {
      @Override
      public void onResponse(Call<List<Kids>> call, Response<List<Kids>> response) {

        kids_progress.setVisibility(View.GONE);

        List<Kids> kidsList = response.body();
        KidsAdapter kidsAdapter = new KidsAdapter(kidsList, getActivity());
        kids_pro_list.setAdapter(kidsAdapter);
      }

      @Override
      public void onFailure(Call<List<Kids>> call, Throwable t) {

        kids_progress.setVisibility(View.GONE);
        Log.e(TAG, "FeltProductsOnFailure: " + t.getMessage());
        Toast.makeText(getActivity(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();

      }
    });

    return view;
  }


}
