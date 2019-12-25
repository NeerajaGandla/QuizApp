// Generated code from Butter Knife. Do not modify!
package com.neeraja.quizjava.view.adapters;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.neeraja.quizjava.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CategoryAdapter$CategoryViewHolder_ViewBinding implements Unbinder {
  private CategoryAdapter.CategoryViewHolder target;

  private View view7f080094;

  @UiThread
  public CategoryAdapter$CategoryViewHolder_ViewBinding(
      final CategoryAdapter.CategoryViewHolder target, View source) {
    this.target = target;

    View view;
    target.categoryItemView = Utils.findRequiredViewAsType(source, R.id.tv_category_name, "field 'categoryItemView'", TextView.class);
    view = Utils.findRequiredView(source, R.id.layout_category, "field 'categoryLayout' and method 'onClickView'");
    target.categoryLayout = Utils.castView(view, R.id.layout_category, "field 'categoryLayout'", ConstraintLayout.class);
    view7f080094 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickView(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    CategoryAdapter.CategoryViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.categoryItemView = null;
    target.categoryLayout = null;

    view7f080094.setOnClickListener(null);
    view7f080094 = null;
  }
}
