package com.kelin.mvvmlight.bindingadapter.checkbox;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.widget.CompoundButton;

/**
 * Created by kelin on 16-3-24.
 */
public final class ViewBindingAdapter {
    //1.这里需要双向绑定的是checked属性，event和method都省略了。
    @InverseBindingMethods({@InverseBindingMethod(type = CompoundButton.class, attribute = "android:checked"),})
    public static class CompoundButtonBindingAdapter {
        //2.设置什么时候调用event
        @BindingAdapter(value = {"android:onCheckedChanged", "android:checkedAttrChanged"},
                requireAll = false)
        public static void setListeners(CompoundButton view, final CompoundButton.OnCheckedChangeListener listener,
                                        final InverseBindingListener attrChange) {
            if (attrChange == null) {
                view.setOnCheckedChangeListener(listener);
            } else {
                view.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (listener != null) {
                            listener.onCheckedChanged(buttonView, isChecked);
                        }
                        attrChange.onChange();
                    }
                });
            }
        }
    }
}

