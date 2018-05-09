package org.lmy.open.wanandroid.business.collection.presenter;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import org.lmy.open.database.collect.DaoCollect;
import org.lmy.open.database.collect.DtoCollect;
import org.lmy.open.netlibrary.internet.api.ISendRequest;
import org.lmy.open.netlibrary.internet.api.JsonUtil;
import org.lmy.open.netlibrary.internet.api.RequestProxy;
import org.lmy.open.utillibrary.LogHelper;
import org.lmy.open.utillibrary.NetWorkUtil;
import org.lmy.open.wanandroid.R;
import org.lmy.open.wanandroid.business.collection.bean.BeanRespCollect;
import org.lmy.open.wanandroid.business.collection.bean.BeanRespCollectData;
import org.lmy.open.wanandroid.business.collection.contract.CollectContract;
import org.lmy.open.wanandroid.business.collection.fragment.CollectionFragment;
import org.lmy.open.wanandroid.core.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**********************************************************************
 *
 *
 * @类名 CollectPresenter
 * @包名 org.lmy.open.wanandroid.business.collection.presenter
 * @author lmy
 * @创建日期 2018/3/28
 ***********************************************************************/
public class CollectPresenter extends BasePresenter<CollectionFragment> implements CollectContract.ICollectPresenter, Handler.Callback {
    /**
     * 检测本地数据
     */
    private static final int MESSAGE_CHECK_LOCAL_DATA = 10001;

    /**
     * 检测网络数据
     */
    private static final int MESSAGE_CHECK_NETWORK_DATA = 10002;
    /**
     * 本地存储的收藏列表
     */
    private volatile List<DtoCollect> mLocalDtoCollects;

    /**
     * 网络获取的收藏列表
     */
    private volatile List<DtoCollect> mNetWorkDtoCollects;
    /**
     * 用户id
     */
    private int mUserId;
    /**
     * 子线程
     */
    private HandlerThread mHandlerThread;
    /**
     * 任务执行
     */
    private Handler mHandler;

