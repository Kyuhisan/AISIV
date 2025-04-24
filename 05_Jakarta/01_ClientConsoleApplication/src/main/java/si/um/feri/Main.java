package si.um.feri;

import javax.naming.InitialContext;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws Exception {
        Properties props=new Properties();
        props.put("java.naming.factory.initial","org.wildfly.naming.client.WildFlyInitialContextFactory");
        props.put("java.naming.provider.url","http-remoting://127.0.0.1:8080");
        props.put("jboss.naming.client.ejb.context","true");
        props.put("java.naming.factory.url.pkgs","org.jboss.ejb.client.naming");
        InitialContext ctx=new InitialContext(props);

        ChargingIService c = (ChargingIService) ctx.lookup("chargingStationApplication/ChargingService!si.um.feri.ChargingIService");
        System.out.println(c.canCharge("matickuhar@gmail.com", "Maribor - Center"));
        System.out.println(c.canCharge("peterkotnik@gmail.com", "Celje - South"));
        System.out.println(c.canCharge("janezplece@gmail.com", "San Francisco - Market St."));
        System.out.println(c.canCharge("aleskokot@gmail.com", "Shanghai - Pudong"));
        System.out.println(c.canCharge("nejcpetric@gmail.com", "Berlin - Alexanderplatz"));
    }
}