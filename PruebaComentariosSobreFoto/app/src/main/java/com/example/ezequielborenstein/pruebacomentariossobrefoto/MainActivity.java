package com.example.ezequielborenstein.pruebacomentariossobrefoto;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity{

    public int centralPositionOfTag = 35;
    public Map<Integer, Tag> tagsAdded;

    /** Called when the activity is first created. */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tagsAdded = new HashMap<>();

        final EditText commentBox = (EditText) findViewById(R.id.comment_box_id);
        ViewsController.setCommentBox(commentBox);
        commentBox.setEnabled(false);

        final RelativeLayout tagAndNumberLayout = ((RelativeLayout)findViewById(R.id.tag_and_number_id));
        ViewsController.setTagAndNumberLayout(tagAndNumberLayout);
        tagAndNumberLayout.setVisibility(View.INVISIBLE);

        final TextView numberOverTag = (TextView)findViewById(R.id.tag_number_id);
        ViewsController.setNumberOverTag(numberOverTag);

        final ImageView baseImage = (ImageView)findViewById(R.id.base_image_id);
        ViewsController.setBaseImage(baseImage);
        baseImage.setOnTouchListener(new View.OnTouchListener() {

            final Handler handler = new Handler();
            int touchX;
            int touchY;

            // This runs when a tag is added over image
            Runnable mLongPressed = new Runnable() {
                public void run() {
                    // Button to send comment of comment box
                    Button sendCommentButton = (Button) findViewById(R.id.apply_comment_id);
                    ViewsController.setSendCommentButton(sendCommentButton);

                    // Keyboard
                    final InputMethodManager keyboard = (InputMethodManager) getSystemService(commentBox.getContext().INPUT_METHOD_SERVICE);
                    ViewsController.setKeyboard(keyboard);
                    keyboard.showSoftInput(commentBox, InputMethodManager.SHOW_IMPLICIT);

                    // Create a tag
                    final Tag tag = new Tag(baseImage.getContext(), centralPositionOfTag, touchX, touchY);

                    // Draw a tag
                    RelativeLayout baseImageLayout = (RelativeLayout) findViewById(R.id.tags_layout_id);
                    ViewsController.setBaseImageLayout(baseImageLayout);
                    TagDrawer.drawTag(tagsAdded, tag);

                    // Set pipe/focus into comment box
                    final EditText commentBox = ((EditText)findViewById(R.id.comment_box_id));
                    commentBox.setEnabled(true);
                    commentBox.requestFocus();

                    // Activate button to send comments when first tag is added
                    sendCommentButton.setEnabled(true);
                    sendCommentButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View button) {
                            Tag tagToUpdate = tagsAdded.get(ViewsController.getNumberOverTagAsInteger());
                            tagToUpdate.setComment(commentBox.getText().toString());
                            button.setEnabled(false);
                            tagAndNumberLayout.setVisibility(View.INVISIBLE);
                            commentBox.setText("");
                            commentBox.setEnabled(false);
                            keyboard.hideSoftInputFromWindow(commentBox.getWindowToken(), 0);
                        }
                    });
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
}
