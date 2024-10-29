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


public class FirstFragment extends Fragment {
    private Button buttonDate, buttonTime, buttonOk;
    private EditText editTextDescription;
    private Calendar selectedDateTime;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        
        buttonDate = view.findViewById(R.id.button_date);
        buttonTime = view.findViewById(R.id.button_time);
        buttonOk = view.findViewById(R.id.button_ok);
        editTextDescription = view.findViewById(R.id.edittext_description);
        selectedDateTime = Calendar.getInstance();

        buttonDate.setOnClickListener(v -> showDatePickerDialog());
        buttonTime.setOnClickListener(v -> showTimePickerDialog());
        buttonOk.setOnClickListener(v -> addCompromisso());

        return view;
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePicker = new DatePickerDialog(getContext(), (view, year, month, day) -> {
            selectedDateTime.set(year, month, day);
        }, selectedDateTime.get(Calendar.YEAR), selectedDateTime.get(Calendar.MONTH), selectedDateTime.get(Calendar.DAY_OF_MONTH));
        datePicker.show();
    }

    private void showTimePickerDialog() {
        TimePickerDialog timePicker = new TimePickerDialog(getContext(), (view, hourOfDay, minute) -> {
            selectedDateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            selectedDateTime.set(Calendar.MINUTE, minute);
        }, selectedDateTime.get(Calendar.HOUR_OF_DAY), selectedDateTime.get(Calendar.MINUTE), true);
        timePicker.show();
    }

    private void addCompromisso() {
        String descricao = editTextDescription.getText().toString();
        // Lógica para salvar o compromisso (usando uma classe ou banco de dados)
        // Exemplo simples para entender:
        Toast.makeText(getContext(), "Compromisso adicionado!", Toast.LENGTH_SHORT).show();
    }
}
