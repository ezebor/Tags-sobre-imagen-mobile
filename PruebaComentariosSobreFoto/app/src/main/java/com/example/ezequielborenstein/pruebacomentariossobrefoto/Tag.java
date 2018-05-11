package com.example.ezequielborenstein.pruebacomentariossobrefoto;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class Tag extends View{
    private int centralPositionOfTag;
    private int leftMargin;
    private int topMargin;
    private int numberOfTag;
    private String comment;

    @SuppressLint("ClickableViewAccessibility")
    public Tag(final Context context){
        super(context);
        this.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View tag, MotionEvent event) {
                setNextToComment();
                return true;
            }
        });
    }

    public void setNextToComment(){
        // Tag next to comment box
        ViewsController.getNumberOverTag().setText("" + this.numberOfTag);
        ViewsController.getTagAndNumberLayout().setVisibility(VISIBLE);

        // Comment box
        ViewsController.getCommentBox().setEnabled(true);
        ViewsController.getCommentBox().setText(this.comment);
        ViewsController.getCommentBox().setSelection(ViewsController.getCommentBox().getText().length());

        // Button to send comment
        ViewsController.getSendCommentButton().setEnabled(true);
    }

    public Tag(final Context context, int centralPositionOfTag, int touchX, int touchY){
        this(context);
        this.centralPositionOfTag = centralPositionOfTag;
        this.leftMargin = touchX - centralPositionOfTag;
        this.topMargin = touchY - centralPositionOfTag;
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

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setNumberOfTag(Integer numberOfTag) {
        this.numberOfTag = numberOfTag;
    }
}
