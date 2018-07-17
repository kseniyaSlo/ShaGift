package com.example.kseniyaslobodyan.shagift.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
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

import com.example.kseniyaslobodyan.shagift.ViewModel.StudentsViewModel2;
import com.example.kseniyaslobodyan.shagift.model.Post;
import com.example.kseniyaslobodyan.shagift.R;

import java.util.List;


public class GiftListActivityDao extends AppCompatActivity {

    private StudentsViewModel2 viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_list_dao);

        viewModel = ViewModelProviders.of(this).get(StudentsViewModel2.class);

        RecyclerView lstStudents = findViewById(R.id.lstStudents);
        final StudentsListAdapter adapter = new StudentsListAdapter(this);
        lstStudents.setAdapter(adapter);
        lstStudents.setLayoutManager(new LinearLayoutManager(this));

        viewModel.getAllStudents().observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(@Nullable final List<Post> students) {
                // Update the cached copy of the words in the adapter.
                adapter.setStudents(students);
                Log.d("111", "getAllStudents");
            }
        });

     /*   FloatingActionButton btnAdd = (FloatingActionButton) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //    public Post(int id, String authorId, String authorName,  String namePost,String postDescription, String image) {
                        Post student = new Post(1,"123", "userID", "name","desc","aaa");
                        Log.d("MyTag", "insert " + student.getId());
                        viewModel.add(student);
                    }
                }).start();
            }
        });*/

    }

    public class StudentsListAdapter extends RecyclerView.Adapter<StudentsListAdapter.StudentViewHolder> {

        class StudentViewHolder extends RecyclerView.ViewHolder {
            private final TextView text1;

            private StudentViewHolder(View itemView) {
                super(itemView);
                text1 = itemView.findViewById(android.R.id.text1);
            }
        }

        private final LayoutInflater mInflater;
        private List<Post> students;

        StudentsListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

        @Override
        public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            return new StudentViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(StudentViewHolder holder, int position) {
            if (students != null) {
                final Post current = students.get(position);
                holder.text1.setText(current.toString());
            }
        }

        void setStudents(List<Post> s){
            students = s;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            if (students != null)
                return students.size();
            else return 0;
        }


    }
}
