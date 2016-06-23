package com.xuan.abouttheme.view;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.xuan.abouttheme.theme.ColorUiInterface;
import com.xuan.abouttheme.theme.ViewAttributeUtil;

/**
 * Created by XuanQj on 16-6-23.
 * Author by xuan
 */
public class ColorButton extends Button implements ColorUiInterface{


    private int attr_drawable = -1;
    private int attr_textColor = -1;

    public ColorButton(Context context) {
        super(context);
    }

    public ColorButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.attr_drawable = ViewAttributeUtil.getBackgroundAttibute(attrs);
        this.attr_textColor = ViewAttributeUtil.getTextColorAttribute(attrs);
    }

    public ColorButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.attr_drawable = ViewAttributeUtil.getBackgroundAttibute(attrs);
        this.attr_textColor = ViewAttributeUtil.getTextColorAttribute(attrs);
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

        if (attr_textColor != -1) {
            ViewAttributeUtil.applyTextColor(this, themeId, attr_textColor);
        }
    }
}
