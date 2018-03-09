package com.yto.template.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yto.template.R;
import com.yto.template.base.BaseV4Fragment;
import com.yto.template.ui.CheckboxActivity;
import com.yto.template.ui.EditActivity;
import com.yto.template.ui.RadioActivity;
import com.yto.template.ui.ScanActivity;
import com.yto.template.ui.SearchViewActivity;
import com.yto.template.ui.SelectorActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Chris on 2018/1/26.
 */

public class FragInfoInput extends BaseV4Fragment {

    @Override
    protected int getLayoutId() {
        return R.layout.frag_info_input;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }
    @OnClick({R.id.rl_to_radio,R.id.rl_to_checkbox,R.id.rl_to_input,R.id.rl_to_selector,R.id.rl_to_search,R.id.rl_to_scan,R.id.rl_to_photo,R.id.rl_to_upload,R.id.rl_to_keyboard})
    void onClick(View view){
        switch (view.getId()){
            case R.id.rl_to_radio:
                startActivity(new Intent(getActivity(), RadioActivity.class));
                break;
            case R.id.rl_to_checkbox:
                startActivity(new Intent(getActivity(), CheckboxActivity.class));
                break;
            case R.id.rl_to_input:
                startActivity(new Intent(getActivity(), EditActivity.class));
                break;
            case R.id.rl_to_selector:
                startActivity(new Intent(getActivity(), SelectorActivity.class));
                break;
            case R.id.rl_to_search:
                startActivity(new Intent(getActivity(), SearchViewActivity.class));
                break;
            case R.id.rl_to_scan:
                startActivity(new Intent(getActivity(), ScanActivity.class));
                break;
            case R.id.rl_to_photo:
                break;
            case R.id.rl_to_upload:
                break;
            case R.id.rl_to_keyboard:
                break;
        }
    }
}
