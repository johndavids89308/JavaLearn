import org.apache.commons.io.FileUtils;
import java.io.File;

public class Main {
    public static void main(String[] args){

        try {
/*            PrintWriter writer = new PrintWriter("data/file.txt");
            writer.write("HELLO");
            writer.flush();
            writer.close();*/

            // 1 Вариант
            CopyDir copyDir = new CopyDir();
            copyDir.doCopy(new File("data"), new File("dataForCopy"));

            // 2 Вариант
            FileUtils.copyDirectory(new File("data"), new File("dataForCopy"));


        }catch (Exception e){
            e.fillInStackTrace();
        }
    }
}
