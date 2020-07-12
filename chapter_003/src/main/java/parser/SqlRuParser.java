package parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 2. Парсинг HTML страницы. [#281902]
 * @author Kirill Asmanov
 * @since 12.07.2020
 */

public class SqlRuParser {
    public static void main(String[] args) throws Exception {
        Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
        Elements row = doc.select(".postslisttopic");
        Elements info = doc.select(".altCol");
        int nameCounter = 0;
        int timeCounter = 1;
        for (Element td : row) {
            Element href = td.child(0);
            Element name = info.get(nameCounter);
            Element time = info.get(timeCounter);

            System.out.println(href.attr("href"));
            System.out.println(href.text());
            System.out.println(name.text());
            System.out.println(time.text());

            nameCounter += 2;
            timeCounter += 2;
        }
    }
}
