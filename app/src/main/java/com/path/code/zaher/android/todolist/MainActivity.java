package com.path.code.zaher.android.todolist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> items = new ArrayList<>();
    ListView listView;
    Button button;
    EditText editTextItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Simple Todo");
        listView = (ListView) findViewById(R.id.noteList);
        button = (Button) findViewById(R.id.btnAddItems);
        editTextItem = (EditText) findViewById(R.id.textItem);
        items = getIntent().getStringArrayListExtra("items");
        if (items == null)
            items = new ArrayList<>();
        final notesArrayAdapter adapter = new notesArrayAdapter(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.add(editTextItem.getText().toString());
                editTextItem.setText("");
                adapter.notifyDataSetChanged();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                items.remove(i);
                adapter.notifyDataSetChanged();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(MainActivity.this, EditItemActivity.class);
                intent.putStringArrayListExtra("items", items);
                intent.putExtra("item_position", i);
                startActivity(intent);

                return false;
            }
        });
    }

    private class notesArrayAdapter extends ArrayAdapter<String> {

        List<String> mIdMap = new ArrayList<>();

        public notesArrayAdapter(Context context, int textViewResourceId,
                                 List<String> objects) {
            super(context, textViewResourceId, objects);
            mIdMap = objects;
        }

    }
}
