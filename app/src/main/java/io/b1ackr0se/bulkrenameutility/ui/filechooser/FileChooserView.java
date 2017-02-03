package io.b1ackr0se.bulkrenameutility.ui.filechooser;


import java.util.List;

import io.b1ackr0se.bulkrenameutility.data.model.Item;
import io.b1ackr0se.bulkrenameutility.ui.base.BaseView;

public interface FileChooserView extends BaseView {

    void showListFiles(List<Item> items);

    void onItemClick(String nextPath);
}
