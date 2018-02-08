import dao.ServerDao;
import dao.ServerDaoInMemImpl;
import dto.Server;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class App {

    public static void main(String[] args) {

        ServerDao dao = new ServerDaoInMemImpl();

        // create several Servers to manipulate
        Server myServer = new Server();
        myServer.setName("web01");
        myServer.setIp("192.168.1.1");
        myServer.setManufacturer("Dell");
        myServer.setRam(8);
        myServer.setNumProcessors(9);
        myServer.setPurchasedDate(LocalDate.parse("2010-01-01", DateTimeFormatter.ISO_DATE));

        dao.addServer(myServer);

        // create several Servers to manipulate
        myServer = new Server();
        myServer.setName("db01");
        myServer.setIp("192.168.3.5");
        myServer.setManufacturer("HP");
        myServer.setRam(16);
        myServer.setNumProcessors(24);
        myServer.setPurchasedDate(LocalDate.parse("2013-01-01", DateTimeFormatter.ISO_DATE));

        dao.addServer(myServer);

        // create several Servers to manipulate
        myServer = new Server();
        myServer.setName("hr124");
        myServer.setIp("192.168.32.111");
        myServer.setManufacturer("IBM");
        myServer.setRam(16);
        myServer.setNumProcessors(12);
        myServer.setPurchasedDate(LocalDate.parse("2014-01-01", DateTimeFormatter.ISO_DATE));

        dao.addServer(myServer);


        // create several Servers to manipulate
        myServer = new Server();
        myServer.setName("eng16");
        myServer.setIp("192.168.32.56");
        myServer.setManufacturer("HP");
        myServer.setRam(4);
        myServer.setNumProcessors(8);
        myServer.setPurchasedDate(LocalDate.parse("2001-01-01", DateTimeFormatter.ISO_DATE));

        dao.addServer(myServer);

        // create several Servers to manipulate
        myServer = new Server();
        myServer.setName("eng64");
        myServer.setIp("192.168.34.56");
        myServer.setManufacturer("HP");
        myServer.setRam(8);
        myServer.setNumProcessors(16);
        myServer.setPurchasedDate(LocalDate.parse("2001-01-01", DateTimeFormatter.ISO_DATE));

        dao.addServer(myServer);


        List<Server> dellServerList = dao.getServersByManufacturer("Dell");
        for(Server currentServer : dellServerList){
            System.out.println(currentServer.getName());
        }

        // do same as above enhanced for loop but leverage lambdas/streams
        dellServerList.stream()
                .forEach(s -> System.out.println(s.getName()));


        Map<String, List<Server>> serverMap = dao.getAllServersGroupByManufacturer();

        Set<String> manufacturers = serverMap.keySet();

        manufacturers.stream()
                .forEach(name -> {
                    System.out.println("=========================================");
                    System.out.println("Manufacturer: " + name);
                        serverMap.get(name).stream().forEach(s -> System.out.println(s.getName()));
                });

    } // end main
} // end class
