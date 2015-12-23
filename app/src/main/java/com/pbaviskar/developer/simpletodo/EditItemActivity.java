package com.pbaviskar.developer.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText itemName = (EditText)findViewById(R.id.txtEditItem);
                String oldItemName = getIntent().getExtras().getString(Constants.ITEM_NAME);
                Intent intent = new Intent();
                intent.putExtra(Constants.NEW_ITEM_NAME, itemName.getText().toString());
                intent.putExtra(Constants.OLD_ITEM_NAME, oldItemName);
                setResult(Constants.RESULT_CODE, intent);
                EditItemActivity.this.finish();
            }
        });
    }

}
