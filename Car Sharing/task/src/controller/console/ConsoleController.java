package controller.console;

import controller.AppState;

public class ConsoleController implements Runnable {

    @Override
    public void run() {
        AppState state = new ConsoleMainMenu();
        while (state != null) {
            state = state.exec();
        }
    }
}
