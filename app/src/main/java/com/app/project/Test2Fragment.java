package com.app.project;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.basecommon.utiles.Logger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

/**
 * @Auther JiRui
 * @CreateTime 2019/5/8 12:45
 * @Describe
 */
public class Test2Fragment extends Fragment {

    private TextView mTvText;
//    private Fragment2Model mFragment2Model;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
//        mFragment2Model = ViewModelProviders.of(this).get(Fragment2Model.class);
        View inflate = inflater.inflate(R.layout.fragment_view,container, false);
//
//
//        mTvText = inflate.findViewById(R.id.tv_text);
//        mTvText.setText(mFragment2Model.count + "");
//        inflate.findViewById(R.id.tv_text).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mTvText.setText(++mFragment2Model.count + "");
//            }
//        });
        return inflate;
    }

}
