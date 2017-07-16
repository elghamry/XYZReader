package com.example.xyzreader.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Loader;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.xyzreader.R;
import com.example.xyzreader.data.Article;
import com.example.xyzreader.data.ArticleLoader;
import com.example.xyzreader.data.ItemsContract;

import java.util.ArrayList;

/**
 * An activity representing a single Article detail screen, letting you swipe between articles.
 */
public class ArticleDetailActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {
    private ProgressDialog progress;
    private Cursor mCursor;
    private long mStartId;
    ArrayList<Article> articles = new ArrayList<>();
    private ViewPager mPager;
    private MyPagerAdapter mPagerAdapter;
    private ProgressBar progressBar;
    //this activity needs better preformance because it loads a viewpager of fragments so i made
    //the following edits : adding the items from cursor once and parse it to PagerAdapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
        setContentView(R.layout.activity_article_detail);
        progressBar= (ProgressBar)findViewById(R.id.progress);
        showProgress();

        getLoaderManager().initLoader(0, null, this);
        mPagerAdapter = new MyPagerAdapter(getFragmentManager());
        mPager = (ViewPager) findViewById(R.id.pager);
        if (savedInstanceState == null) {
            if (getIntent() != null && getIntent().getData() != null) {
                mStartId = ItemsContract.Items.getItemId(getIntent().getData());
            }
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return ArticleLoader.newAllArticlesInstance(this);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        mCursor = cursor;
        mPagerAdapter.notifyDataSetChanged();
        mCursor.moveToFirst();
        //here is the edit
        int position=0;
        while (!mCursor.isAfterLast()) {
          Article item= new Article();
            ArticleLoader.newInstanceForItemId(this, mCursor.getPosition());
            item.set_ID(mCursor.getString(ArticleLoader.Query._ID));
            Log.d("TAG", "onLoadFinished: "+item.get_ID());
            item.setAUTHOR(mCursor.getString(ArticleLoader.Query.AUTHOR));
            item.setBODY(mCursor.getString(ArticleLoader.Query.BODY));
            item.setPHOTO_URL(mCursor.getString(ArticleLoader.Query.PHOTO_URL));
            item.setPUBLISHED_DATE(mCursor.getString(ArticleLoader.Query.PUBLISHED_DATE));
            item.setTHUMB_URL(mCursor.getString(ArticleLoader.Query.THUMB_URL));
            item.setTITLE(mCursor.getString(ArticleLoader.Query.TITLE));
            articles.add(item);
            if(Long.parseLong(item.get_ID())==(mStartId))
                position=mCursor.getPosition();
            mCursor.moveToNext();

        }
        mPagerAdapter.notifyDataSetChanged();
        mPager.setAdapter(mPagerAdapter);
        hideProgress();
        mPager.setCurrentItem(position, false);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        mCursor = null;
        mPagerAdapter.notifyDataSetChanged();
    }


    private class MyPagerAdapter extends FragmentStatePagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            super.setPrimaryItem(container, position, object);

        }

        @Override
        public Fragment getItem(int position) {
            return ArticleDetailFragment.newInstance(articles.get(position));
        }

        @Override
        public int getCount() {
            return (mCursor != null) ? mCursor.getCount() : 0;
        }
    }
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}
