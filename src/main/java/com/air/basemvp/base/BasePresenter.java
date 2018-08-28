package com.air.basemvp.base;

import static com.air.basemvp.utils.android.Util.checkNullException;

/**
 * @desc 基类 Presenter
 * @author Hexl
 * @date
 */
public class BasePresenter<V extends IView, M extends IModel> implements IPresenter {
    protected M mModel;
    protected V mView;

    /**
     * @param view
     * @param model
     */
    public BasePresenter(V view, M model) {
        checkNullException(view, "检查view是否为null");
        checkNullException(model, "检查model是否为null");
        mView = view;
        mModel = model;
        onStart();
    }

    /**
     * 只为 MVP 模式 但不请求网络数据,所以不用传入M层
     *
     * @param view
     */
    public BasePresenter(V view) {
        checkNullException(view, "检查view引用是否为null");
        mView = view;
        onStart();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onDestroy() {
        if (this.mView != null) {
            this.mView = null;
        }
        if (this.mModel != null) {
            this.mModel.onDestroy();
            this.mModel = null;
        }
    }
}