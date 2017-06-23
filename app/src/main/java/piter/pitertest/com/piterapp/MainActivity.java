package piter.pitertest.com.piterapp;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import views.gigigo.com.textviewautofit.AutoFitCallBack;
import views.gigigo.com.textviewautofit.TextFitTextView;

import static piter.pitertest.com.piterapp.R.id.imgAnim;

public class MainActivity extends AppCompatActivity {
  RelativeLayout lytTelon;
  boolean mIsAnimated = true;
  TextFitTextView autoFit;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    autoFit = (TextFitTextView) findViewById(R.id.AutoFitTxt);
    //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    //fab.setOnClickListener(new View.OnClickListener() {
    //  @Override public void onClick(View view) {
    //    mIsAnimated = !mIsAnimated;
    //    Snackbar.make(view, "mIsAnimated-->" + mIsAnimated, Snackbar.LENGTH_LONG)
    //        .setAction("Action", null)
    //        .show();
    //
    //    autoFit.setAnimation(mIsAnimated);
    //    autoFit.reset();
    //  }
    //});

    //USO del CALLBACK
    lytTelon = (RelativeLayout) findViewById(R.id.lytTelon);
    autoFit.setCallBack(new AutoFitCallBack() {
      @Override public void onShrinkComplete() {
        lytTelon.setVisibility(View.GONE);
      }

      @Override public void onError() {

      }
    });
    autoFit.setAnimation(false);
    //autoFit.reset();

    //ImageView imgAnim= (ImageView) findViewById(R.id.imgAnim);
    //AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.more_arrow_anim);
    //set.setTarget(imgAnim);
    //set.start();

    //ObjectAnimator animator1 = ObjectAnimator.ofFloat(imgAnim, "translationY", 120, 0);
    //animator1.setRepeatCount(-1);
    //animator1.setDuration(1000);
    //animator1.setRepeatMode(ValueAnimator.REVERSE );

    //ObjectAnimator animator2 = ObjectAnimator.ofFloat(imgAnim, "alpha", 1f, 0f);
    //animator2.setRepeatCount(Animation.INFINITE);
    //animator2.setDuration(1000);
    //

    // Create an animation set to play multiple animations
    // Use .before to ensure the first animation finishes before the second starts
    //AnimatorSet sett = new AnimatorSet();
    //sett.play(animator1).with(animator2);
    //sett.start();

    //MoreContentArrowView imgAnim2 = (MoreContentArrowView) findViewById(R.id.imgAnim2);
    //imgAnim2.Anim();

    //ImageView imgAnimList = (ImageView) findViewById(R.id.imgAnimList);
    //imgAnimList.setBackgroundResource(R.drawable.more_contain_arrow_anim);
    //gyroAnimation = (AnimationDrawable) imgAnimList.getBackground();

    //anim3Arrow2();
  }

  //private void anim3Arrow() {
  //
  //  ImageView img1 = (ImageView) findViewById(R.id.img1);
  //  ImageView img2 = (ImageView) findViewById(R.id.img2);
  //  ImageView img3 = (ImageView) findViewById(R.id.img3);
  //  float pixelsDp = convertDpToPixel(16, this);
  //  float img2Alpha = 0.8f;
  //  float img3Alpha = 60;
  //
  //  ObjectAnimator translationY1 = ObjectAnimator.ofFloat(img3, "translationY",0 , -pixelsDp*2);
  //  translationY1.setRepeatCount(-1);
  //  translationY1.setDuration(1000);
  //
  //  ObjectAnimator alpha1 = ObjectAnimator.ofFloat(img3, "alpha", 1f, 0.1f);
  //  alpha1.setRepeatCount(Animation.INFINITE);
  //  alpha1.setDuration(1000);
  //
  //
  //
  //  ObjectAnimator alpha2 = ObjectAnimator.ofFloat(img2, "alpha", 0f, 0.1f);
  //  alpha2.setRepeatCount(1);
  //  alpha2.setDuration(500);
  //
  //  ObjectAnimator alpha4 = ObjectAnimator.ofFloat(img1, "alpha", 0f, 0.1f);
  //  alpha2.setRepeatCount(1);
  //  alpha2.setDuration(1);
  //
  //  ObjectAnimator alpha5 = ObjectAnimator.ofFloat(img2, "alpha",   0f);
  //  alpha2.setRepeatCount(1);
  //  alpha2.setDuration(1);
  //
  //  ObjectAnimator alpha6 = ObjectAnimator.ofFloat(img2, "alpha",   0f);
  //  alpha2.setRepeatCount(1);
  //  alpha2.setDuration(1);
  //  //
  //
  //  // Create an animation set to play multiple animations
  //  // Use .before to ensure the first animation finishes before the second starts
  //  AnimatorSet sett = new AnimatorSet();
  //  sett.play(translationY1).after(alpha5).after(alpha6).with(alpha1).with(alpha2).after(alpha4);
  //  sett.start();
  //}
  //
  //private void anim3Arrow2() {
  //
  //  ImageView img1 = (ImageView) findViewById(R.id.img1);
  //  ImageView img2 = (ImageView) findViewById(R.id.img2);
  //  ImageView img3 = (ImageView) findViewById(R.id.img3);
  //  float pixelsDp = convertDpToPixel(16, this);
  //  float img2Alpha = 0.8f;
  //  float img3Alpha = 60;
  //
  //  ObjectAnimator translationY1 = ObjectAnimator.ofFloat(img3, "translationY", -pixelsDp*2 );
  //  translationY1.setRepeatCount(-1);
  //  translationY1.setDuration(1000);
  //
  //  ObjectAnimator alpha1 = ObjectAnimator.ofFloat(img3, "alpha", 1f, 0.1f);
  //  alpha1.setRepeatCount(-1);
  //  alpha1.setDuration(1000);
  //
  //  ObjectAnimator alpha2 = ObjectAnimator.ofFloat(img2, "alpha", 0f, 1f);
  //  alpha2.setRepeatCount(-1);
  //  alpha2.setStartDelay(1100);
  // // alpha2.setRepeatMode(ValueAnimator.REVERSE);
  //  alpha2.setDuration(500);
  //
  //  ObjectAnimator alpha3 = ObjectAnimator.ofFloat(img1, "alpha", 0f, 0.3f);
  //  alpha3.setRepeatCount(-1);
  //  alpha2.setStartDelay(1000);
  //  //alpha3.setRepeatMode(ValueAnimator.REVERSE);
  //  alpha3.setDuration(300);
  //
  //  // Create an animation set to play multiple animations
  //  // Use .before to ensure the first animation finishes before the second starts
  //  AnimatorSet sett = new AnimatorSet();
  //  //sett.play(alpha5).with(alpha6).with(translationY1).with(alpha1).with(alpha2).with(alpha4);
  //  sett.play(translationY1).with(alpha1).with(alpha2).with(alpha3);
  //  sett.start();
  //}
  //public static float convertDpToPixel(float dp, Context context) {
  //  Resources resources = context.getResources();
  //  DisplayMetrics metrics = resources.getDisplayMetrics();
  //  float px = dp * (metrics.densityDpi / 160f);
  //  return px;
  //}
  //
  //public static float convertPixelsToDp(float px, Context context) {
  //  Resources resources = context.getResources();
  //  DisplayMetrics metrics = resources.getDisplayMetrics();
  //  float dp = px / (metrics.densityDpi / 160f);
  //  return dp;
  //}

  //AnimationDrawable gyroAnimation;

  //@Override public void onWindowFocusChanged(boolean hasFocus) {
  //  super.onWindowFocusChanged(hasFocus);
  //  gyroAnimation.start();
  //}
}
