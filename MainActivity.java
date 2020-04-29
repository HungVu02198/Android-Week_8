package com.example.gmailmodule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<UserEmail> listEmail;
    List<UserEmail> listEmailSearch;

    private EditText textSearch;
    private Button btnFavorite;
    private ListView listView;

    Boolean favoriteIcon = false;
    EmailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listEmail = new ArrayList<>();

        listEmail.add(new UserEmail(1, "UserName_01", "Email Subject 1", "Email Description", "Email Content 1"));
        listEmail.add(new UserEmail(2, "UserName_02", "Email Subject 2", "Email Description", "Email Content 2"));
        listEmail.add(new UserEmail(3, "UserName_03", "Email Subject 3", "Email Description", "Email Content 3"));
        listEmail.add(new UserEmail(4, "UserName_04", "Email Subject 4", "Email Description", "Email Content 4"));
        listEmail.add(new UserEmail(5, "UserName_04", "Email Subject 4", "Email Description", "Email Content 5"));
        listEmail.add(new UserEmail(6, "UserName_04", "Email Subject 4", "Email Description", "Email Content 6"));

        listEmailSearch = new ArrayList<>();
        adapter = new EmailAdapter(listEmail);

        listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listEmailSearch.clear();
        listEmailSearch.addAll(listEmail);

        textSearch = findViewById(R.id.text_search);
        listView = findViewById(R.id.list_view);
        btnFavorite = findViewById(R.id.btnFavorite);

        btnFavorite.setBackgroundColor(0xF0F1FCFC);
        textSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charFill, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charFill, int start, int before, int count) {
                if(charFill.toString().length() < 3){
                    adapter = new EmailAdapter(listEmailSearch);
                    listView = findViewById(R.id.list_view);
                    listView.setAdapter(adapter);
                }
                else{
                    searchItem(charFill.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favoriteIcon = !favoriteIcon;
                if(favoriteIcon == true)
                    btnFavorite.setBackgroundColor(0xBBBBBBBB);
                if(favoriteIcon == false)
                    btnFavorite.setBackgroundColor(0xF0F1FCFC);

                searchItem(textSearch.getText().toString());
            }
        });
    }

    public void searchItem (String textToSearch){
        List<UserEmail> listItemsFavorite ;
        listItemsFavorite = new ArrayList<>();
        listEmailSearch.clear();
        listEmailSearch.addAll(listEmail);

        if(favoriteIcon)
            for (UserEmail item : listEmail) {
                if (item.isFavoriteItem(textToSearch)) {
                    listItemsFavorite.add(item);
                }
            } else{
                for (UserEmail item : listEmail) {
                    if (item.searchContent(textToSearch)) {
                        listItemsFavorite.add(item);
                    }
                }
            }

        adapter = new EmailAdapter(listItemsFavorite);
        listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }
}