    @Override
    public void onCreatePresenter(@Nullable Bundle savedState) {
        super.onCreatePresenter(savedState);
        mHandlerThread = new HandlerThread(CollectPresenter.class.getName());
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper(), this);
    }

    @Override
    public void loadCollectList(int userId, int page) {
        if (!NetWorkUtil.isNetworkConnected(getView().getContext())) {
            mLocalDtoCollects = DaoCollect.getInstance().getAllCollect(userId);
            getView().initCollectList(mLocalDtoCollects);
            return;
        }
        if (mLocalDtoCollects == null) {
            mLocalDtoCollects = new ArrayList<>();
        }
        mUserId = userId;
        RequestProxy.getInstance().getCollect(page, mGetCollectListener);
    }

    @Override
    public void onSearch(String key) {
        if (TextUtils.isEmpty(key)) {
            getView().initCollectList(DaoCollect.getInstance().getAllCollect(mUserId));
            return;
        }
        getView().initCollectList(DaoCollect.getInstance().getCollectLikeKeyword(mUserId, key.trim()));
    }

    @Override
    public void onDeleteCollect(DtoCollect dtoCollect) {
        if (dtoCollect == null) {
            return;
        }
        RequestProxy.getInstance().onUnLike(dtoCollect.getId(), new ISendRequest.RequestListener() {
            @Override
            public void onSuccess(String data) {
                loadCollectList(mUserId, 0);
            }

            @Override
            public void onCodeError(int errorCode, String errorMessage) {

            }

            @Override
            public void onFailure(Throwable e, boolean isNetWorkError) {

            }

            @Override
            public void onRequestStart() {

            }

            @Override
            public void onRequestEnd() {

            }
        });
        DaoCollect.getInstance().delete(dtoCollect);
    }

    /**
     * 获取收藏列表回调
     */
    private ISendRequest.RequestListener mGetCollectListener = new ISendRequest.RequestListener() {
        @Override
        public void onSuccess(String data) {
            BeanRespCollect collect = JsonUtil.parseObject(data, BeanRespCollect.class);
            if (collect == null || collect.getDatas() == null || collect.getDatas().size() <= 0) {
                getView().showPrompt(getString(R.string.not_collect));
                return;
            }
            checkNetworkData(collect);
        }

        @Override
        public void onCodeError(int errorCode, String errorMessage) {
            getView().showPrompt(errorMessage);
        }

        @Override
        public void onFailure(Throwable e, boolean isNetWorkError) {
            if (isNetWorkError) {
                mLocalDtoCollects = DaoCollect.getInstance().getAllCollect(mUserId);
                if (mLocalDtoCollects.size() <= 0) {
                    getView().showPrompt(getString(R.string.networ_error));
                    return;
                }
                getView().initCollectList(mLocalDtoCollects);
            }
        }

        @Override
        public void onRequestStart() {

        }

        @Override
        public void onRequestEnd() {

        }
    };

    /**
     * 加载更多
     *
     * @param page 页码
     */
    private void loadMore(int page) {
        RequestProxy.getInstance().getCollect(page, new ISendRequest.RequestListener() {
            @Override
            public void onSuccess(String data) {
                BeanRespCollect collect = JsonUtil.parseObject(data, BeanRespCollect.class);
                if (collect == null || collect.getDatas() == null || collect.getDatas().size() <= 0) {
                    return;
                }
                checkNetworkData(collect);
            }

            @Override
            public void onCodeError(int errorCode, String errorMessage) {

            }

            @Override
            public void onFailure(Throwable e, boolean isNetWorkError) {

            }

            @Override
            public void onRequestStart() {

            }

            @Override
            public void onRequestEnd() {

            }
        });
    }


    /**
     * 数据类型转换
     *
     * @param collectData 需要转换的数据
     * @return 目标数据
     */
    private DtoCollect collectData2DtoCollect(BeanRespCollectData collectData) {
        DtoCollect dtoCollect = new DtoCollect();
        dtoCollect.setAuthor(collectData.getAuthor());
        dtoCollect.setChapterId(collectData.getChapterId());
        dtoCollect.setChapterName(collectData.getChapterName());
        dtoCollect.setCourseId(collectData.getCourseId());
        dtoCollect.setId(collectData.getId());
        dtoCollect.setLink(collectData.getLink());
        dtoCollect.setOriginId(collectData.getOriginId());
        dtoCollect.setTitle(collectData.getTitle());
        dtoCollect.setUserId(collectData.getUserId());
        return dtoCollect;
    }

    /**
     * 校验本地数据
     *
     * @param collects 网络数据
     */
    private void checkLocalData(List<DtoCollect> collects) {
        Message message = new Message();
        message.obj = collects;
        message.what = MESSAGE_CHECK_LOCAL_DATA;
        mHandler.sendMessage(message);
    }

    /**
     * 检测网络数据
     *
     * @param collect 网络数据
     */
    private void checkNetworkData(BeanRespCollect collect) {
        Message message = new Message();
        message.obj = collect;
        message.what = MESSAGE_CHECK_NETWORK_DATA;
        mHandler.sendMessage(message);
    }

    @Override
    public boolean handleMessage(Message message) {
        switch (message.what) {
            case MESSAGE_CHECK_LOCAL_DATA:
                if (mLocalDtoCollects.size() <= 0) {
                    mLocalDtoCollects = DaoCollect.getInstance().getAllCollect(mUserId);
                }
                List<DtoCollect> collects = (List<DtoCollect>) message.obj;
                if (mLocalDtoCollects.size() <= 0) {
                    mLocalDtoCollects = collects;
                    DaoCollect.getInstance().addCollect(mLocalDtoCollects);
                    break;
                }
                List<DtoCollect> deleteData = mLocalDtoCollects;
                deleteData.removeAll(collects);
                DaoCollect.getInstance().delete(deleteData);
                collects.removeAll(mLocalDtoCollects);
                DaoCollect.getInstance().addCollect(collects);
                break;
            case MESSAGE_CHECK_NETWORK_DATA:
                List<DtoCollect> networkCollects = new ArrayList<>();
                BeanRespCollect collect = (BeanRespCollect) message.obj;
                for (BeanRespCollectData collectData : collect.getDatas()) {
                    if (collectData == null) {
                        continue;
                    }
                    networkCollects.add(collectData2DtoCollect(collectData));
                }
                getView().initCollectList(networkCollects);
                if (mNetWorkDtoCollects == null) {
                    mNetWorkDtoCollects = new ArrayList<>();
                }
                mNetWorkDtoCollects.removeAll(networkCollects);
                mNetWorkDtoCollects.addAll(networkCollects);
                if (collect.getCurPage() < collect.getPageCount()) {
                    loadMore(collect.getCurPage() + 1);
                } else {
                    checkLocalData(mNetWorkDtoCollects);
                }
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public void onDestroyPresenter() {
        super.onDestroyPresenter();
        mHandler.removeMessages(MESSAGE_CHECK_LOCAL_DATA);
        mHandler.removeMessages(MESSAGE_CHECK_NETWORK_DATA);
        mHandlerThread.quitSafely();
    }

}
