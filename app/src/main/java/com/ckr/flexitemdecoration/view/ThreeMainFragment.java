package com.ckr.flexitemdecoration.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.ckr.flexitemdecoration.R;
import com.ckr.flexitemdecoration.adapter.ThreeMainAdapter;
import com.ckr.flexitemdecoration.widget.BaseItemDecoration;
import com.ckr.flexitemdecoration.widget.DividerGridItemDecoration;

import java.util.Arrays;

import butterknife.BindDimen;
import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThreeMainFragment extends BaseFragment {
    private static final String TAG = "MainFragment";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindDimen(R.dimen.size10)
    int padding;
    private ThreeMainAdapter mainAdapter;
    public static final int SPAN_COUNT = 2;
    public static final int ORIENTATION = LinearLayoutManager.HORIZONTAL;
    private BaseItemDecoration itemDecoration;
    public boolean[] is_checked = {true, false, false, false, false};
    private boolean isInit = false;

    public static ThreeMainFragment newInstance() {
        Bundle args = new Bundle();
        ThreeMainFragment fragment = new ThreeMainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_main_three;
    }

    @Override
    protected void init() {
        isInit = true;
//        int dimension = (int) getResources().getDimension(R.dimen.size12);
        setItemDecoration();
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), SPAN_COUNT, ORIENTATION, false));
        recyclerView.setPadding(padding, padding, padding, padding);
        mainAdapter = new ThreeMainAdapter(getContext());
        recyclerView.setAdapter(mainAdapter);
    }

    private void setItemDecoration() {
        if (!isInit) {
            return;
        }
        if (itemDecoration != null) {
            recyclerView.removeItemDecoration(itemDecoration);
        }
        DividerGridItemDecoration.Builder builder = new DividerGridItemDecoration.Builder(getContext(),ORIENTATION, SPAN_COUNT);
//        DividerGridItemDecoration.Builder builder = new DividerGridItemDecoration.Builder(getContext(), SPAN_COUNT);
        builder.setDivider(R.drawable.bg_divider_list);
        if (is_checked[0]) {
        } else {
            builder.removeHeaderDivider(is_checked[1])
                    .removeFooterDivider(is_checked[2])
                    .removeLeftDivider(is_checked[3])
                    .removeRightDivider(is_checked[4])
            ;
        }
        itemDecoration = builder.build();
        recyclerView.addItemDecoration(itemDecoration);
       /* itemDecoration = new DividerGridItemDecoration(getContext(), BaseItemDecoration.HORIZONTAL,SPAN_COUNT);
        itemDecoration.setDivider(R.drawable.bg_divider_list);
        if (is_checked[0]) {
        } else {
            itemDecoration.removeHeaderDivider(is_checked[1])
                    .removeFooterDivider(is_checked[2])
                    .removeLeftDivider(is_checked[3])
                    .removeRightDivider(is_checked[4])
            ;
        }
        recyclerView.addItemDecoration(itemDecoration);*/
    }

    @Override
    public void refreshFragment(boolean... params) {
        Log.d(TAG, "refreshFragment: params:" + Arrays.toString(params));
        if (!Arrays.equals(params, is_checked)) {
            System.arraycopy(params, 0, is_checked, 0, params.length);
            Log.d(TAG, "refreshFragment: is_checked:" + Arrays.toString(is_checked));
            setItemDecoration();
        }
    }
}
