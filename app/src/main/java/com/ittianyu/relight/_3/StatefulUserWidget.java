package com.ittianyu.relight._3;

import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

import com.ittianyu.relight.common.bean.UserBean;
import com.ittianyu.relight.common.datasource.UserDataSource;
import com.ittianyu.relight.utils.StateUtils;
import com.ittianyu.relight.widget.native_.TextWidget;
import com.ittianyu.relight.widget.stateful.AsyncState;
import com.ittianyu.relight.widget.stateful.LifecycleStatefulWidget;

public class StatefulUserWidget extends LifecycleStatefulWidget<TextView, TextWidget> {
    private UserBean user = UserDataSource.getInstance().getUser();

    public StatefulUserWidget(Context context, Lifecycle lifecycle) {
        super(context, lifecycle);
    }

    @Override
    protected AsyncState<TextWidget> createState(Context context) {
        return StateUtils.create(new TextWidget(context, lifecycle));
    }

    @Override
    public void initWidget(TextWidget widget) {
        widget
                .text(user.getName())
                .gravity(Gravity.CENTER)
                .matchParent()
                .onClickListener(v -> setState(() -> {
                    user = UserDataSource.getInstance().getUser();
                }));
    }

    @Override
    public void updateWidget(TextWidget widget) {
        widget.text(user.getName());
    }
}