package dao;

import dto.Server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ServerDaoInMemImpl implements ServerDao {

    private Map<String, Server> serverMap = new HashMap<>();

    @Override
    public void addServer(Server server) {
        serverMap.put(server.getName(), server);
    }

    @Override
    public Server getServer(String name) {
        return serverMap.get(name);
    }

    @Override
    public void removeServer(String name) {
        serverMap.remove(name);
    }

    @Override
    public List<Server> getAllServers() {
        return new ArrayList<Server>(serverMap.values());
    }

    @Override
    public Map<String, List<Server>> getAllServersGroupByManufacturer() {
        return serverMap.values()
                .stream()
                .collect(Collectors.groupingBy(Server::getManufacturer));
    }

    @Override
    public List<Server> getServersByManufacturer(String manufacturer) {
        return serverMap.values() // data source of Server objects
                .stream() // intermediate operation
                .filter(s -> s.getManufacturer().equalsIgnoreCase(manufacturer)) // intermediate operation
                .collect(Collectors.toList()); // terminal operation returns a non-stream result
    }

    @Override
    public List<Server> getServersOlderThan(int ageInYears) {
        return serverMap.values() // data source of Server objects
                .stream() // intermediate operation?
                .filter(s -> s.getServerAge() > ageInYears) // filter out servers that are older than passed ageInYears
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, List<Server>> getServersOlderThanGroupByManufacturer(int ageInYears) {
        return serverMap.values() // data source of Server objects
                .stream() // intermediate operation
                .filter(s -> s.getServerAge() > ageInYears) // filter out servers that are older than passed ageInYears
                .collect(Collectors.groupingBy(Server::getManufacturer));
    }

    @Override
    public double getAverageServerAge() {
        return serverMap.values()
                .stream()
                .mapToLong(Server::getServerAge)
                .average()
                .getAsDouble();
    }
}
