package com.example.karkapazhagu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Internships extends AppCompatActivity {
   
    private RecyclerView recyclerView;

   
    private RecyclerView.Adapter adapter;

  
    private DatabaseReference mDatabase;

   
    private ProgressDialog progressDialog;

  
    private List<upload_intern> uploads1;
    public class Constantss
    {

        public  static final String STORAGE_PATH_UPLOADS ="internship/";
        public static final String DATABASE_PATH_UPLOADS ="internship";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_activities);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        progressDialog = new ProgressDialog(this);

        uploads1 = new ArrayList<>();

        
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        mDatabase = FirebaseDatabase.getInstance().getReference(Constantss.DATABASE_PATH_UPLOADS);

       
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                
                progressDialog.dismiss();

               
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    upload_intern upload = postSnapshot.getValue(upload_intern.class);
                    uploads1.add(upload);
                }
               
                adapter = new My_intern(getApplicationContext(), uploads1);

              
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), upload_internship.class));
            }
        });

    }

}
