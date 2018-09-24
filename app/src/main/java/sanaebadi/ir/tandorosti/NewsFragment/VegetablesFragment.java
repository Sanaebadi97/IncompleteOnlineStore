package sanaebadi.ir.tandorosti.NewsFragment;

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
import sanaebadi.ir.tandorosti.NewsAdapter.VegetablesAdapter;
import sanaebadi.ir.tandorosti.NewsModel.Vegetables;
import sanaebadi.ir.tandorosti.rest.ApiClient;
import sanaebadi.ir.tandorosti.rest.ApiInterface;


public class VegetablesFragment extends Fragment {

  private static final String TAG = "VegetablesFragment";


  private RecyclerView vegetables_pro_list;
  private ProgressBar vegetables_progress;

  private ApiInterface apiInterface;
  private Call<List<Vegetables>> vegetablesCall;

  public VegetablesFragment() {
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_vegetables, container, false);

    vegetables_pro_list = view.findViewById(R.id.vegetables_pro_list);
    vegetables_progress = view.findViewById(R.id.vegetables_progress);

    //Recycler View

    vegetables_pro_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    vegetables_pro_list.setItemAnimator(new DefaultItemAnimator());
    vegetables_pro_list.hasFixedSize();
    vegetables_pro_list.setNestedScrollingEnabled(false);

   /* vegetables_pro_list.setFitsSystemWindows(true);
    vegetables_pro_list.setItemViewCacheSize(20);
    vegetables_pro_list.setDrawingCacheEnabled(true);
    vegetables_pro_list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);*/

    vegetables_progress.setVisibility(View.VISIBLE);

    apiInterface = ApiClient.createApi();
    vegetablesCall = apiInterface.getVegetablesFragment();

    vegetablesCall.enqueue(new Callback<List<Vegetables>>() {
      @Override
      public void onResponse(Call<List<Vegetables>> call, Response<List<Vegetables>> response) {

        vegetables_progress.setVisibility(View.GONE);


        List<Vegetables> vegetablesList = response.body();
        VegetablesAdapter vegetablesAdapter = new VegetablesAdapter(vegetablesList, getActivity());
        vegetables_pro_list.setAdapter(vegetablesAdapter);
      }

      @Override
      public void onFailure(Call<List<Vegetables>> call, Throwable t) {

        vegetables_progress.setVisibility(View.GONE);

        Log.e(TAG, "vegetablesOnFailure: " + t.getMessage());
        Toast.makeText(getActivity(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
      }
    });
    return view;


  }


}
