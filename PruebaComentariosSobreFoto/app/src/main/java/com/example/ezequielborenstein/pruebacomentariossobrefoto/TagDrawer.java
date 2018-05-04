package com.example.ezequielborenstein.pruebacomentariossobrefoto;

import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Map;

public class TagDrawer{

    public static Integer drawTag(ImageView baseImage, int[] viewCoords, RelativeLayout layoutOfImage,
                          int touchX, int touchY, int centralPositionOfTag, int numberOfTag, Map<Integer, Tag> tagsAdded){
        baseImage.getLocationOnScreen(viewCoords);

        // Tags
        Tag tag = new Tag(baseImage.getContext(),
                centralPositionOfTag,
                touchX - centralPositionOfTag,
                touchY - centralPositionOfTag,
                numberOfTag);

        int size = 2 * centralPositionOfTag;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(size, size);
        params.leftMargin = tag.getLeftMargin();
        params.topMargin = tag.getTopMargin();

        layoutOfImage.addView(tag, params);

        tagsAdded.put(tag.getNumberOfTag(), tag);
        return tag.getNumberOfTag() + 1;
    }
}
