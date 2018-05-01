package com.kelin.mvvmlight.bindingadapter.edittext;

import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;

/**
 * 文件描述：
 * <p>
 * 作者：   Created by sven on 2017/11/24.
 */

public class PointLenthFilter implements InputFilter {

    private int DECIMAL_DIGITS = 1;

    public PointLenthFilter(int DECIMAL_DIGITS) {
        this.DECIMAL_DIGITS = DECIMAL_DIGITS;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end,
                               Spanned dest, int dstart, int dend) {
        // source:当前输入的字符
        // start:输入字符的开始位置
        // end:输入字符的结束位置
        // dest：当前已显示的内容
        // dstart:当前光标开始位置
        // dent:当前光标结束位置
        Log.i("", "source=" + source + ",start=" + start + ",end=" + end
                + ",dest=" + dest.toString() + ",dstart=" + dstart
                + ",dend=" + dend);
        if (dest.length() == 0 && source.equals(".")) {
            return "0.";
        }
        if (source.equals(".") && dstart < source.length() - 3) {
            return "";
        }
        String dValue = dest.toString();
        String[] splitArray = dValue.split("\\.");
        int pointIndex = dValue.indexOf(".");
        if (splitArray.length > 1) {
            String dotValue = splitArray[1];
            if (dstart > pointIndex) {
                if (dotValue.length() == DECIMAL_DIGITS) {
                    return "";
                }
            } else {
                return source;
            }
        }
        return null;
    }
}
