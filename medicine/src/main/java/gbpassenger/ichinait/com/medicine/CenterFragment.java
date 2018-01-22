package gbpassenger.ichinait.com.medicine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by DawnOct on 2018/1/22.
 */

public class CenterFragment extends BaseFragment implements View.OnClickListener {
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.center_layout, container, false);
        initView();
        return view;
    }

    private void initView() {
        view.setOnClickListener(this);
    }

    @Override
    public void fetchData() {

    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getContext(), "点击了", Toast.LENGTH_SHORT).show();
    }
}
