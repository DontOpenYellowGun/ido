package debug;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fangao.lib_common.base.FragmentActivity;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * 文件描述：用于组件开发模式下承载Fragment的Activity
 * <p>
 * 作者：   Created by sven on 2017/11/14.
 */

public class DebugActivity extends FragmentActivity {
    @Override
    protected SupportFragment loadFragment() {
        return ((SupportFragment) ARouter.getInstance().build("/main/MainFragment").navigation());
    }
}
