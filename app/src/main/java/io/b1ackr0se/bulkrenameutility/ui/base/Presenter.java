package io.b1ackr0se.bulkrenameutility.ui.base;

public interface Presenter<V extends BaseView> {
    void attachView(V view);

    void detachView();
}
