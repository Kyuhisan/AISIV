package si.um.feri;

import si.um.feri.enums.carTypeENUM;
import si.um.feri.enums.connectorENUM;
import si.um.feri.enums.regionENUM;
import si.um.feri.vao.ChargingStationVao;
import si.um.feri.vao.ProviderVao;
import si.um.feri.vao.UserVao;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Demo {
    static final URI usersURI = URI.create("http://localhost:8080/chargingStationApplication/api/users/");
    static final URI stationsURI = URI.create("http://localhost:8080/chargingStationApplication/api/stations/");

    HttpClient client = HttpClient.newBuilder().build();
    Jsonb jsonb = JsonbBuilder.create();

    void getAllUsers() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(usersURI)
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.print(response.body());
    }

    void getAllStations() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(stationsURI)
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.print(response.body());
    }

    void addUser(UserVao user) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(jsonb.toJson(user)))
                .header("Content-Type", "application/json")
                .uri(usersURI)
                .build();
    }

    void addStation(ChargingStationVao station) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(jsonb.toJson(station)))
                .header("Content-Type", "application/json")
                .uri(stationsURI)
                .build();
    }

    public static void main(String[] args) throws Exception {
        Demo client = new Demo();
        client.addUser(new UserVao("Nov Uporabnik", "novuporabnik@gmail.com", 25.0, carTypeENUM.ELECTRIC));
        client.getAllUsers();

        client.addStation(new ChargingStationVao(null, connectorENUM.CCS, "novaPostaja", true, 100));
        client.getAllStations();
    }
}
