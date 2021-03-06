package com.sg.dvdlibrary;

import com.sg.dvdlibrary.controller.DvdController;
import com.sg.dvdlibrary.dao.DvdLibraryAuditDao;
import com.sg.dvdlibrary.dao.DvdLibraryAuditDaoFileImpl;
import com.sg.dvdlibrary.dao.DvdLibraryDao;
import com.sg.dvdlibrary.dao.DvdLibraryDaoFileImpl;
import com.sg.dvdlibrary.service.DvdLibraryServiceLayer;
import com.sg.dvdlibrary.service.DvdLibraryServiceLayerImpl;
import com.sg.dvdlibrary.ui.DvdLibraryView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
//        // Instantiate the UserIO implementation
//        UserIO myIo = new UserIOConsoleImpl();
//        // Instantiate the view and wire the UserIO implementation into it
//        DvdLibraryView myView = new DvdLibraryView(myIo);
//        // Instantiate the DAO
//        DvdLibraryDao myDao = new DvdLibraryDaoFileImpl();
//        // Instantiate the Audit DAO
//        DvdLibraryAuditDao myAuditDao = new DvdLibraryAuditDaoFileImpl();
//        // Instantiate the Service layer adn wire the DAO and Audit DAO into it
//        DvdLibraryServiceLayer myService = new DvdLibraryServiceLayerImpl(myDao, myAuditDao);
//        // Instantiate teh Controller and wire teh Service Layer into it
//        DvdController controller = new DvdController(myService, myView);
//        // kick off the controller
//        controller.run();

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        DvdController controller = ctx.getBean("controller", DvdController.class);
        controller.run();
    }
}
