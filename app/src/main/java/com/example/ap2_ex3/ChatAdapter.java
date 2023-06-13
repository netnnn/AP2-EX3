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

public class ChatAdapter extends BaseAdapter {

    List<Chat> chats;

    private class ViewHolder {

        LinearLayout chatTile;
        TextView friendName;
        TextView lastMessage;
        TextView timeSent;
        ImageView profile;
    }

    public ChatAdapter(List<Chat> chats) {
        this.chats = chats;
    }



    @Override
    public int getCount() {
        return chats.size();
    }

    @Override
    public Object getItem(int position) {
        return chats.get(position);
    }

    @Override
    public long getItemId(int position) {
        return chats.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.chat_layout, parent, false);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.chatTile = convertView.findViewById(R.id.chatTile);
            viewHolder.friendName = convertView.findViewById(R.id.friendNameTv);
            viewHolder.lastMessage = convertView.findViewById(R.id.lastMessageTv);
            viewHolder.timeSent = convertView.findViewById(R.id.TimeSentTv);
            viewHolder.profile = convertView.findViewById(R.id.profilePictureIV);


            convertView.setTag(viewHolder);
        }

        Chat c = chats.get(position);
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.friendName.setText(c.getUserOne().getUsername());

        int lastPosition = c.getMsgList().size() - 1;
        Message lastMessage = c.getMsgList().get(lastPosition);
        viewHolder.lastMessage.setText(lastMessage.getContent());

//        viewHolder.profile.setImageResource(c.getUserOne().getPicture());

        Integer hours = lastMessage.getDate().getHours();
        Integer minutes = lastMessage.getDate().getMinutes();


        viewHolder.timeSent.setText(hours.toString() + ":" + minutes.toString());//check the toString

        viewHolder.chatTile.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Clicked on chat Tile", Toast.LENGTH_LONG).show();
        });

        return convertView;
    }

}
