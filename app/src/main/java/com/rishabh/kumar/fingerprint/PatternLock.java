package com.rishabh.kumar.fingerprint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;

import java.util.List;

import io.paperdb.Paper;

public class PatternLock extends AppCompatActivity {

    String save_pattern_key="pattern_code";
    String final_pattern="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Paper.init(this);
        final String save_pattern = Paper.book().read(save_pattern_key);

        if (save_pattern!=null && !save_pattern.equals("null")){
            setContentView(R.layout.pattern_screen);

            final PatternLockView mPatternLockView=(PatternLockView) findViewById(R.id.patternview);

            mPatternLockView.addPatternLockListener(new PatternLockViewListener() {
                @Override
                public void onStarted() {

                }

                @Override
                public void onProgress(List<PatternLockView.Dot> progressPattern) {

                }

                @Override
                public void onComplete(List<PatternLockView.Dot> pattern) {

                     final_pattern= PatternLockUtils.patternToString(mPatternLockView,pattern);
                    if (final_pattern.equals(save_pattern)){
                        Toast.makeText(PatternLock.this,"Authentication Successful",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(PatternLock.this,Login.class));

                    }
                    else {
                        Toast.makeText(PatternLock.this,"Authentication Unsuccessful",Toast.LENGTH_SHORT).show();

                    }


                }

                @Override
                public void onCleared() {

                }
            });
        }
        else {
            setContentView(R.layout.activity_pattern_lock);
            final PatternLockView mPatternLockView=(PatternLockView) findViewById(R.id.patternview);

            mPatternLockView.addPatternLockListener(new PatternLockViewListener() {
                @Override
                public void onStarted() {

                }

                @Override
                public void onProgress(List<PatternLockView.Dot> progressPattern) {

                }

                @Override
                public void onComplete(List<PatternLockView.Dot> pattern) {

                    final_pattern=PatternLockUtils.patternToString(mPatternLockView,pattern);

                }

                @Override
                public void onCleared() {

                }
            });
            Button btnSetup=(Button) findViewById(R.id.submitPattern);
            btnSetup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Paper.book().write(save_pattern_key,final_pattern);
                    finish();

                }
            });
        }

    }
}
