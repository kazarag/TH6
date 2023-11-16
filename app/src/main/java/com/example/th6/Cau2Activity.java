package com.example.th6;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class Cau2Activity extends AppCompatActivity {
    private productHelper pdtHelper;
    private ListView listView;
    private ArrayAdapter<product> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau2);

        pdtHelper = new productHelper(this);
        product pd=new product("SP-123","iPhone 5S",10000);
        pdtHelper.addproduct(pd);
        pd=new product("SP-124","Vertu Constellation",10000);
        pdtHelper.addproduct(pd);
        pd=new product("SP-125","Nokia Lumia 925",10000);
        pdtHelper.addproduct(pd);
        pd=new product("SP-126","SamSung Galaxy S4",10000);
        pdtHelper.addproduct(pd);
        pd=new product("SP-127","HTC Desire 600",10000);
        pdtHelper.addproduct(pd);
        pd=new product("SP-128","HKPhone Revo LEAD",10000);
        pdtHelper.addproduct(pd);
        pd=new product();
        listView = findViewById(R.id.listView);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the product at the long clicked position
                product pd = arrayAdapter.getItem(position);
                // Call deleteproduct method with the student as argument
                detailproduct(pd);
                // Return true to indicate the event is handled
                return true;
            }
        });

        // Call loadproducts method
        loadproducts();
    }
    public void loadproducts() {
        // Get list of students from database
        List<product> products = pdtHelper.getAllproduct();
        // Create array adapter with list of students
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, products);
        // Set array adapter to listView
        listView.setAdapter(arrayAdapter);
    }
    public void detailproduct(product pd) {
        // Create alert dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Set layout of dialog
        builder.setView(R.layout.dialog_layout);
        // Set neutral button of dialog
        builder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Delete the student from the database
                pdtHelper.deleteproduct(pd);
                // Show a toast message
                Toast.makeText(Cau2Activity.this, "Product deleted", Toast.LENGTH_SHORT).show();
                // Reload the list of students
                loadproducts();
            }
        });

        // Set negative button of dialog
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Dismiss the dialog
                dialog.dismiss();
            }
        });

        // Create and show the dialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        // Set the edit texts with the current values of the product
        EditText editTextId = alertDialog.findViewById(R.id.editTextId);
        EditText editTextName = alertDialog.findViewById(R.id.editTextName);
        EditText editTextAge = alertDialog.findViewById(R.id.editTextPrice);
        editTextName.setText(pd.getName());
        editTextAge.setText(String.valueOf(pd.getPrice()));
        editTextId.setText(pd.getId());
    }

}