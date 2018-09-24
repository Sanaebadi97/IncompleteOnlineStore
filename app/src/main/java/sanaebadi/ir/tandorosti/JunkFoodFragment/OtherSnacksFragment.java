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
import sanaebadi.ir.tandorosti.JUnkFoodAdapter.NutsAdapter;
import sanaebadi.ir.tandorosti.JUnkFoodAdapter.OtherSnacksAdapter;
import sanaebadi.ir.tandorosti.JunkFoodModel.Nuts;
import sanaebadi.ir.tandorosti.JunkFoodModel.OtherSnacks;
import sanaebadi.ir.tandorosti.R;
import sanaebadi.ir.tandorosti.rest.ApiClient;
import sanaebadi.ir.tandorosti.rest.ApiInterface;


public class OtherSnacksFragment extends Fragment {

  private static final String TAG = "OtherSnacksFragment";


  private RecyclerView other_snack_pro_list;
  private ProgressBar other_snack_progress;

  private ApiInterface apiInterface;
  private Call<List<OtherSnacks>> otherSnacksCall;


  public OtherSnacksFragment() {
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
    View view = inflater.inflate(R.layout.fragment_other_snacks, container, false);

    other_snack_pro_list = view.findViewById(R.id.other_snack_pro_list);
    other_snack_progress = view.findViewById(R.id.other_snack_progress);

    //Recycler View

    other_snack_pro_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    other_snack_pro_list.setItemAnimator(new DefaultItemAnimator());
    other_snack_pro_list.hasFixedSize();
    other_snack_pro_list.setNestedScrollingEnabled(false);

   /* other_snack_pro_list.setFitsSystemWindows(true);
    other_snack_pro_list.setItemViewCacheSize(20);
    other_snack_pro_list.setDrawingCacheEnabled(true);
    other_snack_pro_list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);*/


    other_snack_progress.setVisibility(View.VISIBLE);

    apiInterface = ApiClient.createApi();
    otherSnacksCall = apiInterface.getOtherSnacksFragment();


    otherSnacksCall.enqueue(new Callback<List<OtherSnacks>>() {
      @Override
      public void onResponse(Call<List<OtherSnacks>> call, Response<List<OtherSnacks>> response) {

        other_snack_progress.setVisibility(View.GONE);

        List<OtherSnacks> otherSnacksList = response.body();
        OtherSnacksAdapter otherSnacksAdapter = new OtherSnacksAdapter(otherSnacksList, getActivity());
        other_snack_pro_list.setAdapter(otherSnacksAdapter);
      }

      @Override
      public void onFailure(Call<List<OtherSnacks>> call, Throwable t) {

        other_snack_progress.setVisibility(View.GONE);

        Log.e(TAG, "OtherSnackFruitOnFailure: " + t.getMessage());
        Toast.makeText(getActivity(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
      }
    });


    return view;
  }


}
