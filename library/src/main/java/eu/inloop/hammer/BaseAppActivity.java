package eu.inloop.hammer;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class BaseAppActivity extends AppCompatActivity {

    private boolean defaultStartAnim, defaultBackAnim;

    private int animStartIn, animStartOut;
    private int animBackIn, animBackOut;

    public void setDefaultStartAnim(boolean defaultStartAnim) {
        this.defaultStartAnim = defaultStartAnim;
    }

    public void setDefaultBackAnim(boolean defaultBackAnim) {
        this.defaultBackAnim = defaultBackAnim;
    }

    protected void setStartAnims(int in, int out) {
        this.animStartIn = in;
        this.animStartOut = out;
    }

    protected void setBackAnims(int in, int out) {
        this.animBackIn = in;
        this.animBackOut = out;
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
        startAnim();
    }

    protected void startAnim() {
        if (defaultStartAnim) {
            return;
        }

        overridePendingTransition(animStartIn, animStartOut);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        backAnim();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            backAnim();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        super.finish();
        backAnim();
    }

    protected void backAnim() {
        if (defaultBackAnim) {
            return;
        }

        overridePendingTransition(animBackIn, animBackOut);
    }

    public boolean isLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    public boolean isPortrait() {
        return !isLandscape();
    }
}
