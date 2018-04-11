package com.greysonparrelli.gitlabciandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView num1View = (TextView) findViewById(R.id.num1);
        final TextView num2View = (TextView) findViewById(R.id.num2);

        findViewById(R.id.calc_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n1Text = num1View.getText().toString();
                String n2Text = num2View.getText().toString();

                if (InputValidator.getInstance().isInt(n1Text) && InputValidator.getInstance().isInt(n2Text)) {
                    int n1 = Integer.parseInt(n1Text);
                    int n2 = Integer.parseInt(n2Text);

                    int sum = Adder.getInstance().add(n1, n2);
                    startActivity(SumActivity.buildIntent(MainActivity.this, sum));
                } else {
                    Toast.makeText(MainActivity.this, "One of your inputs was invalid.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
