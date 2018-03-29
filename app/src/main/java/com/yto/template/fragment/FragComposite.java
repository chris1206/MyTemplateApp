package com.yto.template.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yto.template.R;
import com.yto.template.base.BaseV4Fragment;
import com.yto.template.ui.FrameActivity;
import com.yto.template.ui.MapActivity;

import butterknife.OnClick;

/**
 * Created by Chris on 2018/1/26.
 *  综合系列
 */

public class FragComposite extends BaseV4Fragment {

    @Override
    protected int getLayoutId() {
        return R.layout.frag_composite;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @OnClick({R.id.rl_frame,R.id.rl_map})
    void onClick(View view){
        switch (view.getId()){
            case R.id.rl_frame:
                startActivity(new Intent(getContext(), FrameActivity.class));
                break;
            case R.id.rl_map:
                startActivity(new Intent(getContext(), MapActivity.class));
                break;
        }
    }
}
