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
import com.neeraja.quizjava.model.Quiz;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unchecked")
public final class QuizDao_Impl implements QuizDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfQuiz;

  public QuizDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfQuiz = new EntityInsertionAdapter<Quiz>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `quiz`(`id`,`quizId`,`question`,`category`,`questionNo`,`attempt`,`correctAnswer`,`incorrectAnswers`,`score`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Quiz value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getQuizId());
        if (value.getQuestion() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getQuestion());
        }
        if (value.getCategory() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getCategory());
        }
        stmt.bindLong(5, value.getQuestionNumber());
        if (value.getAttempt() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getAttempt());
        }
        if (value.getCorrectAnswer() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getCorrectAnswer());
        }
        if (value.getIncorrectAnswers() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getIncorrectAnswers());
        }
        stmt.bindLong(9, value.getScore());
      }
    };
  }

  @Override
  public long[] insertQuiz(Quiz... questions) {
    __db.beginTransaction();
    try {
      long[] _result = __insertionAdapterOfQuiz.insertAndReturnIdsArray(questions);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<Quiz>> getQuestions(int quizId) {
    final String _sql = "SELECT * FROM quiz WHERE quizId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, quizId);
    return new ComputableLiveData<List<Quiz>>(__db.getQueryExecutor()) {
      private Observer _observer;

      @Override
      protected List<Quiz> compute() {
        if (_observer == null) {
          _observer = new Observer("quiz") {
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
          final int _cursorIndexOfQuizId = _cursor.getColumnIndexOrThrow("quizId");
          final int _cursorIndexOfQuestion = _cursor.getColumnIndexOrThrow("question");
          final int _cursorIndexOfCategory = _cursor.getColumnIndexOrThrow("category");
          final int _cursorIndexOfQuestionNumber = _cursor.getColumnIndexOrThrow("questionNo");
          final int _cursorIndexOfAttempt = _cursor.getColumnIndexOrThrow("attempt");
          final int _cursorIndexOfCorrectAnswer = _cursor.getColumnIndexOrThrow("correctAnswer");
          final int _cursorIndexOfIncorrectAnswers = _cursor.getColumnIndexOrThrow("incorrectAnswers");
          final int _cursorIndexOfScore = _cursor.getColumnIndexOrThrow("score");
          final List<Quiz> _result = new ArrayList<Quiz>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Quiz _item;
            _item = new Quiz();
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            final int _tmpQuizId;
            _tmpQuizId = _cursor.getInt(_cursorIndexOfQuizId);
            _item.setQuizId(_tmpQuizId);
            final String _tmpQuestion;
            _tmpQuestion = _cursor.getString(_cursorIndexOfQuestion);
            _item.setQuestion(_tmpQuestion);
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            _item.setCategory(_tmpCategory);
            final int _tmpQuestionNumber;
            _tmpQuestionNumber = _cursor.getInt(_cursorIndexOfQuestionNumber);
            _item.setQuestionNumber(_tmpQuestionNumber);
            final String _tmpAttempt;
            _tmpAttempt = _cursor.getString(_cursorIndexOfAttempt);
            _item.setAttempt(_tmpAttempt);
            final String _tmpCorrectAnswer;
            _tmpCorrectAnswer = _cursor.getString(_cursorIndexOfCorrectAnswer);
            _item.setCorrectAnswer(_tmpCorrectAnswer);
            final String _tmpIncorrectAnswers;
            _tmpIncorrectAnswers = _cursor.getString(_cursorIndexOfIncorrectAnswers);
            _item.setIncorrectAnswers(_tmpIncorrectAnswers);
            final int _tmpScore;
            _tmpScore = _cursor.getInt(_cursorIndexOfScore);
            _item.setScore(_tmpScore);
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

  @Override
  public LiveData<List<Quiz>> getQuizzes() {
    final String _sql = "SELECT * FROM quiz GROUP BY quizId";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<Quiz>>(__db.getQueryExecutor()) {
      private Observer _observer;

      @Override
      protected List<Quiz> compute() {
        if (_observer == null) {
          _observer = new Observer("quiz") {
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
          final int _cursorIndexOfQuizId = _cursor.getColumnIndexOrThrow("quizId");
          final int _cursorIndexOfQuestion = _cursor.getColumnIndexOrThrow("question");
          final int _cursorIndexOfCategory = _cursor.getColumnIndexOrThrow("category");
          final int _cursorIndexOfQuestionNumber = _cursor.getColumnIndexOrThrow("questionNo");
          final int _cursorIndexOfAttempt = _cursor.getColumnIndexOrThrow("attempt");
          final int _cursorIndexOfCorrectAnswer = _cursor.getColumnIndexOrThrow("correctAnswer");
          final int _cursorIndexOfIncorrectAnswers = _cursor.getColumnIndexOrThrow("incorrectAnswers");
          final int _cursorIndexOfScore = _cursor.getColumnIndexOrThrow("score");
          final List<Quiz> _result = new ArrayList<Quiz>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Quiz _item;
            _item = new Quiz();
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            final int _tmpQuizId;
            _tmpQuizId = _cursor.getInt(_cursorIndexOfQuizId);
            _item.setQuizId(_tmpQuizId);
            final String _tmpQuestion;
            _tmpQuestion = _cursor.getString(_cursorIndexOfQuestion);
            _item.setQuestion(_tmpQuestion);
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            _item.setCategory(_tmpCategory);
            final int _tmpQuestionNumber;
            _tmpQuestionNumber = _cursor.getInt(_cursorIndexOfQuestionNumber);
            _item.setQuestionNumber(_tmpQuestionNumber);
            final String _tmpAttempt;
            _tmpAttempt = _cursor.getString(_cursorIndexOfAttempt);
            _item.setAttempt(_tmpAttempt);
            final String _tmpCorrectAnswer;
            _tmpCorrectAnswer = _cursor.getString(_cursorIndexOfCorrectAnswer);
            _item.setCorrectAnswer(_tmpCorrectAnswer);
            final String _tmpIncorrectAnswers;
            _tmpIncorrectAnswers = _cursor.getString(_cursorIndexOfIncorrectAnswers);
            _item.setIncorrectAnswers(_tmpIncorrectAnswers);
            final int _tmpScore;
            _tmpScore = _cursor.getInt(_cursorIndexOfScore);
            _item.setScore(_tmpScore);
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
