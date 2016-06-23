package com.xuan.abouttheme.view;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.xuan.abouttheme.theme.ColorUiInterface;
import com.xuan.abouttheme.theme.ViewAttributeUtil;

/**
 * Created by XuanQj on 16-6-23.
 * Author by xuan
 */
public class ColorImageView extends ImageView implements ColorUiInterface{

    private int attr_drawable = -1;

    public ColorImageView(Context context) {
        super(context);
    }

    public ColorImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.attr_drawable = ViewAttributeUtil.getBackgroundAttibute(attrs);
    }

    public ColorImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.attr_drawable = ViewAttributeUtil.getBackgroundAttibute(attrs);
    }


    @Override
    public View getView() {
        return this;
    }

    @Override
    public void setTheme(Resources.Theme themeId) {

        if (attr_drawable != -1) {
            ViewAttributeUtil.applyBackgroundDrawable(this, themeId, attr_drawable);
        }
    }
}
