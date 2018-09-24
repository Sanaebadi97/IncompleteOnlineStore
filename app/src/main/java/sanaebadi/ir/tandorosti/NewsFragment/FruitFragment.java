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
import sanaebadi.ir.tandorosti.NewsAdapter.FruitAdapter;
import sanaebadi.ir.tandorosti.R;
import sanaebadi.ir.tandorosti.NewsModel.Fruit;
import sanaebadi.ir.tandorosti.rest.ApiClient;
import sanaebadi.ir.tandorosti.rest.ApiInterface;


public class FruitFragment extends Fragment {

  private static final String TAG = "FruitFragment";

//  Unbinder unbinder;

  private RecyclerView fruit_pro_list;
  private ProgressBar fruit_progress;

  private ApiInterface apiInterface;
  private Call<List<Fruit>> fruitCall;

  public FruitFragment() {

  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_fruit, container, false);

    fruit_pro_list = view.findViewById(R.id.fruit_pro_list);
    fruit_progress = view.findViewById(R.id.fruit_progress);

    //Recycler View

    fruit_pro_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    fruit_pro_list.setItemAnimator(new DefaultItemAnimator());
    fruit_pro_list.hasFixedSize();
    fruit_pro_list.setNestedScrollingEnabled(false);
    /*fruit_pro_list.setFitsSystemWindows(true);
    fruit_pro_list.setItemViewCacheSize(20);
    fruit_pro_list.setDrawingCacheEnabled(true);
    fruit_pro_list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);*/

    fruit_progress.setVisibility(View.VISIBLE);

    apiInterface = ApiClient.createApi();
    fruitCall = apiInterface.getFruitFragment();

    fruitCall.enqueue(new Callback<List<Fruit>>() {
      @Override
      public void onResponse(Call<List<Fruit>> call, Response<List<Fruit>> response) {

        fruit_progress.setVisibility(View.GONE);

        List<Fruit> fruitList = response.body();
        FruitAdapter fruitAdapter = new FruitAdapter(fruitList, getActivity());
        fruit_pro_list.setAdapter(fruitAdapter);

      }

      @Override
      public void onFailure(Call<List<Fruit>> call, Throwable t) {

        fruit_progress.setVisibility(View.GONE);

        Log.e(TAG, "fruitOnFailure: " + t.getMessage());
        Toast.makeText(getActivity(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
      }
    });

    return view;
  }


}
