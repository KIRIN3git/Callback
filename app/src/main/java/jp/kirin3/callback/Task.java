package jp.kirin3.callback;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Task {

    private final int mId;
    private final String mName;

    public Task(@NonNull int id, @Nullable String name){
        mId = id;
        mName = name;
    }

    public int getId(){
        return mId;
    }
    public String getName(){
        return mName;
    }
}
