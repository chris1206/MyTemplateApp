package com.yto.template.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.yto.template.R;
import com.yto.template.base.BaseActivity;
import com.yto.template.constant.Cheeses;
import com.yto.template.customview.IconCenterEditText;
import com.yto.template.customview.flowlayout.FlowAdapter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 2018/1/30.
 */

public class NavigationActivity extends BaseActivity{

    private TextView title;
    private ImageView back;
    private ListView mLv;
    private SearchView mSearchView;
    private SearchView.SearchAutoComplete mSearchAutoComplete;

    private IconCenterEditText et_search;
    private TabLayout mTabLayout1;
    private TabLayout mTabLayout2;
    private ViewStub myViewStub;
    private List<String> tlist = new ArrayList<>();
    private FlowAdapter tflowAdapter;
    private boolean hasChecked;
    private RecyclerView rv_two;
    private TextView tv_all_history;

    private ImageView iv_qrcode;
    private TextView tv_cancel;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_navi;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            setTranslucentStatus(true);//设置透明状态栏
//            setTranslucentNavigation(true);//设置透明底部导航栏
//        }
        et_search = findViewById(R.id.et_search);
        mTabLayout1 = findViewById(R.id.tabLayout1);
        mTabLayout2 = findViewById(R.id.tabLayout2);

        back = findViewById(R.id.back);
        title = findViewById(R.id.title);
        title.setText("导航栏");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        iv_qrcode = findViewById(R.id.iv_qrcode);
        tv_cancel = findViewById(R.id.tv_cancel);

        myViewStub = findViewById(R.id.myViewStub);

        et_search.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    iv_qrcode.setVisibility(View.INVISIBLE);
                    tv_cancel.setVisibility(View.VISIBLE);
                    try {
                        myViewStub.inflate();
                        View view=findViewById(R.id.myInflatedViewId);
                        rv_two = view.findViewById(R.id.rv_two);
                        tv_all_history = view.findViewById(R.id.tv_all_history);
                        tlist.add("A20170102fff");
                        tlist.add("A201701");
                        tlist.add("A201701");
                        tlist.add("A201701555");
                        tlist.add("A201701666");
                        tlist.add("A201701777");
                        tflowAdapter = new FlowAdapter(NavigationActivity.this,tlist,1,hasChecked);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(NavigationActivity.this);
                        rv_two.addItemDecoration(new DividerItemDecoration(NavigationActivity.this, DividerItemDecoration.VERTICAL));
                        rv_two.setLayoutManager(linearLayoutManager);
                        rv_two.setAdapter(tflowAdapter);
                        rv_two.setNestedScrollingEnabled(false);

                    } catch (Exception e) {
                        // 如果使用inflate膨胀报错，就说明已经被膨胀过了，使用setVisibility方法显示
                        myViewStub.setVisibility(View.VISIBLE);
                    }
                    tflowAdapter.setItemClickListen(new FlowAdapter.OnItemClickListen() {
                        @Override
                        public void onItemClick(View view, int position) {
                            et_search.setText(tlist.get(position));
                            et_search.setSelection(et_search.length());
                        }
                    });
                    tv_all_history.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(!hasChecked){
                                hasChecked = true;
                                tflowAdapter.setAll(hasChecked);
                                tv_all_history.setText("收起");
                            }else{
                                hasChecked = false;
                                tflowAdapter.setAll(hasChecked);
                                tv_all_history.setText("查看全部记录");
                            }

                        }
                    });
                }else{
                    myViewStub.setVisibility(View.GONE);
                    iv_qrcode.setVisibility(View.VISIBLE);
                    tv_cancel.setVisibility(View.INVISIBLE);
                }
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_search.setText("");
                et_search.clearFocus();
            }
        });
        initCustomSearchView();

        initCustomToolbar();

        initTabs();

    }

    private void initTabs() {
        mTabLayout1.addTab(mTabLayout1.newTab().setText("选项一"));
        mTabLayout1.addTab(mTabLayout1.newTab().setText("选项二"));
        mTabLayout1.setSelectedTabIndicatorColor(getResources().getColor(R.color.indicator_color));
        mTabLayout1.setBackground(getResources().getDrawable(R.drawable.bg_white_radius));
//        mTabLayout1
//        mTabLayout1.setBackgroundColor(getResources().getColor(R.color.red));
        mTabLayout2.addTab(mTabLayout2.newTab().setText("选项一"));
        mTabLayout2.addTab(mTabLayout2.newTab().setText("选项二"));
        mTabLayout2.addTab(mTabLayout2.newTab().setText("选项三"));
        mTabLayout2.setSelectedTabIndicatorColor(getResources().getColor(R.color.indicator_color));
        mTabLayout2.setBackground(getResources().getDrawable(R.drawable.bg_white_radius));
//        mTabLayout2.setBackgroundColor(getResources().getColor(R.color.red));
    }

    private void initCustomSearchView() {
//        mToolBar.setFocusable(true);
        //点击输入框搜搜按钮时的触发事件
        hideKeyboard();
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(et_search,InputMethodManager.SHOW_FORCED);
        imm.hideSoftInputFromWindow(et_search.getWindowToken(), 0); //强制隐藏键盘
    }

    private void showKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(et_search, InputMethodManager.SHOW_FORCED);
    }

    private void initCustomToolbar() {
//        mToolBar.setNavigationIcon(getResources().getDrawable(R.mipmap.arrow_white));
//        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });

        Toolbar toolbar_custom = findViewById(R.id.toolbar_custom);
        setSupportActionBar(toolbar_custom);
        toolbar_custom.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSearchAutoComplete.isShown()) {
                    try {
                        mSearchAutoComplete.setText("");
                        Method method = mSearchView.getClass().getDeclaredMethod("onCloseClicked");
                        method.setAccessible(true);
                        method.invoke(mSearchView);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    finish();
                }
            }
        });

        mLv = findViewById(R.id.lv);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_view, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_search);

        //通过MenuItem得到SearchView
        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        mSearchAutoComplete = (SearchView.SearchAutoComplete) mSearchView.findViewById(R.id.search_src_text);
        mSearchView.setQueryHint("试试搜索 a b c ...");

        //设置输入框提示文字样式
        mSearchAutoComplete.setHintTextColor(getResources().getColor(android.R.color.darker_gray));
        mSearchAutoComplete.setTextColor(getResources().getColor(R.color.text_normal));
        mSearchAutoComplete.setTextSize(14);
        //设置触发查询的最少字符数（默认2个字符才会触发查询）
        mSearchAutoComplete.setThreshold(1);

        //设置搜索框有字时显示叉叉，无字时隐藏叉叉
        mSearchView.onActionViewExpanded();
        mSearchView.setIconified(true);

        //修改搜索框控件间的间隔（这样只是为了更加接近网易云音乐的搜索框）
        LinearLayout search_edit_frame = (LinearLayout) mSearchView.findViewById(R.id.search_edit_frame);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) search_edit_frame.getLayoutParams();
        params.leftMargin = 0;
        params.rightMargin = 0;
        search_edit_frame.setLayoutParams(params);

        //监听SearchView的内容
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                Cursor cursor = TextUtils.isEmpty(s) ? null : queryData(s);

