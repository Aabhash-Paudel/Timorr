package com.example.timorr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder> {

    private final List<Alarm> alarmList;
    private final Context context;

    public AlarmAdapter(List<Alarm> alarmList, Context context) {
        this.alarmList = alarmList;
        this.context = context;
    }

    @NonNull
    @Override
    public AlarmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alarm, parent, false);
        return new AlarmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmViewHolder holder, int position) {
        Alarm alarm = alarmList.get(position);

        holder.tvAlarmTime.setText(alarm.getTime());
        holder.tvAlarmDescription.setText(alarm.getDescription());
        holder.switchAlarm.setChecked(alarm.isEnabled());

        holder.switchAlarm.setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) -> {
            alarm.setEnabled(isChecked);
            String message = isChecked ? "Alarm enabled" : "Alarm disabled";
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        });

        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(context, "Alarm clicked: " + alarm.getTime(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return alarmList.size();
    }

    public static class AlarmViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvAlarmTime;
        private final TextView tvAlarmDescription;
        private final Switch switchAlarm;

        public AlarmViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAlarmTime = itemView.findViewById(R.id.tvAlarmTime);
            tvAlarmDescription = itemView.findViewById(R.id.tvAlarmDescription);
            switchAlarm = itemView.findViewById(R.id.switchAlarm);
        }
    }
}
