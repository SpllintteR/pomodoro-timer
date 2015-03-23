package co.ikust.pomodorotimer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import co.ikust.pomodorotimer.R;
import co.ikust.pomodorotimer.storage.models.TaskTime;
import co.ikust.pomodorotimer.utils.FormatUtils;

/**
 * Created by ivan on 23/03/15.
 */
public class TaskAdapter extends ArrayAdapter<TaskTime> {

    static class ViewHolder {
        @InjectView(R.id.tvTaskName)
        TextView taskNameTextView;

        @InjectView(R.id.tvPomodoroCount)
        TextView pomodoroCountTextView;

        @InjectView(R.id.tvTotalTime)
        TextView totalTimeTextView;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }

        public void prepareView(TaskTime item) {
            taskNameTextView.setText(item.getCard().getName());
            pomodoroCountTextView.setText(String.format(
                    pomodoroCountTextView.getContext().getString(R.string.pomodoro_count_format),
                    item.getPomodoroCount()
            ));
            totalTimeTextView.setText(FormatUtils.formatTime(item.getTime()));
        }
    }

    public TaskAdapter(Context context, ArrayList<TaskTime> tasks) {
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listitem_task, parent, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.prepareView(getItem(position));
        return convertView;
    }
}
