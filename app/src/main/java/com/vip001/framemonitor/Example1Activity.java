package com.vip001.framemonitor;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.vip001.monitor.FrameMonitorManager;

/**
 * Created by xxd on 2018/8/14
 */
public class Example1Activity extends BaseActivity {
    private Button mJANKBtn;
    private Button mStartBtn;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam1);
        JANKSwitch.DEBUG_JANK = false;
        mJANKBtn = (Button) this.findViewById(R.id.btn);
        mStartBtn = (Button) this.findViewById(R.id.start);
        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FrameMonitorManager.getInstance().isStarted()) {
                    FrameMonitorManager.getInstance().stop();
                } else {
                    FrameMonitorManager.getInstance().start().setEnableShow(true);
                }
                updateStartText();
            }
        });
        mListView = (ListView) this.findViewById(R.id.list);
        mListView.setAdapter(new ExampleApdater());
        mJANKBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JANKSwitch.DEBUG_JANK = !JANKSwitch.DEBUG_JANK;
                updateJANKText();
            }
        });
        updateJANKText();
        updateStartText();
    }

    private void updateJANKText() {
        if (JANKSwitch.DEBUG_JANK) {
            mJANKBtn.setText("关闭掉帧");
        } else {
            mJANKBtn.setText("开启掉帧");
        }
    }

    private void updateStartText() {
        if (FrameMonitorManager.getInstance().isStarted()) {
            mStartBtn.setText("停止检测");
        } else {
            mStartBtn.setText("开启检测");
        }
    }
}
