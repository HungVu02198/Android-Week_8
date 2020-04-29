package com.example.gmailmodule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class EmailAdapter extends BaseAdapter {
    List<UserEmail> listEmail;

    public EmailAdapter(List<UserEmail> listEmail) {
        this.listEmail = listEmail;
    }

    class ViewContent {
        TextView Letter;
        TextView Name;
        TextView Subject;
        TextView Content;
        TextView Time;

        public ViewContent(){

        }

        public ViewContent(TextView letter, TextView name, TextView subject, TextView content, TextView time){
            this.Letter =letter;
            this.Name = name;
            this.Subject = subject;
            this.Content = content;
            this.Time = time;
        }
    }

    @Override
    public int getCount() {
        return listEmail.size();
    }

    @Override
    public Object getItem(int position) {
        return listEmail.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewContent viewContent = new ViewContent();
        if(convertView == null) {
            convertView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.email_layout, viewGroup, false);
            viewContent.Name = convertView.findViewById(R.id.name);
            viewContent.Letter = convertView.findViewById(R.id.letter);
            viewContent.Subject = convertView.findViewById(R.id.subject);
            viewContent.Content = convertView.findViewById(R.id.content);
            viewContent.Time = convertView.findViewById(R.id.time);
            convertView.setTag(viewContent);
        }
        else viewContent =(ViewContent)convertView.getTag();

        final TextView textLetter = convertView.findViewById(R.id.letter);
        final ImageView imageFavorite = convertView.findViewById(R.id.image_favorite);
        final UserEmail email = listEmail.get(i);

        viewContent.Name.setText(email.getUserName());
        viewContent.Subject.setText(email.getEmailSubject());
        viewContent.Content.setText(email.getEmailContent());
        viewContent.Time.setText("11:59 PM");
        viewContent.Letter.setText(email.getEmailDescription());


        imageFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isFavorite = email.isFavourite();
                email.setFavourite(!isFavorite);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

}
