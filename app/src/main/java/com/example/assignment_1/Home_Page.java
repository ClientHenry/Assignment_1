package com.example.assignment_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class Home_Page extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //     searchViewAction();
        optionRecycler();
        dealListRecycler();
        bottomNavigation();

    }

    private void optionRecycler() {
        RecyclerView rc_option = findViewById(R.id.recycler_option);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(RecyclerView.HORIZONTAL);
        rc_option.setLayoutManager(lm);

        ArrayList<Option_Item> optionItems = new ArrayList<>();

        optionItems.add(new Option_Item("All", R.drawable.option_all));
        optionItems.add(new Option_Item("Nature", R.drawable.option_nature));
        optionItems.add(new Option_Item("Gourmet", R.drawable.option_gourmet));
        optionItems.add(new Option_Item("Wonder", R.drawable.option_wonder));
        optionItems.add(new Option_Item("Relic", R.drawable.option_relic));
        rc_option.setAdapter(new OptionAdapter(optionItems));
    }

    private void dealListRecycler() {

        RecyclerView rc = findViewById(R.id.recycler_list);
        rc.setLayoutManager(new LinearLayoutManager(this));
        List<Product> x = MyDataBase.getInstance(getApplicationContext()).productDao().getAllProducts();
        rc.setAdapter(new Deal_List_Adapter(x));
    }

/*
    private void searchViewAction(){

        SearchView searchView = findViewById(R.id.searchView);
        TextView textView = findViewById(R.id.textView);

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setVisibility(View.GONE);
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                textView.setVisibility(View.VISIBLE);
                return false;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }*/

    private void bottomNavigation() {

        NavigationBarView nav = findViewById(R.id.bottom_navigation);
        NavigationBarView.OnItemSelectedListener listener = new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.item_1) {

                    Intent intent = new Intent(Home_Page.this, Sign_Up_Page.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.item_2) {
                    Intent intent;
                    Customer customer = MyDataBase.getInstance(getApplicationContext()).customerDao().getLogin();
                    if (customer == null) {
                        intent = new Intent(Home_Page.this, Login_Page.class);
                    } else {
                        intent = new Intent(Home_Page.this, Customer.class);
                    }
                   startActivity(intent);

                   return true;

                } else
                    return false;
            }
        };
        nav.setOnItemSelectedListener(listener);
    }

}