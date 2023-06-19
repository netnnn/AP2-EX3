package com.example.ap2_ex3;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MessageAdapter extends BaseAdapter {

    List<Message> messages;

    private class ViewHolder {

        LinearLayout messageTile;
        TextView content;
        TextView timeSent;
    }

    public MessageAdapter(List<Message> messages) {
        this.messages = messages;
    }



    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return messages.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.message_layout, parent, false);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.messageTile = convertView.findViewById(R.id.messageTile);
            viewHolder.content = convertView.findViewById(R.id.contentTv);
            viewHolder.timeSent = convertView.findViewById(R.id.TimeSentTv);


            convertView.setTag(viewHolder);
        }

        Message m = messages.get(position);
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.content.setText(m.getContent());

        Integer hours = m.getDate().getHours();
        Integer minutes = m.getDate().getMinutes();

        viewHolder.timeSent.setText(hours.toString() + ":" + minutes.toString());//check the toString

        return convertView;
    }

}
