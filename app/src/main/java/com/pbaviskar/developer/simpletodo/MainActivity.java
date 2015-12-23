package com.pbaviskar.developer.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.pbaviskar.developer.simpletodo.Constants.*;
import static com.pbaviskar.developer.simpletodo.Constants.OLD_ITEM_NAME;

public class MainActivity extends AppCompatActivity {


    List<String> itemList;
    ListView listView;
    ArrayAdapter<String> itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        itemList = new ArrayList<>();
        listView = (ListView)findViewById(R.id.listItems);

        itemsAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item , itemList);
        listView.setAdapter(itemsAdapter);

        setupListListener();

    }

    private void setupListListener() {

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                itemList.remove(position);
                itemsAdapter.notifyDataSetChanged();
                return true;
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, EditItemActivity.class);
                intent.putExtra("itemName", itemList.get(position));
                startActivityForResult(intent, EDIT_ITEM_CODE);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == Constants.EDIT_ITEM_CODE && resultCode == Constants.RESULT_CODE) {
            String name = data.getExtras().getString(NEW_ITEM_NAME);
            String oldItemName = data.getExtras().getString(OLD_ITEM_NAME);

            int pos = itemsAdapter.getPosition(oldItemName);
            itemsAdapter.insert(name,pos);

            //itemsAdapter.add(name);
            itemsAdapter.remove(oldItemName);

            Toast.makeText(MainActivity.this, "Item changed to " + name, Toast.LENGTH_SHORT).show();
        }
    }


    public void onAddItemButtonClick(View view) {

        EditText textItem = (EditText) findViewById(R.id.textAddItem);
        itemsAdapter.add(textItem.getText().toString());
        textItem.setText("");
    }
}
