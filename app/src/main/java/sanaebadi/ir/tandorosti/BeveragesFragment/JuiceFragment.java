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
import sanaebadi.ir.tandorosti.BeveragesAdapter.JuiceAdapter;
import sanaebadi.ir.tandorosti.BeveragesModel.Juice;
import sanaebadi.ir.tandorosti.rest.ApiClient;
import sanaebadi.ir.tandorosti.rest.ApiInterface;


public class JuiceFragment extends Fragment {

  private static final String TAG = "JuiceFragment";


  private RecyclerView juice_pro_list;
  private ProgressBar juice_progress;

  private ApiInterface apiInterface;
  private Call<List<Juice>> juiceCall;

  public JuiceFragment() {
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
    View view = inflater.inflate(R.layout.fragment_juice, container, false);

    juice_pro_list = view.findViewById(R.id.juice_pro_list);
    juice_progress = view.findViewById(R.id.juice_progress);

    //Recycler View

    juice_pro_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    juice_pro_list.setItemAnimator(new DefaultItemAnimator());
    juice_pro_list.hasFixedSize();
    juice_pro_list.setNestedScrollingEnabled(false);

   /* juice_pro_list.setFitsSystemWindows(true);
    juice_pro_list.setItemViewCacheSize(20);
    juice_pro_list.setDrawingCacheEnabled(true);
    juice_pro_list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);*/


   juice_progress.setVisibility(View.VISIBLE);

    apiInterface = ApiClient.createApi();
    juiceCall = apiInterface.getJuiceFragment();


    juiceCall.enqueue(new Callback<List<Juice>>() {
      @Override
      public void onResponse(Call<List<Juice>> call, Response<List<Juice>> response) {

        juice_progress.setVisibility(View.GONE);

        List<Juice> juiceList = response.body();
        JuiceAdapter juiceAdapter = new JuiceAdapter(juiceList, getActivity());
        juice_pro_list.setAdapter(juiceAdapter);
      }

      @Override
      public void onFailure(Call<List<Juice>> call, Throwable t) {

        juice_progress.setVisibility(View.GONE);

        Log.e(TAG, "JuiceOnFailure: " + t.getMessage());
        Toast.makeText(getActivity(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
      }
    });

    return view;
  }



}
