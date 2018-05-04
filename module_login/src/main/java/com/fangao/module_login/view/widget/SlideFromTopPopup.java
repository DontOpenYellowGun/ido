package com.fangao.module_login.view.widget;

/**
 * Created by 大灯泡 on 2016/12/6.
 * <p>
 * 从顶部下滑的Poup
 */

public class SlideFromTopPopup/* extends BasePopupWindow*/ {

//    private final List<User> mDatas;
//
//    private final InnerPopupAdapter adapter;
//
//    public SlideFromTopPopup(Activity context, List<User> users) {
//        super(context);
//        mDatas = users;
//        ListView listView = (ListView) findViewById(R.id.popup_list);
//        adapter = new InnerPopupAdapter(context, mDatas);
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (mOnItemClickListener != null) {
//                    mOnItemClickListener.onClick(view, position);
//                }
//            }
//        });
//    }
//
//    public InnerPopupAdapter getAdapter() {
//        return adapter;
//    }
//
//    @Override
//    protected Animation initShowAnimation() {
//        TranslateAnimation translateAnimation = new TranslateAnimation(0f, 0f, -DensityUtil.dp2px(350f), 0);
//        translateAnimation.setDuration(450);
//        translateAnimation.setInterpolator(new OvershootInterpolator(1));
//        return translateAnimation;
//    }
//
//    @Override
//    protected Animation initExitAnimation() {
//        TranslateAnimation translateAnimation = new TranslateAnimation(0f, 0f, 0, -DensityUtil.dp2px(350f));
//        translateAnimation.setDuration(450);
//        translateAnimation.setInterpolator(new OvershootInterpolator(-4));
//        return translateAnimation;
//    }
//
//    @Override
//    public View getClickToDismissView() {
//        return getPopupWindowView();
//    }
//
//    @Override
//    public View onCreatePopupView() {
//        return createPopupById(R.layout.popup_select_from_top);
//    }
//
//    @Override
//    public View initAnimaView() {
//        return findViewById(R.id.popup_anima);
//    }
//
//    private onItemClickListener mOnItemClickListener;
//
//    public interface onItemClickListener {
//        void onClick(View view, int position);
//    }
//
//    public onItemClickListener getmOnItemClickListener() {
//        return mOnItemClickListener;
//    }
//
//    public void setmOnItemClickListener(onItemClickListener mOnItemClickListener) {
//        this.mOnItemClickListener = mOnItemClickListener;
//    }
//
//
//    private OnChildClickListener onChildClickListener;
//
//    public interface OnChildClickListener {
//        void onClick(View view, int position);
//    }
//
//    public OnChildClickListener getOnChildClickListener() {
//        return onChildClickListener;
//    }
//
//    public void setOnChildClickListener(OnChildClickListener onChildClickListener) {
//        this.onChildClickListener = onChildClickListener;
//    }
//
//    //=============================================================adapter
//    private class InnerPopupAdapter extends BaseAdapter {
//        private LayoutInflater mInflater;
//        private Context mContext;
//        private List<User> mItemList;
//
//        InnerPopupAdapter(Context context, @NonNull List<User> itemList) {
//            mContext = context;
//            mItemList = itemList;
//            mInflater = LayoutInflater.from(context);
//        }
//
//        @Override
//        public int getCount() {
//            return mItemList.size();
//        }
//
//        @Override
//        public User getItem(int position) {
//            return mItemList.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(final int position, View convertView, ViewGroup parent) {
//            InnerPopupAdapter.ViewHolder vh;
//            if (convertView == null) {
//                vh = new InnerPopupAdapter.ViewHolder();
//                convertView = mInflater.inflate(R.layout.login_item_contact_list_content, parent, false);
//                vh.mTextView = convertView.findViewById(R.id.name);
//                vh.mLeftImageView = convertView.findViewById(R.id.icon);
//                vh.mRightImageView = convertView.findViewById(R.id.right_icon);
//                convertView.setTag(vh);
//            } else {
//                vh = (InnerPopupAdapter.ViewHolder) convertView.getTag();
//            }
//            final User item = getItem(position);
//            vh.mTextView.setText(item.getLoginName());
//            Glide.with(mContext)
//                    .load(item.getHeadUrl())
//                    .bitmapTransform(new GlideCircleTransform(mContext))
//                    .into(vh.mLeftImageView);
//            vh.mRightImageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (onChildClickListener != null) {
//                        onChildClickListener.onClick(view, position);
//                    }
//                }
//            });
//            return convertView;
//        }
//
//        class ViewHolder {
//            TextView mTextView;
//            ImageView mLeftImageView;
//            ImageView mRightImageView;
//        }
//    }
}
