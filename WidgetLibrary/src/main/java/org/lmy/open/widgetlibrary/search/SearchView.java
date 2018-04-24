package org.lmy.open.widgetlibrary.search;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import org.lmy.open.widgetlibrary.R;

/**********************************************************************
 *
 *
 * @类名 SearchView
 * @包名 org.lmy.open.widgetlibrary.search
 * @author lmy
 * @创建日期 2018/4/16
 ***********************************************************************/
public class SearchView extends LinearLayout implements View.OnClickListener {

    public SearchView(Context context) {
        super(context);
        init();
    }

    public SearchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.layout_search, this);
        this.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        View.inflate(getContext(), R.layout.layout_search_result, this);
        invalidate();
    }
}
