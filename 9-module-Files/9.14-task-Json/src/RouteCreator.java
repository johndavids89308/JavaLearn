import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class RouteCreator {

    private PrintWriter writer = new PrintWriter("data/metroMsk.json");
    private List<String> stationList = new ArrayList<>();
    private Map<String, ArrayList<String>> finalStructure = new LinkedHashMap<>();
    private String dataFile = "data/metroMsk.json";

    public RouteCreator() throws FileNotFoundException {
    }




    // Создание списка линий

    private void stationList(Elements elements){
        for (int j = 1; j < elements.size(); j++){

            // Перебор всех TR по очереди

            Elements tdList = elements.get(j).select("td");

            // Получение текущей линии

            String lineNumSpan = tdList.select("span").get(0).text();

            // Убрать лишние нули

            if(lineNumSpan.charAt(0) == '0'){
                lineNumSpan = lineNumSpan.substring(1);
            }

            // Добавие линии в список

            if (!stationList.contains(lineNumSpan)){
                stationList.add(lineNumSpan);
            }
        }
    }

    // Создать список линий -> станций

    private void createMapWithData(Elements elements){

        for (int k = 0; k < stationList.size(); k++) {

            String stationNum = stationList.get(k);

            finalStructure.put(stationNum, new ArrayList<String>());

            // Перебор всех TR

            for (int i = 1; i < elements.size(); i++) {

                Elements tdList = elements.get(i).select("td");

                // Получение имени станции

                String stationName = elements.get(i).child(1).text();

                // Получение текущей линии

                String lineNumSpan = tdList.select("span").get(0).text();
                Elements lineNumSpanTestTwo = tdList.select("td").get(0).children();
                String lineNumSpanTwoText = "";

                if (lineNumSpanTestTwo.size() == 6){
                    lineNumSpanTwoText = lineNumSpanTestTwo.get(3).text();
                }

                // Удаление лишних нулей у текущей линии

                if (lineNumSpan.charAt(0) == '0') {
                    lineNumSpan = lineNumSpan.substring(1);
                }

                // Получение имени станции если в предыдущем варианте получили пустое имя

                if (stationName.isEmpty()) {
                    stationName = elements.get(i).getElementsByAttribute("href").get(1).text();
                }

                if (stationNum.equals(lineNumSpan) || stationNum.equals(lineNumSpanTwoText)) {
                    finalStructure.get(stationNum).add(stationName);
                }
            }
        }

    }

    // Запись в файл Json

    private void writeDataToJson(){
        writer.write("{\n\t\"stations\" : {\n");

        int l = 0;

        // Перебор HashMap и запись станций

        for (Map.Entry<String, ArrayList<String>> entry : finalStructure.entrySet()){
            String key = entry.getKey();
            ArrayList<String> value = entry.getValue();

            writer.write("\t\t\"" + key + "\" : [");

            int m = 0;

            for (String elem : value){
                if (m == value.size()-1){
                    writer.write("\n\t\t\t\"" + elem + "\"");
                }else{
                    writer.write("\n\t\t\t\"" + elem + "\",");
                }
                m++;
            }

            if (l == finalStructure.size()-1){
                writer.write("\n\t\t]\n");
            }else {
                writer.write("\n\t\t],\n");
            }
            l++;
        }

        writer.write("\t}\n}");
        writer.flush();
        writer.close();
    }

    // Исполнитель

    public void parsHtmlData(String url) throws Exception {
        Document doc = Jsoup.connect(url).maxBodySize(0).get();
        Elements tableList = doc.select("table.standard");
        Elements trList = tableList.get(0).select("tr");

        // Создание списка станций

        stationList(trList);

        // Перебор станций

        createMapWithData(trList);

        // Запись в файл Json

        writeDataToJson();
    }

    public void jsonParser() throws Exception {
        JSONParser parser = new JSONParser();
        JSONObject jsonData = (JSONObject) parser.parse(getJsonFile());
        JSONObject stationsObject = (JSONObject) jsonData.get("stations");

        stationsObject.keySet().forEach(line ->{

            Object StationsObj = stationsObject.get(line);
            JSONArray stationsArr = (JSONArray) StationsObj;
            int j = 0;
            j += stationsArr.stream().count();
            System.out.println("Линия " + line + " : Станции " + j);

        });
    }

    private String getJsonFile() throws IOException {
        StringBuilder builder = new StringBuilder();
        List<String> lines = Files.readAllLines(Paths.get(dataFile));
        lines.forEach(line -> builder.append(line));
        return builder.toString();
    }

}
