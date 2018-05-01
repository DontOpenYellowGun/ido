package com.kelin.mvvmlight.collectionadapter.recyclerview;

import android.support.annotation.ColorRes;
import android.support.annotation.IntDef;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.kelin.mvvmlight.view.GridSpacingItemDecoration;
import com.kelin.mvvmlight.view.HorizontalDividerItemDecoration;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.weavey.loading.lib.DensityUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * A collection of factories to create RecyclerView LayoutManagers so that you can easily set them
 * in your layout.
 */
public class ItemDecorations {
    protected ItemDecorations() {
    }

    public interface ItemDecorationsFactory {
        RecyclerView.ItemDecoration create(RecyclerView recyclerView);
    }

    /**
     * A {@link com.kelin.mvvmlight.view.GridSpacingItemDecoration}.
     */
    public static ItemDecorations.ItemDecorationsFactory grid(int span, int px, Boolean includeEdge) {
        return recyclerView -> new GridSpacingItemDecoration(span, px, includeEdge, 0);
    }

    public static ItemDecorations.ItemDecorationsFactory horizontal(int dp, int color) {
        return recyclerView -> new HorizontalDividerItemDecoration.Builder(recyclerView.getContext())
                .color(ContextCompat.getColor(recyclerView.getContext(), color))
                .size(DensityUtil.dp2px(dp))
                .build();
    }

    public static ItemDecorations.ItemDecorationsFactory horizontal(float px, @ColorRes int color) {
        return recyclerView -> new HorizontalDividerItemDecoration.Builder(recyclerView.getContext())
                .color(color)
                .size((int) px)
                .build();
    }
}
