package cn.edu.gdmec.android.boxuegu.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.bean.VideoBean;

/**
 * Created by student on 17/12/27.
 */

//列表
public class VideoListAdapter extends BaseAdapter{

    private Context mContext;
    private List<VideoBean> vbl;
    private int selectedPosition = -1;
    private OnSelectListener onSelectListener;
    public VideoListAdapter(Context context,OnSelectListener onSelectListener){
        this.mContext = context;
        this.onSelectListener = onSelectListener;
    }

    public void setSelectedPosition(int position){
        selectedPosition = position;
    }

    public void setData(List<VideoBean> vbl){
        this.vbl = vbl;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return vbl == null ? 0 : vbl.size();
    }

    @Override
    public VideoBean getItem(int i) {
        return vbl == null ? null : vbl.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder vh;
        if (view == null){
            vh = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(
                    R.layout.video_list_item,null
            );
            vh.tv_title = (TextView) view.findViewById(R.id.tv_video_title);
            vh.iv_icon = (ImageView) view.findViewById(R.id.iv_left_icon);
            view.setTag(vh);
        }else {
            vh = (ViewHolder) view.getTag();
        }
        final VideoBean bean = getItem(i);
        vh.iv_icon.setImageResource(R.drawable.course_bar_icon);
        vh.tv_title.setTextColor(Color.parseColor("#333333"));
        if (bean!=null){
            vh.tv_title.setText(bean.secondTitle);
            if (selectedPosition == i){
                vh.iv_icon.setImageResource(R.drawable.course_intro_icon);
                vh.tv_title.setTextColor(Color.parseColor("#009958"));

            }else {
                vh.iv_icon.setImageResource(R.drawable.course_bar_icon);
                vh.tv_title.setTextColor(Color.parseColor("#333333"));
            }
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bean == null)
                    return;
                onSelectListener.onSelect(i,vh.iv_icon);
            }
        });
        return view;
    }

    class ViewHolder{
        public TextView tv_title;
        public ImageView iv_icon;
    }

    public interface OnSelectListener{
        void onSelect(int position,ImageView iv);
    }
}