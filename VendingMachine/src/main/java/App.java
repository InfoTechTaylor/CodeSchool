import controller.VendingMachineController;
import dao.VendingMachineDao;
import dao.VendingMachineDaoFileImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.VendingMachineServiceLayer;
import service.VendingMachineServiceLayerImpl;
import ui.UserIO;
import ui.UserIOConsoleImpl;
import ui.VendingMachineView;

public class App {

    public static void main(String[] args) {
//        // Instantiate the UserIO implementation
//        UserIO myIo = new UserIOConsoleImpl();
//        // Instantiate the view and wire teh UserIO implementation into it
//        VendingMachineView myView = new VendingMachineView(myIo);
//        // Instantiate the Dao
//        VendingMachineDao myDao = new VendingMachineDaoFileImpl("vendingMachineItems_prod.txt");
//        // Instantiate the service layer and wire the dao into it
//        VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDao);
//        // instantiate the controller and wire the service layer into it and the view
//        VendingMachineController myController = new VendingMachineController(myView, myService);
//        // kick off the controller
//        myController.run();

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        VendingMachineController controller = ctx.getBean("controller", VendingMachineController.class);
        controller.run();
    }
}
