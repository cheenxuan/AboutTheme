package com.xuan.abouttheme;


import android.animation.Animator;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xuan.abouttheme.theme.ColorUiUtil;
import com.xuan.abouttheme.theme.Theme;
import com.xuan.abouttheme.utils.PreUtils;
import com.xuan.abouttheme.view.ColorButton;
import com.xuan.abouttheme.view.ColorImageView;
import com.xuan.abouttheme.view.ColorRecycleView;
import com.xuan.abouttheme.view.ColorRelativeLayout;
import com.xuan.abouttheme.view.ColorTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ThemeActivity extends AppCompatActivity {


    @BindView(R.id.status_bar)
    ColorImageView statusBar;
    @BindView(R.id.icon)
    ImageView icon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tool_bar)
    ColorRelativeLayout toolBar;
    @BindView(R.id.tv_test)
    ColorTextView tvTest;
    @BindView(R.id.btn_1)
    ColorButton btn1;
    @BindView(R.id.btn_2)
    ColorButton btn2;
    @BindView(R.id.btn_3)
    ColorButton btn3;
    @BindView(R.id.listview)
    ColorRecycleView listview;
    @BindView(R.id.rootView)
    LinearLayout rootView;

    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onPreCreate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        setContentView(R.layout.activity_theme);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ButterKnife.bind(this);

        adapter = new MyAdapter();
        listview.setAdapter(adapter);

    }


    private void onPreCreate() {
        Theme theme = PreUtils.getCurrentTheme(this);
        switch (theme) {
            case Blue:
                setTheme(R.style.BlueTheme);
                break;
            case Red:
                setTheme(R.style.RedTheme);
                break;
            case Brown:
                setTheme(R.style.BrownTheme);
                break;
        }

    }


    @OnClick({R.id.btn_1, R.id.btn_2, R.id.btn_3})
    void changeTheme(View view) {

        int id = view.getId();

        if (id == R.id.btn_1) {

            setTheme(R.style.BlueTheme);
            PreUtils.setCurrentTheme(this, Theme.Blue);

        } else if (id == R.id.btn_2) {

            setTheme(R.style.RedTheme);
            PreUtils.setCurrentTheme(this, Theme.Red);

        } else if (id == R.id.btn_3) {

            setTheme(R.style.BrownTheme);
            PreUtils.setCurrentTheme(this, Theme.Brown);

        }

        final View rootView = getWindow().getDecorView();
        rootView.setDrawingCacheEnabled(true);
        rootView.buildDrawingCache(true);

        final Bitmap localBitmap = Bitmap.createBitmap(rootView.getDrawingCache());
        rootView.setDrawingCacheEnabled(false);
        if (null != localBitmap && rootView instanceof ViewGroup) {
            final View tmpView = new View(getApplicationContext());
            tmpView.setBackgroundDrawable(new BitmapDrawable(getResources(), localBitmap));
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            ((ViewGroup) rootView).addView(tmpView, params);
            tmpView.animate().alpha(0).setDuration(400).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    ColorUiUtil.changeTheme(rootView, getTheme());
                    System.gc();
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    ((ViewGroup) rootView).removeView(tmpView);
                    localBitmap.recycle();
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            }).start();
        }
    }


    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view;
            ViewHolder holder;

            if (convertView != null && convertView.getTag() instanceof ViewHolder) {
                holder = (ViewHolder) convertView.getTag();
            } else {
                view = LayoutInflater.from(ThemeActivity.this).inflate(R.layout.list_item, null);
                holder = new ViewHolder(view);
                view.setTag(holder);
                convertView = view;
            }

            holder.tv.setText("测试切换主题====" + position + "====");

            return convertView;
        }


    }

    static class ViewHolder {
        @BindView(R.id.tv)
        TextView tv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}


