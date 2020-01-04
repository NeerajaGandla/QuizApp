// Generated code from Butter Knife. Do not modify!
package com.neeraja.quizjava.view.activities;

import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.neeraja.quizjava.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class QuestionActivity_ViewBinding implements Unbinder {
  private QuestionActivity target;

  private View view7f080049;

  private View view7f080048;

  private View view7f08004a;

  private View view7f08004c;

  private View view7f0800c2;

  private View view7f0800c3;

  private View view7f0800c4;

  private View view7f0800c5;

  @UiThread
  public QuestionActivity_ViewBinding(QuestionActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public QuestionActivity_ViewBinding(final QuestionActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_prev, "field 'prevBtn' and method 'onButtonClick'");
    target.prevBtn = Utils.castView(view, R.id.btn_prev, "field 'prevBtn'", Button.class);
    view7f080049 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onButtonClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_next, "field 'nextBtn' and method 'onButtonClick'");
    target.nextBtn = Utils.castView(view, R.id.btn_next, "field 'nextBtn'", Button.class);
    view7f080048 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onButtonClick(p0);
      }
    });
    target.optionsGroup = Utils.findRequiredViewAsType(source, R.id.radioGroup, "field 'optionsGroup'", RadioGroup.class);
    target.questionTv = Utils.findRequiredViewAsType(source, R.id.tv_question, "field 'questionTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_quit_quiz, "field 'quitBtn' and method 'onButtonClick'");
    target.quitBtn = Utils.castView(view, R.id.btn_quit_quiz, "field 'quitBtn'", Button.class);
    view7f08004a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onButtonClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_submit, "field 'submitBtn' and method 'onButtonClick'");
    target.submitBtn = Utils.castView(view, R.id.btn_submit, "field 'submitBtn'", Button.class);
    view7f08004c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onButtonClick(p0);
      }
    });
    target.timerTv = Utils.findRequiredViewAsType(source, R.id.tv_timer, "field 'timerTv'", TextView.class);
    target.questionNumTv = Utils.findRequiredViewAsType(source, R.id.tv_question_num, "field 'questionNumTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rb_optionA, "method 'onRadioButtonClicked'");
    view7f0800c2 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onRadioButtonClicked(Utils.castParam(p0, "doClick", 0, "onRadioButtonClicked", 0, RadioButton.class));
      }
    });
    view = Utils.findRequiredView(source, R.id.rb_optionB, "method 'onRadioButtonClicked'");
    view7f0800c3 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onRadioButtonClicked(Utils.castParam(p0, "doClick", 0, "onRadioButtonClicked", 0, RadioButton.class));
      }
    });
    view = Utils.findRequiredView(source, R.id.rb_optionC, "method 'onRadioButtonClicked'");
    view7f0800c4 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onRadioButtonClicked(Utils.castParam(p0, "doClick", 0, "onRadioButtonClicked", 0, RadioButton.class));
      }
    });
    view = Utils.findRequiredView(source, R.id.rb_optionD, "method 'onRadioButtonClicked'");
    view7f0800c5 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onRadioButtonClicked(Utils.castParam(p0, "doClick", 0, "onRadioButtonClicked", 0, RadioButton.class));
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    QuestionActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.prevBtn = null;
    target.nextBtn = null;
    target.optionsGroup = null;
    target.questionTv = null;
    target.quitBtn = null;
    target.submitBtn = null;
    target.timerTv = null;
    target.questionNumTv = null;

    view7f080049.setOnClickListener(null);
    view7f080049 = null;
    view7f080048.setOnClickListener(null);
    view7f080048 = null;
    view7f08004a.setOnClickListener(null);
    view7f08004a = null;
    view7f08004c.setOnClickListener(null);
    view7f08004c = null;
    view7f0800c2.setOnClickListener(null);
    view7f0800c2 = null;
    view7f0800c3.setOnClickListener(null);
    view7f0800c3 = null;
    view7f0800c4.setOnClickListener(null);
    view7f0800c4 = null;
    view7f0800c5.setOnClickListener(null);
    view7f0800c5 = null;
  }
}
