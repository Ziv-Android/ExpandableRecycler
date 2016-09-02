package com.ziv.expandablerecycler;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ziv
 * Date: 15-8-28
 * Time: 下午7:18
 */
public class ExpandableAdapter extends RecyclerView.Adapter<ExpandableAdapter.ViewHolder> implements View.OnClickListener {

    private Context context;
    private List<Entity> list;
    private RecyclerView recyclerView;
    public GridLayoutManager.SpanSizeLookup lookup = new GridLayoutManager.SpanSizeLookup() {
        @Override
        public int getSpanSize(int position) {
            if (list.get(position).isGroup()){
                return 3;
            } else {
                return 1;
            }
        }
    };
    public ExpandableAdapter(Context context, List<Entity> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        LayoutInflater inflater = LayoutInflater.from(context);
        switch (viewType){
            case 0:
                view = inflater.inflate(R.layout.item_group, parent, false);
                break;
            case 1:
                view = inflater.inflate(R.layout.item_child, parent, false);
                break;
        }
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Entity entity = list.get(position);
        if (entity.isGroup()){
            holder.group.setText(entity.getText());
            ViewCompat.setRotation(holder.expand, entity.isExpand() ? 90 : 0);
        } else {
            holder.child.setText(entity.getText());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * 返回值在onCreateViewHolder中做为参数使用
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (list.get(position).isGroup()){
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Override
    public void onClick(View v) {
        int position = recyclerView.getChildAdapterPosition(v);
        Entity entity = list.get(position);
        if (entity.isGroup()){
            if (entity.isExpand()){
                list.removeAll(entity.getChildren());
                notifyItemRangeRemoved(position + 1, entity.getChildren().size());
            } else {
                list.addAll(position + 1, entity.getChildren());
                notifyItemRangeInserted(position + 1, entity.getChildren().size());
            }
            entity.setExpand(!entity.isExpand());
            notifyItemChanged(position);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView expand;
        private TextView group;
        private TextView child;

        public ViewHolder(View itemView) {
            super(itemView);
            expand = ((ImageView) itemView.findViewById(R.id.group_is_expand));
            group = ((TextView) itemView.findViewById(R.id.group_text));
            child = ((TextView) itemView.findViewById(R.id.child_text));
        }
    }
}
