package com.weavey.loading.lib;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.github.ybq.android.spinkit.SpinKitView;


/**
 * 文件描述：加载等待Dialog
 * <p>
 * 作者：   Created by sven on 2017/12/4.
 */

public class LoadingDialog extends Dialog {

    private TextView mMessageView;

    private SpinKitView mAnimView;

    private String mMessage;

    private Context context;

    public LoadingDialog(Context context) {
        this(context, R.style.LoadingDialog);
    }

    public LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
        init();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void init() {

        View view = View.inflate(context, R.layout.view_dialog, null);

        setContentView(view);

        setCanceledOnTouchOutside(false);

        Window win = getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.height = DensityUtils.dip2px(context, 120);
        lp.width = DensityUtils.dip2px(context, 120);
        win.setAttributes(lp);


        mMessageView = view.findViewById(R.id.message_text_view);

        mAnimView = view.findViewById(R.id.anim_view);

        mAnimView.setColor(Color.parseColor("#2196f3"));
    }


    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String mMessage) {
        this.mMessage = mMessage;
        mMessageView.setText(mMessage);
    }
}
