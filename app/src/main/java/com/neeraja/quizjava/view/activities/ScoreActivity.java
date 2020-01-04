package com.neeraja.quizjava.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.neeraja.quizjava.R;
import com.neeraja.quizjava.model.Category;

public class ScoreActivity extends AppCompatActivity {

    private int noOfQuestions;
    private double score, percentage;
    @BindView(R.id.tv_score)
    TextView scoreTv;
    @BindView(R.id.tv_remarks)
    TextView remarksTv;
    @BindView(R.id.iv_home)
    ImageView homeIv;
    @BindView(R.id.iv_share)
    ImageView shareIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        ButterKnife.bind(this);

        getIncomingIntent();
        updateData();
    }

    @OnClick({R.id.iv_home, R.id.iv_share})
    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.iv_home:
                startActivity(new Intent(ScoreActivity.this, CategoriesActivity.class));
                break;
            case R.id.iv_share:
                share();
                break;
            default:
                break;
        }
    }

    private void share() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        String remarks = "I have attempted <font color=#cc0029>" + noOfQuestions + "</font> Questions and <font color=#cc0029>"
                + (int)score + "</font> answers are correct"
                + " on Quiz App. Try it out at: http://test.com/quizapp";
        sendIntent.putExtra(Intent.EXTRA_TEXT, remarks);
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }

    private void getIncomingIntent() {
        noOfQuestions = getIntent().getIntExtra("totalQuestions", 0);
        score = getIntent().getDoubleExtra("score", 0);
        percentage = getIntent().getDoubleExtra("percentage", 0);
    }

    private void updateData() {
        scoreTv.setText(percentage + "%" + " score");
        String remarks = "I have attempted <font color=#E88331>" + noOfQuestions + "</font> Questions and <font color=#E88331>"
                + (int)score + "</font> answers are correct"
                + " on Quiz App. Try it out <a href=\"http://test.com/quizapp\">Here</a>";
        remarksTv.setText(Html.fromHtml(remarks));
        remarksTv.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ScoreActivity.this, CategoriesActivity.class));
    }
}
