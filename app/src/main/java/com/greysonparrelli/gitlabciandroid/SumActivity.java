package com.greysonparrelli.gitlabciandroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Displays the provided sum.
 *
 * @author Greyson Parrelli (keybase.io/greyson)
 */
public class SumActivity extends AppCompatActivity {

    private static final String KEY_SUM = "sum";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum);

        if (getIntent() == null || !getIntent().hasExtra(KEY_SUM)) {
            throw new IllegalStateException("Must be provided a sum to display.");
        }
        int sum = getIntent().getIntExtra(KEY_SUM, 0);

        TextView sumView = (TextView) findViewById(R.id.sum);
        sumView.setText(String.valueOf(sum));
    }

    public static Intent buildIntent(Context context, int sum) {
        Intent intent = new Intent(context, SumActivity.class);
        intent.putExtra(KEY_SUM, sum);
        return intent;
    }
}
