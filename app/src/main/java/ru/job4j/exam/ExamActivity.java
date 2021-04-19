package ru.job4j.exam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExamActivity extends AppCompatActivity {

    private static final String TAG = "MyLogs";

    static final String ROTATION = "Rotation";
    private static int count = 0;

    private RadioGroup variants;

    //поле для хранения ответов пользователя
    private List<Integer> answer = new ArrayList<>();

////////////////////////////////////////////////////////////////////////////////////////////////////


    private final List<Question> questions = Arrays.asList(
            new Question(
                    1, "How many primitive variables does Java have?",
                    Arrays.asList(
                            new Option(1, "1.1"), new Option(2, "1.2"),
                            new Option(3, "1.3"), new Option(4, "1.4")
                    ), 4
            ),
            new Question(
                    2, "What is Java Virtual Machine?",
                    Arrays.asList(
                            new Option(1, "2.1"), new Option(2, "2.2"),
                            new Option(3, "2.3"), new Option(4, "2.4")
                    ), 4
            ),
            new Question(
                    3, "What is happen if we try unboxing null?",
                    Arrays.asList(
                            new Option(1, "3.1"), new Option(2, "3.2"),
                            new Option(3, "3.3"), new Option(4, "3.4")
                    ), 4
            )
    );

    private int position = 0;

    //Создадим метод, который будет брать текущую позиции и заполнять вопрос и варианты ответов.

    private void fillForm() {
        findViewById(R.id.previous).setEnabled(position != 0);
        findViewById(R.id.next).setEnabled(position != questions.size() - 1);
        final TextView text = findViewById(R.id.question);
        Question question = this.questions.get(this.position);
        text.setText(question.getText());
        variants = findViewById(R.id.variants);
        for (int index = 0; index != variants.getChildCount(); index++) {
            RadioButton button = (RadioButton) variants.getChildAt(index);
            Option option = question.getOptions().get(index);
            button.setId(option.getId());
            button.setText(option.getText());
        }
    }

    private void showAnswer() {
        variants = findViewById(R.id.variants);
        int id = variants.getCheckedRadioButtonId();
        Question question = this.questions.get(this.position);
        Toast.makeText(
                this, "Your answer is " + id + ", correct is " + question.getAnswer(),
                Toast.LENGTH_SHORT
        ).show();
    }

    private void  notSelectedNext(){
        variants = findViewById(R.id.variants);
        int id = variants.getCheckedRadioButtonId();
        if(id > 0) {
         position++;
         showAnswer();
        }
        else    Toast.makeText(
                this, "Your no answer  ",
                Toast.LENGTH_SHORT
        ).show();
    }

    private void  notSelectedPrevious(){
        variants = findViewById(R.id.variants);
        int id = variants.getCheckedRadioButtonId();
        if(id > 0) {
            position--;
        }
        else    Toast.makeText(
                this, "Your no answer  ",
                Toast.LENGTH_SHORT
        ).show();
    }

    private void saveAnswers(){
        this.answer.add(position, variants.getCheckedRadioButtonId());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        variants = findViewById(R.id.variants);

        if (savedInstanceState != null) {
          savedInstanceState.getInt(ROTATION);
        }

        this.fillForm();

        Button next = findViewById(R.id.next);
        next.setOnClickListener(
                view -> {
                    saveAnswers();
                    notSelectedNext();
                    fillForm();
                }
        );

        Button previous = findViewById(R.id.previous);
        previous.setOnClickListener(
                view -> {
                    notSelectedPrevious();
                    fillForm();
                }
        );
    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "OnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "OnResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "OnPause");
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ROTATION, count++);
        Log.i(TAG, "count: " + count);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "OnStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "OnDestroy");
    }




}