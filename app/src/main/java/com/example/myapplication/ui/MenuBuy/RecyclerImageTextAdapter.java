package com.example.myapplication.ui.MenuBuy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.data.orderMenuData.OrderMenu;
import com.example.myapplication.data.orderMenuData.OrderMenuList;
import com.example.myapplication.ui.bottomBar.InitBottomBar;

import java.util.ArrayList;

import static com.example.myapplication.ui.InitActivity.getFunctionState;

/**
 * <p>
 * 참고
 * https://chebaum.tistory.com/10
 * 리사이클러뷰에 구분선 넣기
 * https://itpangpang.xyz/243
 * Glide 사용법
 * https://black-jin0427.tistory.com/100
 * 리사이클러뷰와 glide
 * </p>
 * {@link MenuBuyFragment}의 리싸이클러뷰 어댑터, 자세한 설명 생략
 */
public class RecyclerImageTextAdapter extends RecyclerView.Adapter<RecyclerImageTextAdapter.ViewHolder> {
    private ArrayList<RecyclerItem> mData = null ;
    private OrderMenuList mData2 = null;
    private View view;
    private RecyclerImageTextAdapter.ViewHolder vh;

    // 생성자에서 데이터 리스트 객체를 전달받음.
    public RecyclerImageTextAdapter(ArrayList<RecyclerItem> list) {
        mData = list ;
    }
    public RecyclerImageTextAdapter(OrderMenuList list) {
        mData2 = list ;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    @Override
    public RecyclerImageTextAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        view = inflater.inflate(R.layout.buy_recycler_item, parent, false) ;
        vh = new RecyclerImageTextAdapter.ViewHolder(view) ;

        return vh ;
    }

    @Override
    public void onBindViewHolder(RecyclerImageTextAdapter.ViewHolder holder, int position) {
        OrderMenu item = mData2.getOrderMenuList().get(position);

        String url = item.getMenu().getUrl();

        if((getFunctionState() & InitBottomBar.COLORBLIND) != 0)
            url = url.replace("original", "colorblind");


        Glide.with(holder.itemView.getContext())
                .load(url)
                .into(holder.menuImg);
        holder.menuName.setText(item.getMenu().getName());
        holder.menuQuantity.setText(String.valueOf(item.getQuantity()));
        holder.menuPrice.setText(String.valueOf(item.getMenu().getPrice())+"원");
    }

    @Override
    public int getItemCount() {
        return mData2.getOrderMenuList().size() ;
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView menuImg ;
        TextView menuName ;
        TextView menuQuantity ;
        TextView menuPrice ;
        Button minus;
        Button plus;

        ViewHolder(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조. (hold strong reference)
            menuImg = itemView.findViewById(R.id.img_recyclerBuy_menu) ;
            menuName = itemView.findViewById(R.id.text_recyclerNuy_menuName) ;
            menuQuantity = itemView.findViewById(R.id.text_fragMenuBuy_quantity);
            menuPrice = itemView.findViewById(R.id.text_recyclerBuy_menuPrice);
            minus = itemView.findViewById(R.id.btn_recyclerBuy_minus);
            plus = itemView.findViewById(R.id.btn_recyclerBuy_plus);

            ////////////////////////////////////////////////////////////////////
            //https://recipes4dev.tistory.com/168
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition() ;
                    if (pos != RecyclerView.NO_POSITION) {
                        // 리스너 객체의 메서드 호출.
                        if (mListener != null) {
                            mListener.onItemClick(v, pos) ;
                        }
                    }
                }
            });


            //////////////////////////////////////////////////////////////
            //수량 조절 버튼 이벤트
            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition() ;
                    int afterQuantity = Integer.parseInt((String) menuQuantity.getText())-1;

                    if(afterQuantity < 0)
                        afterQuantity = 0;

                    menuQuantity.setText(String.valueOf(afterQuantity));
                    mData2.getOrderMenuList().get(pos).setQuantity(afterQuantity);
                }
            });

            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition() ;
                    int afterQuantity = Integer.parseInt((String) menuQuantity.getText())+1;
                    menuQuantity.setText(String.valueOf(afterQuantity));
                    mData2.getOrderMenuList().get(pos).setQuantity(afterQuantity);
                }
            });
            ////////////////////////////////////////////////////////////////
        }
    }

    //////////////////////////////////////////////////////
    //https://recipes4dev.tistory.com/168
    public interface OnItemClickListener {
        void onItemClick(View v, int position) ;
    }

    // 리스너 객체 참조를 저장하는 변수
    private OnItemClickListener mListener = null ;

    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener ;
    }

    ///////////////////////////////////////////////////////
}