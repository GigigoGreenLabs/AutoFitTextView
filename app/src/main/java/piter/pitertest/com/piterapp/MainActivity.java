package piter.pitertest.com.piterapp;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
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
    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        mIsAnimated=!mIsAnimated;
        Snackbar.make(view, "mIsAnimated-->" + mIsAnimated, Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show();

        autoFit.setAnimation(mIsAnimated);
        autoFit.reset();


      }
    });

    //USO del CALLBACK
    lytTelon = (RelativeLayout) findViewById(R.id.lytTelon);
    autoFit.setCallBack(new AutoFitCallBack() {
      @Override public void onShrinkComplete() {
        lytTelon.setVisibility(View.GONE);
      }

      @Override public void onError() {

      }
    });
    autoFit.setAnimation(true);
    //autoFit.reset();

    //ImageView imgAnim= (ImageView) findViewById(R.id.imgAnim);
    //AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.more_arrow_anim);
    //set.setTarget(imgAnim);
    //set.start();

    ObjectAnimator animator1 = ObjectAnimator.ofFloat(imgAnim, "translationY", 120, 0);
    animator1.setRepeatCount(-1);
    animator1.setDuration(1000);
    //animator1.setRepeatMode(ValueAnimator.REVERSE );

    ObjectAnimator animator2 = ObjectAnimator.ofFloat(imgAnim, "alpha", 1f,0f);
    animator2.setRepeatCount(Animation.INFINITE);
    animator2.setDuration(1000);
    //

    // Create an animation set to play multiple animations
    // Use .before to ensure the first animation finishes before the second starts
    AnimatorSet sett = new AnimatorSet();
    sett.play(animator1).with(animator2) ;
    sett.start();

    MoreContentArrowView imgAnim2 = (MoreContentArrowView) findViewById(R.id.imgAnim2);
    imgAnim2.Anim();
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
