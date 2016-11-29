package co.edu.udea.compumovil.socialchallenge.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import co.edu.udea.compumovil.socialchallenge.R;
import co.edu.udea.compumovil.socialchallenge.entities.Task;

/**
 * Created by steven on 13/10/16.
 */

public class TaskAdapter extends BaseAdapter {

    private Context context;
    private List<Task> tasks;

    public TaskAdapter(Context context, List<Task> items) {
        this.context = context;
        this.tasks = items;
    }

    @Override
    public int getCount() {
        return this.tasks.size();
    }

    @Override
    public Object getItem(int i) {
        return this.tasks.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView = view;

        if (view == null) {
            // Create a new view into the list.
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(android.R.layout.simple_list_item_1, viewGroup, false);
        }

        // Set data into the view.
        TextView tvItem = (TextView) rowView.findViewById(android.R.id.text1);


        Task item = this.tasks.get(i);
        tvItem.setText(item.getName());


        return rowView;
    }
}
