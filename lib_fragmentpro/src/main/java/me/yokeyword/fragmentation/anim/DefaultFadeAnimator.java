package me.yokeyword.fragmentation.anim;

import android.os.Parcel;

import me.yokeyword.fragmentation.R;

public class DefaultFadeAnimator extends FragmentAnimator {

    public DefaultFadeAnimator() {
        enter = R.anim.task_f_fragment_enter;
        exit = R.anim.task_f_fragment_exit;
        popEnter =R.anim.task_f_fragment_pop_enter;
        popExit =R.anim.task_f_fragment_pop_exit;
    }

    protected DefaultFadeAnimator(Parcel in) {
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

    public static final Creator<DefaultFadeAnimator> CREATOR = new Creator<DefaultFadeAnimator>() {
        @Override
        public DefaultFadeAnimator createFromParcel(Parcel in) {
            return new DefaultFadeAnimator(in);
        }

        @Override
        public DefaultFadeAnimator[] newArray(int size) {
            return new DefaultFadeAnimator[size];
        }
    };
}