//                if (mSearchView.getSuggestionsAdapter() == null) {
//                    mSearchView.setSuggestionsAdapter(new SimpleCursorAdapter(SearchViewActivity2.this, R.layout.item_layout, cursor, new String[]{"name"}, new int[]{R.id.text1}));
//                } else {
//                    mSearchView.getSuggestionsAdapter().changeCursor(cursor);
//                }
                setAdapter(cursor);

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    // 让菜单同时显示图标和文字
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    private Cursor queryData(String key) {
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(getFilesDir() + "music.db", null);
        Cursor cursor = null;
        try {
            String querySql = "select * from tb_music where name like '%" + key + "%'";
            cursor = db.rawQuery(querySql, null);
            Log.e("CSDN_LQR", "querySql = " + querySql);
        } catch (Exception e) {
            e.printStackTrace();
            String createSql = "create table tb_music (_id integer primary key autoincrement,name varchar(100))";
            db.execSQL(createSql);

            String insertSql = "insert into tb_music values (null,?)";
            for (int i = 0; i < Cheeses.sCheeseStrings.length; i++) {
                db.execSQL(insertSql, new String[]{Cheeses.sCheeseStrings[i]});
            }

            String querySql = "select * from tb_music where name like '%" + key + "%'";
            cursor = db.rawQuery(querySql, null);

            Log.e("CSDN_LQR", "createSql = " + createSql);
            Log.e("CSDN_LQR", "querySql = " + querySql);
        }
        return cursor;
    }

    private void setAdapter(Cursor cursor) {
        if (mLv.getAdapter() == null) {
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(NavigationActivity.this, R.layout.item_layout, cursor, new String[]{"name"}, new int[]{R.id.text1});
            mLv.setAdapter(adapter);
        } else {
            ((SimpleCursorAdapter) mLv.getAdapter()).changeCursor(cursor);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |=  bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void setTranslucentNavigation(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
        if (on) {
            winParams.flags |=  bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}
