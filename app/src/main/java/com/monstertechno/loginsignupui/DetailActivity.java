package com.monstertechno.loginsignupui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {
    ImageView imageView;
    TextView textView, result;
    int count = 0;
    ImageView pluse, minus;
    Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        imageView = findViewById(R.id.image);
        textView = findViewById(R.id.name);
        pluse = findViewById(R.id.plus);
        minus = findViewById(R.id.minus);
        result = findViewById(R.id.in_de);
        done = findViewById(R.id.done);

        int getImg = getIntent().getIntExtra("image", 0);
        imageView.setImageResource(getImg);
        textView.setText(getIntent().getExtras().getString("name"));

        pluse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;

                result.setText("" + count);

            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (count <= 0) count = 0;

                else {

                    count--;
                    result.setText("" + count);
                }

            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailActivity.this, "Successfully.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}