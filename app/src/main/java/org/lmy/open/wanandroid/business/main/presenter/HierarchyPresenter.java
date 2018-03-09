package org.lmy.open.wanandroid.business.main.presenter;

import org.lmy.open.database.option.DaoOption;
import org.lmy.open.database.option.DtoOption;
import org.lmy.open.utillibrary.LogHelper;
import org.lmy.open.wanandroid.business.main.contract.HierarchyConeract;
import org.lmy.open.wanandroid.business.main.fragment.HierarchyFragment;
import org.lmy.open.wanandroid.core.base.BasePresenter;

import java.util.List;

/**********************************************************************
 *
 *
 * @类名 HierarchyPresenter
 * @包名 org.lmy.open.wanandroid.business.main.presenter
 * @author lmy
 * @创建日期 2018/3/9
 ***********************************************************************/
public class HierarchyPresenter extends BasePresenter<HierarchyFragment> implements HierarchyConeract.IHierarchyPresenter {

    @Override
    public void onLoadOptionData() {
        List<DtoOption> options = DaoOption.getInstance().selectOption();
        LogHelper.d("liumy=== options:"+options);
        if (options == null || options.size() <= 0) {
            getView().jump2CourseFragment();
        } else {
            getView().initOptionList(options);
        }
    }
}
