package sanaebadi.ir.tandorosti.BeansFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
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
import sanaebadi.ir.tandorosti.BeansAdapter.BranAdapter;
import sanaebadi.ir.tandorosti.BeansModel.Bran;
import sanaebadi.ir.tandorosti.rest.ApiClient;
import sanaebadi.ir.tandorosti.rest.ApiInterface;


public class BranFragment extends Fragment {

  private static final String TAG = "BranFragment";


  private RecyclerView bran_pro_list;
  private ProgressBar bran_progress;
  private View snackView;

  private ApiInterface apiInterface;
  private Call<List<Bran>> branCall;

  public BranFragment() {
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
    View view = inflater.inflate(R.layout.fragment_bran, container, false);

    bran_pro_list = view.findViewById(R.id.bran_pro_list);
    bran_progress = view.findViewById(R.id.bran_progress);

    //View For Snack Bar
    snackView = getActivity().findViewById(android.R.id.content);

    //Recycler View

    bran_pro_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    bran_pro_list.setItemAnimator(new DefaultItemAnimator());
    bran_pro_list.hasFixedSize();
    bran_pro_list.setNestedScrollingEnabled(false);
   /* bran_pro_list.setFitsSystemWindows(true);
    bran_pro_list.setItemViewCacheSize(20);
    bran_pro_list.setDrawingCacheEnabled(true);
    bran_pro_list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);*/

   bran_progress.setVisibility(View.VISIBLE);

    apiInterface = ApiClient.createApi();
    branCall = apiInterface.getBranFragment();

    branCall.enqueue(new Callback<List<Bran>>() {
      @Override
      public void onResponse(Call<List<Bran>> call, Response<List<Bran>> response) {

        bran_progress.setVisibility(View.GONE);

        List<Bran> branList = response.body();
        BranAdapter dairyAdapter = new BranAdapter(branList, getActivity());
        bran_pro_list.setAdapter(dairyAdapter);
      }

      @Override
      public void onFailure(Call<List<Bran>> call, Throwable t) {

        bran_progress.setVisibility(View.GONE);

        Log.e(TAG, "branOnFailure: " + t.getMessage());
        //Toast.makeText(getActivity(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();

        Snackbar snackbar = Snackbar
          .make(snackView, "خطا در اتصال به سرور ", Snackbar.LENGTH_INDEFINITE)
          .setAction("تنظیمات", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
            }
          });

        ViewCompat.setLayoutDirection(snackbar.getView(), ViewCompat.LAYOUT_DIRECTION_RTL);

        snackbar.show();


      }
    });


    return view;
  }


}