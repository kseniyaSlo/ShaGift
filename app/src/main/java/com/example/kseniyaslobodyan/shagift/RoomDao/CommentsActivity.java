package com.example.kseniyaslobodyan.shagift.RoomDao;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kseniyaslobodyan.shagift.R;
import com.example.kseniyaslobodyan.shagift.activities.MainActivity;

import java.util.List;

public class CommentsActivity extends AppCompatActivity {
    private CommentsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        viewModel = ViewModelProviders.of(this).get(CommentsViewModel.class);

        RecyclerView lstStudents = findViewById(R.id.lstStudents);
        final CommentListAdapter adapter = new CommentListAdapter(this);
        lstStudents.setAdapter(adapter);
        lstStudents.setLayoutManager(new LinearLayoutManager(this));

        viewModel.getAllComments().observe(this, new Observer<List<Comment>>() {
            @Override
            public void onChanged(@Nullable final List<Comment> comments) {
                // Update the cached copy of the words in the adapter.
                adapter.setComments(comments);
                Log.d("111", "getAllStudents");
            }
        });

        FloatingActionButton btnAdd = (FloatingActionButton) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //    public Comment(String commentname, String commentdesc, String giftpostId) {
                        Comment student = new Comment("Nice gift", "very impressed", "aaaa");
                        Log.d("MyTag", "insert " + student.getId());
                        viewModel.add(student);
                    }
                }).start();
            }
        });

        FloatingActionButton btnbackmain = (FloatingActionButton) findViewById(R.id.btnbackmain);
        btnbackmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommentsActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }

    public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.CommentViewHolder> {

        class CommentViewHolder extends RecyclerView.ViewHolder {
            private final TextView text1;

            private CommentViewHolder(View itemView) {
                super(itemView);
                text1 = itemView.findViewById(android.R.id.text1);
            }
        }

        private final LayoutInflater mInflater;
        private List<Comment> comments;

        CommentListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

        @Override
        public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            return new CommentViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(CommentViewHolder holder, int position) {
            if (comments != null) {
                final Comment current = comments.get(position);
                holder.text1.setText(current.toString());
            }
        }

        void setComments(List<Comment> s){
            comments = s;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            if (comments != null)
                return comments.size();
            else return 0;
        }


    }
}

