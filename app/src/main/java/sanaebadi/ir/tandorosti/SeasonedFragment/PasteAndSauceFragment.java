package sanaebadi.ir.tandorosti.SeasonedFragment;

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
import sanaebadi.ir.tandorosti.R;
import sanaebadi.ir.tandorosti.SeasonedAdapter.HoneyAndJamAdapter;
import sanaebadi.ir.tandorosti.SeasonedAdapter.PasteAndSauceAdapter;
import sanaebadi.ir.tandorosti.SeasonedModel.HoneyAndJam;
import sanaebadi.ir.tandorosti.SeasonedModel.PasteAndSauce;
import sanaebadi.ir.tandorosti.rest.ApiClient;
import sanaebadi.ir.tandorosti.rest.ApiInterface;


public class PasteAndSauceFragment extends Fragment {

  private static final String TAG = "PasteAndSauceFragment";


  private RecyclerView paste_sauce_pro_list;
  private ProgressBar paste_sauce_progress;

  private ApiInterface apiInterface;
  private Call<List<PasteAndSauce>> pasteAndSauceCall;

  public PasteAndSauceFragment() {
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
    View view = inflater.inflate(R.layout.fragment_paste_and_sauce, container, false);

    paste_sauce_pro_list = view.findViewById(R.id.paste_sauce_pro_list);
    paste_sauce_progress = view.findViewById(R.id.paste_sauce_progress);

    //Recycler View

    paste_sauce_pro_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    paste_sauce_pro_list.setItemAnimator(new DefaultItemAnimator());
    paste_sauce_pro_list.hasFixedSize();
    paste_sauce_pro_list.setNestedScrollingEnabled(false);

   /* paste_sauce_pro_list.setFitsSystemWindows(true);
    paste_sauce_pro_list.setItemViewCacheSize(20);
    paste_sauce_pro_list.setDrawingCacheEnabled(true);
    paste_sauce_pro_list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);*/

    paste_sauce_progress.setVisibility(View.VISIBLE);


    apiInterface = ApiClient.createApi();
    pasteAndSauceCall = apiInterface.getPasteAndSauceFragment();

    pasteAndSauceCall.enqueue(new Callback<List<PasteAndSauce>>() {
      @Override
      public void onResponse(Call<List<PasteAndSauce>> call, Response<List<PasteAndSauce>> response) {

        paste_sauce_progress.setVisibility(View.GONE);


        List<PasteAndSauce> pasteAndSauceList = response.body();
        PasteAndSauceAdapter pasteAndSauceAdapter = new PasteAndSauceAdapter(pasteAndSauceList, getActivity());
        paste_sauce_pro_list.setAdapter(pasteAndSauceAdapter);
      }

      @Override
      public void onFailure(Call<List<PasteAndSauce>> call, Throwable t) {

        paste_sauce_progress.setVisibility(View.GONE);

        Log.e(TAG, "PasteAndSauceOnFailure: " + t.getMessage());
        Toast.makeText(getActivity(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();

      }
    });


    return view;
  }


}
