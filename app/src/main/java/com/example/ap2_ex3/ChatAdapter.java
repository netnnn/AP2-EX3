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

import androidx.core.content.ContextCompat;

import java.util.List;

public class ChatAdapter extends BaseAdapter {

    List<Chat> chats;
    User currentUser;

    private class ViewHolder {

        LinearLayout chatTile;
        TextView friendName;
        TextView lastMessage;
        TextView timeSent;
        ImageView profile;
    }

    public ChatAdapter(List<Chat> chats, User currentUser) {
        this.chats = chats;
        this.currentUser = currentUser;
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
        User friend = c.getUserOne().getUsername().equals(currentUser.getUsername())
                ? c.getUserTwo() : c.getUserOne();
        viewHolder.friendName.setText(friend.getDisplayName());

        int lastPosition = c.getMsgList().size() - 1;
        if (c.getMsgList().size() != 0) {
            Message lastMessage = c.getMsgList().get(lastPosition);
            viewHolder.lastMessage.setText(lastMessage.getContent());

            Integer hours = lastMessage.getDate().getHours();
            Integer minutes = lastMessage.getDate().getMinutes();
            viewHolder.timeSent.setText(hours + ":" + minutes);//check the toString
        }else{
            viewHolder.lastMessage.setText("");
            viewHolder.timeSent.setText("");//check the toString
        }
        viewHolder.profile.setImageResource(friend.getPicture());


        viewHolder.chatTile.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ChatActivity.class);
            intent.putExtra("name", currentUser.getUsername());
            intent.putExtra("friendname", viewHolder.friendName.getText());
            intent.putExtra("position", position);
            v.getContext().startActivity(intent);
        });

        return convertView;
    }

}
