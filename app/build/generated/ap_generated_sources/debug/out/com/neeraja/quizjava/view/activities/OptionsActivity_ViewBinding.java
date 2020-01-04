// Generated code from Butter Knife. Do not modify!
package com.neeraja.quizjava.view.activities;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.neeraja.quizjava.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OptionsActivity_ViewBinding implements Unbinder {
  private OptionsActivity target;

  private View view7f08004b;

  @UiThread
  public OptionsActivity_ViewBinding(OptionsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public OptionsActivity_ViewBinding(final OptionsActivity target, View source) {
    this.target = target;

    View view;
    target.radioGroup = Utils.findRequiredViewAsType(source, R.id.radioGroup, "field 'radioGroup'", RadioGroup.class);
    target.noOfQuestionsEt = Utils.findRequiredViewAsType(source, R.id.et_no_of_questions, "field 'noOfQuestionsEt'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btn_start, "field 'startBtn' and method 'onStartClick'");
    target.startBtn = Utils.castView(view, R.id.btn_start, "field 'startBtn'", Button.class);
    view7f08004b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onStartClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    OptionsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.radioGroup = null;
    target.noOfQuestionsEt = null;
    target.startBtn = null;

    view7f08004b.setOnClickListener(null);
    view7f08004b = null;
  }
}
