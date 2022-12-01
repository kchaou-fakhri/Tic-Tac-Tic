package app;

import controller.MainApplication;

public class Application {

    public static void main(String[] args) {

        new Thread(){
            @Override
            public void run() {
                javafx.application.Application.launch(MainApplication.class);
            }
        }.start();
    }
}
