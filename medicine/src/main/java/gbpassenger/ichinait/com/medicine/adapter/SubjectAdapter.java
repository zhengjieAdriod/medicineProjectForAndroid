package gbpassenger.ichinait.com.medicine.adapter;

//import com.squareup.picasso.Picasso;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import gbpassenger.ichinait.com.medicine.R;
import gbpassenger.ichinait.com.medicine.netbean.Detail;


/**
 * Created by Steven Tang on 2017/4/17.
 * <TextView
 * android:id="@+id/disease_type"
 * android:layout_width="wrap_content"
 * android:layout_height="30dp"
 * android:text="disease_type" />
 * <p>
 * <TextView
 * android:id="@+id/doctor_address"
 * android:layout_width="wrap_content"
 * android:layout_height="30dp"
 * android:paddingLeft="30dp"
 * android:text="doctor_address" />
 * <p>
 * <TextView
 * android:id="@+id/doctor_type"
 * android:layout_width="wrap_content"
 * android:layout_height="30dp"
 * android:paddingLeft="30dp"
 * android:text="doctor_type" />
 */

public class SubjectAdapter extends BaseQuickAdapter<Detail.SubjectBean, BaseViewHolder> {
    public SubjectAdapter(List<Detail.SubjectBean> list) {
        super(R.layout.subject_list_item, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, Detail.SubjectBean item) {
//        helper.addOnClickListener(R.id.duibi);
//        ImageView imageView = (ImageView) helper.getView(R.id.img);
//        Picasso.with(imageView.getContext())
//                .load(item.getPhotoUrl())
//                .placeholder(R.mipmap.pica)
//                .error(R.mipmap.pica)
//                .into(imageView);
        helper.setText(R.id.disease_type, item.getPk() + "--" + item.getDisease_type());

        helper.setText(R.id.doctor_address, item.getDoctor_address());
        helper.setText(R.id.doctor_type, item.getDoctor_type());


    }
}
