package fr.polytech.dsl.dsl.execution;

import fr.polytech.dsl.dsl.model.structures.grafana.Dashboard;
import fr.polytech.dsl.dsl.model.structures.grafana.Panel;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
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
        for(Panel panel : dashboard.getPanels()){
            JSONObject panel_object = new JSONObject(panel_json);
            String sensorName = panel.getLot()+":"+panel.getSensor()+":"+panel.getSensorNumber();
            panel_object.getJSONObject("targets").put("measurement",sensorName);
            panel_object.put("title",panel.getTitle());
            if(panel.getType() == Panel.PanelType.TABLE){
                panel_object.put("type","table");
            }
            if(panel.getType() == Panel.PanelType.GRAPH){
                panel_object.put("type","graph");
            }
            dashboard_object.getJSONObject("dashboard").getJSONArray("panels").put(panel_object);
        }
        return null; //TODO
    }

    public void saveDashboard(Dashboard dashboard) throws IOException {
        URL obj = new URL(url+DASHBOARD_URL);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("Authorization", "Bearer "+apiKey);
        con.setRequestProperty("Content-Type", "application/json");

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        String json = toJson(dashboard);
        wr.writeChars(json);
        wr.flush();
        wr.close();

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

}
