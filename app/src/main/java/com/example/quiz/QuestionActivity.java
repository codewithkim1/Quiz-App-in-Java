package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    TextView lblQuestion;
    RadioButton optionA;
    RadioButton optionB;
    RadioButton optionC;
    RadioButton optionD;
    Button confirm;
    String rightAnswer;
    String Answer;
    List<Question> questions;
    int score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        confirm = findViewById(R.id.confirm);
        lblQuestion = findViewById(R.id.lblPergunta);
        optionA = findViewById(R.id.opcaoA);
        optionB = findViewById(R.id.opcaoB);
        optionC = findViewById(R.id.opcaoC);
        optionD = findViewById(R.id.opcaoD);
        score = 0;
        radioGroup = findViewById(R.id.radioGroup);

        questions = new ArrayList<Question>(){
            {
                add(new Question(" What is the current version of Android, and how old is it?", "C", "3.1", "1.7","12.0", "9.0"));
                add(new Question("What database is used for the Android platform: \"What database is used for the Android platform\"?", "A", "SQLite", "MariaDB","MSQL", "noSQL"));
                add(new Question("Can you change an application’s name after you have deployed it?", "D", "No", "I'm not sure","Why?", "Yes"));
                add(new Question("What language does Android use", "B", "HTML", "Java", "CSS", "Bootstrap"));
                add(new Question("Android phone is based on which OS?", "A", "Linux", "no OS", "Windows", "HIOS"));
                add(new Question("What’s a “bundle” in Android?", "D", "Screen", "Data", "Web View of the APP", "used to pass the required data to sub-folders."));
                add(new Question("How do you identify view elements in an android program?", "C", "Read out", "use data network", "Use the keyword findViewById.\n" + "\n", "Use keywords"));
                add(new Question(" Can Bytecode that is written in Java run on Android?", "B", "Yes", "No", "I don't know", "No opinion"));
                add(new Question("What is ADB?", "B", "Alphabets", "Android Debug Bridge", "Type of phone", "Operating system"));
                add(new Question("Android Os Works well on?", "D", "linux laptop", "calling", "basket", "Android phone"));
            }
        };

        loadQuestion();
    }


    @Override
    protected void onRestart(){
        super.onRestart();
        loadQuestion();
    }


    private void loadQuestion(){
        if(questions.size() > 0) {
            Question q = questions.remove(0);
            lblQuestion.setText(q.getQuestion());
            List<String> answers = q.getAnswers();

            optionA.setText(answers.get(0));
            optionB.setText(answers.get(1));
            optionC.setText(answers.get(2));
            optionD.setText(answers.get(3));

            rightAnswer = q.getRightAnswer();
        } else {
            Intent intent = new Intent(this, ShowScoreActivity.class);
            intent.putExtra("score", score);
            startActivity(intent);
            finish();
        }
    }

    public void loadAnswer(View view) {
        int op = radioGroup.getCheckedRadioButtonId();

        switch (op){
            case R.id.opcaoA:
                Answer="A";
                break;

            case R.id.opcaoB:
                Answer="B";
                break;

            case R.id.opcaoC:
                Answer="C";
                break;

            case R.id.opcaoD:
                Answer="D";
                break;

            default:
                return;

        }

        radioGroup.clearCheck();

        this.startActivity(isRightOrWrong(Answer));

    }

    private Intent isRightOrWrong(String Answer){
        Intent screen;
        if(Answer.equals(rightAnswer)) {
            this.score += 1;
            screen = new Intent(this, RightActivity.class);

        }else {
            screen = new Intent(this, WrongActivity.class);
        }

        return screen;
    }
}
