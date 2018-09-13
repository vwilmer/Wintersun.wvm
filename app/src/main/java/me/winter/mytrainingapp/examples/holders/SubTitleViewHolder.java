package me.winter.mytrainingapp.examples.holders;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import me.winter.mytrainingapp.R;

public class SubTitleViewHolder extends ChildViewHolder {
    private TextView subTitleTextView, fechaEmision, descripcion;

    public SubTitleViewHolder(View itemView) {
        super(itemView);
        subTitleTextView = itemView.findViewById(R.id.tv_subtitle_ley);
        // begin here
        fechaEmision = itemView.findViewById(R.id.tv_fecha_de_emision);
        descripcion = itemView.findViewById(R.id.tv_descripcion_ley);
    }

    public void setSubTitletName(String name) {
        subTitleTextView.setText(name);
    }

    // begin here

    public void setFechaEmision(String fecha) {
        fechaEmision.setText(fecha);
    }

    public void setDescripcion(String desc) {
        descripcion.setText(desc);
    }

}
