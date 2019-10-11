import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class Main {
    static public void main(String[] args){

        try {

            long kb = 1024;
            long mb = 1024 * 1024;
            long gb = 1024 * 1024 * 1024;

            Files.walk(Paths.get("scan"))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList())
                    .forEach(file -> {
                        if(file.length() < kb){
                            System.out.println(file + " : " + file.length() + " B");
                        }else if (file.length() >= kb && file.length() < mb){
                            System.out.println(file + " : " + file.length() / kb + " KB");
                        }else if(file.length() >= mb && file.length() < gb){
                            System.out.println(file + " : " + file.length() / mb + " MB");
                        }else {
                            System.out.println(file + " : " + file.length() / gb + " GB");
                        }
                    });
        }catch (Exception e){
            e.getStackTrace();
        }
    }
}
