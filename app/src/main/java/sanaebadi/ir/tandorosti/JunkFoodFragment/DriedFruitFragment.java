package sanaebadi.ir.tandorosti.JunkFoodFragment;

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
import sanaebadi.ir.tandorosti.JunkFoodModel.DriedFruit;
import sanaebadi.ir.tandorosti.R;
import sanaebadi.ir.tandorosti.rest.ApiClient;
import sanaebadi.ir.tandorosti.rest.ApiInterface;


public class DriedFruitFragment extends Fragment {

  private static final String TAG = "DriedFruitFragment";


  private RecyclerView dried_fruit_pro_list;
  private ProgressBar dried_fruit_progress;

  private ApiInterface apiInterface;
  private Call<List<DriedFruit>> driedFruitCall;


  public DriedFruitFragment() {
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
    View view = inflater.inflate(R.layout.fragment_dried_fruit, container, false);

    //  unbinder = ButterKnife.bind(this, view);
    dried_fruit_pro_list = view.findViewById(R.id.dried_fruit_pro_list);
    dried_fruit_progress = view.findViewById(R.id.dried_fruit_progress);

    //Recycler View

    dried_fruit_pro_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    dried_fruit_pro_list.setItemAnimator(new DefaultItemAnimator());
    dried_fruit_pro_list.hasFixedSize();
    dried_fruit_pro_list.setNestedScrollingEnabled(false);

   /* dried_fruit_pro_list.setFitsSystemWindows(true);
    dried_fruit_pro_list.setItemViewCacheSize(20);
    dried_fruit_pro_list.setDrawingCacheEnabled(true);
    dried_fruit_pro_list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);*/

    dried_fruit_progress.setVisibility(View.VISIBLE);

    apiInterface = ApiClient.createApi();
    driedFruitCall = apiInterface.getDriedFruitFragment();

    driedFruitCall.enqueue(new Callback<List<DriedFruit>>() {
      @Override
      public void onResponse(Call<List<DriedFruit>> call, Response<List<DriedFruit>> response) {

        dried_fruit_progress.setVisibility(View.GONE);

        List<DriedFruit> driedFruitList = response.body();
        DriedFruitAdapter driedFruitAdapter = new DriedFruitAdapter(driedFruitList, getActivity());
        dried_fruit_pro_list.setAdapter(driedFruitAdapter);

      }

      @Override
      public void onFailure(Call<List<DriedFruit>> call, Throwable t) {

        dried_fruit_progress.setVisibility(View.GONE);

        Log.e(TAG, "DriedFruitOnFailure: " + t.getMessage());
        Toast.makeText(getActivity(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
      }
    });


    return view;
  }


}
