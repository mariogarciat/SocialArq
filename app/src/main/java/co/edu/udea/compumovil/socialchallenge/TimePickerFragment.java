package co.edu.udea.compumovil.socialchallenge;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by steven on 6/11/16.
 */

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    TextView begin;
    TextView finish;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {

        begin = (TextView) getActivity().findViewById(R.id.edit_beginAt);
        finish = (TextView) getActivity().findViewById(R.id.edit_finishAt);



        if(getTag().toString().equals("begin")) {
            begin.setText(i + ":"+i1);
        }else {
            finish.setText(i + ":"+i1);
        }

    }
}
