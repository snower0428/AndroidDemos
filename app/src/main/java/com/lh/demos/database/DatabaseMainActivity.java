package com.lh.demos.database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lh.demos.R;

import org.litepal.LitePal;

import java.util.List;

public class DatabaseMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_main);

        initView();
    }

    private void initView() {
        Button btnCreateDatabase = findViewById(R.id.create_database);
        btnCreateDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.getDatabase();
            }
        });

        Button btnAddData = findViewById(R.id.add_data);
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("The Da Vinci Code");
                book.setAuthor("Dan Brown");
                book.setPages(454);
                book.setPrice(16.96);
                book.setPress("Unknow");
                book.save();

                Book book2 = new Book();
                book2.setName("The Lost Temple");
                book2.setAuthor("Scope");
                book2.setPages(232);
                book2.setPrice(14.99);
                book2.setPress("Unknow");
                book2.save();
            }
        });

        Button btnUpdateData = findViewById(R.id.update_data);
        btnUpdateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setPrice(20.99);
                book.setPress("Anchor");
                book.updateAll("name = ? and author = ?", "The Da Vinci Code", "Dan Brown");
            }
        });

        Button btnDeleteData = findViewById(R.id.delete_data);
        btnDeleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.deleteAll(Book.class, "price < ?", "15");
            }
        });

        Button btnQueryData = findViewById(R.id.query_data);
        btnQueryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books = LitePal.findAll(Book.class);
                for (Book book : books) {
                    Log.d("lh123", "------------------------------------------------------------");
                    Log.d("lh123", "book name:" + book.getName());
                    Log.d("lh123", "book author:" + book.getAuthor());
                    Log.d("lh123", "book pages:" + book.getPages());
                    Log.d("lh123", "book price:" + book.getPrice());
                    Log.d("lh123", "book press:" + book.getPress());
                }
            }
        });
    }
}
