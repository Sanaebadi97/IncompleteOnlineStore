package sanaebadi.ir.tandorosti.ClothesAdapter;

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
import sanaebadi.ir.tandorosti.ClothesDetailsActivity.WomensDetailsActivity;
import sanaebadi.ir.tandorosti.ClothesModel.Mens;
import sanaebadi.ir.tandorosti.ClothesModel.Womens;
import sanaebadi.ir.tandorosti.R;
import sanaebadi.ir.tandorosti.activity.NewDetailsActivity;

/**
 * Created by sanaebadi on 4/21/18.
 */

public class WomensAdapter extends RecyclerView.Adapter<WomensAdapter.WomensViewHolder> {


  private List<Womens> womensList;
  private Context context;

  public WomensAdapter(List<Womens> womensList, Context context) {
    this.womensList = womensList;
    this.context = context;
  }


  @NonNull
  @Override
  public WomensViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.mens_frag_pro_single, parent, false);
    return new WomensViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull WomensViewHolder holder, int position) {

    Womens womens = womensList.get(position);
    Glide.with(context).clear(holder.img_new_pro);

    Glide.with(context).load("https://sanaebadi.ir/tandorosti/make_thumbnail.php?url="
      + womens.getPro_image())
      .into(holder.img_new_pro);

    holder.txt_name_pro.setText(womens.getPro_name());
    holder.txt_price.setText(String.valueOf(womens.getPro_price()));
    holder.txt_weight.setText(String.valueOf(womens.getPro_weight()));

    holder.first_card.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(context, WomensDetailsActivity.class);
        intent.putExtra("PRO_IMAGE", womens.getPro_image());
        intent.putExtra("PRO_NAME", womens.getPro_name());
        intent.putExtra("PRO_PRICE", womens.getPro_price());
        intent.putExtra("PRO_DESC", womens.getPro_desc());
        context.startActivity(intent);

      }
    });

    NumberFormat formatter = new DecimalFormat("#,###,###,###");
    holder.txt_price.setText(" " + formatter.format(womens.getPro_price()));


  }

  @Override
  public int getItemCount() {
    try {
      return womensList.size();
    } catch (Exception e) {
      return 0;
    }
  }

  public class WomensViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.img_new_pro)
    ImageView img_new_pro;

    @BindView(R.id.txt_name_pro)
    TextView txt_name_pro;

    @BindView(R.id.txt_price)
    TextView txt_price;

    @BindView(R.id.txt_weight)
    TextView txt_weight;

    @BindView(R.id.first_card)
    CardView first_card;

    private Typeface face;

    public WomensViewHolder(View itemView) {
      super(itemView);

      ButterKnife.bind(this, itemView);

      face = Typeface.createFromAsset(context.getAssets(), "fonts/bzar.ttf");

      txt_price.setTypeface(face);
      txt_weight.setTypeface(face);
     // txt_name_pro.setTypeface(face);
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
