package views.gigigo.com.textviewautofit;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

public class TextFitTextView extends TextView {

  static final String TAG = "TextFitTextView";
  private static final float DEFAULT_MIN_TEXT_SIZE = 8.0f;
  boolean fit = true;
  boolean mIsAnimEnabled = false;
  static float textSizeBase = 0.0f;
  static float initialTextSizeBase;
  int mColorText = -1;
  int mColorBackground = -1;

  AutoFitCallBack mCallback;

  public TextFitTextView(Context context) {
    super(context);
  }

  public TextFitTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public TextFitTextView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  public void setCallBack(AutoFitCallBack callback) {
    mCallback = callback;
  }

  public void setAnimation(boolean isAnimEnabled) {
    mIsAnimEnabled = isAnimEnabled;
  }

  String str;

  public void reset() {
    str = this.getText().toString();
    this.setText("");
    this.setTextSize(TypedValue.COMPLEX_UNIT_PX, initialTextSizeBase);
    textSizeBase = initialTextSizeBase;
    fit = true;
    this.setText(str);
    this.invalidate();
    System.out.println("reset");

  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    initialTextSizeBase =this.getTextSize();
  }

  protected void onDraw(Canvas canvas) {

    if (mColorText == -1 && !mIsAnimEnabled) {
      mColorText = this.getCurrentTextColor();
      ColorDrawable cd = (ColorDrawable) this.getBackground();
      int colorCode = Color.TRANSPARENT;
      if (cd != null) colorCode = cd.getColor();

      mColorBackground = colorCode;
    }
    super.onDraw(canvas);
    if (fit) {
      if (!mIsAnimEnabled) this.setTextColor(mColorBackground);
      _shrinkToFit();
      if (!fit && !mIsAnimEnabled) {
        this.setTextColor(mColorText);
        super.onDraw(canvas);
      }
    }
  }

  private float getMiminumReadableFontSize() {
    float scaledDensity = this.getContext().getResources().getDisplayMetrics().scaledDensity;
    float minTextSize = scaledDensity * DEFAULT_MIN_TEXT_SIZE;
    return minTextSize;
  }

  protected void _shrinkToFit() {

    int height = this.getHeight();
    int lines = this.getLineCount();
    Rect r = new Rect();
    // int y1 = this.getLineBounds(0, r);
    int y2 = this.getLineBounds(lines - 1, r);

    System.out.println("text Size getTextSize-->" + this.getTextSize());

    if (textSizeBase == 0.0f) {
      textSizeBase = initialTextSizeBase;// this.getTextSize();
     // initialTextSizeBase = textSizeBase;
    }
    if (y2 > height && textSizeBase >= getMiminumReadableFontSize()) {
      textSizeBase = textSizeBase - 2f;

      this.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizeBase);
      System.out.println("text Size-->" + textSizeBase);
      //recursive in onDraw
    } else {
      fit = false;
      if (mCallback != null) mCallback.onShrinkComplete();
    }
  }
}