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
import sanaebadi.ir.tandorosti.ClothesAdapter.MensAdapter;
import sanaebadi.ir.tandorosti.ClothesAdapter.WomensAdapter;
import sanaebadi.ir.tandorosti.ClothesModel.Mens;
import sanaebadi.ir.tandorosti.ClothesModel.Womens;
import sanaebadi.ir.tandorosti.R;
import sanaebadi.ir.tandorosti.rest.ApiClient;
import sanaebadi.ir.tandorosti.rest.ApiInterface;


public class WomensFragment extends Fragment {

  private static final String TAG = "WomensFragment";

  Unbinder unbinder;

  private RecyclerView women_pro_list;
  private ProgressBar women_progress;

  private ApiInterface apiInterface;
  private Call<List<Womens>> womensCall;


  public WomensFragment() {
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
    View view = inflater.inflate(R.layout.fragment_womens, container, false);

    women_pro_list = view.findViewById(R.id.women_pro_list);
    women_progress = view.findViewById(R.id.womens_progress);

    //Recycler View

    women_pro_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    women_pro_list.setItemAnimator(new DefaultItemAnimator());
    women_pro_list.hasFixedSize();
    women_pro_list.setNestedScrollingEnabled(false);

   /* women_pro_list.setFitsSystemWindows(true);
    women_pro_list.setItemViewCacheSize(20);
    women_pro_list.setDrawingCacheEnabled(true);
    women_pro_list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);*/

    women_progress.setVisibility(View.VISIBLE);

    apiInterface = ApiClient.createApi();
    womensCall = apiInterface.getWomensFragment();


    womensCall.enqueue(new Callback<List<Womens>>() {
      @Override
      public void onResponse(Call<List<Womens>> call, Response<List<Womens>> response) {

        women_progress.setVisibility(View.GONE);

        List<Womens> womensList = response.body();
        WomensAdapter womensAdapter = new WomensAdapter(womensList, getActivity());
        women_pro_list.setAdapter(womensAdapter);
      }

      @Override
      public void onFailure(Call<List<Womens>> call, Throwable t) {

        women_progress.setVisibility(View.GONE);

        Log.e(TAG, "WomensOnFailure: " + t.getMessage());
        Toast.makeText(getActivity(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
      }
    });


    return view;
  }


}
