package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

public class CompteARebours implements TimerChangeListener {

    int compteur;
    TimerService timerService;
    String name;

    public CompteARebours(String name, int startValue, TimerService timerService) {
        this.name = name;
        this.compteur = startValue;
        this.timerService = timerService;
        timerService.addTimeChangeListener(this);
        System.out.println("Compte à rebours " + name + " démarré à " + startValue);
    }

    @Override
    public void propertyChange(String prop, Object oldValue, Object newValue) {
        if (TimerChangeListener.SECONDE_PROP.equals(prop)) {
            compteur--;
            System.out.println(name + " : " + compteur);
            if (compteur <= 0) {
                System.out.println(name + " terminé !");
                timerService.removeTimeChangeListener(this); // 🔹 se désinscrit automatiquement
            }
        }
    }
}
