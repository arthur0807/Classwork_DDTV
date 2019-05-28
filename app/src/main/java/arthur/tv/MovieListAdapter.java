package arthur.tv;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>{
    //实例化对象
    private static final String TAG = MovieListAdapter.class.getSimpleName();
    private List<movieDataList> mList;
    private Context context;
    private Listener listener;

    public MovieListAdapter(List<movieDataList> mList, Listener listener){
        this.listener = listener;
        this.mList = mList;
    }

    /**
     * 设置接口
     */
//    public interface OnItemClickListener {
//        void onItemClick(View view, int position);
//        void onItemLongClick(View view, int position);
//    }
    /**
     * 初始化事件回调监听
     */
//    private static OnItemClickListener itemClickListener;

    /**
     * 设置回调监听
     */
//    public static void setOnItemClickListener(OnItemClickListener onItemClickListener){
//         itemClickListener = onItemClickListener;
//    }

    /**
     * 创建构造函数，构造函数的参数是List的集合
     * 在实例化adapter中必须传入List的集合数据
     * @param movie
     */
//    public  void setDataList(List<String> movie){
//        Log.d(TAG,"setDataList:"+movie.size());//创建日志
//        mList = movie;
//        notifyDataSetChanged();//调用动态更新方法
//    }

    /**创建内部类,与电影行布局XML对应
     * 创建类ViewHolder（视图容器），承载的是item中的控件
     * 做item中控件的声明和绑定
     */
    public class MovieViewHolder extends RecyclerView.ViewHolder {
//        View.OnClickListener mItemClickListener;
        private TextView title;
        private TextView quality;
        private ImageView logo;
        private TextView introduction;
        private ImageView suoluetu;
        public MovieViewHolder(View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.Title);
            quality = itemView.findViewById(R.id.Quality);
            logo = itemView.findViewById(R.id.Image);
            introduction = itemView.findViewById(R.id.introduction);
            suoluetu = itemView.findViewById(R.id.suoluetu);
        }
        /**
         * 把传入的值显示在对应的控件上
         * @param
         */
        public void bind(Context context,movieDataList dataList){
            title.setText(dataList.getTitle());
            quality.setText(dataList.getQuality());
            Glide.with(context)
                    .load("http://1118cctv.com/upload/images/cctvlogo/cctv1.jpg")
                    .override(320,320)
                    .into(logo);

        }



    }

    /**
     * 创建一个具体的行，在这里指的是电影行对应的ViewHolder对象
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//     实例化展示的View,找到电影行中对应的xml，并将其反向解析出来（inflate）
        context = parent.getContext();
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_1,parent,false);
        final MovieViewHolder holder = new MovieViewHolder(row);
        row.setOnClickListener(v -> listener.onClick(v,holder.getLayoutPosition()));
//    返回实例化的MovieViewHolder
        return holder;
    }

    /**
     * 填充每一行的内容，
     * 给ViewHolder的控件设置数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(context,mList.get(position));
    }

    /**
     * 表示行数，根据JSON文件中的length显示几行
     * @return
     */
    @Override
    public int getItemCount() {
        return mList.size();
    }



}
