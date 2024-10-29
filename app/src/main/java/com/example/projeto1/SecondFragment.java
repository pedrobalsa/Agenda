package com.example.projeto1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import java.util.Calendar;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;


public class SecondFragment extends Fragment {
    private Button buttonToday, buttonOtherDate;
    private TextView textViewAppointments;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        
        buttonToday = view.findViewById(R.id.button_today);
        buttonOtherDate = view.findViewById(R.id.button_other_date);
        textViewAppointments = view.findViewById(R.id.textview_appointments);

        buttonToday.setOnClickListener(v -> showTodayAppointments());
        buttonOtherDate.setOnClickListener(v -> showDatePickerDialog());

        return view;
    }

    private void showTodayAppointments() {
        // Logica para exibir compromissos do dia corrente
        textViewAppointments.setText("Compromissos para hoje:\n10:00 - Reunião\n15:00 - Consulta médica");
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePicker = new DatePickerDialog(getContext(), (view, year, month, day) -> {
            // Lógica para exibir compromissos da data selecionada
            textViewAppointments.setText("Compromissos para " + day + "/" + (month + 1) + "/" + year + ":\nNenhum compromisso.");
        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePicker.show();
    }
}
