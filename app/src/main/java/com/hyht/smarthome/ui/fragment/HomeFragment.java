package com.hyht.smarthome.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyht.smarthome.MainActivity;
import com.hyht.smarthome.R;
import com.hyht.smarthome.base.BaseFragment;
import com.hyht.smarthome.ui.view.LineGridView;
import com.hyht.smarthome.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Longer on 2016/10/26.
 * home页面的Fragment
 */
public class HomeFragment extends BaseFragment<MainActivity> {

    private View mRootView;
    private LineGridView deviceGrid;
    private List<String> data;

    @Override
    protected void initTitleBar() {

    }

    @Override
    public View initView() {
        mRootView = View.inflate(mContext, R.layout.home_fragment_layout, null);
        deviceGrid = mRootView.findViewById(R.id.home_device_grid);

        return mRootView;
    }

    @Override
    public void initData() {
        data = new ArrayList<String>();
        for (int i = 0; i < 9; i++) {
            data.add("空调");
        }
        deviceGrid.setAdapter(new DeviceAdapter(mContext, data));
    }

    class DeviceAdapter extends BaseAdapter{

        private Context context;
        private List<String> data;
        public DeviceAdapter(Context context, List<String> data){
            this.context = context;
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int i) {
            return data.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            ViewHolder holder = null;
            if(view == null){
                view = LayoutInflater.from(context).inflate(R.layout.home_grid_item, null);
                holder = new ViewHolder();
                holder.deviceIv = view.findViewById(R.id.home_device_iv);
                holder.deviceTv = view.findViewById(R.id.home_device_tv);
                view.setTag(holder);
            }else {
                holder = (ViewHolder) view.getTag();
            }
            int width = UiUtils.getScreenWidthPixels((Activity) context) / 3;
            view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, width));
            holder.deviceTv.setText(data.get(position));
            return view;
        }

        class ViewHolder {
            ImageView deviceIv;
            TextView deviceTv;
        }
    }

}
