package org.emp.gl.time.service.impl;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList; // ✅ FIX : import pour créer une copie
import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

public class DummyTimeServiceImpl implements TimerService {

    int dixiemeDeSeconde;
    int minutes;
    int secondes;
    int heures;
    List<TimerChangeListener> listeners = new LinkedList<>();

    public DummyTimeServiceImpl() {
        setTimeValues();
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                timeChanged();
            }
        };
        timer.scheduleAtFixedRate(task, 100, 100);
    }

    private void setTimeValues() {
        LocalTime localTime = LocalTime.now();
        setSecondes(localTime.getSecond());
        setMinutes(localTime.getMinute());
        setHeures(localTime.getHour());
        setDixiemeDeSeconde(localTime.getNano() / 100000000);
    }

    @Override
    public void addTimeChangeListener(TimerChangeListener pl) {
        listeners.add(pl);
    }

    @Override
    public void removeTimeChangeListener(TimerChangeListener pl) {
        listeners.remove(pl);
    }

    private void timeChanged() {
        setTimeValues();
    }

    public void setDixiemeDeSeconde(int newDixiemeDeSeconde) {
        if (dixiemeDeSeconde == newDixiemeDeSeconde)
            return;

        int oldValue = dixiemeDeSeconde;
        dixiemeDeSeconde = newDixiemeDeSeconde;
        dixiemeDeSecondesChanged(oldValue, dixiemeDeSeconde);
    }

    private void dixiemeDeSecondesChanged(int oldValue, int newValue) {
        for (TimerChangeListener l : new ArrayList<>(listeners)) { // ✅ FIX
            l.propertyChange(TimerChangeListener.DIXEME_DE_SECONDE_PROP,
                    oldValue, dixiemeDeSeconde);
        }
    }

    public void setSecondes(int newSecondes) {
        if (secondes == newSecondes)
            return;

        int oldValue = secondes;
        secondes = newSecondes;
        secondesChanged(oldValue, secondes);
    }

    private void secondesChanged(int oldValue, int secondes) {
        for (TimerChangeListener l : new ArrayList<>(listeners)) { // ✅ FIX
            l.propertyChange(TimerChangeListener.SECONDE_PROP,
                    oldValue, secondes);
        }
    }

    public void setMinutes(int newMinutes) {
        if (minutes == newMinutes)
            return;

        int oldValue = minutes;
        minutes = newMinutes;
        minutesChanged(oldValue, minutes);
    }

    private void minutesChanged(int oldValue, int minutes) {
        for (TimerChangeListener l : new ArrayList<>(listeners)) { // ✅ FIX
            l.propertyChange(TimerChangeListener.MINUTE_PROP,
                    oldValue, secondes);
        }
    }

    public void setHeures(int newHeures) {
        if (heures == newHeures)
            return;

        int oldValue = heures;
        heures = newHeures;
        heuresChanged(oldValue, heures);
    }

    private void heuresChanged(int oldValue, int heures) {
        for (TimerChangeListener l : new ArrayList<>(listeners)) { // ✅ FIX
            l.propertyChange(TimerChangeListener.HEURE_PROP,
                    oldValue, secondes);
        }
    }

    @Override
    public int getDixiemeDeSeconde() {
        return dixiemeDeSeconde;
    }

    @Override
    public int getHeures() {
        return heures;
    }

    @Override
    public int getMinutes() {
        return minutes;
    }

    @Override
    public int getSecondes() {
        return secondes;
    }
}
