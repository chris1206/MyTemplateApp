package com.yto.template.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yto.template.R;
import com.yto.template.base.BaseV4Fragment;
import com.yto.template.ui.ActionSheetActivity;
import com.yto.template.ui.CheckboxActivity;
import com.yto.template.ui.EditActivity;
import com.yto.template.ui.ExceptionActivity;
import com.yto.template.ui.LoadingActivity;
import com.yto.template.ui.ModalBoxActivity;
import com.yto.template.ui.NoticeBarActivity;
import com.yto.template.ui.ResultActivity;
import com.yto.template.ui.ScanActivity;
import com.yto.template.ui.SearchViewActivity;
import com.yto.template.ui.SelectorActivity;
import com.yto.template.ui.ToastActivity;

import butterknife.OnClick;

/**
 * Created by Chris on 2018/1/26.
 */

public class FragInfoFeedback extends BaseV4Fragment {
    @Override
    protected int getLayoutId() {
        return R.layout.frag_info_feedback;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
    }

    @OnClick({R.id.rl_notic_back_bar,R.id.rl_action_sheet,R.id.rl_to_loading,R.id.rl_to_refresh,R.id.rl_to_toast,R.id.rl_modal_box,R.id.rl_result_page,R.id.rl_to_exception})
    void onClick(View view){
        switch (view.getId()){
            case R.id.rl_notic_back_bar:
                startActivity(new Intent(getActivity(), NoticeBarActivity.class));
                break;
            case R.id.rl_action_sheet:
                startActivity(new Intent(getActivity(), ActionSheetActivity.class));
                break;
            case R.id.rl_to_loading:
                startActivity(new Intent(getActivity(), LoadingActivity.class));
                break;
            case R.id.rl_to_refresh:
                startActivity(new Intent(getActivity(), SelectorActivity.class));
                break;
            case R.id.rl_to_toast:
                startActivity(new Intent(getActivity(), ToastActivity.class));
                break;
            case R.id.rl_modal_box:
                startActivity(new Intent(getActivity(), ModalBoxActivity.class));
                break;
            case R.id.rl_result_page:
                startActivity(new Intent(getActivity(), ResultActivity.class));
                break;
            case R.id.rl_to_exception:
                startActivity(new Intent(getActivity(), ExceptionActivity.class));
                break;

        }
    }
}
