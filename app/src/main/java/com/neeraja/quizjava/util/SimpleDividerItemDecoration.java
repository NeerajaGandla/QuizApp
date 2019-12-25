package com.neeraja.quizjava.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.neeraja.quizjava.R;

import androidx.recyclerview.widget.RecyclerView;

public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {
    private final ThreadLocal<Drawable> mDivider = new ThreadLocal<Drawable>();

    public SimpleDividerItemDecoration(Context context) {
        mDivider.set(context.getResources().getDrawable(R.drawable.recyclerview_decoration));
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.get().getIntrinsicHeight();

            mDivider.get().setBounds(left, top, right, bottom);
            mDivider.get().draw(c);
        }
    }
}
