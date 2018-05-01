package com.fangao.lib_common.http.client.subscribers.progress;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

import com.weavey.loading.lib.LoadingDialog;

/**
 * 文件描述：
 * <p>
 * 作者：   Created by Sven on 2017/7/21 0021.
 */

public class ProgressDialogHandler extends Handler {

    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 2;
    public static final int END_PROGRESS_DIALOG_LOADING = 3;

    public LoadingDialog pd;

    private Context context;
    private boolean cancelable;
    private String message;
    private ProgressCancelListener mProgressCancelListener;

    public ProgressDialogHandler(Context context, ProgressCancelListener mProgressCancelListener, boolean cancelable, String message) {
        super();
        this.context = context;
        this.message = message;
        this.mProgressCancelListener = mProgressCancelListener;
        this.cancelable = cancelable;
    }

    private void initProgressDialog() {
        if (context != null) {
            if (pd == null) {
                pd = new LoadingDialog(context);
                if (message != null) {
                    pd.setMessage(message);
                }
                pd.setCancelable(cancelable);

                if (cancelable) {
                    pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialogInterface) {
                            mProgressCancelListener.onCancelProgress();
                        }
                    });
                }
                if (!pd.isShowing()) {
                    pd.show();
                }
            } else {
                if (!pd.isShowing()) {
                    pd.show();
                }
            }
        }
    }


    private void dismissProgressDialog() {
        if (context != null) {
            if (pd != null) {
                pd.dismiss();
                pd = null;
            }
        }
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                initProgressDialog();
                break;
            case DISMISS_PROGRESS_DIALOG:
            case END_PROGRESS_DIALOG_LOADING:
                dismissProgressDialog();
                break;
        }
    }
}
