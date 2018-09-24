package sanaebadi.ir.tandorosti.manage;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import sanaebadi.ir.tandorosti.R;

/**
 * Created by sanaebadi on 4/3/18.
 */

public class ExpandableTextView extends android.support.v7.widget.AppCompatTextView {

  private static final int DEFAULT_TRIM_LENGTH =400;
  private static final String ELLIPSIS = "...";


  private CharSequence originalText;
  private CharSequence trimmedText;
  private BufferType bufferType;
  private boolean trim = true;
  private int trimLength;


  public ExpandableTextView(Context context) {
    super(context, null);
  }

  public ExpandableTextView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);

    TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandableTextView);
    this.trimLength = typedArray.getInt(R.styleable.ExpandableTextView_trimLength, DEFAULT_TRIM_LENGTH);
    typedArray.recycle();

    setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        trim = !trim;
        setText();
        requestFocusFromTouch();
      }
    });
  }

  private void setText() {
    super.setText(getDisplayableText(), bufferType);
  }

  private CharSequence getDisplayableText() {
    return trim ? trimmedText : originalText;
  }

  @Override
  public void setText(CharSequence text, BufferType type) {
    originalText = text;
    trimmedText = getTrimmedText(text);
    bufferType = type;
    setText();
  }

  private CharSequence getTrimmedText(CharSequence text) {
    if (originalText != null && originalText.length() > trimLength) {
      return new SpannableStringBuilder(originalText, 0, trimLength + 1).append(ELLIPSIS);
    } else {
      return originalText;
    }
  }

  public CharSequence getOriginalText() {
    return originalText;
  }

  public void setTrimLength(int trimLength) {
    this.trimLength = trimLength;
    trimmedText = getTrimmedText(originalText);
    setText();
  }

  public int getTrimLength() {
    return trimLength;
  }
}

