package com.example.ap2_ex3;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class MessageAdapter extends BaseAdapter {

    List<Message> messages;
    String myUsername;

    private class ViewHolder {

        RelativeLayout messageTile;
        TextView content;
        TextView timeSent;
    }

    public MessageAdapter(List<Message> messages, String username) {
        this.messages = messages;
        this.myUsername = username;
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


        if (messages.get(position).getSender().getUsername().equals(myUsername)) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.message_layout_mine, parent, false);
        } else {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.message_layout_yours, parent, false);
        }


        ViewHolder viewHolder = new ViewHolder();
        viewHolder.messageTile = convertView.findViewById(R.id.messageTile);
        viewHolder.content = convertView.findViewById(R.id.contentTv);
        viewHolder.timeSent = convertView.findViewById(R.id.TimeSentTv);


        convertView.setTag(viewHolder);


        Message m = messages.get(position);
        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.content.setText(m.getContent());

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        viewHolder.timeSent.setText(sdf.format(new Date()));//check the toString

        return convertView;
    }

}
