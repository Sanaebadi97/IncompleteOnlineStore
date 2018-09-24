package sanaebadi.ir.tandorosti.NewsFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import sanaebadi.ir.tandorosti.NewsAdapter.DairyAdapter;
import sanaebadi.ir.tandorosti.NewsModel.Dairy;
import sanaebadi.ir.tandorosti.rest.ApiClient;
import sanaebadi.ir.tandorosti.rest.ApiInterface;


public class DairyFragment extends Fragment {

  private static final String TAG = "DairyFragment";

  private RecyclerView dairy_pro_list;
  private ProgressBar dairy_progress;

  private ApiInterface apiInterface;
  private Call<List<Dairy>> dairyCall;

  public DairyFragment() {

  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_dairy, container, false);

    dairy_pro_list = view.findViewById(R.id.dairy_pro_list);
    dairy_progress = view.findViewById(R.id.dairy_progress);

    //Recycler View

    dairy_pro_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    dairy_pro_list.setItemAnimator(new DefaultItemAnimator());
    dairy_pro_list.hasFixedSize();
    dairy_pro_list.setNestedScrollingEnabled(false);
 /*   dairy_pro_list.setFitsSystemWindows(true);
    dairy_pro_list.setItemViewCacheSize(20);
    dairy_pro_list.setDrawingCacheEnabled(true);
    dairy_pro_list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);*/


    dairy_progress.setVisibility(View.VISIBLE);

    apiInterface = ApiClient.createApi();
    dairyCall = apiInterface.getDairyFragment();

    dairyCall.enqueue(new Callback<List<Dairy>>() {
      @Override
      public void onResponse(Call<List<Dairy>> call, Response<List<Dairy>> response) {

        dairy_progress.setVisibility(View.GONE);

        List<Dairy> dairyList = response.body();
        DairyAdapter dairyAdapter = new DairyAdapter(dairyList, getActivity());
        dairy_pro_list.setAdapter(dairyAdapter);
      }

      @Override
      public void onFailure(Call<List<Dairy>> call, Throwable t) {
        dairy_progress.setVisibility(View.GONE);

        Log.e(TAG, "dairyOnFailure: " + t.getMessage());
        Toast.makeText(getActivity(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();

      }
    });

    return view;
  }


}
