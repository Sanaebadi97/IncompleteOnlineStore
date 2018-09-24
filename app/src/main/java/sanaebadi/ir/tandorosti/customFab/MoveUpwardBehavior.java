package sanaebadi.ir.tandorosti.customFab;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sanaebadi on 4/1/18.
 */

public class MoveUpwardBehavior extends CoordinatorLayout.Behavior<View> {
  public MoveUpwardBehavior() {
    super();
  }

  public MoveUpwardBehavior(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
    return dependency instanceof Snackbar.SnackbarLayout;
  }

  @Override
  public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
    float translationY = Math.min(0, ViewCompat.getTranslationY(dependency) - dependency.getHeight());
    ViewCompat.setTranslationY(child, translationY);
    return true;
  }

  //you need this when you swipe the snackbar(thanx to ubuntudroid's comment)
  public void onDependentViewRemoved(CoordinatorLayout parent, View child, View dependency) {
    ViewCompat.animate(child).translationY(0).start();
  }
}
