package jp.kirin3.callback;

import androidx.annotation.NonNull;

import java.util.List;

import static androidx.core.util.Preconditions.checkNotNull;

public class TasksRepository{

    List<Task> mCachedTasks;

    TasksDataSource mTasksBackgroundSource;

    private static TasksRepository INSTANCE = null;

    public static TasksRepository getInstance(TasksDataSource tasksLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new TasksRepository(tasksLocalDataSource);
        }
        return INSTANCE;
    }

    // Prevent direct instantiation.
    private TasksRepository(@NonNull TasksDataSource tasksBackgroundDataSource) {
        // TasksBackgroundSourceのインスタンス
        mTasksBackgroundSource = checkNotNull(tasksBackgroundDataSource);
        mTasksBackgroundSource.getTasks(new TasksDataSource.LoadTasksCallback() {
            // データ取得後に呼ばれる処理（データありの時）
            @Override
            public void onTasksLoaded(List<Task> tasks) {
                MainActivity.showTextMsg(changeTasksToString(tasks));
                mCachedTasks = tasks;
            }
            // データ取得後に呼ばれる処理（データなしの時）
            @Override
            public void onDataNotAvailable() {
                // 今回は処理なし
            }
        });
    }

    // タスクの文字列結合
    private String changeTasksToString(@NonNull List<Task> tasks){
        StringBuilder text = new StringBuilder();
        for(int i = 0; i < tasks.size(); i++){
            text.append("id:").append(tasks.get(i).getId()).append(" ");
            text.append("name:").append(tasks.get(i).getName()).append("\n");
        }
        return text.toString();
    }
}
