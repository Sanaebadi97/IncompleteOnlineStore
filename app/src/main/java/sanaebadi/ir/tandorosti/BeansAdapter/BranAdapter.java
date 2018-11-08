package sanaebadi.ir.tandorosti.BeansAdapter;

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
import sanaebadi.ir.tandorosti.BeansDetailsActivirty.BranDetailsActivity;
import sanaebadi.ir.tandorosti.R;
import sanaebadi.ir.tandorosti.BeansModel.Bran;
import sanaebadi.ir.tandorosti.activity.NewDetailsActivity;

/**
 * Created by sanaebadi on 4/10/18.
 */

public class BranAdapter extends RecyclerView.Adapter<BranAdapter.BranViewHolder> {

  private List<Bran> branList;
  private Context context;


  public BranAdapter(List<Bran> branList, Context context) {
    this.branList = branList;
    this.context = context;
  }

  @NonNull
  @Override
  public BranViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.bran_frag_pro_single, parent, false);
    return new BranViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull BranViewHolder holder, int position) {

    Bran bran = branList.get(position);
    Glide.with(context).clear(holder.img_new_pro);

    Glide.with(context).load("https://sanaebadi.ir/tandorosti/make_thumbnail.php?url="
      + bran.getPro_image())
      .into(holder.img_new_pro);

    holder.txt_name_pro.setText(bran.getPro_name());
    holder.txt_price.setText(String.valueOf(bran.getPro_price()));
    holder.txt_weight.setText(String.valueOf(bran.getPro_weight()));


    holder.first_card.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(context, BranDetailsActivity.class);
        intent.putExtra("PRO_IMAGE", bran.getPro_image());
        intent.putExtra("PRO_NAME", bran.getPro_name());
        intent.putExtra("PRO_PRICE", bran.getPro_price());
        intent.putExtra("PRO_DESC", bran.getPro_desc());
        context.startActivity(intent);

      }
    });

 

  }

  @Override
  public int getItemCount() {
    try {
      return branList.size();
    } catch (Exception e) {
      return 0;
    }
    
  }
  
     NumberFormat formatter = new DecimalFormat("#,###,###,###");
    holder.txt_price.setText(" " + formatter.format(bran.getPro_price()));

  public class BranViewHolder extends RecyclerView.ViewHolder {


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

    public BranViewHolder(View itemView) {
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


