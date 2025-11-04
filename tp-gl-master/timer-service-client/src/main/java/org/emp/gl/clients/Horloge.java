package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

public class Horloge implements TimerChangeListener {

    String name;
    TimerService timerService;

    public Horloge(String name, TimerService timerService) {
        this.name = name;
        this.timerService = timerService;
        this.timerService.addTimeChangeListener(this); // 🔹 s’inscrit comme observer
        System.out.println("Horloge " + name + " initialisée !");
    }

    @Override
    public void propertyChange(String prop, Object oldValue, Object newValue) {
        if (TimerChangeListener.SECONDE_PROP.equals(prop)) {
            afficherHeure();
        }
    }

    public void afficherHeure() {
        System.out.println(name + " affiche " +
                String.format("%02d:%02d:%02d",
                        timerService.getHeures(),
                        timerService.getMinutes(),
                        timerService.getSecondes()));
    }
}
