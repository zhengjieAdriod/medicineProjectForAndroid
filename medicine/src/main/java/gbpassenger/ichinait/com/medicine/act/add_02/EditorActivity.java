package gbpassenger.ichinait.com.medicine.act.add_02;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.niuduz.richeditor_ding.EditActivity;
import com.niuduz.richeditor_ding.richeditor.RichEditor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import gbpassenger.ichinait.com.medicine.R;
import gbpassenger.ichinait.com.medicine.netbean.Detail;
import me.jessyan.art.base.BaseActivity;
import me.jessyan.art.mvp.IView;
import me.jessyan.art.mvp.Message;

/**
 * Created by DawnOct on 2018/2/5.
 */

public class EditorActivity extends BaseActivity<AddPresenter> implements IView, View.OnClickListener {
    private InputMethodManager imm;//软键盘管理器
    private RelativeLayout rl_layout_editor;
    private ImageButton action_undo, action_redo, action_font, action_add;
    private RichEditor mEditor;
    private LinearLayout ll_layout_add, ll_layout_font;//添加布局，字体布局

    private ImageButton ib_Bold, ib_Italic, ib_StrikeThough, ib_BlockQuote, ib_H1, ib_H2, ib_H3, ib_H4;
    private boolean flag1, flag2, flag3, flag4, flag5, flag6, flag7, flag8;
    public final static int RICH_IMAGE_CODE = 0x33;

    TextView save;

    @Override
    protected int initView() {
        return R.layout.activity_main_editor;
    }

    @Override
    protected void initData() {
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        initViews();
        initEvents();
        Detail.SubjectBean subject = (Detail.SubjectBean) getIntent().getSerializableExtra("subject");
        if (subject != null && !TextUtils.isEmpty(subject.getContent())) {
            mEditor.setHtml(subject.getContent());
        }
    }

    private void initViews() {
        save = (TextView) findViewById(R.id.save);
        save.setOnClickListener(this);
        //富文本编辑初始化
        mEditor = (RichEditor) findViewById(R.id.editor);
        mEditor.setEditorFontSize(15);
        mEditor.setPadding(10, 10, 10, 50);
        mEditor.setPlaceholder("*项目详情：不得少于100字，说明项目的情况\\n如：项目介绍，筹款如何使用，自我介绍等");

        rl_layout_editor = (RelativeLayout) findViewById(R.id.rl_layout_editor);
        ll_layout_add = (LinearLayout) findViewById(R.id.ll_layout_add);
        ll_layout_font = (LinearLayout) findViewById(R.id.ll_layout_font);

        action_undo = (ImageButton) findViewById(R.id.action_undo);
        action_redo = (ImageButton) findViewById(R.id.action_redo);
        action_font = (ImageButton) findViewById(R.id.action_font);
        action_add = (ImageButton) findViewById(R.id.action_add);

        //字体布局
        ib_Bold = (ImageButton) findViewById(R.id.action_bold);
        ib_Italic = (ImageButton) findViewById(R.id.action_italic);
        ib_StrikeThough = (ImageButton) findViewById(R.id.action_strikethrough);
        ib_BlockQuote = (ImageButton) findViewById(R.id.action_blockquote);
        ib_H1 = (ImageButton) findViewById(R.id.action_heading1);
        ib_H2 = (ImageButton) findViewById(R.id.action_heading2);
        ib_H3 = (ImageButton) findViewById(R.id.action_heading3);
        ib_H4 = (ImageButton) findViewById(R.id.action_heading4);


    }

