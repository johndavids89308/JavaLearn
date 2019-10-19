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
        Elements newImg = doc.select("img.g-picture");
        InputStream in;
        String[] fileName;

        for(Element element : newImg){
            fileName = element.attr("src").split("/");
            in = new URL(element.attr("src")).openStream();
            Files.copy(in, Paths.get("data/" + fileName[fileName.length - 1]));
        }
    }

}
