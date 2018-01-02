package com.zero.tools;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.zero.logs.Logs;

public class LogsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);
        Button logButton = this.findViewById(R.id.button1);
        Button logButton2 = this.findViewById(R.id.button2);
        Button logButton3 = this.findViewById(R.id.button3);
        Button logButton4 = this.findViewById(R.id.button4);
        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logs.initLogs(true, true, true, true);
                Logs.v("日");
                Logs.d("志");
                Logs.i("打");
                Logs.w("印");
                Logs.e("测");
                Logs.restoreDefault();
            }
        });
        logButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Logs.initLogs(true, true, true, true);

                        long start = System.currentTimeMillis();
                        Logs.e("start" + start);
                        for (int i = 0; i < 9000; i++) {
                            Logs.i("" + i);
                        }
                        long end = System.currentTimeMillis();
                        Logs.e("end" + System.currentTimeMillis() + "spend = " + (end - start));

                    }
                });
                thread.setName("默认设置");
                thread.start();
            }
        });
        logButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Logs.initLogs(true, true, true, true);
                        Logs.startTransaction();
                        for (int i = 0; i < 9000; i++) {
                            Logs.i("" + i);
                        }
                        Logs.endTransaction();
                        Logs.restoreDefault();
                    }
                });
                thread.setName("全开设置");
                thread.start();
            }
        });
        logButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long start = System.currentTimeMillis();
                        Logs.e("start-->" + start);
                        for (int i = 0; i < 9000; i++) {

                        }
                        long end = System.currentTimeMillis();
                        Logs.e("end---->" + System.currentTimeMillis() + ",spend = " + (end - start));
                    }
                });
                thread.start();
            }
        });
    }
}
