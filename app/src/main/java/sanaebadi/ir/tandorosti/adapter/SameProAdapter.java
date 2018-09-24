package sanaebadi.ir.tandorosti.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sanaebadi.ir.tandorosti.R;
import sanaebadi.ir.tandorosti.activity.SameDetailsActivity;
import sanaebadi.ir.tandorosti.model.SamePro;

/**
 * Created by sanaebadi on 4/4/18.
 */

public class SameProAdapter extends RecyclerView.Adapter<SameProAdapter.SameProViewHolder> {
  private Context context;
  private List<SamePro> sameProList;

  public SameProAdapter(Context context, List<SamePro> sameProList) {
    this.context = context;
    this.sameProList = sameProList;
  }

  @NonNull
  @Override
  public SameProViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.same_single_item,
      parent, false);
    return new SameProViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull SameProViewHolder holder, int position) {
    SamePro samePro = sameProList.get(position);
    Glide.with(context).clear(holder.img_same_pro);

    Glide.with(context).load("https://sanaebadi.ir/tandorosti/make_thumbnail.php?url="
      + samePro.getPro_image())
      .into(holder.img_same_pro);

    holder.txt_same_name.setText(samePro.getPro_name());
    holder.txt_price.setText(String.valueOf(samePro.getPro_price()));


    NumberFormat formatter = new DecimalFormat("#,###,###,###");
    holder.txt_price.setText(" " + formatter.format(samePro.getPro_price()));

    holder.card_same.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(context, SameDetailsActivity.class);
        intent.putExtra("PRO_IMAGE", samePro.getPro_image());
        intent.putExtra("PRO_NAME", samePro.getPro_name());
        intent.putExtra("PRO_PRICE", samePro.getPro_price());
        intent.putExtra("PRO_DESC", samePro.getPro_desc());
        context.startActivity(intent);
      }
    });

  }

  @Override
  public int getItemCount() {

    try {
      return sameProList.size();
    } catch (Exception e) {
      return 0;
    }
  }

  public class SameProViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.txt_same_name)
    TextView txt_same_name;

    @BindView(R.id.txt_price)
    TextView txt_price;


    @BindView(R.id.img_same_pro)
    ImageView img_same_pro;

    private Typeface face;

    @BindView(R.id.card_same)
    CardView card_same;

    public SameProViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);

      face = Typeface.createFromAsset(context.getAssets(), "fonts/bzar.ttf");
      txt_price.setTypeface(face);


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
