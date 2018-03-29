package com.yto.template.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.yto.template.R;
import com.yto.template.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RadioActivity extends BaseActivity {
    public static final String TAG = "RadioActivity";

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.radio_first_single)
    CheckBox radio_first_single;

    @BindView(R.id.radio_group_more)
    RadioGroup radio_group_more;
    @BindView(R.id.radio_first_more)
    RadioButton radio_first_more;
    @BindView(R.id.radio_two_more)
    RadioButton radio_two_more;
    @BindView(R.id.radio_three_more)
    RadioButton radio_three_more;
    @BindView(R.id.radio_four_more)
    RadioButton radio_four_more;

    @BindView(R.id.radio_first_alone)
    CheckBox radio_first_alone;

    @BindView(R.id.radio_group_equal)
    RadioGroup radio_group_equal;
    @BindView(R.id.radio_first_equal)
    RadioButton radio_first_equal;
    @BindView(R.id.radio_two_equal)
    RadioButton radio_two_equal;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_radio;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        title.setText("单选框");
        radio_first_single.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(!isChecked){
//                    radio_first_single.setChecked(true);
//                }
                Toast.makeText(RadioActivity.this,"选中了单选---选项一",Toast.LENGTH_SHORT).show();
            }
        });
        radio_group_more.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio_first_more:
                        Toast.makeText(RadioActivity.this,"选中了多选---选项一",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radio_two_more:
                        Toast.makeText(RadioActivity.this,"选中了多选---选项二",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radio_three_more:
                        Toast.makeText(RadioActivity.this,"选中了多选---选项三",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radio_four_more:
                        Toast.makeText(RadioActivity.this,"选中了多选---选项四",Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });
        radio_first_alone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(!isChecked){
//                    radio_first_alone.setChecked(true);
//                }
                Toast.makeText(RadioActivity.this,"选中了单独使用",Toast.LENGTH_SHORT).show();
            }
        });
        radio_group_equal.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio_first_equal:
                        Toast.makeText(RadioActivity.this,"选中了同类---选项一",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radio_two_equal:
                        Toast.makeText(RadioActivity.this,"选中了同类---选项二",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    @OnClick(R.id.back)
    void onClick(){
        onBackPressed();
    }

}
