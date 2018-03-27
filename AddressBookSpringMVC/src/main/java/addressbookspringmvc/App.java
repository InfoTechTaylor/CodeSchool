package addressbookspringmvc;

import addressbookspringmvc.controller.AddressBookController;

public class App {
    public static void main(String[] args) {
        AddressBookController controller = new AddressBookController();
        controller.run();
    }
}
