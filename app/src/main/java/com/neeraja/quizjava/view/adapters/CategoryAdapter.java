package com.neeraja.quizjava.view.adapters;
/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.BroadcastReceiver;
import android.content.Context;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.neeraja.quizjava.R;
import com.neeraja.quizjava.model.Category;
import com.neeraja.quizjava.view.activities.OptionsActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int LOADING_TYPE = 2;
    private static final int CATEGORY_TYPE = 3;

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_category_name)
        TextView categoryItemView;
        @BindView(R.id.layout_category)
        ConstraintLayout categoryLayout;

        private CategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(Category category) {
            categoryItemView.setText(category.getName());
        }

        @OnClick(R.id.layout_category)
        public void onClickView(View v) {
            int itemPosition = getAdapterPosition();
            Category item = mCategorys.get(itemPosition);
            Intent intent = new Intent(context, OptionsActivity.class);
            intent.putExtra("categoryId", item.getId());
            context.startActivity(intent);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mCategorys.get(position).getName().equals("LOADING...")) {
            return LOADING_TYPE;
        } else {
            return CATEGORY_TYPE;
        }
    }

    private final LayoutInflater mInflater;
    private List<Category> mCategorys; // Cached copy of categorys
    private Context context;

    public CategoryAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        switch (viewType) {
            case LOADING_TYPE:
                itemView = mInflater.inflate(R.layout.layout_loading_list_item, parent, false);
                return new LoadingViewHolder(itemView);
            case CATEGORY_TYPE:
                itemView = mInflater.inflate(R.layout.category_item, parent, false);
                return new CategoryViewHolder(itemView);
            default:
                itemView = mInflater.inflate(R.layout.category_item, parent, false);
                return new CategoryViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == CATEGORY_TYPE) {
            ((CategoryViewHolder) holder).onBind(mCategorys.get(position));
        }
    }

    public void setCategories(List<Category> categorys) {
        mCategorys = categorys;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mCategorys has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mCategorys != null)
            return mCategorys.size();
        else return 0;
    }

    // display loading during search request
    public void displayOnlyLoading() {
        clearList();
        Category recipe = new Category();
        recipe.setName("LOADING...");
        mCategorys.add(recipe);
        notifyDataSetChanged();
    }

    private void clearList() {
        if (mCategorys == null) {
            mCategorys = new ArrayList<>();
        } else {
            mCategorys.clear();
        }
        notifyDataSetChanged();
    }

    public void setQueryExhausted() {
        hideLoading();
        Category exhaustedRecipe = new Category();
        exhaustedRecipe.setName("EXHAUSTED...");
        mCategorys.add(exhaustedRecipe);
        notifyDataSetChanged();
    }

    public void hideLoading() {
        if (isLoading()) {
            if (mCategorys.get(0).getName().equals("LOADING...")) {
                mCategorys.remove(0);
            } else if (mCategorys.get(mCategorys.size() - 1).equals("LOADING...")) {
                mCategorys.remove(mCategorys.size() - 1);
            }
            notifyDataSetChanged();
        }
    }

    private boolean isLoading() {
        if (mCategorys != null) {
            if (mCategorys.size() > 0) {
                if (mCategorys.get(mCategorys.size() - 1).getName().equals("LOADING...")) {
                    return true;
                }
            }
        }
        return false;
    }
}