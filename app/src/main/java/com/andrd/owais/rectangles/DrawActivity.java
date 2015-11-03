package com.andrd.owais.rectangles;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by owais on 11/1/15.
 */
public class DrawActivity extends AppCompatActivity {

    private DrawView drawView;
    private TextView displayTextView;
    private TextView clearCanvasButton;
    private Bundle bundle;

    int choice;
    private Rect rect1, rect2;
    int rect1_left, rect1_top, rect1_right, rect1_bottom;
    int rect2_left, rect2_top, rect2_right, rect2_bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

        extractBundleData();

        // create rects
        rect1 = new Rect(rect1_left, rect1_top, rect1_right, rect1_bottom);
        rect2 = new Rect(rect2_left, rect2_top, rect2_right, rect2_bottom);
        drawView = (DrawView) findViewById(R.id.xml_draw_view);
        drawView.setParameters(rect1, rect2);
        drawView.setBackgroundColor(Color.WHITE);

        // display textView
        displayTextView = (TextView) findViewById(R.id.xml_display_textView);

        // clear button
        clearCanvasButton = (TextView) findViewById(R.id.xml_clear_canvas);
        clearCanvasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawActivity.this.finish();
            }
        });

        displayMessage();
    }

    // get data from bundle
    public void extractBundleData() {
        // get bundle
        bundle = getIntent().getExtras();

        // extract data
        choice = bundle.getInt("choice");
        rect1_left = bundle.getInt("rect1_left");
        rect1_top = bundle.getInt("rect1_top");
        rect1_right = bundle.getInt("rect1_right");
        rect1_bottom = bundle.getInt("rect1_bottom");
        rect2_left = bundle.getInt("rect2_left");
        rect2_top = bundle.getInt("rect2_top");
        rect2_right = bundle.getInt("rect2_right");
        rect2_bottom = bundle.getInt("rect2_bottom");
    }

    // display intersection, containment, or adjacency message
    public void displayMessage() {
        switch (choice) {
            case 1:
                if(doIntersect(rect1, rect2)) {
                    displayTextView.setText("INTERSECTION!");
                } else {
                    displayTextView.setText("NO INTERSECTION!");
                }
                break;
            case 2:
                if(areContained(rect1, rect2)) {
                    displayTextView.setText("CONTAINMENT!");
                } else {
                    displayTextView.setText("NO CONTAINMENT!");
                }
                break;
            case 3:
                if(areAdjacent(rect1, rect2)) {
                    displayTextView.setText("ADJACENCY!");
                } else {
                    displayTextView.setText("NO ADJACENCY!");
                }
                break;
        }
    }

    // if the interior of both rectangles intersect, or overlap, return true
    public boolean doIntersect(Rect rect1, Rect rect2) {

        int x1 = rect1.left;
        int y1 = rect1.bottom;
        int w1 = rect1.right - rect1.left;
        int h1 = rect1.bottom - rect1.top;

        int x2 = rect2.left;
        int y2 = rect2.bottom;
        int w2 = rect2.right - rect2.left;
        int h2 = rect2.bottom - rect2.top;

        if(x1+w1 <= x2 || x2+w2 <= x1 || y1+h1 <= y2 || y2+h2 <= y1) {
            return false;
        }
        return true;
    }

    // if the interior of either rectangle is wholly within the boundaries of the other rectangle, return true
    public boolean areContained(Rect rect1, Rect rect2) {

        int w1 = rect1.right - rect1.left;
        int h1 = rect1.bottom - rect1.top;

        int w2 = rect2.right - rect2.left;
        int h2 = rect2.bottom - rect2.top;

        int area1 = w1*h1;
        int area2 = w2*h2;

        if(area1 <= area2) {
            // if rect1 is smaller than rect2
            if(rect2.left <= rect1.left && rect2.right >= rect1.right &&
                    rect2.top <= rect1.top && rect2.bottom >= rect1.bottom) {
                return true;
            }
            return false;
        } else {
            // if rect2 is smaller than rect1
            if(rect1.left <= rect2.left && rect1.right >= rect2.right &&
                    rect1.top <= rect2.top && rect1.bottom >= rect2.bottom) {
                return true;
            }
            return false;
        }
    }

    // if either rectangle shares a side (proper or sub-line, return true
    public boolean areAdjacent(Rect rect1, Rect rect2) {

        if(rect1.left == rect2.left || rect1.right==rect2.right || rect1.right == rect2.left || rect1.left == rect2.right) {
            if( (rect1.top > rect2.top && rect1.bottom > rect2.bottom)  ||  (rect1.top < rect2.top && rect1.bottom < rect2.bottom) ) {
                return false;
            }
            return true;
        } else if(rect1.top == rect2.top || rect1.bottom == rect2.bottom || rect1.top == rect2.bottom || rect1.bottom == rect2.top) {
            if( (rect1.left > rect2.left && rect1.right > rect2.right)  ||  (rect1.left < rect2.left && rect1.right < rect2.right) ) {
                return false;
            }
            return true;
        }
        return false;
    }
}
