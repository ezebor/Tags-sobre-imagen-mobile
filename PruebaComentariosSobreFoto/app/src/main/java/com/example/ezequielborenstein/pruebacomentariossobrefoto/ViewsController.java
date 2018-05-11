package com.example.ezequielborenstein.pruebacomentariossobrefoto;

import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ViewsController{
    private static EditText commentBox;
    private static RelativeLayout tagAndNumberLayout;
    private static TextView numberOverTag;
    private static ImageView baseImage;
    private static Button sendCommentButton;
    private static InputMethodManager keyboard;
    private static RelativeLayout baseImageLayout;


    public static RelativeLayout getBaseImageLayout() {
        return baseImageLayout;
    }

    public static void setBaseImageLayout(RelativeLayout baseImageLayout) {
        ViewsController.baseImageLayout = baseImageLayout;
    }

    public static EditText getCommentBox() {
        return commentBox;
    }

    public static void setCommentBox(EditText commentBox) {
        ViewsController.commentBox = commentBox;
    }

    public static RelativeLayout getTagAndNumberLayout() {
        return tagAndNumberLayout;
    }

    public static void setTagAndNumberLayout(RelativeLayout tagAndNumberLayout) {
        ViewsController.tagAndNumberLayout = tagAndNumberLayout;
    }

    public static TextView getNumberOverTag() {
        return numberOverTag;
    }

    public static Integer getNumberOverTagAsInteger() {
        return Integer.parseInt(numberOverTag.getText().toString());
    }

    public static void setNumberOverTag(TextView numberOverTag) {
        ViewsController.numberOverTag = numberOverTag;
    }

    public static ImageView getBaseImage() {
        return baseImage;
    }

    public static void setBaseImage(ImageView baseImage) {
        ViewsController.baseImage = baseImage;
    }

    public static Button getSendCommentButton() {
        return sendCommentButton;
    }

    public static void setSendCommentButton(Button sendCommentButton) {
        ViewsController.sendCommentButton = sendCommentButton;
    }

    public static InputMethodManager getKeyboard() {
        return keyboard;
    }

    public static void setKeyboard(InputMethodManager keyboard) {
        ViewsController.keyboard = keyboard;
    }
}
