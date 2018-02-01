package gbpassenger.ichinait.com.medicine.act.subject_00;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import gbpassenger.ichinait.com.medicine.MainActivity;
import gbpassenger.ichinait.com.medicine.MyListFragment;
import gbpassenger.ichinait.com.medicine.R;
import gbpassenger.ichinait.com.medicine.act.add_02.CenterFragment;
import gbpassenger.ichinait.com.medicine.netbean.Detail;
import me.jessyan.art.base.BaseActivity;
import me.jessyan.art.mvp.IPresenter;
import me.jessyan.art.mvp.IView;
import me.jessyan.art.mvp.Message;

/**
 * Created by DawnOct on 2018/2/1.
 */

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.toolbar_tab)
    TabLayout mTabLayout;
    private ArrayList<String> titles = new ArrayList<>();
    private int[] tabIcons = {
            R.mipmap.icon_down,
            R.mipmap.icon_down,
    };
    DetailPagerAdapter mPagerAdapter;
    DetailContentFragment mFragment1;
    DetailTaskFragment mFragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_base);
        initView(savedInstanceState);
    }

    private void initView(Bundle savedInstanceState) {

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setOffscreenPageLimit(1);
        mTabLayout = (TabLayout) findViewById(R.id.toolbar_tab);

        if (savedInstanceState == null) {
            mFragment1 = new DetailContentFragment();
            mFragment2 = new DetailTaskFragment();
        }
        titles.add("话题详情");
        titles.add("任务进度");

        mPagerAdapter = new DetailPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setCustomView(getTabView(0)); //获得自定义的view
        mTabLayout.getTabAt(1).setCustomView(getTabView(1));
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
                startPropertyAnim(imgTitle);
                mViewPager.setCurrentItem(position);
            }
        });
        return view;
    }

    private void startPropertyAnim(ImageView v) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(v, "rotation", 180f, 360f);
        anim.setDuration(500);
        anim.start();
    }

    private class DetailPagerAdapter extends FragmentPagerAdapter {

        public DetailPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return mFragment1;
            } else if (position == 1) {
                return mFragment2;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
