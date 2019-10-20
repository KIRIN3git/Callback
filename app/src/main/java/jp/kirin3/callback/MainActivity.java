package jp.kirin3.callback;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static TextView  mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.textView);

        // インスタンス生成しているだけ
        TasksRepository.getInstance(TasksBackgroundDataSource.getInstance());
    }

    // 良くない書き方だけど、シンプルにするために・・・
    public static void showTextMsg(String word) {
        mTextView.setText(word);
    }
}
