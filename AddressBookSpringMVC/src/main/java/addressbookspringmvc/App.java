package addressbookspringmvc;

import addressbookspringmvc.controller.AddressBookController;
import addressbookspringmvc.dao.AddressBookDao;
import addressbookspringmvc.dao.AddressBookDaoDBImpl;
import addressbookspringmvc.ui.AddressBookView;
import addressbookspringmvc.ui.UserIO;
import addressbookspringmvc.ui.UserIOConsoleImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
//        UserIO io = new UserIOConsoleImpl();
//        AddressBookView view = new AddressBookView(io);
//        AddressBookDao dao = new AddressBookDaoDBImpl();
//        AddressBookController controller = new AddressBookController(view, dao);

        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-persistence.xml");
        AddressBookController controller = ctx.getBean("controller", AddressBookController.class);
        controller.run();
    }
}
