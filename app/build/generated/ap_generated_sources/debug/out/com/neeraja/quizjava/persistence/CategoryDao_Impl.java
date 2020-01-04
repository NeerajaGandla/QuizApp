package com.neeraja.quizjava.persistence;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.lifecycle.ComputableLiveData;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.InvalidationTracker.Observer;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.neeraja.quizjava.model.Category;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unchecked")
public final class CategoryDao_Impl implements CategoryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfCategory;

  public CategoryDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCategory = new EntityInsertionAdapter<Category>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `categories`(`id`,`name`,`timestamp`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Category value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        stmt.bindLong(3, value.getTimestamp());
      }
    };
  }

  @Override
  public long[] insertCategories(Category... categories) {
    __db.beginTransaction();
    try {
      long[] _result = __insertionAdapterOfCategory.insertAndReturnIdsArray(categories);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<Category>> getCategories() {
    final String _sql = "SELECT * FROM categories";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<Category>>(__db.getQueryExecutor()) {
      private Observer _observer;

      @Override
      protected List<Category> compute() {
        if (_observer == null) {
          _observer = new Observer("categories") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
          final int _cursorIndexOfTimestamp = _cursor.getColumnIndexOrThrow("timestamp");
          final List<Category> _result = new ArrayList<Category>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Category _item;
            _item = new Category();
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            _item.setName(_tmpName);
            final int _tmpTimestamp;
            _tmpTimestamp = _cursor.getInt(_cursorIndexOfTimestamp);
            _item.setTimestamp(_tmpTimestamp);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }
}
