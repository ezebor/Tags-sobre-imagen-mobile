package com.example.ezequielborenstein.pruebacomentariossobrefoto;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class Tag extends View{
    private int centralPositionOfTag;
    private int leftMargin;
    private int topMargin;
    private int numberOfTag;
    private String comment;

    public static Toast CURRENT_TOAST;

    @SuppressLint("ClickableViewAccessibility")
    public Tag(final Context context){
        super(context);
        this.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View tag, MotionEvent event) {
                hideCurrentToast();

                CURRENT_TOAST = Toast.makeText(context, comment, Toast.LENGTH_LONG);
                CURRENT_TOAST.setGravity(Gravity.TOP|Gravity.LEFT, tag.getLeft(), tag.getTop() + 2 * tag.getHeight());

                View view = CURRENT_TOAST.getView();
                view.setBackgroundResource(android.R.drawable.dialog_holo_dark_frame);
                CURRENT_TOAST.setView(view);

                CURRENT_TOAST.show();
                return true;
            }
        });
    }

    public static void hideCurrentToast(){
        if(CURRENT_TOAST != null){
            CURRENT_TOAST.cancel();
        }
    }

    public Tag(final Context context, int centralPositionOfTag, int touchX, int touchY, int numberOfTag){
        this(context);
        this.centralPositionOfTag = centralPositionOfTag;
        this.leftMargin = touchX - centralPositionOfTag;
        this.topMargin = touchY - centralPositionOfTag;
        this.numberOfTag = numberOfTag;
    }

    // Este método es el que dibuja dentro del canvas
    protected void onDraw(Canvas canvas){
        Paint brush = new Paint();

        // Black circle: used to draw the black contour of tag
        brush.setColor(Color.BLACK);
        brush.setStrokeWidth(8);
        brush.setStyle(Paint.Style.FILL);
        canvas.drawCircle(this.centralPositionOfTag, this.centralPositionOfTag, 35, brush);

        // Red circle
        brush.setColor(Color.RED);
        brush.setStrokeWidth(8);
        brush.setStyle(Paint.Style.FILL);
        canvas.drawCircle(this.centralPositionOfTag, this.centralPositionOfTag, 33, brush);

        // Number inside the tag
        brush.setColor(Color.WHITE);
        brush.setStrokeWidth(10);
        brush.setTextSize(50);
        brush.setTypeface(Typeface.SANS_SERIF);

        // Adjust center coordinates to numbers with two digits
        if(this.numberOfTag <= 9){
            canvas.drawText(""+this.numberOfTag, this.centralPositionOfTag -13, this.centralPositionOfTag +20, brush);
        }else{
            canvas.drawText(""+this.numberOfTag, this.centralPositionOfTag -30, this.centralPositionOfTag +20, brush);
        }
    }

    public int getCentralPositionOfTag() {
        return centralPositionOfTag;
    }

    public int getLeftMargin() {
        return leftMargin;
    }

    public int getTopMargin() {
        return topMargin;
    }

    public int getNumberOfTag() {
        return numberOfTag;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}