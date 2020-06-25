package com.example.myapplication.ui.menubuy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.addfunc.AddFunction;
import com.example.myapplication.data.orderData.Order;
import com.example.myapplication.data.orderData.OrderList;

import java.util.ArrayList;


/**
 * {@link MenuBuyFragment}의 카테고리 바 프래그먼트
 * <p>
 * 인스턴스 변수:
 * {@link #mData2}, {@link #view}, {@link #vh}, {@link #parentViewGroup}
 * </p>
 * <p>
 * 메소드:
 * {@link #onCreateViewHolder(ViewGroup, int)} , {@link #onBindViewHolder(ViewHolder, int)}, {@link ViewHolder}
 * </p>
 */
public class RecyclerImageTextAdapter extends RecyclerView.Adapter<RecyclerImageTextAdapter.ViewHolder> implements AddFunction {
    /**
     * 보여주고자 하는 데이터
     */
    private OrderList mData2 = null;
    /**
     * 현재 뷰
     */
    private View view;
    /**
     * 뷰홀더 클래스
     */
    private RecyclerImageTextAdapter.ViewHolder vh;
    /**
     * 현재 뷰 그룹
     */
    private ViewGroup parentViewGroup;

    /**
     * 생성자로 데이터 입력받음
     * @param list 보여주고자 하는 데이터
     */
    public RecyclerImageTextAdapter(OrderList list) {
        mData2 = list ;
    }

    /**
     * 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
     */
    @Override
    public RecyclerImageTextAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        view = inflater.inflate(R.layout.buy_recycler_item, parent, false) ;
        vh = new RecyclerImageTextAdapter.ViewHolder(view) ;
        parentViewGroup = parent;

        return vh ;
    }

    /**
     * 뷰홀더와 데이터 연결
     * @param holder 뷰홀더
     * @param position 현재 데이터 인덱스
     */
    @Override
    public void onBindViewHolder(RecyclerImageTextAdapter.ViewHolder holder, int position) {
        Order item = mData2.getOrderList().get(position);

        String url = item.getMenu().getUrl();

        url = colorBlind.changeURL(url);

        Glide.with(holder.itemView.getContext())
                .load(url)
                .placeholder(R.drawable.ic_loading)
                .into(holder.menuImg);
        holder.menuName.setText(item.getMenu().getName());
        holder.menuQuantity.setText(String.valueOf(item.getQuantity()));
        holder.menuPrice.setText(String.valueOf(item.getMenu().getPrice())+"원");
    }

    /**
     * 총 데이터 개수 리턴
     * @return 총 데이터 개수
     */
    @Override
    public int getItemCount() {
        return mData2.getOrderList().size() ;
    }

    /**
     * 아이템 뷰를 저장하는 뷰홀더 클래스.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView menuImg ;
        TextView menuName ;
        TextView menuQuantity ;
        TextView menuPrice ;
        Button minus;
        Button plus;

        /**
         * 생성자로 뷰에 대한 정보 반환 및 이벤트 설정
         * @param itemView
         */
        ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조. (hold strong reference)
            menuImg = itemView.findViewById(R.id.img_recyclerBuy_menu) ;
            menuName = itemView.findViewById(R.id.text_recyclerNuy_menuName) ;
            menuQuantity = itemView.findViewById(R.id.text_fragMenuBuy_quantity);
            menuPrice = itemView.findViewById(R.id.text_recyclerBuy_menuPrice);
            minus = itemView.findViewById(R.id.btn_recyclerBuy_minus);
            plus = itemView.findViewById(R.id.btn_recyclerBuy_plus);

            //수량 빼기 버튼 이벤트
            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition() ;
                    int afterQuantity = Integer.parseInt((String) menuQuantity.getText())-1;
                    int totalPrice=0;

                    if(afterQuantity < 0)
                        afterQuantity = 0;

                    menuQuantity.setText(String.valueOf(afterQuantity));
                    mData2.getOrderList().get(pos).setQuantity(afterQuantity);

                    TextView tv = ((LinearLayout)parentViewGroup.getParent()).findViewById(R.id.text_fragMenuBuy_totalPrice);

                    for(Order order : mData2.getOrderList())
                        totalPrice += order.getQuantity() * order.getMenu().getPrice();

                    tv.setText(String.valueOf(totalPrice));
                }
            });

            //수량 더하기 버튼 이벤트
            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition() ;
                    int afterQuantity = Integer.parseInt((String) menuQuantity.getText())+1;
                    int totalPrice=0;

                    menuQuantity.setText(String.valueOf(afterQuantity));
                    mData2.getOrderList().get(pos).setQuantity(afterQuantity);

                    TextView tv = ((LinearLayout)parentViewGroup.getParent()).findViewById(R.id.text_fragMenuBuy_totalPrice);

                    for(Order order : mData2.getOrderList())
                        totalPrice += order.getQuantity() * order.getMenu().getPrice();

                    tv.setText(String.valueOf(totalPrice));
                }
            });
        }
    }
}