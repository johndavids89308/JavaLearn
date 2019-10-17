import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParsCsv {

    private File file;
    private Map<Integer, String> hmFormatList = new HashMap<>();

    // Конструктор

    public ParsCsv(File file){
        this.file = file;
    }

    // Метод форматирует список строк к единому формату для удобной обработки.

    private void formatString() throws IOException {

        List<String> list = Files.readAllLines(Paths.get(file.getAbsolutePath()));
        int i = 0;
        for (String line : list) {
            // Убрать лишние символы "
            String delChar = line.replace("\"", "");

            // Узнать колличество запятых, чтобы свормировать единый формат строк
            String[] countChars = line.split(",");

            if (countChars.length > 8) {
                int index = delChar.lastIndexOf(",");
                // Перевод строки в массив символов
                char[] arrayChar = delChar.toCharArray();
                // Замена последней запятой на точку
                arrayChar[index] = '.';
                // Перевод массива символов в строку
                delChar = String.valueOf(arrayChar);
            }
            //formatList.add(delChar);

            hmFormatList.put(i, delChar);
            i++;
        }
    }

    // Сортировка по контрагенту с общей суммой для каждого и пишет в CSV

    public void sortCounterparty() throws IOException {

        PrintWriter writer = new PrintWriter("data/sortCounterparty.csv");

        List<String> codeArr = new ArrayList<>();
        String code;
        double consumptionSum;
        double comingSum;

        formatString();

        writeOutFile(writer,hmFormatList.get(0) + "\n");

        for (int j = 0; j < hmFormatList.size(); j++){

            consumptionSum = 0;
            comingSum = 0;

            String[] fragments1 = hmFormatList.get(j).split(",");
            String[] descriptions = fragments1[5].split("\\s");
            code = descriptions[descriptions.length-1];

            // Проверка на добавление дублей

            if (codeArr.size() > 0 && codeArr.contains(code)){
                j++;
                continue;
            }

            codeArr.add(code);

            for (int i = 1; i < hmFormatList.size(); i++){
                if (hmFormatList.get(i).contains(code)){

                    String[] fragments2 = hmFormatList.get(i).split(",");

                    consumptionSum += Double.parseDouble(fragments2[fragments2.length-1]);
                    comingSum += Double.parseDouble(fragments2[fragments2.length-2]);

                    writeOutFile(writer,hmFormatList.get(i) + "\n");
                }
            }

            if (j != 0) {
                writeOutFile(writer,"Текущий приход,,,,,," + comingSum + ",\n");
                writeOutFile(writer,"Текущий расход,,,,,,," + consumptionSum + "\n");
            }
        }
        writer.flush();
        writer.close();
    }

    // Метод считает общий приход-расход и пишет в CSV

    public void publicInOut() throws IOException{
        PrintWriter writer = new PrintWriter("data/publicInInOut.csv");
        double in = 0;
        double out = 0;

        formatString();

        writeOutFile(writer,hmFormatList.get(0) + "\n");

        for (int i = 1; i < hmFormatList.size(); i++){
            String[] fragments = hmFormatList.get(i).split(",");
            out += Double.parseDouble(fragments[fragments.length-1]);
            in += Double.parseDouble(fragments[fragments.length-2]);

            writeOutFile(writer,hmFormatList.get(i) + "\n");
        }

        writeOutFile(writer,"Общий приход,,,,,," + in + ",\n");
        writeOutFile(writer,"Общий расход,,,,,,," + out + "\n");

        writer.flush();
        writer.close();
    }

    // Записать в файл таблицы

    private void writeOutFile(PrintWriter writer,String data) throws IOException {
        writer.write(data);
    }

}
