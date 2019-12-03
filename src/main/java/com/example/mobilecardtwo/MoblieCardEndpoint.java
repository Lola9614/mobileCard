package com.example.mobilecardtwo;

import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.property.StructuredName;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

@RestController
@RequestMapping("/mobileCard")
public class MoblieCardEndpoint {

    @GetMapping("/search")
    public ResponseEntity searchUser(@RequestParam String searchPerson) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://adm.edu.p.lodz.pl/user/users.php?search=" + searchPerson;

        Document doc = Jsoup.connect(url).get();
        Elements users = doc.select("div.user-info");

        StringBuffer buffer = new StringBuffer();
        buffer.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        String button = "<form action= \"DOMEN" +
                        " \"> " +
                        "<input type =\"button\" value = \"Generate VCard\">" +
                        " </form>";

        for (Element e : users) {
            buffer.append(e.toString());
            buffer.append("<a href=" + "/moblieCard/users/"+ searchPerson + "/generate/" + 1 + "><button>Generate Vcard</button></a>");
        }
        return ResponseEntity.ok(buffer.toString());
    }

    @GetMapping("/users/{personName}/generate/{id}")
    public ResponseEntity generate(@PathVariable String personName, @PathVariable int id) throws IOException {

        Document doc = Jsoup.connect("https://adm.edu.p.lodz.pl/user/users.php?search=" + personName).get();
        Elements users = doc.select("div.user-info");

        Elements name = users.select("h3").select("a");
        Elements userInfo = users.select("span.item-content");

        String currentName = null;
        String info = null;

        int i = 0;
        for (Element e : name) {
            if (i++ == id) {
                currentName = e.text();
                break;
            }
        }
        i=0;
        for (Element e : userInfo) {
            if (i++ == id) {
                info = e.text();
                break;
            }
        }


        VCard vcard = new VCard();
        vcard.setFormattedName(currentName);
        vcard.setOrganization(info);

        String str = Ezvcard.write(vcard).version(VCardVersion.V4_0).go();

        return ResponseEntity.ok(str);

    }

}
