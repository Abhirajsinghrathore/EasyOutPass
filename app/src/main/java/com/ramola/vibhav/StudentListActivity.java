package com.ramola.vibhav;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.ramola.vibhav.Model.Student;
import com.ramola.vibhav.Model.StudentListResponse;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private StudentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        recyclerView = (RecyclerView) findViewById(R.id.list);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        adapter = new StudentAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        getDetail();



    }

    private void getDetail(){
        Call<StudentListResponse> call = Util.getRetrofitService().getAllStudentDetail();
        call.enqueue(new Callback<StudentListResponse>() {
            @Override
            public void onResponse(Call<StudentListResponse> call, Response<StudentListResponse> response) {
                progressBar.setVisibility(View.GONE);
                StudentListResponse r=response.body();
                if(r!=null&&response.isSuccess()){
                    ArrayList<Student> list = response.body().getGetAllStudent();
                    adapter.refresh(list);
                }
            }

            @Override
            public void onFailure(Call<StudentListResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private int getMidnightTime(){
        int midTime =0;

        return midTime;
    }
}
