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
import sanaebadi.ir.tandorosti.NewsAdapter.ProteinAdapter;
import sanaebadi.ir.tandorosti.NewsModel.Protein;
import sanaebadi.ir.tandorosti.rest.ApiClient;
import sanaebadi.ir.tandorosti.rest.ApiInterface;

public class ProteinFragment extends Fragment {

  private static final String TAG = "ProteinFragment";

  private RecyclerView protein_pro_list;
  private ProgressBar protein_progress;

  private ApiInterface apiInterface;
  private Call<List<Protein>> proteinCall;


  public ProteinFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_protein, container, false);

    protein_pro_list = view.findViewById(R.id.protein_pro_list);
    protein_progress = view.findViewById(R.id.protein_progress);

    //Recycler View

    protein_pro_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    protein_pro_list.setItemAnimator(new DefaultItemAnimator());
    protein_pro_list.hasFixedSize();
    protein_pro_list.setNestedScrollingEnabled(false);

   /* protein_pro_list.setFitsSystemWindows(true);
    protein_pro_list.setItemViewCacheSize(20);
    protein_pro_list.setDrawingCacheEnabled(true);
    protein_pro_list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);*/


    protein_progress.setVisibility(View.VISIBLE);

    apiInterface = ApiClient.createApi();
    proteinCall = apiInterface.getProteinFragment();


    proteinCall.enqueue(new Callback<List<Protein>>() {
      @Override
      public void onResponse(Call<List<Protein>> call, Response<List<Protein>> response) {

        protein_progress.setVisibility(View.GONE);

        List<Protein> proteinList = response.body();
        ProteinAdapter proteinAdapter = new ProteinAdapter(proteinList, getActivity());
        protein_pro_list.setAdapter(proteinAdapter);
      }

      @Override
      public void onFailure(Call<List<Protein>> call, Throwable t) {

        protein_progress.setVisibility(View.GONE);

        Log.e(TAG, "proteinOnFailure: " + t.getMessage());
        Toast.makeText(getActivity(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
      }
    });

    return view;
  }


}
