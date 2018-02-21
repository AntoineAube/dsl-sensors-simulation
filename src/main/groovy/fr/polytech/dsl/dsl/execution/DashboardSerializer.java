package fr.polytech.dsl.dsl.execution;

import fr.polytech.dsl.dsl.model.structures.dashboards.Dashboard;
import fr.polytech.dsl.dsl.model.structures.dashboards.Panel;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

/**
 * Communique avec Grafana pour ajouter des dashboards
 *
 * @author Robin Alonzo
 */
public class DashboardSerializer {

    private static final String DASHBOARD_URL = "/api/dashboards/db";
    private static final String PANEL_JSON_FILE = "grafana/panel.json";
    private static final String DASHBOARD_JSON_FILE = "grafana/dashboard.json";

    private String url;
    private String apiKey;

    public DashboardSerializer(String serverURL, String apiKey) {
        this.url = serverURL;
        this.apiKey = apiKey;
    }

    private String getFile(String fileName) {

        StringBuilder result = new StringBuilder("");

        //Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    private String toJson(Dashboard dashboard){
        final String dashboard_json = getFile(DASHBOARD_JSON_FILE);
        final String panel_json = getFile(PANEL_JSON_FILE);
        JSONObject dashboard_object = new JSONObject(dashboard_json);
        dashboard_object.getJSONObject("dashboard").put("title",dashboard.getTitle());
        for(Panel panel : dashboard.getPanels()){
            JSONObject panel_object = new JSONObject(panel_json);
            String sensorName = panel.getLot().getName() + ":" + panel.getSensor().getSensorName() +
                    ":" + panel.getSensorNumber();
            panel_object.getJSONArray("targets").getJSONObject(0).put("measurement",sensorName);
            panel_object.put("title",panel.getTitle());
            if(panel.getType() == Panel.PanelType.TABLE){
                panel_object.put("type","table");
            }
            if(panel.getType() == Panel.PanelType.GRAPH){
                panel_object.put("type","graph");
            }
            dashboard_object.getJSONObject("dashboards").getJSONArray("panels").put(panel_object);
        }
        dashboard_object.getJSONObject("dashboard").getJSONObject("time").put("from",dashboard.getFrom());
        dashboard_object.getJSONObject("dashboard").getJSONObject("time").put("to",dashboard.getTo());
        System.out.println(dashboard_object.toString());
        return dashboard_object.toString();
    }

    public void saveDashboard(Dashboard dashboard) throws IOException {
        URL obj = new URL(url+DASHBOARD_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("Authorization", "Bearer "+apiKey);
        con.setRequestProperty("Content-Type", "application/json");

        // Send post request
        con.setDoOutput(true);
        //DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        BufferedOutputStream os = new BufferedOutputStream(con.getOutputStream());
        String json = toJson(dashboard);
        os.write(json.getBytes());
        os.flush();

        //wr.writeChars(json);
        //wr.flush();

        int responseCode = con.getResponseCode();
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());
    }

    public static void main(String[] args) throws IOException {
//        Dashboard d = new Dashboard();
//        d.setTitle("Test #"+new Random().nextInt());
//        d.setFrom(new Date(10000000));
//        d.setTo(new Date(20000000));
//        Panel p1 = new Panel("panel1");
//        p1.setLot("School");
//        p1.setSensor("temperature random");
//        p1.setSensorNumber(0);
//        p1.setType(Panel.PanelType.GRAPH);
//        d.addPanel(p1);
//        Panel p2 = new Panel();
//        p2.setTitle("panel2");
//        p2.setLot("School");
//        p2.setSensor("temperature random");
//        p2.setSensorNumber(1);
//        p2.setType(Panel.PanelType.TABLE);
//        d.addPanel(p2);
//        DashboardSerializer ser = new DashboardSerializer("http://localhost:3000","eyJrIjoicjg3SmZuMFhoTWxLTEFoUDdUcUFNaUx2M1NENnFoRWgiLCJuIjoia2V5IiwiaWQiOjF9");
//        ser.saveDashboard(d);
    }

}
