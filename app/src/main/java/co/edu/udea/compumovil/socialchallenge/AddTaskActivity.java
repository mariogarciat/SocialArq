package co.edu.udea.compumovil.socialchallenge;

import android.app.AlarmManager;
import android.app.DialogFragment;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import co.edu.udea.compumovil.socialchallenge.entities.Challenge;
import co.edu.udea.compumovil.socialchallenge.entities.Task;

public class AddTaskActivity extends AppCompatActivity {

    private EditText editTask;
    private TextView editBegin;
    private TextView editFinish;
    private String stringTask;
    private String sBeginAt;
    private String sFinishAt;
    private List<String> listDays;
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("challenges").child("tasks");
        auth = FirebaseAuth.getInstance();

        task = new Task();
        listDays = new ArrayList<>();

        editTask = (EditText) findViewById(R.id.editText_task);
        editBegin = (TextView) findViewById(R.id.edit_beginAt);
        editFinish = (TextView) findViewById(R.id.edit_finishAt);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_challenge, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if(id == R.id.button_save) {

            if (auth.getCurrentUser() != null) {

                stringTask = editTask.getText().toString();
                sBeginAt = editBegin.getText().toString();
                sFinishAt = editFinish.getText().toString();

                if(!"".equals(stringTask) && !"".equals(sBeginAt) && !"".equals(sFinishAt)){

                    task.setName(stringTask);
                    task.setTimeBegin(sBeginAt);
                    task.setTimeEnd(sFinishAt);
                    task.setDays(listDays);
                    for (int i = 0; i < listDays.size(); i++){
                        Log.d("tag", listDays.get(i));
                    }
                    Intent intent = new Intent();
                    intent.putExtra("task", task);
                    /*intent.putExtra("name", stringTask);
                    intent.putExtra("begin", sBeginAt);
                    intent.putExtra("finish", sFinishAt);*/
                    setResult(RESULT_OK, intent);
                    super.finish();
                }else {
                    Toast.makeText(getApplicationContext(), "Please enter all data", Toast.LENGTH_SHORT).show();
                }
            }
        }

        return super.onOptionsItemSelected(item);
    }
    public void onCheckBoxSelected(View view){
        boolean checked = ((CheckBox)view).isChecked();

        switch (view.getId()){
            case R.id.c_mon:
                if (checked){
                    listDays.add("Moday");
                    Log.d("tag", "Monday added");
                    return;
                }
            case R.id.c_tue:
                if (checked){
                    listDays.add("Tuesday");
                    Log.d("tag", "Tuesday added");
                    return;
                }
            case R.id.c_wed:
                if (checked){
                    listDays.add("Wednesday");
                    Log.d("tag", "Wednesday added");
                    return;
                }
            case R.id.c_thu:
                if (checked){
                    listDays.add("Thursday");
                    Log.d("tag", "Thursday added");
                    return;
                }
            case R.id.c_fry:
                if (checked){
                    listDays.add("Friday");
                    Log.d("tag", "Friday added");
                    return;
                }
            case R.id.c_sat:
                if (checked){
                    listDays.add("Saturday");
                    Log.d("tag", "Saturday added");
                    return;
                }
            case R.id.c_sun:
                if (checked){
                    listDays.add("Sunday");
                    Log.d("tag", "Sunday added");
                    return;
                }
        }
        for (int i = 0; i < listDays.size(); i++){
            Log.d("tag", listDays.get(i));
        }
    }

    public void showTimePickerDialog(View v) {

        DialogFragment newFragment = new TimePickerFragment();
        switch (v.getId()) {
            case R.id.edit_beginAt:
                newFragment.show(this.getFragmentManager(), "begin");
                return;
            case R.id.edit_finishAt:
                newFragment.show(this.getFragmentManager(), "finish");
                return;
        }


    }
}
