package com.example.assignment_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

public class Admin_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        RecyclerView recyclerView = findViewById(R.id.admin_recycler);
        Admin_Adapter admin_adapter = new Admin_Adapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(admin_adapter);

        Admin admin = MyDataBase.getInstance(getApplicationContext()).adminDao().getAllAdmins().get(0);
        admin.setStatus(1);
        MyDataBase.getInstance(getApplicationContext()).adminDao().update(admin);

        List<Customer> customers = MyDataBase.getInstance(getApplicationContext()).customerDao().getAll();
        List<Supplier> suppliers = MyDataBase.getInstance(getApplicationContext()).supplierDao().getAll();
        List<Product> products = MyDataBase.getInstance(getApplicationContext()).productDao().getAllProducts();
        List<Deal> deals = MyDataBase.getInstance(getApplicationContext()).dealDao().getAllDeals();

        Button btn_customer = (Button) findViewById(R.id.admin_btn_customer);
        Button btn_supplier = (Button) findViewById(R.id.admin_btn_supplier);
        Button btn_product = (Button) findViewById(R.id.admin_btn_order);
        Button btn_deal = (Button) findViewById(R.id.admin_btn_deal);
        FloatingActionButton btn_return = findViewById(R.id.admin_btn_return);
        Button btn_sign_out = (Button) findViewById(R.id.admin_btn_sign_out);

        btn_customer.setText("Customer\n" + customers.size());
        btn_supplier.setText("Supplier\n" + suppliers.size());
        btn_product.setText("Product\n" + products.size());
        btn_deal.setText("Deal\n" + deals.size());

        btn_return.setOnClickListener(v -> {
            finish();
        });

        btn_customer.setOnClickListener(v -> {

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new Admin_Adapter(customers, null, null, null));

        });

        btn_supplier.setOnClickListener(v -> {

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new Admin_Adapter(null, suppliers, null, null));

        });

        btn_product.setOnClickListener(v -> {

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new Admin_Adapter(null, null, products, null));

        });

        btn_deal.setOnClickListener(v -> {

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new Admin_Adapter(null, null, null, deals));

        });

        btn_sign_out.setOnClickListener(v -> {
            Intent intent = new Intent(Admin_Page.this, Login_Page.class);
            admin.setStatus(0);
            MyDataBase.getInstance(getApplicationContext()).adminDao().update(admin);
            startActivity(intent);
        });

        bottomNavigation();
    }

    private void bottomNavigation() {

        NavigationBarView nav = findViewById(R.id.bottom_navigation);
        NavigationBarView.OnItemSelectedListener listener = new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.item_1) {

                    Intent intent = new Intent(Admin_Page.this, Home_Page.class);
                    startActivity(intent);
                    return true;
                } else{
                    return false;}
            }
        };
        nav.setOnItemSelectedListener(listener);
    }
}