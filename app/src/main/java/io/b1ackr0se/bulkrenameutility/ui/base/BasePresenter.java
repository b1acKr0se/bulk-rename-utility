package io.b1ackr0se.bulkrenameutility.ui.base;


public class BasePresenter<V extends BaseView> implements Presenter<V> {

    private V view;

    @Override
    public void attachView(V view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    public V getView() {
        return view;
    }

    public boolean isViewAttached() {
        return this.view != null;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new ViewNotAttachedException();
    }

    public static class ViewNotAttachedException extends RuntimeException {
        public ViewNotAttachedException() {
            super("Please call Presenter.attachView() before" +
                    " requesting data to the Presenter");
        }
    }
}
