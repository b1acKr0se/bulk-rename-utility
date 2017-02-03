package io.b1ackr0se.bulkrenameutility.ui.filechooser;


import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.b1ackr0se.bulkrenameutility.R;
import io.b1ackr0se.bulkrenameutility.data.model.Item;

public class FileChooserFragment extends Fragment implements FileChooserView {
    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    private List<Item> list = new ArrayList<>();
    private FileAdapter fileAdapter;

    private FileCallback callback;
    private FileChooserPresenter presenter;

    public FileChooserFragment() {
        // Required empty public constructor
    }

    public static FileChooserFragment newInstance(String path) {
        FileChooserFragment fragment = new FileChooserFragment();
        Bundle bundle = new Bundle();
        bundle.putString("path", path);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {
            attachCallback((Activity) context);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            attachCallback(activity);
        }
    }

    private void attachCallback(Activity activity) {
        try {
            callback = (FileCallback) activity;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_file_chooser, container, false);
        ButterKnife.bind(this, view);

        Bundle bundle = getArguments();

        String pathAddress = bundle.getString("path");

        presenter = new FileChooserPresenter();
        presenter.attachView(this);
        presenter.setPath(pathAddress);

        fileAdapter = new FileAdapter(list);
        fileAdapter.setOnItemClickListener(presenter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setAdapter(fileAdapter);

        presenter.load();

        return view;
    }

    @Override
    public void showListFiles(List<Item> items) {
        list.clear();
        list.addAll(items);
        fileAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(String nextPath) {
        if (callback != null) {
            callback.onFileClick(nextPath);
        }
    }
}
