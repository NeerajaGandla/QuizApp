// Generated code from Butter Knife. Do not modify!
package com.neeraja.quizjava.view.activities;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.neeraja.quizjava.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CategoriesActivity_ViewBinding implements Unbinder {
  private CategoriesActivity target;

  @UiThread
  public CategoriesActivity_ViewBinding(CategoriesActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CategoriesActivity_ViewBinding(CategoriesActivity target, View source) {
    this.target = target;

    target.categoriesRv = Utils.findRequiredViewAsType(source, R.id.rv_categories, "field 'categoriesRv'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CategoriesActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.categoriesRv = null;
  }
}
