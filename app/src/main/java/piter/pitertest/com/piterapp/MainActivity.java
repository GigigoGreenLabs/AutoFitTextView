package piter.pitertest.com.piterapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import views.gigigo.com.textviewautofit.AutoFitCallBack;
import views.gigigo.com.textviewautofit.TextFitTextView;

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
