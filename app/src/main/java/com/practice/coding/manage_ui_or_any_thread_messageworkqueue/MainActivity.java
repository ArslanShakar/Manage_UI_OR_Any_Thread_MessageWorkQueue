package com.practice.coding.manage_ui_or_any_thread_messageworkqueue;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ScrollView scrollView;
    private TextView tvData;
    StringBuffer buffer = new StringBuffer();
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBarHorizontal);
        scrollView = findViewById(R.id.scrollView);
        tvData = findViewById(R.id.textView);

        tvData.setText(R.string.randomText);
    }

    public void runCode(View view) {

        tvData.append("\nCode Running. . .\n");
        scrollView.fullScroll(View.FOCUS_DOWN);
        progressBar.setVisibility(View.VISIBLE);

        //Here we want to block the code execution for some time like 4 seconds on the UI/Main Thread.How we can do it let see
         /*
          //First way to stop the execution of code on the main thread but its blocks the UI.
        try {
            //here we are on the main thread and we perform direct or straight forward blocking call block on the
            // main or UI thread That result our UI face blocking
            //Thread.sleep(4000);
        } catch (Exception e) {

        }
        */

         //Second way to delaying the code execution using handler with post method but it also block the UI
        /*
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    //here we are still on the main thread and this this blocking call block our UI
                    Thread.sleep(4000);
                } catch (Exception e) {}
            }
        };

        Handler handler = new Handler();
        handler.post(runnable); */
        /*
        post method put the message or work packet at the end of the MessageWorkQueue of the Thread. But it does not
        delays its execution.
         */

        //Third way to stop the code execution for some time and our UI does not block
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
               //Do Whatever here after specified time like here 4 seconds the run method execute..and
                // here i am set progress bar Visibility GONE..
                progressBar.setVisibility(View.GONE);
            }
        };

        Handler handler = new Handler();
        handler.postDelayed(runnable, 4000);
        /*
        Basically postDelayed method release the main thread , it does not disturb the main thread and main thread doing his work
        like mainting the UI and input events , after every 16 mili seconds main thread responsibility is to refresh or redraw the
        secreen components or views.
        post delayed method delayed the task execution on the worker thread and after specified time it ask the work queue to perform
        the specific task. like here our work is the UI means Main thread work queue. Handler is bound to that work queue or thread
        that creating the handler object. so handler post delayed methods delays a specific task after that time it send the task
        in the work queue and ask work queue corresponding thread to execute it.
         */
    }
}
