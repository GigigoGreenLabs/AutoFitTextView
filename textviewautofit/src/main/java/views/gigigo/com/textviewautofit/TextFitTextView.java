package views.gigigo.com.textviewautofit;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatTextView;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.TypedValue;

public class TextFitTextView extends AppCompatTextView {

  static final String TAG = "TextFitTextView";
  private static final float DEFAULT_MIN_TEXT_SIZE = 8.0f;
  private static final int DEFAULT_CHARS_PER_LINE = 20;
  boolean fit = true;
  boolean mIsAnimEnabled = false;
  float textSizeBase = 0.0f;
  float initialTextSizeBase;
  AutoFitCallBack mCallback;
  String str;
  private boolean newLinesWhenWordsAreToLong = false;
  private boolean shrinkTextWhenWordsIsTooLong = false;
  private float currentAlpha;

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
    initialTextSizeBase = this.getTextSize();
  }

  protected void onDraw(Canvas canvas) {
    if (!mIsAnimEnabled && currentAlpha == 0) {
      currentAlpha = getAlpha();
      setAlpha(0f);
    }
    super.onDraw(canvas);
    if (fit) {
      _shrinkToFit();
      if (!fit && !mIsAnimEnabled) {
        this.setAlpha(currentAlpha);
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
      if (!newLinesWhenWordsAreToLong) {
        newLinesWhenWordsAreToLong = true;
        wrapText();
        postInvalidate();
      } else if (!shrinkTextWhenWordsIsTooLong) {

        if (calculateStringIsTooLongPerLine()) {
          postInvalidate();
        } else {
          shrinkTextWhenWordsIsTooLong = true;
        }
      } else {
        fit = false;
        if (mCallback != null) mCallback.onShrinkComplete();
      }
    }
  }

  public void wrapText() {
    int charsPerLine = calculateMaxCharPerLine();

    String mQuestion = getText().toString();

    String temp = "";
    String sentence = "";

    String[] array = mQuestion.split("\\s");

    for (int i = 0; i < array.length; i++) {
      if (array[i].length() < charsPerLine) {
        if (i == 0) {
          temp += array[i];
        } else {
          temp += " " + array[i];
        }
      } else {
        sentence += temp + "\n";
        temp = array[i];
      }
    }

    setText(sentence + temp);
  }

  private boolean calculateStringIsTooLongPerLine() {
    int charsPerLine = calculateMaxCharPerLine();

    String mQuestion = getText().toString();

    String[] array = mQuestion.split("\\s|\\n");

    for (int i = 0; i < array.length; i++) {
      if (array[i].length() > charsPerLine) {
        textSizeBase = textSizeBase - 2f;
        this.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizeBase);
        return true;
      }
    }
    return false;
  }

  public int calculateMaxCharPerLine() {
    Layout layout = getLayout();

    int charsPerLine = DEFAULT_CHARS_PER_LINE;

    if (layout == null) {
      return charsPerLine;
    }

    int lineCount = layout.getLineCount();

    if (lineCount <= 1) {
      return charsPerLine;
    }

    for (int i = 0; i < lineCount - 1; i++) {
      int curLine = layout.getLineStart(i);
      int nextLine = layout.getLineStart(i + 1);
      int difference = nextLine - curLine;

      if (charsPerLine < difference) {
        charsPerLine = difference;
      }
    }

    return charsPerLine;
  }
}