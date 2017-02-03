package io.b1ackr0se.bulkrenameutility.ui.filechooser;


import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.b1ackr0se.bulkrenameutility.data.model.Item;
import io.b1ackr0se.bulkrenameutility.ui.base.BasePresenter;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

public class FileChooserPresenter extends BasePresenter<FileChooserView> implements FileAdapter.OnItemClickListener {

    private File path;

    public void setPath(String currentPath) {
        if (currentPath == null) {
            path = Environment.getExternalStorageDirectory();
        } else {
            path = new File(currentPath);
        }
    }

    public void load() {
        Observable.defer(new Func0<Observable<List<Item>>>() {
            @Override
            public Observable<List<Item>> call() {
                return Observable.just(loadFileList());
            }
        }).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<List<Item>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(List<Item> items) {
                getView().showListFiles(items);
            }
        });
    }

    public List<Item> loadFileList() {
        try {
            path.mkdirs();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        List<Item> list = new ArrayList<>();
        File[] listFiles = path.listFiles();
        if (listFiles == null)
            return list;
        for (int i = 0; i < listFiles.length; i++) {
            File sel = listFiles[i];
            list.add(i, new Item(sel.getName(), sel.isDirectory(), sel.canRead()));
        }
        if (list.size() == 0) {
        } else {// sort non empty list
            Collections.sort(list, new ItemFileNameComparator());
        }
        return list;
    }

    @Override
    public void onItemClicked(Item item) {
        getView().onItemClick(path + "/" + item.name);
    }
}
