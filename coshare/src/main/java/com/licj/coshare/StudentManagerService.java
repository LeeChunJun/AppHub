package com.licj.coshare;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;

import androidx.annotation.Nullable;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class StudentManagerService extends Service {
    private static final String TAG = "StudentManagerService";

    // 判断当前Service是否被消费
    private AtomicBoolean mIsServiceDestory = new AtomicBoolean(false);
    // 用于进程安全的传输列表列
    private CopyOnWriteArrayList<Student> mStudentList = new CopyOnWriteArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        // 在服务端添加两个学生
        mStudentList.add(new Student(1,"Nancy", "woman"));
        mStudentList.add(new Student(2,"Rhythm", "man"));


    }

    @Override
    public void onDestroy() {
        mIsServiceDestory.set(true);
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyStudentManager();
    }

    class MyStudentManager extends IStudentManager.Stub {

        @Override
        public List<Student> getStudentList() throws RemoteException {
            SystemClock.sleep(2000);// 休眠2秒，模拟服务端耗时
            return mStudentList;
        }

        @Override
        public void addStudent(Student student) throws RemoteException {
            mStudentList.add(student);
        }
    }
}
