package gbpassenger.ichinait.com.medicine.act.subject_00;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import gbpassenger.ichinait.com.medicine.R;
import gbpassenger.ichinait.com.medicine.adapter.GirdDropDownAdapter;
import gbpassenger.ichinait.com.medicine.adapter.SubjectAdapter;
import gbpassenger.ichinait.com.medicine.customView.EmptyLayout;
import gbpassenger.ichinait.com.medicine.netbean.Detail;
import gbpassenger.ichinait.com.medicine.netbean.ResponceSubjects;
import gbpassenger.ichinait.com.medicine.utils.SwipeRefreshHelper;
import gbpassenger.ichinait.com.medicine.utils.ToastUtil;
import me.jessyan.art.base.BaseFragment;
import me.jessyan.art.mvp.IPresenter;
import me.jessyan.art.mvp.IView;
import me.jessyan.art.mvp.Message;

/**
 * Created by DawnOct on 2018/1/30.
 */

public class SubjectFragment extends BaseFragment<SubjectPresenter> implements IView, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;
    LayoutInflater mInflater;
    GirdDropDownAdapter scaleAdapter, typeAdapter;
    private String headers[] = {"病类", "地区"};
    public String doctorTypes[] = {"不限", "中医", "西医"};
    public String types[] = {"不限", "东北", "华北", "西北", "华中", "华东", "华南", "西南"};//功能类型
    private List<View> popupViews = new ArrayList<>();
    public static int PAGE_SIZE = 3;
    public boolean hasTotal = false;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.task_list, container, false);
        mDropDownMenu = view.findViewById(R.id.dropDownMenu);
        initViews();
        return view;
    }

    @Override
    protected void initData() {
        mCurrentCounter = 0;
        mPresenter.requestSubjectList(Message.obtain(SubjectFragment.this), 1, doctorType);
    }

    @Nullable
    SwipeRefreshLayout mSwipeRefresh;
    @Nullable
    EmptyLayout mEmptyLayout;
    List<Detail.SubjectBean> mList;
    SubjectAdapter subjectAdapter;
    RecyclerView mRecyclerView;
    String doctorType;

    private void initViews() {
        mInflater = LayoutInflater.from(getActivity().getApplicationContext());
        //init city menu
        final ListView scaleView = new ListView(getContext());
        scaleAdapter = new GirdDropDownAdapter(getContext(), Arrays.asList(doctorTypes));
        scaleView.setDividerHeight(0);
        scaleView.setLayoutParams(new ViewGroup.LayoutParams(10, ViewGroup.LayoutParams.MATCH_PARENT));
        scaleView.setAdapter(scaleAdapter);

        //init age menu
        final ListView typeView = new ListView(getContext());
        typeAdapter = new GirdDropDownAdapter(getContext(), Arrays.asList(types));
        typeView.setDividerHeight(0);
        typeView.setLayoutParams(new ViewGroup.LayoutParams(10, ViewGroup.LayoutParams.MATCH_PARENT));
        typeView.setAdapter(typeAdapter);
        //init popupViews
        popupViews.clear();
        popupViews.add(scaleView);//添加View
        popupViews.add(typeView);
        initListener(scaleView, typeView);

         /*todo 自定义的内容列表*/
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.task_view_list, null);
        mSwipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipeLayout);
        SwipeRefreshHelper.init(mSwipeRefresh, this);//下拉刷新的控件与Cmy没有关系
        mEmptyLayout = (EmptyLayout) view.findViewById(R.id.loading_layout);

//        mEmptyLayout.hide();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mList = new ArrayList<>();
        subjectAdapter = new SubjectAdapter(mList);
        mRecyclerView.setAdapter(subjectAdapter);

        subjectAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        }, mRecyclerView);
//        subjectAdapter.disableLoadMoreIfNotFullPage();
        subjectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //跳转到详情页
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                startActivity(intent);
            }
        });
        //todo 组装mDropDownMenu
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, view);
    }

    @Override
    public void onRefresh() {
        mPresenter.requestSubjectList(Message.obtain(SubjectFragment.this), 1, doctorType);
    }

    int mCurrentCounter;

    private void loadMore() {
        if (mSwipeRefresh != null) {
            SwipeRefreshHelper.controlRefresh(mSwipeRefresh, false);//避免多次刷新后, 不能再刷新
            SwipeRefreshHelper.enableRefresh(mSwipeRefresh, false);//设置下拉刷新的失效
        }
        if (mCurrentCounter >= total_size) {
            //数据全部加载完毕
            SwipeRefreshHelper.enableRefresh(mSwipeRefresh, true);//设置下拉刷新的生效
            subjectAdapter.loadMoreEnd();

        } else {
            //成功获取更多数据
            int page = page_num + 1;
            mPresenter.requestSubjectList(Message.obtain(SubjectFragment.this), page, doctorType);
        }
    }


    private void initListener(ListView scaleView, ListView typeView) {
        //add item click event
        scaleView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                scaleAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[0] : doctorTypes[position]);
                mDropDownMenu.closeMenu();
                doctorType = position == 0 ? "" : position == 1 ? "01" : "02";
//                mScale = position;
//                presenter.getNetData(loginBean, 1, mScale, mType, mLv, mBridgeType, false);//默认是false
                mPresenter.requestSubjectList(Message.obtain(SubjectFragment.this), 1, doctorType);
            }
        });

        typeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                typeAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[1] : types[position]);
                mDropDownMenu.closeMenu();
//                mPresenter.requestSubjectList(Message.obtain(SubjectFragment.this), 1, doctorType);
//                if (position > 0) {
//                    mType = types[position];
//                } else {
//                    mType = "";
//                }
//                presenter.getNetData(loginBean, 1, mScale, mType, mLv, mBridgeType, false);//默认是false
            }
        });
    }

    public static final int SUBJECTS_LIST_SUCCESS = 0;
    public static final int SUBJECTS_LIST_ERROR = 1;
    private int page_num; //当前页
    private int page_size;//每页的数据条数
    private int total_page;//一共多少页
    private int total_size;//一共多少条数据

    @Override
    public void handleMessage(Message message) {
        switch (message.what) {
            case SUBJECTS_LIST_SUCCESS:

                ResponceSubjects responce = (ResponceSubjects) message.obj;
                total_size = responce.getTotal_size();
                total_page = responce.getTotal_page();
                page_num = responce.getPage_num();
                List<Detail.SubjectBean> list = responce.getSubject_list();
                if (page_num == 1) {
                    subjectAdapter.setNewData(list);
                    SwipeRefreshHelper.controlRefresh(mSwipeRefresh, false);//先设置下拉刷新的生效
                } else {
                    subjectAdapter.addData(list);
                    subjectAdapter.loadMoreComplete();
                    SwipeRefreshHelper.enableRefresh(mSwipeRefresh, true);//设置下拉刷新的生效
                }
                mCurrentCounter = subjectAdapter.getData().size();//获得当前总条数
                break;
            case SUBJECTS_LIST_ERROR:
                subjectAdapter.loadMoreFail();
                SwipeRefreshHelper.enableRefresh(mSwipeRefresh, true);//设置下拉刷新的生效
                SwipeRefreshHelper.controlRefresh(mSwipeRefresh, false);//先设置下拉刷新的生效
                Toast.makeText(getContext(), "网络异常", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected SubjectPresenter getPresenter() {
        return new SubjectPresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }


}
