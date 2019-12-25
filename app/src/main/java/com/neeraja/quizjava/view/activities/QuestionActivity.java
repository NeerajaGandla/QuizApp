package com.neeraja.quizjava.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.neeraja.quizjava.R;
import com.neeraja.quizjava.model.Question;
import com.neeraja.quizjava.util.Testing;
import com.neeraja.quizjava.viewmodel.QuestionsViewModel;

import org.apache.commons.lang3.StringEscapeUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {

    private static final String TAG = "QuestionActivity";

    private String difficulty, noOfQuestions;
    private int categoryId;
    private QuestionsViewModel questionsViewModel;
    private List<Question> questionList = new ArrayList<>();
    private HashMap<Integer, String> answersMap = new HashMap<>();
    private int currentPosition;
    private boolean[] isAnswered;

    @BindView(R.id.btn_prev)
    Button prevBtn;
    @BindView(R.id.btn_next)
    Button nextBtn;
    @BindView(R.id.radioGroup)
    RadioGroup optionsGroup;
    @BindView(R.id.tv_question)
    TextView questionTv;
    @BindView(R.id.btn_quit_quiz)
    Button quitBtn;
    @BindView(R.id.btn_submit)
    Button submitBtn;
    @BindView(R.id.tv_timer)
    TextView timerTv;
    @BindView(R.id.tv_question_num)
    TextView questionNumTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        ButterKnife.bind(this);

        questionsViewModel = ViewModelProviders.of(this).get(QuestionsViewModel.class);

        getIncomingIntent();
        subscribeObservers();
        getQuestions();
    }

    private void setTimer(int noOfQuestions) {
        int totalTime = noOfQuestions * 20 * 1000;
        int interval = 1000;
        new CountDownTimer(totalTime, interval) { //20 seconds for each question

            public void onTick(long millisUntilFinished) {
                String sSeconds = String.format("%02d", millisUntilFinished / 1000);
                timerTv.setText(sSeconds);
            }

            public void onFinish() {
                Toast.makeText(getApplicationContext(), "Time's up. Submitting Answers", Toast.LENGTH_SHORT).show();
                submitQuiz();
            }
        }.start();
    }

    private void submitQuiz() {
        double score = evaluateQuiz();
        int totalQuestions = questionList.size();
        double percentage = (score / totalQuestions) * 100;
        goNext(percentage, score, totalQuestions);
    }

    private double evaluateQuiz() {
        double score = 0;
        for (int i = 0; i < questionList.size(); i++) {
            Log.d(TAG, "evaluateQuiz: " + answersMap.toString());
            if (answersMap.get(i) != null && answersMap.get(i).equalsIgnoreCase(questionList.get(i).getCorrect_answer())) {
                score++;
            }
        }
        return score;
    }

    private void goNext(double percentage, double score, int totalQuestions) {
        Intent intent = new Intent(QuestionActivity.this, ScoreActivity.class);
        intent.putExtra("percentage", percentage);
        intent.putExtra("score", score);
        intent.putExtra("totalQuestions", totalQuestions);
        startActivity(intent);
    }

    public void subscribeObservers() {
        questionsViewModel.getQuestions().observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {
                if (questions != null) {
                    Log.d(TAG, "onChanged: ");
                    Testing.printQuestions(questions, TAG);
                    questionList = questions;
                    updateQuestion(0);
                    setTimer(questions.size());
                }
            }
        });
    }

    private void updateQuestion(int position) {
        questionTv.setText(StringEscapeUtils.unescapeHtml4(questionList.get(position).getQuestion()));
        List<String> answerOptions = questionList.get(position).getIncorrect_answers();
        answerOptions.add(questionList.get(position).getCorrect_answer());
        Collections.shuffle(answerOptions);
        questionNumTv.setText(String.valueOf(position + 1));

        for (int i = 0; i < optionsGroup.getChildCount(); i++) {
            ((RadioButton) optionsGroup.getChildAt(i)).setText(StringEscapeUtils.unescapeHtml4(answerOptions.get(i)));
        }

        currentPosition = position;
        manageButtons(position);
    }

    @OnClick({R.id.rb_optionA, R.id.rb_optionB, R.id.rb_optionC, R.id.rb_optionD})
    public void onRadioButtonClicked(RadioButton radioButton) {
        boolean checked = radioButton.isChecked();
        if (checked) {
            isAnswered[currentPosition] = true;
            answersMap.put(currentPosition, radioButton.getText().toString());
        }
        radioButton.setChecked(checked);
    }

    @OnClick({R.id.btn_quit_quiz, R.id.btn_submit, R.id.btn_prev, R.id.btn_next})
    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.btn_quit_quiz:
                showQuitAlert();
                break;
            case R.id.btn_submit:
                submitQuiz();
                break;
            case R.id.btn_prev:
                optionsGroup.clearCheck();
                if (questionList != null) {
                    updateQuestion(currentPosition - 1);
                }
                break;
            case R.id.btn_next:
                optionsGroup.clearCheck();
                if (questionList != null) {
                    updateQuestion(currentPosition + 1);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        showQuitAlert();
    }

    public void showQuitAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(QuestionActivity.this);
        builder.setMessage("Are You Sure You Want to Quit?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                startActivity(new Intent(QuestionActivity.this, CategoriesActivity.class));
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog d = builder.create();
        d.show();
    }

    private void manageButtons(int position) {
        if (position == questionList.size() - 1) {
            quitBtn.setVisibility(View.GONE);
            submitBtn.setVisibility(View.VISIBLE);
            nextBtn.setVisibility(View.GONE);
            prevBtn.setVisibility(View.VISIBLE);
        } else if (position == 0) {
            quitBtn.setVisibility(View.VISIBLE);
            submitBtn.setVisibility(View.GONE);
            prevBtn.setVisibility(View.GONE);
            nextBtn.setVisibility(View.VISIBLE);
        } else {
            submitBtn.setVisibility(View.GONE);
            quitBtn.setVisibility(View.VISIBLE);
            prevBtn.setVisibility(View.VISIBLE);
            nextBtn.setVisibility(View.VISIBLE);
        }
    }

    private void getIncomingIntent() {
        if (getIntent().getExtras() != null) {
            difficulty = getIntent().getStringExtra("difficulty");
            noOfQuestions = getIntent().getStringExtra("noOfQuestions");
            isAnswered = new boolean[Integer.parseInt(noOfQuestions)];
            categoryId = getIntent().getIntExtra("categoryId", 0);
        }
    }

    private void getQuestions() {
        questionsApi();
    }

    private void questionsApi() {
        questionsViewModel.questionsApi(noOfQuestions, categoryId, difficulty);
    }
}
