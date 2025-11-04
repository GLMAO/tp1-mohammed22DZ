package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

import javax.swing.*;
import java.awt.*;

public class HorlogeGUI extends JFrame implements TimerChangeListener {

    private final JLabel labelHeure = new JLabel("00:00:00", SwingConstants.CENTER);
    private final TimerService timerService;

    public HorlogeGUI(TimerService timerService) {
        this.timerService = timerService;

        // Enregistrement comme observateur
        timerService.addTimeChangeListener(this);

        // Configuration de la fenêtre
        setTitle("Horloge Graphique");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        labelHeure.setFont(new Font("Consolas", Font.BOLD, 36));
        add(labelHeure, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void propertyChange(String propertyName, Object oldValue, Object newValue) {
        // À chaque changement de seconde, on met à jour l’affichage
        if (TimerChangeListener.SECONDE_PROP.equals(propertyName)) {
            SwingUtilities.invokeLater(() -> {
                String heureFormatee = String.format("%02d:%02d:%02d",
                        timerService.getHeures(),
                        timerService.getMinutes(),
                        timerService.getSecondes());
                labelHeure.setText(heureFormatee);
            });
        }
    }
}
