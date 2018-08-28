package com.air.basemvp.base;

/**
 * @desc 所有view 接口 都继承此接口
 * @author Hexl
 */
public interface IView<R> {
    void showSuccessData(R r);
}