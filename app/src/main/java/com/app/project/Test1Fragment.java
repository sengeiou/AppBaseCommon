package com.app.project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.basecommon.navigator.ServiceFactory;
import com.app.project.wangyi.ClockView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

/**
 * @Auther JiRui
 * @CreateTime 2019/5/8 12:45
 * @Describe
 */
public class Test1Fragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View inflate = inflater.inflate(R.layout.fragment_view,container, false);
//        View inflate = new ClockView(getContext());
        final ClockView clockView = inflate.findViewById(R.id.clockview);
        ((TextView)inflate.findViewById(R.id.tv_text)).setText("test1");
        inflate.findViewById(R.id.jumpToTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceFactory.getInstance().startAct(getContext(),"TestActivity",null);
            }
        });
        inflate.findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clockView.reset();
            }
        });

        return inflate;
    }


}
