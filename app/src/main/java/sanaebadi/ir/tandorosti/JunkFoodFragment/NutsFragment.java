package sanaebadi.ir.tandorosti.JunkFoodFragment;

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
import sanaebadi.ir.tandorosti.JUnkFoodAdapter.DriedFruitAdapter;
import sanaebadi.ir.tandorosti.JUnkFoodAdapter.NutsAdapter;
import sanaebadi.ir.tandorosti.JunkFoodModel.DriedFruit;
import sanaebadi.ir.tandorosti.JunkFoodModel.Nuts;
import sanaebadi.ir.tandorosti.R;
import sanaebadi.ir.tandorosti.rest.ApiClient;
import sanaebadi.ir.tandorosti.rest.ApiInterface;


public class NutsFragment extends Fragment {

  private static final String TAG = "NutsFragment";


  private RecyclerView nuts_pro_list;
  private ProgressBar nuts_progress;

  private ApiInterface apiInterface;
  private Call<List<Nuts>> nutsCall;


  public NutsFragment() {
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
    View view = inflater.inflate(R.layout.fragment_nuts, container, false);

    nuts_pro_list = view.findViewById(R.id.nuts_pro_list);
    nuts_progress = view.findViewById(R.id.nuts_progress);

    //Recycler View

    nuts_pro_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    nuts_pro_list.setItemAnimator(new DefaultItemAnimator());
    nuts_pro_list.hasFixedSize();
    nuts_pro_list.setNestedScrollingEnabled(false);
   /* nuts_pro_list.setFitsSystemWindows(true);
    nuts_pro_list.setItemViewCacheSize(20);
    nuts_pro_list.setDrawingCacheEnabled(true);
    nuts_pro_list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);*/


    nuts_progress.setVisibility(View.VISIBLE);

    apiInterface = ApiClient.createApi();
    nutsCall = apiInterface.getNutsFragment();

    nutsCall.enqueue(new Callback<List<Nuts>>() {
      @Override
      public void onResponse(Call<List<Nuts>> call, Response<List<Nuts>> response) {

        nuts_progress.setVisibility(View.GONE);

        List<Nuts> nutsList = response.body();
        NutsAdapter nutsAdapter = new NutsAdapter(nutsList, getActivity());
        nuts_pro_list.setAdapter(nutsAdapter);
      }

      @Override
      public void onFailure(Call<List<Nuts>> call, Throwable t) {

        nuts_progress.setVisibility(View.GONE);

        Log.e(TAG, "NutsFruitOnFailure: " + t.getMessage());
        Toast.makeText(getActivity(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
      }
    });


    return view;


  }


}
