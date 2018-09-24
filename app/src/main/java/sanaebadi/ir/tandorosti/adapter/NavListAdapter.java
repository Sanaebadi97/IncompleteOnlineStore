package sanaebadi.ir.tandorosti.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import sanaebadi.ir.tandorosti.DrawerActivity.Beans;
import sanaebadi.ir.tandorosti.DrawerActivity.Beverages;
import sanaebadi.ir.tandorosti.DrawerActivity.Clothing;
import sanaebadi.ir.tandorosti.DrawerActivity.Cosmetics;
import sanaebadi.ir.tandorosti.DrawerActivity.JunkFood;
import sanaebadi.ir.tandorosti.DrawerActivity.News;
import sanaebadi.ir.tandorosti.DrawerActivity.Oil;
import sanaebadi.ir.tandorosti.DrawerActivity.Seasoned;
import sanaebadi.ir.tandorosti.R;
import sanaebadi.ir.tandorosti.activity.LoginActivity;
import sanaebadi.ir.tandorosti.activity.MainActivity;
import sanaebadi.ir.tandorosti.model.NavList;

/**
 * Created by sanaebadi on 3/24/18.
 */

public class NavListAdapter extends RecyclerView.Adapter<NavListAdapter.NavViewHolder> {

  private static final String TAG = "NavListAdapter";
  public MainActivity activity;
  @SuppressLint("StaticFieldLeak")
  private static final int TYPE_HEADER = 0;
  private static final int TYPE_ITEM = 1;
  private int row_index;

  public Context context;
  private List<NavList> navLists;

  public NavListAdapter(Context context, List<NavList> navLists) {
    this.context = context;
    this.navLists = navLists;
  }


  @NonNull
  @Override
  public NavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    if (viewType == TYPE_ITEM) {
      View view = LayoutInflater.from(context)
        .inflate(R.layout.nav_row, parent, false);
      return new NavViewHolder(view, viewType);

    } else if (viewType == TYPE_HEADER) {
      View view = LayoutInflater.from(context)
        .inflate(R.layout.header_nav, parent, false);
      return new NavViewHolder(view, viewType);
    }
    return null;
  }

  @Override
  public void onBindViewHolder(@NonNull NavViewHolder holder, int position) {

    NavList navList = navLists.get(position);

    if (holder.holderId == 1) {
      holder.txt_nav_title.setText(navList.getNavTitle());
      holder.img_nav_icon.setImageResource(navList.getNavIcon());
      holder.parent.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          row_index = position;
          notifyDataSetChanged();
          Log.e(TAG, "Position : " + String.valueOf(row_index));

          switch (row_index) {
           /* case 1:
              context.startActivity(new Intent(context, MainActivity.class));
              Log.d(TAG, "Working case 1");
              break;*/

            case 1:
              context.startActivity(new Intent(context, News.class));
              Log.d(TAG, "Working case 2");
              break;

            case 2:
              context.startActivity(new Intent(context, Beverages.class));
              Log.d(TAG, "Working case 3");
              break;

            case 3:
              context.startActivity(new Intent(context, JunkFood.class));
              Log.d(TAG, "Working case 4");
              break;

            case 4:
              context.startActivity(new Intent(context, Seasoned.class));
              Log.d(TAG, "Working case 5");
              break;

            case 5:
              context.startActivity(new Intent(context, Beans.class));
              Log.d(TAG, "Working case 6");
              break;

            case 6:
              context.startActivity(new Intent(context, Oil.class));
              Log.d(TAG, "Working case 7");
              break;

            case 7:
              context.startActivity(new Intent(context, Cosmetics.class));
              Log.d(TAG, "Working case 8");
              break;

            case 8:
              context.startActivity(new Intent(context, Clothing.class));
              Log.d(TAG, "Working case 9");
              break;


          }

          activity.drawer_layout.closeDrawers();


        }
      });


   /* if (row_index == position) {
      holder.parent.setBackgroundColor(ContextCompat.getColor(context, R.color.nav_selected));
      holder.txt_nav_title.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
    } else {

      holder.parent.setBackgroundColor(ContextCompat.getColor(context, R.color.bg));
      holder.txt_nav_title.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
    }*/

    } else

    {

      //  holder.txt_name.setText(navList.getHeadName());
      holder.txt_name.setText("سینا عبادی");

      Glide.with(context)
        //.load(navList.getHeadProf())
        .load(R.drawable.profile)
        .into(holder.img_profile);

      // holder.lay_expend.setVisibility(View.GONE);

      holder.img_drop_down.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          //holder.lay_expend.setVisibility(holder.lay_expend.isShown() ? View.GONE : View.VISIBLE);

          //Creating the instance of PopupMenu
          PopupMenu popupMenu = new PopupMenu(context, holder.img_drop_down);

          //Inflating the Popup using xml file
          popupMenu.getMenuInflater().inflate(R.menu.popup, popupMenu.getMenu());

          //registering popup with OnMenuItemClickListener
          popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
              //  Toast.makeText(context, "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();

              switch (item.getItemId()) {

                case R.id.login:
                  context.startActivity(new Intent(context, LoginActivity.class));
                  break;
              }


              return true;
            }
          });

          popupMenu.show();
        }
      });


    }

  }

  @Override
  public int getItemCount() {

    try {
      return navLists.size();
    } catch (Exception e) {
      return 0;
    }
  }

  public class NavViewHolder extends RecyclerView.ViewHolder {

    private int holderId;
    private TextView txt_name, txt_nav_title;
    private ImageView img_profile, img_nav_icon, img_drop_down;
    private LinearLayout parent, lay_expend;


    public NavViewHolder(View itemView, int viewType) {
      super(itemView);

      if (viewType == TYPE_ITEM) {
        txt_nav_title = itemView.findViewById(R.id.txt_nav_title);
        img_nav_icon = itemView.findViewById(R.id.img_nav_icon);
        parent = itemView.findViewById(R.id.parent);
        holderId = 1;

      } else {
        txt_name = itemView.findViewById(R.id.txt_name);
        img_profile = itemView.findViewById(R.id.img_profile);
        img_drop_down = itemView.findViewById(R.id.img_drop_down);
        holderId = 0;
      }


    }

  }

  @Override
  public int getItemViewType(int position) {
    if (isPositionHeader(position))
      return TYPE_HEADER;

    return TYPE_ITEM;
  }

  private boolean isPositionHeader(int position) {

    return position == 0;
  }


  @Override
  public long getItemId(int position) {
    return position;
  }


}

