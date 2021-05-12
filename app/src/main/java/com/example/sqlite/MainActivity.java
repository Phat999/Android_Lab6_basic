package com.example.sqlite;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    DatabaseHandler databaseHandler;
    Button add_btn, remove_btn, cancel_btn;
    ListView name_list;
    ArrayList nameList, id_list;
    ArrayAdapter adapter;
    EditText nameEdit;
    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHandler = new DatabaseHandler(this);
        databaseHandler.addStudent(new Student("Phat"));
        databaseHandler.addStudent(new Student("Phat"));
        databaseHandler.addStudent(new Student("Phat"));
        databaseHandler.addStudent(new Student("Phat"));
        databaseHandler.addStudent(new Student("Phat"));
        databaseHandler.addStudent(new Student("Phat"));
        databaseHandler.addStudent(new Student("Phat"));
        databaseHandler.addStudent(new Student("Phat"));

        nameList = new ArrayList();
        id_list = new ArrayList();
        nameEdit = findViewById(R.id.nameEdit);
        add_btn = findViewById(R.id.add_btn);
        remove_btn = findViewById(R.id.remove_btn);
        cancel_btn = findViewById(R.id.cancel_btn);
        name_list = findViewById(R.id.name_list);

        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, nameList);
        name_list.setAdapter(adapter);

        nameList = getNameList();
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHandler.addStudent(new Student(nameEdit.getText().toString()));
                getNameList();
                adapter.notifyDataSetChanged();
            }
        });

        remove_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,
                //        id_list.get(index) + "here", Toast.LENGTH_SHORT).show();
                databaseHandler.deleteStudent((int) id_list.get(index));
                getNameList();
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this,
                        "Succesfull", Toast.LENGTH_SHORT).show();
            }
        });

        name_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = 1;
            }
        });
    }

    private ArrayList getNameList(){
        nameList.clear();
        id_list.clear();
        for (Iterator iterator = databaseHandler.getAllStudents().iterator(); iterator.hasNext();){
            Student student = (Student) iterator.next();
            nameList.add(student.getName());
            id_list.add(student.getId());
        }
        return nameList;
    }
}
