package sanaebadi.ir.tandorosti.BeveragesFragment;

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
import sanaebadi.ir.tandorosti.BeveragesAdapter.TeaAdapter;
import sanaebadi.ir.tandorosti.BeveragesModel.Tea;
import sanaebadi.ir.tandorosti.rest.ApiClient;
import sanaebadi.ir.tandorosti.rest.ApiInterface;


public class TeaFragment extends Fragment {

  private static final String TAG = "TeaFragment";


  private RecyclerView tea_pro_list;
  private ProgressBar tea_progress;


  private ApiInterface apiInterface;
  private Call<List<Tea>> teaCall;

  public TeaFragment() {
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
    View view = inflater.inflate(R.layout.fragment_tea, container, false);

    tea_pro_list = view.findViewById(R.id.tea_pro_list);
    tea_progress = view.findViewById(R.id.tea_progress);

    //Recycler View

    tea_pro_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    tea_pro_list.setItemAnimator(new DefaultItemAnimator());
    tea_pro_list.hasFixedSize();
    tea_pro_list.setNestedScrollingEnabled(false);

  /*  tea_pro_list.setFitsSystemWindows(true);
    tea_pro_list.setItemViewCacheSize(20);
    tea_pro_list.setDrawingCacheEnabled(true);
    tea_pro_list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);*/


    tea_progress.setVisibility(View.VISIBLE);

    apiInterface = ApiClient.createApi();
    teaCall = apiInterface.getTeaFragment();

    teaCall.enqueue(new Callback<List<Tea>>() {
      @Override
      public void onResponse(Call<List<Tea>> call, Response<List<Tea>> response) {

        tea_progress.setVisibility(View.GONE);

        List<Tea> teaList = response.body();
        TeaAdapter teaAdapter = new TeaAdapter(teaList, getActivity());
        tea_pro_list.setAdapter(teaAdapter);
      }

      @Override
      public void onFailure(Call<List<Tea>> call, Throwable t) {

        tea_progress.setVisibility(View.GONE);

        Log.e(TAG, "TeaOnFailure: " + t.getMessage());
        Toast.makeText(getActivity(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
      }
    });


    return view;
  }


}
