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
import android.widget.Toast;

import com.amitshekhar.DebugDB;
import com.neeraja.quizjava.R;
import com.neeraja.quizjava.model.Category;
import com.neeraja.quizjava.util.Resource;
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

        mCategoriesViewModel = ViewModelProviders.of(CategoriesActivity.this).get(CategoriesViewModel.class);
        categoryAdapter = new CategoryAdapter(this);
        categoriesRv.setAdapter(categoryAdapter);
        categoriesRv.setLayoutManager(new LinearLayoutManager(this));
        categoriesRv.setNestedScrollingEnabled(false);
        categoriesRv.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));
        subscribeObservers();

        if (mCategoriesViewModel.getCategories().getValue() == null) {
            Log.d(TAG, "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");
            getCategories();
        } else {
            Log.d(TAG, "onCreate: " + mCategoriesViewModel.getCategories().getValue().data.toString());
        }

        if (categoryAdapter != null)
            categoryAdapter.notifyDataSetChanged();
    }

    private void subscribeObservers() {
        mCategoriesViewModel.getCategories().observe(this, new Observer<Resource<List<Category>>>() {
            @Override
            public void onChanged(Resource<List<Category>> listResource) {
                if (listResource.data != null) {
                    switch (listResource.status) {
                        case LOADING: {
                            break;
                        }
                        case ERROR: {
                            Log.e(TAG, "onChanged: cannot refresh the cache.");
                            Log.e(TAG, "onChanged: ERROR message: " + listResource.message);
                            Log.e(TAG, "onChanged: status: ERROR, #Categories: " + listResource.data.size());
                            categoryAdapter.hideLoading();
                            categoryAdapter.setCategories(listResource.data);
                            Toast.makeText(CategoriesActivity.this, listResource.message, Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case SUCCESS: {
                            Log.d(TAG, "onChanged: cache has been refreshed.");
                            Log.d(TAG, "onChanged: status: SUCCESS, #Categories: " + listResource.data.size());
                            categoryAdapter.hideLoading();
                            categoryAdapter.setCategories(listResource.data);
                            break;
                        }
                    }
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
