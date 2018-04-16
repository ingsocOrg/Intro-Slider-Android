package com.example.pankaj.introslider;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {


    private ViewPager viewPager;
    private int[] layouts;
    private MyViewPagerAdapter myViewPagerAdapter;
    private TextView mIndicatorView[];
    private SharedPrefs sharedPrefs;
    private LinearLayout mIndicatorLayout;
    private Button gotIt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPrefs=new SharedPrefs(this);
        if(!sharedPrefs.checkFirstTime())
        {
            launchHomePage();

        }
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        viewPager = (ViewPager) findViewById(R.id.viewPager);
        mIndicatorLayout = (LinearLayout) findViewById(R.id.indicator_layout);
        gotIt = (Button) findViewById(R.id.button);
        layouts=new int[]{
                R.layout.slide1,
                R.layout.slide2,
                R.layout.slide3
        };
        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(this);

        setUpDots();
        gotIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            launchHomePage();
            }
        });
    }


    public void setUpDots()
    {
       int dotsCount=3;
       mIndicatorView=new TextView[3];
       for(int i=0;i<dotsCount;i++)
       {
           mIndicatorView[i]=new TextView(this);
           mIndicatorView[i].setWidth((int)getResources().getDimension(R.dimen.dp12));
           mIndicatorView[i].setHeight((int)getResources().getDimension(R.dimen.dp12));
           mIndicatorView[i].setGravity(Gravity.CENTER);
           LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
           params.setMargins(0, 0, (int)getResources().getDimension(R.dimen.dp12), 0);
           mIndicatorView[i].setLayoutParams(params);
           mIndicatorView[i].setBackgroundResource(R.drawable.rounded_cell_gray);
           mIndicatorLayout.addView(mIndicatorView[i]);
       }
        mIndicatorView[0].setBackgroundResource(R.drawable.rounded_cell_red);
        mIndicatorView[0].setGravity(Gravity.CENTER);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        if(position==2)
        {
            mIndicatorLayout.setVisibility(View.GONE);
            gotIt.setVisibility(View.VISIBLE);
        }
        else{
            mIndicatorLayout.setVisibility(View.VISIBLE);
            gotIt.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < mIndicatorView.length; i++) {
            mIndicatorView[i].setBackgroundResource(R.drawable.rounded_cell_gray);
        }
        mIndicatorView[position].setBackgroundResource(R.drawable.rounded_cell_red);


    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }




    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    private void launchHomePage()
    {
        sharedPrefs.saveFirstTime(false);
        Intent i=new Intent(MainActivity.this,HomePage.class);
        startActivity(i);
        finish();
    }

}
