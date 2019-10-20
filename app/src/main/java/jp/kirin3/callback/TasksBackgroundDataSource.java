package jp.kirin3.callback;

import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class TasksBackgroundDataSource implements TasksDataSource {

    private static volatile TasksBackgroundDataSource INSTANCE;

    public static TasksBackgroundDataSource getInstance() {
        if (INSTANCE == null) {
            // 他のスレッドからアクセスされないようにブロックをかける（スレッド・セーフ）
            synchronized (TasksBackgroundDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TasksBackgroundDataSource();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getTasks(@NonNull final LoadTasksCallback callback) {

        final Handler handler = new Handler();

        // スレッド立ち上げ
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                 // Handlerを使用してメインスレッドに処理を依頼する（View更新のため）
                 handler.post(new Runnable() {
                     @Override
                     public void run() {

                         // データ入れてるだけ・・・
                         List<Task> tasks = new ArrayList<Task>();
                         tasks.add(new Task(1, "title1"));
                         tasks.add(new Task(2, "title2"));
                         // 非同期をわかりやすく3秒スリープ
                         try {
                             Thread.sleep(3000); //ミリ秒
                         } catch (InterruptedException e) {
                         }

                         if (tasks.isEmpty()) {
                             // データがない時の処理
                             callback.onDataNotAvailable();

                         } else {
                             // データがあった時の処理
                             callback.onTasksLoaded(tasks);
                         }
                     }
                 });

            }
        });
        thread.start();
    }
}