    private void initEvents() {

        action_add.setOnClickListener(this);
        action_font.setOnClickListener(this);
        action_redo.setOnClickListener(this);
        action_undo.setOnClickListener(this);

        ib_Bold.setOnClickListener(this);
        ib_Italic.setOnClickListener(this);
        ib_StrikeThough.setOnClickListener(this);
        ib_BlockQuote.setOnClickListener(this);
        ib_H1.setOnClickListener(this);
        ib_H2.setOnClickListener(this);
        ib_H3.setOnClickListener(this);
        ib_H4.setOnClickListener(this);


        mEditor.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
                    rl_layout_editor.setVisibility(View.VISIBLE);
//                    clickableType = 1;
                } else {
                    imm.hideSoftInputFromWindow(mEditor.getWindowToken(), 0); //强制隐藏键盘
                    rl_layout_editor.setVisibility(View.INVISIBLE);
                }
            }
        });


        /**
         *获取点击出文本的标签类型
         */
        mEditor.setOnDecorationChangeListener(new RichEditor.OnDecorationStateListener() {
            @Override
            public void onStateChangeListener(String text, List<RichEditor.Type> types) {

                if (types.contains(RichEditor.Type.BOLD)) {
                    ib_Bold.setImageResource(R.mipmap.bold_l);
                    flag1 = true;
                    isBold = true;
                } else {
                    ib_Bold.setImageResource(R.mipmap.bold_d);
                    flag1 = false;
                    isBold = false;
                }

                if (types.contains(RichEditor.Type.ITALIC)) {
                    ib_Italic.setImageResource(R.mipmap.italic_l);
                    flag2 = true;
                    isItalic = true;
                } else {
                    ib_Italic.setImageResource(R.mipmap.italic_d);
                    flag2 = false;
                    isItalic = false;
                }

                if (types.contains(RichEditor.Type.STRIKETHROUGH)) {
                    ib_StrikeThough.setImageResource(R.mipmap.strikethrough_l);
                    flag3 = true;
                    isStrikeThrough = true;
                } else {
                    ib_StrikeThough.setImageResource(R.mipmap.strikethrough_d);
                    flag3 = false;
                    isStrikeThrough = false;
                }

                //块引用
                if (types.contains(RichEditor.Type.BLOCKQUOTE)) {
                    flag4 = true;
                    flag5 = false;
                    flag6 = false;
                    flag7 = false;
                    flag8 = false;
                    isclick = true;
                    ib_BlockQuote.setImageResource(R.mipmap.blockquote_l);
                    ib_H1.setImageResource(R.mipmap.h1_d);
                    ib_H2.setImageResource(R.mipmap.h2_d);
                    ib_H3.setImageResource(R.mipmap.h3_d);
                    ib_H4.setImageResource(R.mipmap.h4_d);
                } else {
                    ib_BlockQuote.setImageResource(R.mipmap.blockquote_d);
                    flag4 = false;
                    isclick = false;
                }


                if (types.contains(RichEditor.Type.H1)) {
                    flag4 = false;
                    flag5 = true;
                    flag6 = false;
                    flag7 = false;
                    flag8 = false;

                    isclick = true;
                    ib_BlockQuote.setImageResource(R.mipmap.blockquote_d);
                    ib_H1.setImageResource(R.mipmap.h1_l);
                    ib_H2.setImageResource(R.mipmap.h2_d);
                    ib_H3.setImageResource(R.mipmap.h3_d);
                    ib_H4.setImageResource(R.mipmap.h4_d);
                } else {
                    ib_H1.setImageResource(R.mipmap.h1_d);
                    flag5 = false;
                    isclick = false;
                }

                if (types.contains(RichEditor.Type.H2)) {
                    flag4 = false;
                    flag5 = false;
                    flag6 = true;
                    flag7 = false;
                    flag8 = false;

                    isclick = true;
                    ib_BlockQuote.setImageResource(R.mipmap.blockquote_d);
                    ib_H1.setImageResource(R.mipmap.h1_d);
                    ib_H2.setImageResource(R.mipmap.h2_l);
                    ib_H3.setImageResource(R.mipmap.h3_d);
                    ib_H4.setImageResource(R.mipmap.h4_d);
                } else {
                    ib_H2.setImageResource(R.mipmap.h2_d);
                    flag6 = false;
                    isclick = false;
                }

                if (types.contains(RichEditor.Type.H3)) {
                    flag4 = false;
                    flag5 = false;
                    flag6 = false;
                    flag7 = true;
                    flag8 = false;
                    isclick = true;
                    ib_BlockQuote.setImageResource(R.mipmap.blockquote_d);
                    ib_H1.setImageResource(R.mipmap.h1_d);
                    ib_H2.setImageResource(R.mipmap.h2_d);
                    ib_H3.setImageResource(R.mipmap.h3_l);
                    ib_H4.setImageResource(R.mipmap.h4_d);
                } else {
                    ib_H4.setImageResource(R.mipmap.h3_d);
                    flag7 = false;
                    isclick = false;
                }

                if (types.contains(RichEditor.Type.H4)) {
                    flag4 = false;
                    flag5 = false;
                    flag6 = false;
                    flag7 = false;
                    flag8 = true;
                    isclick = true;
                    ib_BlockQuote.setImageResource(R.mipmap.blockquote_d);
                    ib_H1.setImageResource(R.mipmap.h1_d);
                    ib_H2.setImageResource(R.mipmap.h2_d);
                    ib_H3.setImageResource(R.mipmap.h3_d);
                    ib_H4.setImageResource(R.mipmap.h4_l);
                } else {
                    ib_H4.setImageResource(R.mipmap.h4_d);
                    flag8 = false;
                    isclick = false;
                }
            }
        });


        //布局全局改变监听
        rl_layout_editor.getViewTreeObserver().addOnGlobalLayoutListener(onGroupCollapseListener);


        /**
         * 插入图片
         */
        findViewById(R.id.action_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                new PhotoAdapter(EditActivity.this, selectedRichImage, 1);
//                PhotoPickerIntent intent = new PhotoPickerIntent(EditActivity.this);
//                intent.setPhotoCount(1);//可以添加1张图片
//                startActivityForResult(intent, RICH_IMAGE_CODE);


                Intent picture = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(picture, RICH_IMAGE_CODE);

            }
        });

        /**
         * 插入链接
         */
        findViewById(R.id.action_link).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInsertLinkDialog();
            }
        });
        /**
         * 插入分割线
         */
        findViewById(R.id.action_split).

                setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mEditor.insertHr();
                    }
                });
    }

    /**
     * 插入链接Dialog
     */


    private void showInsertLinkDialog() {

        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        linkDialog = adb.create();

        View view = getLayoutInflater().inflate(R.layout.dialog_insertlink, null);

        final EditText et_link_address = (EditText) view.findViewById(R.id.et_link_address);
        final EditText et_link_title = (EditText) view.findViewById(R.id.et_link_title);

        Editable etext = et_link_address.getText();
        Selection.setSelection(etext, etext.length());

        //点击确实的监听
        view.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String linkAddress = et_link_address.getText().toString();
                String linkTitle = et_link_title.getText().toString();

                if (linkAddress.endsWith("http://") || TextUtils.isEmpty(linkAddress)) {
                    Toast.makeText(EditorActivity.this, "请输入超链接地址", Toast.LENGTH_SHORT);
                } else if (TextUtils.isEmpty(linkTitle)) {
                    Toast.makeText(EditorActivity.this, "请输入超链接标题", Toast.LENGTH_SHORT);
                } else {
                    mEditor.insertLink(linkAddress, linkTitle);
                    linkDialog.dismiss();
                }
            }
        });
        //点击取消的监听
        view.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linkDialog.dismiss();
            }
        });
        linkDialog.setCancelable(false);
        linkDialog.setView(view, 0, 0, 0, 0); // 设置 view
        linkDialog.show();
    }


    ViewTreeObserver.OnGlobalLayoutListener onGroupCollapseListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {

            WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
            int height = wm.getDefaultDisplay().getHeight();

            if (rl_layout_editor.getHeight() <= height * 0.75) {//当布局y轴坐标小于于屏幕高度的3/4，居于中部
                rl_layout_editor.setVisibility(View.VISIBLE);

            } else if (rl_layout_editor.getHeight() > height * 0.75) {
                rl_layout_editor.setVisibility(View.INVISIBLE);
                if (ll_layout_add.getVisibility() == View.VISIBLE) {
                    ll_layout_add.setVisibility(View.GONE);
                }

                if (ll_layout_font.getVisibility() == View.VISIBLE) {
                    ll_layout_font.setVisibility(View.GONE);
                }
            }
        }
    };

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }


    @Override
    protected AddPresenter getPresenter() {
        return new AddPresenter();
    }

    boolean isclick = true;
    boolean isItalic;//是否斜体
    boolean isBold;//是否加粗
    boolean isStrikeThrough;//是否有删除线
    //富文本图片保存的集合
    private ArrayList<String> selectedRichImage = new ArrayList<>();

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save:
                saveSubject();
                //todo 将html 发送到后台系统

