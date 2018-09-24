package sanaebadi.ir.tandorosti.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sanaebadi.ir.tandorosti.R;
import sanaebadi.ir.tandorosti.activity.BestDetailsActivity;
import sanaebadi.ir.tandorosti.model.BestPro;

/**
 * Created by sanaebadi on 3/22/18.
 */

public class BestProAdapter extends RecyclerView.Adapter<BestProAdapter.CategoryViewHolder> {

  private Context context;
  private List<BestPro> categories;

  public BestProAdapter(Context context, List<BestPro> categories) {
    this.context = context;
    this.categories = categories;
  }

  @NonNull
  @Override
  public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.best_pro_single_item,
      parent, false);
    return new CategoryViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
    BestPro bestPro = categories.get(position);

    holder.txt_best_name.setText(bestPro.getPro_name());

    Glide.with(context).clear(holder.img_best_pro);

    Glide.with(context).load("https://sanaebadi.ir/tandorosti/make_thumbnail.php?url="
      + bestPro.getPro_image())
      .into(holder.img_best_pro);


    holder.best_card.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(context, BestDetailsActivity.class);
      intent.putExtra("PRO_IMAGE", bestPro.getPro_image());
        intent.putExtra("PRO_NAME", bestPro.getPro_name());
        intent.putExtra("PRO_PRICE", bestPro.getPro_price());
        intent.putExtra("PRO_DESC", bestPro.getPro_desc());
        context.startActivity(intent);
      }
    });
  }

  @Override
  public int getItemCount() {
    try {
      return categories.size();
    } catch (Exception e) {
      return 0;
    }
  }


  public class CategoryViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.txt_best_name)
    TextView txt_best_name;

    @BindView(R.id.img_best_pro)
    ImageView img_best_pro;

    @BindView(R.id.best_card)
    CardView best_card;

    public CategoryViewHolder(View itemView) {
      super(itemView);

      ButterKnife.bind(this, itemView);

    }
  }


  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public int getItemViewType(int position) {
    return position;
  }
}
