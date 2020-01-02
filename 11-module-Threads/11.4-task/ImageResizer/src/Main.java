import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main
{

    public static void main(String[] args) {

        String srcFolder = "imgOrig";
        String dstFolder = "dst";
        int newWidth = 300;
        int countCore = Runtime.getRuntime().availableProcessors();

        File srcDir = new File(srcFolder);
        File[] files = srcDir.listFiles();

        int countList = files.length / countCore + files.length % countCore;

        List<File[]> filesArr = ArrayDelimiter.arrayDelimiter(files, countList);

        Thread[] threads = new Thread[countCore];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new ImageResizer(filesArr.get(i), newWidth,
                        dstFolder, System.currentTimeMillis()));

        }

        for (Thread t : threads) {
            t.start();
        }

/*        for (File[] file : filesArr) {
            new ImageResizer(file, newWidth, dstFolder, System.currentTimeMillis()).run();
        }*/

    }
}
