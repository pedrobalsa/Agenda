package com.example.projeto1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.app.DatePickerDialog;
import java.text.SimpleDateFormat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class SecondFragment extends Fragment {
    private Button buttonToday, buttonOtherDate, buttonBackToFirst;
    private TextView textViewAppointments;
    private Agenda agenda;

    @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        buttonToday = view.findViewById(R.id.button_today);
        buttonOtherDate = view.findViewById(R.id.button_other_date);
        textViewAppointments = view.findViewById(R.id.textview_appointments);
        buttonBackToFirst = view.findViewById(R.id.button_back_to_first);

        Agenda agenda = Agenda.getInstance();

        buttonToday.setOnClickListener(v -> showTodayAppointments());
        buttonOtherDate.setOnClickListener(v -> showDatePickerDialog());
        
        buttonBackToFirst.setOnClickListener(v -> {
            // Código para voltar ao FirstFragment
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
            navController.navigate(R.id.firstFragment);
        });

        return view;
    }
    private void showTodayAppointments() {
        Calendar today = Calendar.getInstance();
        displayAppointmentsForDate(today);
    }

    private void showDatePickerDialog() {
        Calendar currentDate = Calendar.getInstance();
        new DatePickerDialog(getContext(), (view, year, month, day) -> {
            Calendar selectedDate = Calendar.getInstance();
            selectedDate.set(year, month, day);
            displayAppointmentsForDate(selectedDate);
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH)).show();
    }

     private void displayAppointmentsForDate(Calendar date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String dataFormatada = dateFormat.format(date.getTime());

        List<Compromisso> compromissos = Agenda.getInstance().getCompromissosPorData(dataFormatada);
        StringBuilder displayText = new StringBuilder("Compromissos para " + dataFormatada + ":\n");

        if (compromissos.isEmpty()) {
            displayText.append("Nenhum compromisso marcado.");
        } else {
            for (Compromisso compromisso : compromissos) {
                displayText.append(compromisso.getHora()).append(" - ")
                        .append(compromisso.getDescricao()).append("\n");
            }
        }

        textViewAppointments.setText(displayText.toString());
    }

}
