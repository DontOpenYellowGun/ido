package com.kelin.mvvmlight.bindingadapter.loadinglayout;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.kelin.mvvmlight.command.ReplyCommand;
import com.weavey.loading.lib.LoadingLayout;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kelin on 16-6-1.
 */
public class ViewBindingAdapter {
    @BindingAdapter(value = {"pageState", "reloadCommand"}, requireAll = false)
    public static void setPageState(final LoadingLayout layout, int pageState, ReplyCommand command) {
        if (layout != null) {

//            if (layout.getStatus() == 4) {
//                Observable.timer(1000, TimeUnit.MILLISECONDS)
//                        .lastElement()
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(aLong -> layout.setStatus(pageState));
//            } else {
                layout.setStatus(pageState);
//            }
        }
        if (layout != null && command != null) {
            layout.setOnReloadListener(v -> command.execute());
        }
    }

    @BindingAdapter(value = {"errorStr"})
    public static void setErrorText(final LoadingLayout layout, String errorText) {
        if (layout != null) {
            layout.setErrorText(errorText);
        }
    }
}
