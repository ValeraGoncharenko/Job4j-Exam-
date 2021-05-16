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

/**
 * MVC. Создания события и получение данных от пользователя.
 * @author Valera Goncharenko (goncharikvv@gmail.com).
 * @version 1.
 * @since 23.04.2021.
 */
public class ExamActivity extends AppCompatActivity {

    private static final String TAG = "MyLogs";

    static final String ROTATION = "Rotation";
    private static int count = 0;

    private RadioGroup variants;

    /** Поле для хранения ответов пользователя*/
    private List<Integer> answer = new ArrayList<>();

    /**Поле содержащее список вопросов*/
    private final QuestionStore store = QuestionStore.getInstance();

    /**Поле указывающее на текущий вопрос*/
    private int position = 0;

    /**
     * Метод, берет текущюю позицию и заполняет вопрос и варианты ответов.
     */
    private void fillForm() {
        findViewById(R.id.previous).setEnabled(position != 0);
        findViewById(R.id.next).setEnabled(position != store.size() - 1);
        final TextView text = findViewById(R.id.question);
        Question question = this.store.get(this.position);
        text.setText(question.getText());
        variants = findViewById(R.id.variants);
        for (int index = 0; index != variants.getChildCount(); index++) {
            RadioButton button = (RadioButton) variants.getChildAt(index);
            Option option = question.getOptions().get(index);
            button.setId(option.getId());
            button.setText(option.getText());
        }
    }

    /**
     * Когда пользователь нажимает кнопку далее ему показывает правильный вариант ответа.
     */
    private void showAnswer() {
        variants = findViewById(R.id.variants);
        int id = variants.getCheckedRadioButtonId();
        Question question = this.store.get(this.position);
        Toast.makeText(
                this, "Your answer is " + id + ", correct is " + question.getAnswer(),
                Toast.LENGTH_SHORT
        ).show();
    }

    /**
     * Не переключает кнопку Next если вариант не выбран.
     */
    private void notSelectedNext() {
        variants = findViewById(R.id.variants);
        int id = variants.getCheckedRadioButtonId();
        if (id > 0) {
            position++;
            showAnswer();
        } else Toast.makeText(
                this, "Your no answer  ",
                Toast.LENGTH_SHORT
        ).show();
    }

    /**
     * Не переключает кнопку Previous если вариант не выбран.
     */
    private void notSelectedPrevious() {
        variants = findViewById(R.id.variants);
        int id = variants.getCheckedRadioButtonId();
        if (id > 0) {
            position--;
        } else Toast.makeText(
                this, "Your no answer  ",
                Toast.LENGTH_SHORT
        ).show();
    }

    /**
     * Сохраняет ответы в памяти.
     */
    private void saveAnswers() {
        this.answer.add(position, variants.getCheckedRadioButtonId());
    }

    /**
     * Метод для упрощения кода и использования его в лямбда-выражениях.
     * @param view ссылка на кнопку next.
     */
    private void nextBtn(View view) {
        saveAnswers();
        notSelectedNext();
        fillForm();
    }

    /**
     * Метод для упрощения кода и использования его в лямбда-выражениях.
     * @param view ссылка на кнопку back.
     */
    private void previousBtn(View view) {
        notSelectedPrevious();
        fillForm();
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
        next.setOnClickListener(this::nextBtn);

        Button previous = findViewById(R.id.previous);
        previous.setOnClickListener(this::previousBtn);
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