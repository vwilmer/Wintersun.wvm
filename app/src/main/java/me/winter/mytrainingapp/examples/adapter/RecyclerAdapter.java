package me.winter.mytrainingapp.examples.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

import me.winter.mytrainingapp.R;
import me.winter.mytrainingapp.examples.holders.SubTitleViewHolder;
import me.winter.mytrainingapp.examples.holders.TitleViewHolder;
import me.winter.mytrainingapp.examples.somes.SubTitle;
import me.winter.mytrainingapp.examples.somes.Title;

public class RecyclerAdapter extends ExpandableRecyclerViewAdapter<TitleViewHolder, SubTitleViewHolder> {
    private Context context;

    private ItemClickChild mListener;


    public RecyclerAdapter(Context context, List<? extends ExpandableGroup> groups) {
        super(groups);
        this.context = context;

        mListener = (ItemClickChild) context;
    }

    @Override
    public TitleViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_title, parent, false);
        return new TitleViewHolder(view);
    }

    @Override
    public SubTitleViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_subtitle, parent, false);
        return new SubTitleViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(SubTitleViewHolder holder, int flatPosition, ExpandableGroup group, final int childIndex) {
        final SubTitle subTitle = ((Title) group).getItems().get(childIndex);
        holder.setSubTitletName(subTitle.getName());

        // begin here
        holder.setFechaEmision(subTitle.getFecha());
        holder.setDescripcion(subTitle.getDesc());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onChildClick(childIndex);
            }
        });
    }

    @Override
    public void onBindGroupViewHolder(TitleViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setGenreTitle(context, group);
    }

    public interface ItemClickChild{
        void onChildClick(int position);
    }
}
