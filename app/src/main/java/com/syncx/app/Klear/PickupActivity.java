package com.syncx.app.Klear;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class PickupActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    double cost = 0;
    String already ="";
    Button btProceed;
    int etSum;
    String metal="";
    String paper="";
    String glass="";
    String plastic="";
    String rubber="";
    String cardboard="";
    String trash="";
    String waste="";
    private FirebaseAuth mAuth;
    private static final String TAG = "PickupActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup);
        mAuth = FirebaseAuth.getInstance();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.waste_array, android.R.layout.simple_spinner_item);

// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        String selected = parent.getItemAtPosition(pos).toString();
        TextView etName = (TextView) findViewById(R.id.etName);
        TextView etPrice= (TextView) findViewById(R.id.etPrice);
        String medicinesalready = etName.getText().toString();
        String costalready = etPrice.getText().toString();
        if(selected.equals("Select Item")){
            //
        }else{
            if(selected.equals("Metal")){
                metal();
                if(!already.contains("1")){
                    if(cost==0){
                        etName.setText("Metal");
                        etPrice.setText("₹33/kg");
                        already+="1";
                    }else{
                        etName.setText(medicinesalready + "\nMetal");
                        etPrice.setText(costalready + "\n₹33/kg");
                    }
                    cost += 33;
                }
            }else if(selected.equals("Paper")){
                paper();
                if(!(already.contains("2"))){if(cost==0){
                    etName.setText("Paper");
                    etPrice.setText("₹11/kg");
                    already+="2";
                }else{
                    etName.setText(medicinesalready + "\nPaper");
                    etPrice.setText(costalready + "\n₹11/kg");
                }}
                cost += 11;
            }else if(selected.equals("Glass")){
                glass();
                if(!(already.contains("3"))){
                    if(cost==0){
                        etName.setText("Glass");
                        etPrice.setText("₹7/kg");
                        already+="3";
                    }else{
                        etName.setText(medicinesalready + "\nGlass");
                        etPrice.setText(costalready + "\n₹7/kg");
                    }}
                cost += 7;
            }else if(selected.equals("Plastic")){
                plastic();
                if(!(already.contains("4"))){
                    if(cost==0){
                        etName.setText("Plastic");
                        etPrice.setText("₹28/kg");
                        already+="4";
                    }else{
                        etName.setText(medicinesalready + "\nPlastic");
                        etPrice.setText(costalready + "\n₹28/kg");
                    }}
                cost += 28;
            }else if(selected.equals("Rubber")){
                rubber();
                if(!(already.contains("5"))){
                    if(cost==0){
                        etName.setText("Rubber");
                        etPrice.setText("₹6.5/kg");
                        already+="5";
                    }else {
                        etName.setText(medicinesalready + "\nRubber");
                        etPrice.setText(costalready + "\n₹6.5/kg");
                    }}
                cost += 6.5;
            }else if(selected.equals("Cardboard")){
                cardboard();
                if(!(already.contains("6"))){
                    if(cost==0){
                        etName.setText("Cardboard");
                        etPrice.setText("₹15/kg");
                        already+="6";
                    }else {
                        etName.setText(medicinesalready + "\nCardboard");
                        etPrice.setText(costalready + "\n₹15/kg");
                    }}
                cost += 15;
            }else if(selected.equals("Trash")){
                trash();
                if(!(already.contains("7"))){
                    if(cost==0){
                        etName.setText("Trash");
                        etPrice.setText("₹17.5/kg");
                        already+="7";
                    }else {
                        etName.setText(medicinesalready + "\nTrash");
                        etPrice.setText(costalready + "\n₹17.5/kg");
                    }}
                cost += 17.5;
            }
        }
        // waste();
    }
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private String metal(){
        metal="Metal ";
        return metal;
    }
    private String paper(){
        paper="Paper ";
        return paper;
    }
    private String glass(){
        glass="Glass ";
        return glass;
    }
    private String plastic(){
        plastic="Plastic ";
        return plastic;
    }
    private String rubber(){
        rubber="Rubber ";
        return rubber;
    }
    private String cardboard(){
        cardboard="Cardboard ";
        return cardboard;
    }
    private String trash(){
        trash="Trash ";
        return trash;
    }
    private String waste(){
        waste = metal + paper + glass + plastic + rubber + cardboard + trash;
        Log.d(TAG, "waste: " + metal + "end");
        return waste;
    }

    public void goToProceed(View view) {
        waste();
        Intent intent = new Intent(PickupActivity.this, ProceedActivity.class);
        intent.putExtra("waste_materials", waste);
        String costStr = String.valueOf(cost);
        intent.putExtra("cost_of_waste", costStr);
        startActivity(intent);
    }

    public void goToOrderHistory(View view) {
        startActivity(new Intent(PickupActivity.this, OrderHistoryActivity.class));
    }
}
