package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements ContactAdapter.ItemClickListener {
    private ContactAdapter adapter;
    private int editingPosition=-1;
    private EditText fullNameEt;
    private ImageView addNewContactBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set adapter to recycler view
        final RecyclerView recyclerView=findViewById(R.id.rv_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        adapter=new ContactAdapter(this);
        recyclerView.setAdapter(adapter);

        //set action on app(add, edit & ...)
        fullNameEt = findViewById(R.id.et_main_contactFullname);
        addNewContactBtn = findViewById(R.id.iv_main_addNewContact);
        addNewContactBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(fullNameEt.length()>0){
                    if (editingPosition>-1) {
                        adapter.updateContact(fullNameEt.getText().toString(),editingPosition);
                        editingPosition =-1;
                        addNewContactBtn.setImageResource(R.drawable.ic_add_white_24dp);
                    } else{
                        adapter.addNewContact(fullNameEt.getText().toString());
                        //smoothScrollToPosition is with animation and scrollToPosition is without animation
                        recyclerView.scrollToPosition(0);
                   }
                    fullNameEt.setText("");
                }
            }
        });
    }

    @Override
    public void onClick(String fullname, int position) {

        editingPosition = position;
        fullNameEt.setText(fullname);
        addNewContactBtn.setImageResource(R.drawable.ic_done_white_24dp);

    }
}
