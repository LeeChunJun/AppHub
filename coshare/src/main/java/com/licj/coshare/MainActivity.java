package com.licj.coshare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "main_activity";
    private IStudentManager mRemoteStudentManager;
    private int student_size = 0;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 获取到 IStudentmanager 对象
            IStudentManager iStudentManager = IStudentManager.Stub.asInterface(service);
            mRemoteStudentManager = iStudentManager;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mRemoteStudentManager = null;
            Log.d(TAG, "onServiceDisconnected.threadName" + Thread.currentThread().getName());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, StudentManagerService.class);
        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }


    public void toSecondActivity(View view) {
        Student.NAME = "neteasy";
        Log.d(TAG, Student.NAME);
        startActivity(new Intent(this, SecondActivity.class));
    }

    public void getStudentList(View view) {
        Toast.makeText(this, "正在获取学生列表", Toast.LENGTH_SHORT).show();
        // 由于服务端查询是耗时操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (mRemoteStudentManager != null) {
                    try {
                        List<Student> studentList = mRemoteStudentManager.getStudentList();
                        student_size = studentList.size();
                        Log.d(TAG, "获取到了学生列表：" + studentList.toString());

                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void addStudent(View view) {
        if (mRemoteStudentManager != null) {
            try {
                int student_id = student_size + 1;
                Student student = new Student(student_id, "Nancy" + student_id, student_id % 2 == 0 ? "man" : "woman");
                mRemoteStudentManager.addStudent(student);
                Log.d(TAG, "添加一位学生：" + student.toString());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
