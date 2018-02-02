package com.sg.dvdlibrary;

import com.sg.dvdlibrary.controller.DvdController;

public class App {
    public static void main(String[] args) {
        DvdController controller = new DvdController();
        controller.run();
    }
}
