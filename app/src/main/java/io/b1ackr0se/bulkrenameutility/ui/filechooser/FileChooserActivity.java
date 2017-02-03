package io.b1ackr0se.bulkrenameutility.ui.filechooser;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.b1ackr0se.bulkrenameutility.R;
import io.b1ackr0se.bulkrenameutility.ui.base.BaseActivity;

public class FileChooserActivity extends BaseActivity implements FileCallback {
    @BindView(R.id.container) View container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_chooser);

        ButterKnife.bind(this);

        changeDirectory(null);

    }

    private void changeDirectory(String path) {
        Fragment fragment = FileChooserFragment.newInstance(path);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (path != null) {
            fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
            fragmentTransaction.addToBackStack(path);
        }
        fragmentTransaction.replace(R.id.container, fragment);

        fragmentTransaction.commit();
    }

    @Override
    public void onFileClick(String path) {
        changeDirectory(path);
    }
}
