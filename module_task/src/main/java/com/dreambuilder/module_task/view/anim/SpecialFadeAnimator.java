package com.dreambuilder.module_task.view.anim;

import android.os.Parcel;

import com.dreambuilder.module_task.R;

import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class SpecialFadeAnimator extends FragmentAnimator {

    public SpecialFadeAnimator() {
        enter = R.anim.task_fade_fragment_enter;
        exit = R.anim.task_fade_fragment_exit;
        popEnter =R.anim.task_fade_fragment_pop_enter;
        popExit =R.anim.task_fade_fragment_pop_exit;
    }

    protected SpecialFadeAnimator(Parcel in) {
        super(in);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SpecialFadeAnimator> CREATOR = new Creator<SpecialFadeAnimator>() {
        @Override
        public SpecialFadeAnimator createFromParcel(Parcel in) {
            return new SpecialFadeAnimator(in);
        }

        @Override
        public SpecialFadeAnimator[] newArray(int size) {
            return new SpecialFadeAnimator[size];
        }
    };
}
