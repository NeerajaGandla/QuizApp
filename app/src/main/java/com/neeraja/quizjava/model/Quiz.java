package com.neeraja.quizjava.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "quiz")
public class Quiz {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "quizId")
    private int quizId;

    @ColumnInfo(name = "question")
    private String question;

    @ColumnInfo(name = "category")
    private String category;

    @ColumnInfo(name = "questionNo")
    private int questionNumber;

    @ColumnInfo(name = "attempt")
    private String attempt;

    @ColumnInfo(name = "correctAnswer")
    private String correctAnswer;

    @ColumnInfo(name = "incorrectAnswers")
    private String incorrectAnswers;

    @ColumnInfo(name = "score")
    private int score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getAttempt() {
        return attempt;
    }

    public void setAttempt(String attempt) {
        this.attempt = attempt;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(String incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "quizId=" + quizId +
                ", question='" + question + '\'' +
                ", category=" + category +
                ", questionNumber=" + questionNumber +
                ", attempt='" + attempt + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", incorrectAnswers='" + incorrectAnswers + '\'' +
                ", score=" + score +
                '}';
    }
}