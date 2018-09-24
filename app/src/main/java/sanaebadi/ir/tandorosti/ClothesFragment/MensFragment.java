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
import sanaebadi.ir.tandorosti.ClothesAdapter.KidsAdapter;
import sanaebadi.ir.tandorosti.ClothesAdapter.MensAdapter;
import sanaebadi.ir.tandorosti.ClothesModel.Kids;
import sanaebadi.ir.tandorosti.ClothesModel.Mens;
import sanaebadi.ir.tandorosti.R;
import sanaebadi.ir.tandorosti.rest.ApiClient;
import sanaebadi.ir.tandorosti.rest.ApiInterface;

public class MensFragment extends Fragment {

  private static final String TAG = "MensFragment";



  private RecyclerView mens_pro_list;
  private ProgressBar mens_progress;

  private ApiInterface apiInterface;
  private Call<List<Mens>> mensCall;


  public MensFragment() {
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
    View view = inflater.inflate(R.layout.fragment_mens, container, false);

    mens_pro_list = view.findViewById(R.id.mens_pro_list);
    mens_progress = view.findViewById(R.id.mens_progress);

    //Recycler View

    mens_pro_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    mens_pro_list.setItemAnimator(new DefaultItemAnimator());
    mens_pro_list.hasFixedSize();
    mens_pro_list.setNestedScrollingEnabled(false);
    /*mens_pro_list.setFitsSystemWindows(true);
    mens_pro_list.setItemViewCacheSize(20);
    mens_pro_list.setDrawingCacheEnabled(true);
    mens_pro_list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);*/


    mens_progress.setVisibility(View.VISIBLE);

    apiInterface = ApiClient.createApi();
    mensCall = apiInterface.getMensFragment();

    mensCall.enqueue(new Callback<List<Mens>>() {
      @Override
      public void onResponse(Call<List<Mens>> call, Response<List<Mens>> response) {

        mens_progress.setVisibility(View.GONE);

        List<Mens> mensList = response.body();
        MensAdapter mensAdapter = new MensAdapter(mensList, getActivity());
        mens_pro_list.setAdapter(mensAdapter);
      }

      @Override
      public void onFailure(Call<List<Mens>> call, Throwable t) {

        mens_progress.setVisibility(View.GONE);

        Log.e(TAG, "MensOnFailure: " + t.getMessage());
        Toast.makeText(getActivity(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
      }
    });

    return view;
  }


}
