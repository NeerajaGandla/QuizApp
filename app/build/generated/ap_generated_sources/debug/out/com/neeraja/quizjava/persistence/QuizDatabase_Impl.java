package com.neeraja.quizjava.persistence;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public final class QuizDatabase_Impl extends QuizDatabase {
  private volatile CategoryDao _categoryDao;

  private volatile QuizDao _quizDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `categories` (`id` INTEGER NOT NULL, `name` TEXT, `timestamp` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `quiz` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `quizId` INTEGER NOT NULL, `question` TEXT, `category` TEXT, `questionNo` INTEGER NOT NULL, `attempt` TEXT, `correctAnswer` TEXT, `incorrectAnswers` TEXT, `score` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"1eca62319c680e88e28a955ad0f3410c\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `categories`");
        _db.execSQL("DROP TABLE IF EXISTS `quiz`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsCategories = new HashMap<String, TableInfo.Column>(3);
        _columnsCategories.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsCategories.put("name", new TableInfo.Column("name", "TEXT", false, 0));
        _columnsCategories.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCategories = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCategories = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCategories = new TableInfo("categories", _columnsCategories, _foreignKeysCategories, _indicesCategories);
        final TableInfo _existingCategories = TableInfo.read(_db, "categories");
        if (! _infoCategories.equals(_existingCategories)) {
          throw new IllegalStateException("Migration didn't properly handle categories(com.neeraja.quizjava.model.Category).\n"
                  + " Expected:\n" + _infoCategories + "\n"
                  + " Found:\n" + _existingCategories);
        }
        final HashMap<String, TableInfo.Column> _columnsQuiz = new HashMap<String, TableInfo.Column>(9);
        _columnsQuiz.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsQuiz.put("quizId", new TableInfo.Column("quizId", "INTEGER", true, 0));
        _columnsQuiz.put("question", new TableInfo.Column("question", "TEXT", false, 0));
        _columnsQuiz.put("category", new TableInfo.Column("category", "TEXT", false, 0));
        _columnsQuiz.put("questionNo", new TableInfo.Column("questionNo", "INTEGER", true, 0));
        _columnsQuiz.put("attempt", new TableInfo.Column("attempt", "TEXT", false, 0));
        _columnsQuiz.put("correctAnswer", new TableInfo.Column("correctAnswer", "TEXT", false, 0));
        _columnsQuiz.put("incorrectAnswers", new TableInfo.Column("incorrectAnswers", "TEXT", false, 0));
        _columnsQuiz.put("score", new TableInfo.Column("score", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysQuiz = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesQuiz = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoQuiz = new TableInfo("quiz", _columnsQuiz, _foreignKeysQuiz, _indicesQuiz);
        final TableInfo _existingQuiz = TableInfo.read(_db, "quiz");
        if (! _infoQuiz.equals(_existingQuiz)) {
          throw new IllegalStateException("Migration didn't properly handle quiz(com.neeraja.quizjava.model.Quiz).\n"
                  + " Expected:\n" + _infoQuiz + "\n"
                  + " Found:\n" + _existingQuiz);
        }
      }
    }, "1eca62319c680e88e28a955ad0f3410c", "bc4416fb58136a6ec7bb93465cb9dffd");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "categories","quiz");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `categories`");
      _db.execSQL("DELETE FROM `quiz`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public CategoryDao categoryDao() {
    if (_categoryDao != null) {
      return _categoryDao;
    } else {
      synchronized(this) {
        if(_categoryDao == null) {
          _categoryDao = new CategoryDao_Impl(this);
        }
        return _categoryDao;
      }
    }
  }

  @Override
  public QuizDao quizDao() {
    if (_quizDao != null) {
      return _quizDao;
    } else {
      synchronized(this) {
        if(_quizDao == null) {
          _quizDao = new QuizDao_Impl(this);
        }
        return _quizDao;
      }
    }
  }
}
