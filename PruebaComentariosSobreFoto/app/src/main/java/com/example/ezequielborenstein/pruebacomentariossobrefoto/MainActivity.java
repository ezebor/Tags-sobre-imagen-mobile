package com.example.ezequielborenstein.pruebacomentariossobrefoto;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity{

    public int centralPositionOfCommentTag = 35;
    public int contador = 1;

    /** Called when the activity is first created. */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView base_image = (ImageView)findViewById(R.id.base_image_id);
        base_image.setOnTouchListener(new View.OnTouchListener() {

            final Handler handler = new Handler();
            int touchX;
            int touchY;

            Runnable mLongPressed = new Runnable() {
                public void run() {
                    int[] viewCoords = new int[2];
                    base_image.getLocationOnScreen(viewCoords);

                    RelativeLayout layoutOfImage = (RelativeLayout) findViewById(R.id.principal_id);

                    // Tags
                    CommentTagView commentTag = new CommentTagView(base_image.getContext());
                    commentTag.setCoordinatesOfCenter(centralPositionOfCommentTag, centralPositionOfCommentTag);
                    commentTag.setNumberOfTag(contador);

                    int size = 2 * centralPositionOfCommentTag;
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(size, size);
                    params.leftMargin = touchX - centralPositionOfCommentTag;
                    params.topMargin = touchY - centralPositionOfCommentTag;

                    layoutOfImage.addView(commentTag, params);
                    contador = contador + 1;
                }
            };


            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        touchX = (int) event.getX();
                        touchY = (int) event.getY();
                        handler.postDelayed(mLongPressed, 500);
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

    public class CommentTagView extends View{
        public int centerX;
        public int centerY;
        public int numberOfTag;


        // Necesito este constructor si heredo de View
        public CommentTagView(Context context){
            super(context);
        }

        public void setCoordinatesOfCenter(int centerX, int centerY){
            this.centerX = centerX;
            this.centerY = centerY;
        }

        public void setNumberOfTag(int numberOfTag){
            this.numberOfTag = numberOfTag;
        }

        // Este método es el que dibuja dentro del canvas
        protected void onDraw(Canvas canvas){
            Paint brush = new Paint();

            // Black circle: used to draw the black contour of tag
            brush.setColor(Color.BLACK);
            brush.setStrokeWidth(8);
            brush.setStyle(Paint.Style.FILL);
            canvas.drawCircle(this.centerX, this.centerY, 35, brush);

            // Red circle
            brush.setColor(Color.RED);
            brush.setStrokeWidth(8);
            brush.setStyle(Paint.Style.FILL);
            canvas.drawCircle(this.centerX, this.centerY, 33, brush);

            // Number inside the tag
            brush.setColor(Color.WHITE);
            brush.setStrokeWidth(10);
            brush.setTextSize(50);
            brush.setTypeface(Typeface.SANS_SERIF);

            // Adjust center coordinates to numbers with two digits
            if(this.numberOfTag <= 9){
                canvas.drawText(""+this.numberOfTag, this.centerX -13, this.centerY +20, brush);
            }else{
                canvas.drawText(""+this.numberOfTag, this.centerX -30, this.centerY +20, brush);
            }
        }
    }
}
