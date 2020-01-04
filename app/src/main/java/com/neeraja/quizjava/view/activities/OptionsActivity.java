package com.neeraja.quizjava.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.neeraja.quizjava.R;
import com.neeraja.quizjava.util.Utils;

public class OptionsActivity extends AppCompatActivity {
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.et_no_of_questions)
    EditText noOfQuestionsEt;
    @BindView(R.id.btn_start)
    Button startBtn;
    private int categoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        ButterKnife.bind(this);

        getIncomingIntent();
    }

    private void getIncomingIntent() {
        if (getIntent().getExtras() != null) {
            categoryId = getIntent().getIntExtra("categoryId", 0);
        }
    }

    @OnClick(R.id.btn_start)
    public void onStartClick(View view) {
        String noOfQuestions = noOfQuestionsEt.getText().toString().trim();
        int checkedId = radioGroup.getCheckedRadioButtonId();
        String difficulty = getDifficulty(checkedId);
        if (Utils.isValidString(noOfQuestions)) {
            Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
            intent.putExtra("noOfQuestions", Integer.parseInt(noOfQuestions));
            intent.putExtra("difficulty", difficulty);
            intent.putExtra("categoryId", categoryId);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Please Enter No. of Questions", Toast.LENGTH_SHORT).show();
        }
    }

    private String getDifficulty(int checkedId) {
        String difficulty = null;
        if (checkedId == R.id.rb_easy) {
            difficulty = "easy";
        } else if (checkedId == R.id.rb_medium) {
            difficulty = "medium";
        } else {
            difficulty = "hard";
        }
        return difficulty;
    }
}
