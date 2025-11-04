package org.emp.gl.core.launcher;

import org.emp.gl.clients.Horloge;
import org.emp.gl.clients.CompteARebours;
import org.emp.gl.clients.HorlogeGUI;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;
import org.emp.gl.timer.service.TimerService;

public class App {

    public static void main(String[] args) {
        testDuTimeService();
    }

    private static void testDuTimeService() {
        TimerService timerService = new DummyTimeServiceImpl();

        new Horloge("Horloge 1", timerService);
        new Horloge("Horloge 2", timerService);
        new CompteARebours("C1", 5, timerService);
        new CompteARebours("C2", 10, timerService);
        new CompteARebours("C3", 15, timerService);

        // BONUS : interface graphique Swing 🖥️
        new HorlogeGUI(timerService);
    }
}
