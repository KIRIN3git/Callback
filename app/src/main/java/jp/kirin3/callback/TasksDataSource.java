package jp.kirin3.callback;

import androidx.annotation.NonNull;

import java.util.List;

public interface TasksDataSource {

    // コールバックとして呼ばれる関数
    interface LoadTasksCallback {
        void onTasksLoaded(List<Task> tasks);
        void onDataNotAvailable();
    }

    // データ取得関数（別に書かなくてよいがまとめて書いておく）
    void getTasks(@NonNull LoadTasksCallback callback);
}
