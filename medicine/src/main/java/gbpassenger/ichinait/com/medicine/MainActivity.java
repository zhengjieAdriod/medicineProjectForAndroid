package gbpassenger.ichinait.com.medicine;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import gbpassenger.ichinait.com.medicine.act.add_02.EditorActivity;
import gbpassenger.ichinait.com.medicine.act.subject_00.SubjectFragment;

/**
 * Created by star on 2017/7/4.
 */

public class MainActivity extends AppCompatActivity {


    ViewPager mViewPager;

    SubjectFragment mFragment1;

    MyListFragment mFragment2;
    //    CenterFragment mFragment3;
    MyListFragment mFragment3;

    MyListFragment mFragment4;
    MyListFragment mFragment5;
    PagerAdapter mPagerAdapter;

    private TabLayout mTabLayout;

    private ArrayList<String> titles = new ArrayList<>();

    private boolean isSelected = false;

    private int[] tabIcons = {
            R.mipmap.icon_down,
            R.mipmap.icon_down,
            R.mipmap.icon_down,
            R.mipmap.icon_down,
            R.mipmap.icon_down,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_tabitem_layout);
        initView(savedInstanceState);
    }

    private void initView(Bundle savedInstanceState) {

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setOffscreenPageLimit(2);
        mTabLayout = (TabLayout) findViewById(R.id.toolbar_tab);

        if (savedInstanceState == null) {
            mFragment1 = new SubjectFragment();
//            mFragment1.initData('a', 'z');
            mFragment2 = new MyListFragment();
            mFragment2.initData('A', 'Z');
            mFragment3 = new MyListFragment();
//            mFragment3 = new CenterFragment();
            mFragment3.initData('a', 'z');
            mFragment4 = new MyListFragment();
            mFragment4.initData('A', 'Z');
            mFragment5 = new MyListFragment();
            mFragment5.initData('A', 'Z');
        }
        titles.add("任务");
        titles.add("经验");
        titles.add("添加");
        titles.add("常识");
        titles.add("我的");
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setCustomView(getTabView(0)); //获得自定义的view
        mTabLayout.getTabAt(1).setCustomView(getTabView(1));
        View inflate = LayoutInflater.from(this).inflate(R.layout.center_layout, null);
        inflate.setTag("center");
        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo 跳转到编辑的activity, 而不是中间的Fragment
                startActivity(new Intent(MainActivity.this, EditorActivity.class));
            }
        });
        mTabLayout.getTabAt(2).setCustomView(inflate); //获得自定义的view
        mTabLayout.getTabAt(3).setCustomView(getTabView(3));
        mTabLayout.getTabAt(4).setCustomView(getTabView(4));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                changeTabStatus(tab, true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                changeTabStatus(tab, false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private void changeTabStatus(TabLayout.Tab tab, boolean selected) {
        if (tab.getCustomView().getTag().equals("center")) return;
        View view = tab.getCustomView();
        final ImageView imgTitle = (ImageView) view.findViewById(R.id.img_title);
        TextView txtTitle = (TextView) view.findViewById(R.id.txt_title);
        imgTitle.setVisibility(View.VISIBLE);
        if (selected) {
            txtTitle.setTextColor(Color.parseColor("#0EA73C"));
            startPropertyAnim(imgTitle);
        } else {
            txtTitle.setTextColor(Color.parseColor("#7f7f7f"));
            imgTitle.setVisibility(View.INVISIBLE);
        }
    }

    public View getTabView(final int position) {
        final View view = LayoutInflater.from(this).inflate(R.layout.item_tab, null);
        view.setTag(position);
        TextView txtTitle = (TextView) view.findViewById(R.id.txt_title);
        final ImageView imgTitle = (ImageView) view.findViewById(R.id.img_title);
        imgTitle.setImageResource(tabIcons[position]);
        txtTitle.setText(titles.get(position));
        if (position == 0) {
            txtTitle.setTextColor(Color.parseColor("#057523"));
        } else {
            imgTitle.setVisibility(View.INVISIBLE);
            txtTitle.setTextColor(Color.parseColor("#ced0d3"));
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position != 2) {
                    startPropertyAnim(imgTitle);
                    mViewPager.setCurrentItem(position);
                }

            }
        });
        return view;
    }

    private void startPropertyAnim(ImageView v) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(v, "rotation", 180f, 360f);
        anim.setDuration(500);
        anim.start();
    }


    public class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return mFragment1;
            } else if (position == 1) {
                return mFragment2;
            } else if (position == 2) {
                return mFragment3;
            } else if (position == 3) {
                return mFragment4;
            } else if (position == 4) {
                return mFragment5;
            }

            return null;
        }

        @Override
        public int getCount() {
            return 5;
        }

    }
}
