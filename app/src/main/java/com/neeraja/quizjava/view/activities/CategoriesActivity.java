package com.neeraja.quizjava.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.neeraja.quizjava.R;
import com.neeraja.quizjava.model.Category;
import com.neeraja.quizjava.util.SimpleDividerItemDecoration;
import com.neeraja.quizjava.util.Testing;
import com.neeraja.quizjava.view.adapters.CategoryAdapter;
import com.neeraja.quizjava.viewmodel.CategoriesViewModel;

import java.util.ArrayList;
import java.util.List;

public class CategoriesActivity extends AppCompatActivity {
    private static final String TAG = "CategoriesActivity";

    private CategoriesViewModel mCategoriesViewModel;
    private CategoryAdapter categoryAdapter;

    @BindView(R.id.rv_categories)
    RecyclerView categoriesRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        ButterKnife.bind(this);

        mCategoriesViewModel = ViewModelProviders.of(this).get(CategoriesViewModel.class);
        categoryAdapter = new CategoryAdapter(this);
        categoriesRv.setAdapter(categoryAdapter);
        categoriesRv.setLayoutManager(new LinearLayoutManager(this));
        categoriesRv.setNestedScrollingEnabled(false);
        categoriesRv.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));
        subscribeObservers();

        getCategories();

        if (categoryAdapter != null)
            categoryAdapter.notifyDataSetChanged();
    }

    private void subscribeObservers() {
        mCategoriesViewModel.getCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                if (categories != null) {
                    Testing.printCategories(categories, TAG);
                    categoryAdapter.setCategories(categories);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        this.finishAffinity();
    }

    private void categoriesApi() {
        mCategoriesViewModel.categoriesApi();
    }

    private void getCategories() {
        categoriesApi();
    }

}
