package sanaebadi.ir.tandorosti.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sanaebadi.ir.tandorosti.R;

/**
 * Created by sanaebadi on 3/22/18.
 */

public class ViewPagerAdapter extends PagerAdapter {

  private Context context;

  public ViewPagerAdapter(Context context) {
    this.context = context;
  }

  public ViewPagerAdapter() {

  }

  @NonNull
  @Override
  public Object instantiateItem(@NonNull ViewGroup container, int position) {
    LayoutInflater inflater = (LayoutInflater) container.getContext()
      .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    int resId = 0;
    switch (position) {

      case 0:
        resId = R.layout.first_slide;
        break;

      case 1:
        resId = R.layout.second_slide;
        break;

      case 2:
        resId = R.layout.third_slide;
        break;

      case 3:
        resId = R.layout.forth_slide;
        break;

    }
    assert inflater != null;
    View view = inflater.inflate(resId, null);
    //   ((ViewPager) container).addView(view, 0);
    container.addView(view, 0);

    return view;
  }

  @Override
  public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
    container.removeView((View) object);
  }

  @Override
  public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
    return view == object;
  }

  @Nullable
  @Override
  public Parcelable saveState() {
    return null;
  }

  @Override
  public int getCount() {
    return 4;
  }
}
