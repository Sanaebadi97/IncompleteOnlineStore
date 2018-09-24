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
import sanaebadi.ir.tandorosti.BeveragesAdapter.DistillatesAdapter;
import sanaebadi.ir.tandorosti.BeveragesModel.Distillates;
import sanaebadi.ir.tandorosti.JUnkFoodAdapter.BiscuitsAdapter;
import sanaebadi.ir.tandorosti.JunkFoodModel.Biscuits;
import sanaebadi.ir.tandorosti.NewsModel.Dairy;
import sanaebadi.ir.tandorosti.R;
import sanaebadi.ir.tandorosti.rest.ApiClient;
import sanaebadi.ir.tandorosti.rest.ApiInterface;


public class BiscuitsFragment extends Fragment {

  private static final String TAG = "BiscuitsFragment";


  private RecyclerView biscuit_pro_list;
  private ProgressBar biscuit_progress;

  private ApiInterface apiInterface;
  private Call<List<Biscuits>> biscuitsCall;


  public BiscuitsFragment() {
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
    View view = inflater.inflate(R.layout.fragment_biscuits, container, false);

    biscuit_pro_list = view.findViewById(R.id.biscuit_pro_list);
    biscuit_progress = view.findViewById(R.id.biscuit_progress);

    //Recycler View

    biscuit_pro_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    biscuit_pro_list.setItemAnimator(new DefaultItemAnimator());
    biscuit_pro_list.hasFixedSize();
    biscuit_pro_list.setNestedScrollingEnabled(false);

   /* biscuit_pro_list.setFitsSystemWindows(true);
    biscuit_pro_list.setItemViewCacheSize(20);
    biscuit_pro_list.setDrawingCacheEnabled(true);
    biscuit_pro_list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);*/

    biscuit_progress.setVisibility(View.VISIBLE);

    apiInterface = ApiClient.createApi();
    biscuitsCall = apiInterface.getBiscuitFragment();


    biscuitsCall.enqueue(new Callback<List<Biscuits>>() {
      @Override
      public void onResponse(Call<List<Biscuits>> call, Response<List<Biscuits>> response) {

        biscuit_progress.setVisibility(View.GONE);


        List<Biscuits> biscuitsList = response.body();
        BiscuitsAdapter distillatesAdapter = new BiscuitsAdapter(biscuitsList, getActivity());
        biscuit_pro_list.setAdapter(distillatesAdapter);
      }

      @Override
      public void onFailure(Call<List<Biscuits>> call, Throwable t) {

        biscuit_progress.setVisibility(View.GONE);

        Log.e(TAG, "DistillatesOnFailure: " + t.getMessage());
        Toast.makeText(getActivity(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
      }
    });

    return view;
  }


}
