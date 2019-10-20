import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HtmlPars {

    public void htmlParse(String url) throws Exception {

        Document doc = Jsoup.connect(url).get();
        Elements img = doc.select("img");
        InputStream in;

        String[] fileNameArr;

        for(Element element : img){
            String link = element.absUrl("src");
            fileNameArr = link.split("/");
            String fileName = fileNameArr[fileNameArr.length - 1];

            if (!fileName.contains("gif")
                    && !fileName.contains("jpg")
                    && !fileName.contains("png")
                    && !fileName.contains("jpeg")){
                continue;
            }

            in = new URL(link).openStream();
            Files.copy(in, Paths.get("data/" + fileName));
        }
    }

}
