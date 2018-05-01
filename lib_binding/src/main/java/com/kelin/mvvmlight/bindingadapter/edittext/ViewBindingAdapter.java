package com.kelin.mvvmlight.bindingadapter.edittext;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.kelin.mvvmlight.command.ReplyCommand;
import com.kelin.mvvmlight.util.EditFilterUtil;

/**
 * Created by kelin on 16-3-24.
 */
public final class ViewBindingAdapter {


    @android.databinding.BindingAdapter({"requestFocus"})
    public static void requestFocusCommand(EditText editText, final Boolean needRequestFocus) {
        if (needRequestFocus) {
            editText.setFocusableInTouchMode(true);
            editText.setSelection(editText.getText().length());
            editText.requestFocus();
            InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
        } else {
            editText.setEnabled(false);
            editText.setEnabled(true);
        }

    }

    @android.databinding.BindingAdapter({"sectionStr"})
    public static void setSection(EditText editText, final String text) {
        editText.setSelection(TextUtils.isEmpty(text) ? 0 : text.length());

    }


    @android.databinding.BindingAdapter(value = {"beforeTextChangedCommand", "onTextChangedCommand", "afterTextChangedCommand"}, requireAll = false)
    public static void editTextCommand(EditText editText,
                                       final ReplyCommand<TextChangeDataWrapper> beforeTextChangedCommand,
                                       final ReplyCommand<TextChangeDataWrapper> onTextChangedCommand,
                                       final ReplyCommand<String> afterTextChangedCommand) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (beforeTextChangedCommand != null) {
                    beforeTextChangedCommand.execute(new TextChangeDataWrapper(s, start, count, count));
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (onTextChangedCommand != null) {
                    onTextChangedCommand.execute(new TextChangeDataWrapper(s, start, before, count));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (afterTextChangedCommand != null) {
                    afterTextChangedCommand.execute(s.toString());
                }
            }
        });
    }

    public static class TextChangeDataWrapper {
        public CharSequence s;
        public int start;
        public int before;
        public int count;

        public TextChangeDataWrapper(CharSequence s, int start, int before, int count) {
            this.s = s;
            this.start = start;
            this.before = before;
            this.count = count;
        }
    }

    @android.databinding.BindingAdapter(value = {"length"})
    public static void setEditFilter(EditText editText, int length) {
        EditFilterUtil.setFilter(editText.getContext(), editText, length);
    }


    /**
     * Method:      保留几位小数 <br>
     * Description:  <br>
     * Creator:     sven <br>
     * Date:        2017/11/24 下午1:19
     */
    @android.databinding.BindingAdapter(value = {"point"})
    public static void setFilter(EditText editText, int point) {
        editText.setFilters(new InputFilter[]{new PointLenthFilter(point)});
    }
}

