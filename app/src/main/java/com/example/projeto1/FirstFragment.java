package com.example.projeto1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import java.util.Calendar;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import java.text.SimpleDateFormat;
import java.util.Locale;



public class FirstFragment extends Fragment {
    private Button buttonDate, buttonTime, buttonOk;
    private EditText editTextDescription;
    private Calendar selectedDateTime;
    private AgendaDB agendaDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        agendaDB = new AgendaDB(getContext());
    }

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

        Button buttonShowSecondFragment = view.findViewById(R.id.button_show_second_fragment);
        buttonShowSecondFragment.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
            navController.navigate(R.id.action_firstFragment_to_secondFragment);
        });

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
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

        String dataFormatada = dateFormat.format(selectedDateTime.getTime());
        String horaFormatada = timeFormat.format(selectedDateTime.getTime());

        Compromisso compromisso = new Compromisso(descricao, dataFormatada, horaFormatada);

        long resultado = agendaDB.inserirCompromisso(compromisso);

        if (resultado != -1) {
            Toast.makeText(getContext(), "Compromisso salvo com sucesso!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Erro ao salvar compromisso.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        agendaDB.close();
    }
}
