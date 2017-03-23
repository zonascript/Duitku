package com.mi1.duitku.Tab5;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mi1.duitku.Common.AppGlobal;
import com.mi1.duitku.R;
import com.pkmmte.view.CircularImageView;

public class ShareCodeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_code);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;
        Bitmap blurTemplate = BitmapFactory.decodeResource(getResources(), R.drawable.house, options);
        ImageView ivBlurPhoto = (ImageView)findViewById(R.id.img_full);
        ivBlurPhoto.setImageBitmap(blurTemplate);

        CircularImageView civUserPhoto = (CircularImageView)findViewById(R.id.civ_user_photo);
        civUserPhoto.setImageResource(R.drawable.house);

        TextView tvName = (TextView)findViewById(R.id.txt_name);
        tvName.setText(AppGlobal._userInfo.name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        ActionBar actionBar = getSupportActionBar();

        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.actionbar_bg));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            ShareCodeActivity.this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}