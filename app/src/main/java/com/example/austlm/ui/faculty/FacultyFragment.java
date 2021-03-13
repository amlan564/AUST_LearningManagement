package com.example.austlm.ui.faculty;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.austlm.MainActivity;
import com.example.austlm.Models.Student;
import com.example.austlm.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FacultyFragment extends Fragment {

    private RecyclerView cseDepartment, eeeDepartment, teDepartment, ceDepartment;
    private LinearLayout cseNoData, eeeNoData, teNoData, ceNoData, ceLinearLayout, eeeLinearLayout, cseLinearLayout, teLinearLayout;
    private List<TeacherData> list1, list2, list3, list4;
    private TeacherAdapter adapter;
    FirebaseAuth auth;
    String studentUserId;
    private DatabaseReference studentDatabaseReference, reference, dbRef;
    String studentDept;
    ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_faculty, container, false);
        progressBar =view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        cseDepartment = view.findViewById(R.id.cseDepartment);
        eeeDepartment = view.findViewById(R.id.eeeDepartment);
        teDepartment = view.findViewById(R.id.teDepartment);
        ceDepartment = view.findViewById(R.id.ceDepartment);

        cseNoData = view.findViewById(R.id.cseNoData);
        eeeNoData = view.findViewById(R.id.eeeNoData);
        teNoData = view.findViewById(R.id.teNoData);
        ceNoData = view.findViewById(R.id.ceNoData);

        ceLinearLayout = view.findViewById(R.id.ceLinearLayout);
        eeeLinearLayout = view.findViewById(R.id.eeeLinearLayout);
        cseLinearLayout = view.findViewById(R.id.cseLinearLayout);
        teLinearLayout = view.findViewById(R.id.teLinearLayout);


        reference = FirebaseDatabase.getInstance().getReference().child("teacher");
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        studentUserId = user.getUid();


        studentDatabaseReference = FirebaseDatabase.getInstance().getReference("Students/" + studentUserId);
        studentDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Student studentProfile = snapshot.getValue(Student.class);
                studentDept = studentProfile.getDept();
                Log.d("faculty", "dept: " + studentDept);
                showSpecificDepartment(studentDept);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });


        return view;

    }

    private void showSpecificDepartment(String studentDept) {
        if (studentDept != null) {
            switch (studentDept) {
                case "CSE":
                    cseLinearLayout.setVisibility(View.VISIBLE);
                    cseDepartment();
                    break;
                case "EEE":
                    eeeLinearLayout.setVisibility(View.VISIBLE);
                    eeeDepartment();
                    break;
                case "TE":
                    teLinearLayout.setVisibility(View.VISIBLE);
                    teDepartment();
                    break;
                case "CE":
                    ceLinearLayout.setVisibility(View.VISIBLE);
                    ceDepartment();
                    break;
            }
        }
    }

    private void cseDepartment() {
        dbRef = reference.child("CSE");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list1 = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    cseNoData.setVisibility(View.VISIBLE);
                    cseDepartment.setVisibility(View.GONE);
                } else {
                    cseNoData.setVisibility(View.GONE);
                    cseDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list1.add(data);
                    }
                    cseDepartment.setHasFixedSize(true);
                    cseDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list1, getContext());
                    cseDepartment.setAdapter(adapter);
                }
                progressBar.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void eeeDepartment() {
        dbRef = reference.child("EEE");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list2 = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    eeeNoData.setVisibility(View.VISIBLE);
                    eeeDepartment.setVisibility(View.GONE);
                } else {
                    eeeNoData.setVisibility(View.GONE);
                    eeeDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list2.add(data);
                    }
                    eeeDepartment.setHasFixedSize(true);
                    eeeDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list2, getContext());
                    eeeDepartment.setAdapter(adapter);
                }
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void teDepartment() {
        dbRef = reference.child("TE");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list3 = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    teNoData.setVisibility(View.VISIBLE);
                    teDepartment.setVisibility(View.GONE);
                } else {
                    teNoData.setVisibility(View.GONE);
                    teDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list3.add(data);
                    }
                    teDepartment.setHasFixedSize(true);
                    teDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list3, getContext());
                    teDepartment.setAdapter(adapter);
                }
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void ceDepartment() {
        dbRef = reference.child("CE");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list4 = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    ceNoData.setVisibility(View.VISIBLE);
                    ceDepartment.setVisibility(View.GONE);
                } else {
                    ceNoData.setVisibility(View.GONE);
                    ceDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list4.add(data);
                    }
                    ceDepartment.setHasFixedSize(true);
                    ceDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list4, getContext());
                    ceDepartment.setAdapter(adapter);
                }
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}