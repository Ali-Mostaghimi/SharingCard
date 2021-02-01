package com.skyproduct.sharingcard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MySharingCard mySharingCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySharingCard = findViewById(R.id.my_sharing_card);
        init();
    }

    private void init() {
        mySharingCard.getSocialBtn1().setOnClickListener(this);
        mySharingCard.getSocialBtn2().setOnClickListener(this);
        mySharingCard.getSocialBtn3().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == mySharingCard.getSocialBtn1().getId() ||
                id == mySharingCard.getSocialBtn2().getId() ||
                id == mySharingCard.getSocialBtn3().getId()){
            Toast.makeText(this, ((AppCompatButton) v).getText(), Toast.LENGTH_SHORT).show();

        }
    }
}