//                Intent intent = new Intent(getActivity(), ReadAcitvity.class);
//                intent.putExtra("html", html);
//                startActivity(intent);
//        mEditor.clearFocusEditor();
//        mEditor.setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);
                Log.e("", "");
                break;

//            //上传图片
//            case R.id.img_uploading_pic:
//                PhotoPickerIntent intent = new PhotoPickerIntent(EditActivity.this);
//                intent.setPhotoCount(6 - selectedPhotos.size());//可以添加6张图片
//                startActivityForResult(intent, REQUEST_CODE);
//                break;

            //撤回
            case R.id.action_undo:

                mEditor.undo();
                break;
            //复原
            case R.id.action_redo:

                mEditor.redo();
                break;
            //字体
            case R.id.action_font:

                if (ll_layout_font.getVisibility() == View.VISIBLE) {
                    ll_layout_font.setVisibility(View.GONE);
                } else {
                    if (ll_layout_add.getVisibility() == View.VISIBLE) {
                        ll_layout_add.setVisibility(View.GONE);
                    }
                    ll_layout_font.setVisibility(View.VISIBLE);
                    startAnimation(ll_layout_font);
                }
                break;


            //添加
            case R.id.action_add:

                if (ll_layout_add.getVisibility() == View.VISIBLE) {
                    ll_layout_add.setVisibility(View.GONE);
                } else {
                    if (ll_layout_font.getVisibility() == View.VISIBLE) {
                        ll_layout_font.setVisibility(View.GONE);
                    }
                    ll_layout_add.setVisibility(View.VISIBLE);
                    startAnimation(ll_layout_add);
                }
                break;
            /**
             *粗体
             */
            case R.id.action_bold:
                if (flag1) {
                    ib_Bold.setImageResource(R.mipmap.bold_d);
                    flag1 = false;
                    isBold = false;
                } else {
                    ib_Bold.setImageResource(R.mipmap.bold_l);
                    flag1 = true;
                    isBold = true;
                }
                mEditor.setBold();
                break;
            //斜体
            case R.id.action_italic:
                if (flag2) {
                    ib_Italic.setImageResource(R.mipmap.italic_d);
                    flag2 = false;
                    isItalic = false;
                } else {
                    ib_Italic.setImageResource(R.mipmap.italic_l);
                    flag2 = true;
                    isItalic = true;
                }
                mEditor.setItalic();
                break;
            //删除线
            case R.id.action_strikethrough:
                if (flag3) {
                    ib_StrikeThough.setImageResource(R.mipmap.strikethrough_d);
                    flag3 = false;
                    isStrikeThrough = false;
                } else {
                    ib_StrikeThough.setImageResource(R.mipmap.strikethrough_l);
                    flag3 = true;
                    isStrikeThrough = true;
                }
                mEditor.setStrikeThrough();
                break;
            //块引用
            case R.id.action_blockquote:
                if (flag4) {
                    ib_BlockQuote.setImageResource(R.mipmap.blockquote_d);
                    flag4 = false;
                    isclick = false;
                } else {
                    flag4 = true;
                    flag5 = false;
                    flag6 = false;
                    flag7 = false;
                    flag8 = false;
                    isclick = true;
                    ib_BlockQuote.setImageResource(R.mipmap.blockquote_l);
                    ib_H1.setImageResource(R.mipmap.h1_d);
                    ib_H2.setImageResource(R.mipmap.h2_d);
                    ib_H3.setImageResource(R.mipmap.h3_d);
                    ib_H4.setImageResource(R.mipmap.h4_d);
                }
                Log.e("BlockQuote", "isItalic:" + isItalic + "，isBold：" + isBold + "，isStrikeThrough:" + isStrikeThrough);
                mEditor.setBlockquote(isclick, isItalic, isBold, isStrikeThrough);
                break;
            /**
             * H1-H4字体
             */
            case R.id.action_heading1:
                if (flag5) {
                    ib_H1.setImageResource(R.mipmap.h1_d);
                    flag5 = false;
                    isclick = false;

                    //使加粗灰显并去除效果
                    ib_Bold.setImageResource(R.mipmap.bold_d);
                    flag1 = false;
                    isBold = false;
                } else {
                    flag4 = false;
                    flag5 = true;
                    flag6 = false;
                    flag7 = false;
                    flag8 = false;
                    isclick = true;
                    ib_BlockQuote.setImageResource(R.mipmap.blockquote_d);
                    ib_H1.setImageResource(R.mipmap.h1_l);
                    ib_H2.setImageResource(R.mipmap.h2_d);
                    ib_H3.setImageResource(R.mipmap.h3_d);
                    ib_H4.setImageResource(R.mipmap.h4_d);
                }
                mEditor.setHeading(1, isclick, isItalic, isBold, isStrikeThrough);
                break;
            case R.id.action_heading2:
                if (flag6) {
                    ib_H2.setImageResource(R.mipmap.h2_d);
                    flag6 = false;
                    isclick = false;

                    //使加粗灰显并去除效果
                    ib_Bold.setImageResource(R.mipmap.bold_d);
                    flag1 = false;
                    isBold = false;
                } else {
                    flag4 = false;
                    flag5 = false;
                    flag6 = true;
                    flag7 = false;
                    flag8 = false;

                    isclick = true;
                    ib_BlockQuote.setImageResource(R.mipmap.blockquote_d);
                    ib_H1.setImageResource(R.mipmap.h1_d);
                    ib_H2.setImageResource(R.mipmap.h2_l);
                    ib_H3.setImageResource(R.mipmap.h3_d);
                    ib_H4.setImageResource(R.mipmap.h4_d);
                }
                mEditor.setHeading(2, isclick, isItalic, isBold, isStrikeThrough);
                break;
            case R.id.action_heading3:
                if (flag7) {
                    ib_H3.setImageResource(R.mipmap.h3_d);
                    flag7 = false;
                    isclick = false;

                    //使加粗灰显并去除效果
                    ib_Bold.setImageResource(R.mipmap.bold_d);
                    flag1 = false;
                    isBold = false;
                } else {
                    flag4 = false;
                    flag5 = false;
                    flag6 = false;
                    flag7 = true;
                    flag8 = false;
                    isclick = true;
                    ib_BlockQuote.setImageResource(R.mipmap.blockquote_d);
                    ib_H1.setImageResource(R.mipmap.h1_d);
                    ib_H2.setImageResource(R.mipmap.h2_d);
                    ib_H3.setImageResource(R.mipmap.h3_l);
                    ib_H4.setImageResource(R.mipmap.h4_d);
                }
                mEditor.setHeading(3, isclick, isItalic, isBold, isStrikeThrough);
                break;
            case R.id.action_heading4:
                if (flag8) {
                    ib_H4.setImageResource(R.mipmap.h4_d);
                    flag8 = false;
                    isclick = false;

                    //使加粗灰显并去除效果
                    ib_Bold.setImageResource(R.mipmap.bold_d);
                    flag1 = false;
                    isBold = false;
                } else {
                    flag4 = false;
                    flag5 = false;
                    flag6 = false;
                    flag7 = false;
                    flag8 = true;
                    isclick = true;
                    ib_BlockQuote.setImageResource(R.mipmap.blockquote_d);
                    ib_H1.setImageResource(R.mipmap.h1_d);
                    ib_H2.setImageResource(R.mipmap.h2_d);
                    ib_H3.setImageResource(R.mipmap.h3_d);
                    ib_H4.setImageResource(R.mipmap.h4_l);
                }
                mEditor.setHeading(4, isclick, isItalic, isBold, isStrikeThrough);
                break;

        }
    }


    private AlertDialog linkDialog;

    // 执行动画效果
    public void startAnimation(View mView) {

        AlphaAnimation aa = new AlphaAnimation(0.4f, 1.0f); // 0完全透明 1 完全不透明
        // 以(0%,0.5%)为基准点，从0.5缩放至1
        ScaleAnimation sa = new ScaleAnimation(0.5f, 1, 0.5f, 1,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0.5f);

        // 添加至动画集合
        AnimationSet as = new AnimationSet(false);
        as.addAnimation(aa);
        as.addAnimation(sa);
        as.setDuration(500);
        // 执行动画
        mView.startAnimation(as);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RICH_IMAGE_CODE && resultCode == Activity.RESULT_OK && null != data) {

            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String picturePath = c.getString(columnIndex);
            c.close();
            Log.i("dgs", "picturePath----" + picturePath);
            //todo 上传图片,完成后获得图片网络地址
            mPresenter.postImage(Message.obtain(this), "6", "pic", picturePath);
        }
    }

    Detail.SubjectBean subjectBean;

    private void saveSubject() {
        String html = mEditor.getHtml();
        subjectBean = new Detail.SubjectBean();
        //发起人
        Detail.SubjectBean.InitiatorBean initiatorBean = new Detail.SubjectBean.InitiatorBean();
        initiatorBean.setName("赵柳");
        initiatorBean.setTelephone("155");
        subjectBean.setInitiator(initiatorBean);
        //众筹实体
        Detail.SubjectBean.CrowdBean crowdBean = new Detail.SubjectBean.CrowdBean();
        crowdBean.setCrowd_funding("10000");
        crowdBean.setCrowd_progress("0");
        subjectBean.setCrowd(crowdBean);
        //待任务
        Detail.SubjectBean.TaskBean taskBean = new Detail.SubjectBean.TaskBean();
        taskBean.setTask_deadline("100天");
        taskBean.setTask_outline("50天");
        subjectBean.setTask(taskBean);
        //是否顶置
        Detail.SubjectBean.TopBean topBean = new Detail.SubjectBean.TopBean();
        topBean.setIs_top(false);
        subjectBean.setTop(topBean);
        //标题
        subjectBean.setTitle("去河南看中医");
        //描述
        subjectBean.setDescribe("听说有个牛逼的民间中医");
        //内容
        subjectBean.setContent(html);//富文本内容
        subjectBean.setDisease_type("高血压");
        subjectBean.setDoctor_address("某某村庄");
        subjectBean.setDoctor_type("01");
        subjectBean.setOrigin_from("01");//话题来源
        subjectBean.setPraise(0);
        mPresenter.addSubject(Message.obtain(this), subjectBean);
    }

    public static final int POST_IMAGE_SUCCESS = 0;
    public static final int POST_IMAGE_ERROR = 1;
    public static final int POST_SUBJECT_SUCCESS = 2;
    public static final int POST_SUBJECT_ERROR = 3;

    @Override
    public void handleMessage(Message message) {
        switch (message.what) {
            case POST_IMAGE_SUCCESS://图片上传成功
                String url = (String) message.obj;
                mEditor.insertImage(url, "图片02");
                break;
            case POST_IMAGE_ERROR://图片上传失败
                mEditor.insertImage("http://img.blog.csdn.net/20161019105746983?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center", "图片02");
                break;
            case POST_SUBJECT_SUCCESS://文章上传成功
                //跳转到展示页(而展示页可以选择再次编辑)
                String subjectPk = (String) message.obj;
                Intent intent = new Intent(EditorActivity.this, ShowActivity.class);
                //跳转到展示页. 有两种跳转情况:1, 携带SubjectBean对象; 2,只携带上传成功后获得的subjectPk
//                intent.putExtra("subjectBean", subjectBean);
                intent.putExtra("subjectPk", subjectPk); //考虑到对ShowActivity的复用性,暂时采用第二种.()
                startActivity(intent);
                finish();
                break;
            case POST_SUBJECT_ERROR://文章上传失败
                break;
        }

    }

}
