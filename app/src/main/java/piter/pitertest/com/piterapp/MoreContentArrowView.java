package piter.pitertest.com.piterapp;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.Animation;

/**
 * Created by nubor on 05/04/2017.
 */

public class MoreContentArrowView extends android.support.v7.widget.AppCompatImageView {

  public MoreContentArrowView(Context context) {
    super(context);
  }

  public MoreContentArrowView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public MoreContentArrowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public void Anim()
  {

    ObjectAnimator animator0 = ObjectAnimator.ofFloat(this, "translationY", 120, 0);
    animator0.setRepeatCount(1);
    animator0.setDuration(1000);

    ObjectAnimator animator1 = ObjectAnimator.ofFloat(this, "translationY", 120, 0);
    animator1.setRepeatCount(-1);
    animator1.setDuration(1000);
    //animator1.setRepeatMode(ValueAnimator.REVERSE );

    ObjectAnimator animator2 = ObjectAnimator.ofFloat(this, "alpha", 1f,0f);
    animator2.setRepeatCount(Animation.INFINITE);
    animator2.setDuration(1000);
    //

    // Create an animation set to play multiple animations
    // Use .before to ensure the first animation finishes before the second starts
    AnimatorSet sett = new AnimatorSet();
    sett.play(animator1).with(animator2) ;
    sett.start();
  }

}
