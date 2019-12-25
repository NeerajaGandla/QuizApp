// Generated code from Butter Knife. Do not modify!
package com.neeraja.quizjava.view.activities;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.neeraja.quizjava.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ScoreActivity_ViewBinding implements Unbinder {
  private ScoreActivity target;

  private View view7f08008f;

  private View view7f080090;

  @UiThread
  public ScoreActivity_ViewBinding(ScoreActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ScoreActivity_ViewBinding(final ScoreActivity target, View source) {
    this.target = target;

    View view;
    target.scoreTv = Utils.findRequiredViewAsType(source, R.id.tv_score, "field 'scoreTv'", TextView.class);
    target.remarksTv = Utils.findRequiredViewAsType(source, R.id.tv_remarks, "field 'remarksTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.iv_home, "field 'homeIv' and method 'onButtonClick'");
    target.homeIv = Utils.castView(view, R.id.iv_home, "field 'homeIv'", ImageView.class);
    view7f08008f = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onButtonClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_share, "field 'shareIv' and method 'onButtonClick'");
    target.shareIv = Utils.castView(view, R.id.iv_share, "field 'shareIv'", ImageView.class);
    view7f080090 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onButtonClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ScoreActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.scoreTv = null;
    target.remarksTv = null;
    target.homeIv = null;
    target.shareIv = null;

    view7f08008f.setOnClickListener(null);
    view7f08008f = null;
    view7f080090.setOnClickListener(null);
    view7f080090 = null;
  }
}
