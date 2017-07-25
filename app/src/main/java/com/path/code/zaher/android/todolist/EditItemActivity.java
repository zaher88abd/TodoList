package com.path.code.zaher.android.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by zaher on 2017-07-25.
 */

public class EditItemActivity extends AppCompatActivity {

    EditText editText;
    Button btnSave;
    ArrayList<String> items = new ArrayList<>();
    int itemId = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edite_item_activity);
        setTitle("Edit Item");
        items = getIntent().getStringArrayListExtra("items");
        itemId = getIntent().getIntExtra("item_position", 0);
        editText = (EditText) findViewById(R.id.editText);
        editText.setText(items.get(itemId));
        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                items.add(itemId, editText.getText().toString());
                items.remove(itemId + 1);
                Intent intent = new Intent(EditItemActivity.this, MainActivity.class);
                intent.putStringArrayListExtra("items", items);
                startActivity(intent);
            }
        });

    }
}
