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
import co.ikust.pomodorotimer.rest.models.Card;
import co.ikust.pomodorotimer.utils.FormatUtils;

/**
 * Created by ivan on 23/03/15.
 */
public class TaskAdapter extends ArrayAdapter<Card> {

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

        public void prepareView(Card item) {
            taskNameTextView.setText(item.getName());
            pomodoroCountTextView.setText(String.format(
                    pomodoroCountTextView.getContext().getString(R.string.pomodoro_count_format),
                    0 //TODO get pomodoro count
            ));
            totalTimeTextView.setText(FormatUtils.formatTime(0)); //TODO get task time
        }
    }

    public TaskAdapter(Context context, ArrayList<Card> tasks) {
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
