package com.xuan.abouttheme;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.xuan.abouttheme.theme.Theme;
import com.xuan.abouttheme.utils.PreUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 简单的单个activity动态切换主题
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_testTheme)
    TextView tvTestTheme;
    @BindView(R.id.btn_blue)
    Button btnBlue;
    @BindView(R.id.btn_red)
    Button btnRed;
    @BindView(R.id.btn_brown)
    Button btnBrown;
    @BindView(R.id.btn_trransStyle)
    Button btnTrransStyle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("ceshi", "OnCreat执行");
        onPreCreate();
        //        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        //            setTranslucentStatus(true);
        //        }
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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


    @OnClick({R.id.btn_blue, R.id.btn_red, R.id.btn_brown,R.id.btn_trransStyle})
    public void setTheme(View view) {


        if (view.getId() == R.id.btn_blue) {
            Toast.makeText(this, "蓝色", Toast.LENGTH_SHORT).show();
            setTheme(R.style.BlueTheme);
            PreUtils.setCurrentTheme(this, Theme.Blue);

        } else if (view.getId() == R.id.btn_red) {
            Toast.makeText(this, "红色", Toast.LENGTH_SHORT).show();
            setTheme(R.style.RedTheme);
            PreUtils.setCurrentTheme(this, Theme.Red);

        } else if (view.getId() == R.id.btn_brown) {
            Toast.makeText(this, "棕色", Toast.LENGTH_SHORT).show();
            setTheme(R.style.BrownTheme);
            PreUtils.setCurrentTheme(this, Theme.Brown);

        } else {
            finish();
            Intent intent = new Intent(this, ThemeActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in,
                    R.anim.fade_out);

            return;
        }
        finish();

        Intent mainIntent = new Intent(this, this.getClass());
        this.startActivity(mainIntent);
        overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);


    }
}
