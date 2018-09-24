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
import sanaebadi.ir.tandorosti.BeansAdapter.FlourAdapter;
import sanaebadi.ir.tandorosti.BeansModel.Flour;
import sanaebadi.ir.tandorosti.rest.ApiClient;
import sanaebadi.ir.tandorosti.rest.ApiInterface;


public class FlourFragment extends Fragment {

  private static final String TAG = "FlourFragment";


  private RecyclerView flour_pro_list;
  private ProgressBar flour_progress;
  private View snackView;

  private ApiInterface apiInterface;
  private Call<List<Flour>> flourCall;


  public FlourFragment() {
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
    View view = inflater.inflate(R.layout.fragment_flour, container, false);

    flour_pro_list = view.findViewById(R.id.flour_pro_list);
    flour_progress = view.findViewById(R.id.flour_progress);

    //View For Snack Bar
    snackView = getActivity().findViewById(android.R.id.content);

    //Recycler View

    flour_pro_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    flour_pro_list.setItemAnimator(new DefaultItemAnimator());
    flour_pro_list.hasFixedSize();
    flour_pro_list.setNestedScrollingEnabled(false);
/*   flour_pro_list.setFitsSystemWindows(true);
    flour_pro_list.setItemViewCacheSize(20);
    flour_pro_list.setDrawingCacheEnabled(true);
    flour_pro_list.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);*/

    flour_progress.setVisibility(View.VISIBLE);

    apiInterface = ApiClient.createApi();
    flourCall = apiInterface.getFlourFragment();


    flourCall.enqueue(new Callback<List<Flour>>() {
      @Override
      public void onResponse(Call<List<Flour>> call, Response<List<Flour>> response) {

        flour_progress.setVisibility(View.GONE);

        List<Flour> flourList = response.body();
        FlourAdapter dairyAdapter = new FlourAdapter(flourList, getActivity());
        flour_pro_list.setAdapter(dairyAdapter);
      }

      @Override
      public void onFailure(Call<List<Flour>> call, Throwable t) {

        flour_progress.setVisibility(View.GONE);

        Log.e(TAG, "FlourOnFailure: " + t.getMessage());
       // Toast.makeText(getActivity(), "onFailure: " + t.getMessage(), Toast.LENGTH_SHORT).show();


        Snackbar snackbar = Snackbar
          .make(snackView, "خطا در اتصال به اینترنت ", Snackbar.LENGTH_INDEFINITE)
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
