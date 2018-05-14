package com.ljedesign.landonhall.finalproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lhall4 on 5/11/2018.
 */

public class PostAdapter extends ArrayAdapter {
    List list = new ArrayList();
    public PostAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public void add(Posts object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row;
        row = convertView;
        PostHolder postHolder;

        if(row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.fb_row_layout, parent, false);
            postHolder = new PostHolder();
            postHolder.postDate = row.findViewById(R.id.postDate);
            postHolder.postContent = row.findViewById(R.id.postText);
            row.setTag(postHolder);
        }

        else {
            postHolder = (PostHolder) row.getTag();
        }

        Posts posts = (Posts) this.getItem(position);
        postHolder.postContent.setText(posts.getPostContent());
        postHolder.postDate.setText(posts.getPostDate());

        return row;
    }

    static class PostHolder {
        TextView  postDate, postContent;

    }
}
