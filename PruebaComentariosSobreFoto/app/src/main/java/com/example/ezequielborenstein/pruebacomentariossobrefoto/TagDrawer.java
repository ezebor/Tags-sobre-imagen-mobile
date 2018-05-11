package com.example.ezequielborenstein.pruebacomentariossobrefoto;

import android.widget.RelativeLayout;

import java.util.Map;

public class TagDrawer{

    public static Tag drawTag(Map<Integer, Tag> tagsAdded, Tag tag){
        // Put tag into the layout of image
        int size = 2 * tag.getCentralPositionOfTag();
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(size, size);
        params.leftMargin = tag.getLeftMargin();
        params.topMargin = tag.getTopMargin();
        ViewsController.getBaseImageLayout().addView(tag, params);

        int numberOfTag = tagsAdded.size() + 1;
        tagsAdded.put(numberOfTag, tag);
        tag.setNumberOfTag(numberOfTag);
        tag.setNextToComment();

        return tag;
    }
}
