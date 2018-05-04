package com.example.ezequielborenstein.pruebacomentariossobrefoto;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity{

    public int centralPositionOfTag = 35;
    public int numberOfTag = 1;
    public Map<Integer, Tag> tagsAdded;

    /** Called when the activity is first created. */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tagsAdded = new HashMap<>();

        CustomKeyboard keyboard = new CustomKeyboard();
        EditText commentBox = (EditText) findViewById(R.id.comment_box_id);
        commentBox.setOnEditorActionListener(keyboard);
        commentBox.setEnabled(false);

        final ImageView baseImage = (ImageView)findViewById(R.id.base_image_id);
        baseImage.setOnTouchListener(new View.OnTouchListener() {

            final Handler handler = new Handler();
            int touchX;
            int touchY;

            Runnable mLongPressed = new Runnable() {
                public void run() {
                    int[] viewCoords = new int[2];

                    numberOfTag = TagDrawer.drawTag(baseImage, viewCoords, (RelativeLayout) findViewById(R.id.tags_layout_id), touchX, touchY,
                            centralPositionOfTag, numberOfTag, tagsAdded);

                    EditText commentBox = ((EditText)findViewById(R.id.comment_box_id));
                    commentBox.setEnabled(true);
                    commentBox.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(commentBox.getContext().INPUT_METHOD_SERVICE);
                    imm.showSoftInput(commentBox, InputMethodManager.SHOW_IMPLICIT);
                }
            };


            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        touchX = (int) event.getX();
                        touchY = (int) event.getY();
                        handler.postDelayed(mLongPressed, 200);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_MOVE:
                        handler.removeCallbacks(mLongPressed);
                        break;
                }
                return true;
            }
        });
    }

    // Keyboard manager
    class CustomKeyboard implements TextView.OnEditorActionListener{
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
            if(actionId == EditorInfo.IME_ACTION_DONE){
                insertComment(null);
            }
            return false;
        }
    }

    public void insertComment(View view){
        EditText commentBox = (EditText)findViewById(R.id.comment_box_id);
        String comment = commentBox.getText().toString();
        tagsAdded.get(numberOfTag - 1).setComment(comment);
        commentBox.setText("");
        commentBox.setEnabled(false);
        InputMethodManager keyboard = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        keyboard.hideSoftInputFromWindow(commentBox.getWindowToken(), 0);
    }
}
