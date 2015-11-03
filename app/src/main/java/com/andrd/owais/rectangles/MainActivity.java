package com.andrd.owais.rectangles;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private EditText rect1_left, rect1_top, rect1_right, rect1_bottom;
    private EditText rect2_left, rect2_top, rect2_right, rect2_bottom;
    private int r1_left, r1_top, r1_right, r1_bottom;
    private int r2_left, r2_top, r2_right, r2_bottom;
    private TextView intersectionButton;
    private TextView containedButton;
    private TextView adjacentButton;
    private Bundle bundle;

    private int INTERSECT = 1;
    private int CONTAIN = 2;
    private int ADJACENT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set toolbar
        setToolbar();
        setViews();
        setListeners();

    }

    void setViews() {

        intersectionButton = (TextView) findViewById(R.id.xml_intersection_button);
        containedButton = (TextView) findViewById(R.id.xml_contained_button);
        adjacentButton = (TextView) findViewById(R.id.xml_adjacent_button);

        rect1_left = (EditText) findViewById(R.id.xml_rect1_left);
        rect1_top = (EditText) findViewById(R.id.xml_rect1_top);
        rect1_right = (EditText) findViewById(R.id.xml_rect1_right);
        rect1_bottom =  (EditText) findViewById(R.id.xml_rect1_bottom);

        rect2_left = (EditText) findViewById(R.id.xml_rect2_left);
        rect2_top = (EditText) findViewById(R.id.xml_rect2_top);
        rect2_right = (EditText) findViewById(R.id.xml_rect2_right);
        rect2_bottom = (EditText) findViewById(R.id.xml_rect2_bottom);
    }

    void setListeners() {
        intersectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isInputValid()) {

                    // store data
                    bundleData(INTERSECT);

                    // start draw activity
                    Intent intent = new Intent(MainActivity.this, DrawActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }
            }
        });

        containedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isInputValid()) {

                    // store data
                    bundleData(CONTAIN);

                    // start draw activity
                    Intent intent = new Intent(MainActivity.this, DrawActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }
            }
        });

        adjacentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isInputValid()) {

                    // store data
                    bundleData(ADJACENT);

                    // start draw activity
                    Intent intent = new Intent(MainActivity.this, DrawActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }
            }
        });
    }

    // ensure width < maxWidth and height < maxHeight
    public boolean isInputValid() {

        if(rect1_left.getText().toString().matches("") ||
                rect1_top.getText().toString().matches("") ||
                rect1_right.getText().toString().matches("") ||
                rect1_bottom.getText().toString().matches("") ||
                rect2_left.getText().toString().matches("") ||
                rect2_top.getText().toString().matches("") ||
                rect2_right.getText().toString().matches("") ||
                rect2_bottom.getText().toString().matches("")) {
            Toast.makeText(MainActivity.this, "empty field", Toast.LENGTH_LONG).show();
            return false;
        }

        r1_left = Integer.parseInt(rect1_left.getText().toString());
        r1_top = Integer.parseInt(rect1_top.getText().toString());
        r1_right = Integer.parseInt(rect1_right.getText().toString());
        r1_bottom = Integer.parseInt(rect1_bottom.getText().toString());

        r2_left = Integer.parseInt(rect2_left.getText().toString());
        r2_top = Integer.parseInt(rect2_top.getText().toString());
        r2_right = Integer.parseInt(rect2_right.getText().toString());
        r2_bottom = Integer.parseInt(rect2_bottom.getText().toString());

        // values bounded by screen
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels - getStatusBarHeight(MainActivity.this);
        int width = displaymetrics.widthPixels;
        if(!((r1_right <= width && r2_right <= width) && (r1_bottom <= height && r2_bottom <= height))) {
            Toast.makeText(MainActivity.this, "values larger than screen size", Toast.LENGTH_LONG).show();
            return false;
        }

        // valid rectangles
        if((r1_left < r1_right && r1_top < r1_bottom)&&(r2_left < r2_right && r2_top < r2_bottom)) {
            return true;
        }
        Toast.makeText(MainActivity.this, "invalid rectangle values", Toast.LENGTH_LONG).show();
        return false;
    }


    public void bundleData(int choice) {
            bundle = new Bundle();
            bundle.putInt("choice", choice);
            bundle.putInt("rect1_left", r1_left);
            bundle.putInt("rect1_top", r1_top);
            bundle.putInt("rect1_right", r1_right);
            bundle.putInt("rect1_bottom", r1_bottom);
            bundle.putInt("rect2_left", r2_left);
            bundle.putInt("rect2_top", r2_top);
            bundle.putInt("rect2_right", r2_right);
            bundle.putInt("rect2_bottom", r2_bottom);
    }

    @Override
    protected void onResume() {
        super.onResume();
        clearEditTexts();
    }


    public void clearEditTexts() {
        rect1_left.setText("");
        rect1_top.setText("");
        rect1_right.setText("");
        rect1_bottom.setText("");
        rect2_left.setText("");
        rect2_top.setText("");
        rect2_right.setText("");
        rect2_bottom.setText("");
    }

    public void setToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Rectangles");

    }

    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }

}
