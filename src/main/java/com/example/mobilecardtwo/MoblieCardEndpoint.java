package com.example.mobilecardtwo;

import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.VCardVersion;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
public class MoblieCardEndpoint {

    @GetMapping("/search")
    public ResponseEntity searchUser(@RequestParam String searchPerson) throws IOException {
        Document doc = Jsoup.connect("https://adm.edu.p.lodz.pl/user/users.php?search=" + searchPerson).get();
        Elements users = doc.select("div.user-info");

        StringBuffer buffer = new StringBuffer();
        buffer.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");

        for (Element e : users) {
            buffer.append(e.toString());
            buffer.append("<a href=" + "/vCard/"+ searchPerson + "/generate/" + 1 + "><button>Generate Vcard</button></a>");
        }
        return ResponseEntity.ok(buffer.toString());
    }

    @GetMapping("/vCard/{personName}/generate/{id}")
    public ResponseEntity<Resource> generate(@PathVariable String personName, @PathVariable int id) throws IOException {

        Document doc = Jsoup.connect("https://adm.edu.p.lodz.pl/user/users.php?search=" + personName).get();
        Elements users = doc.select("div.user-info");

        Elements name = users.select("h3").select("a");
        Elements userInfo = users.select("span.item-content");

        File file = new File("fileCard.vcf");
        VCard vcard = new VCard();

        int i = 0;
        for (Element e : name) {
            if (i++ == id) {
                vcard.setFormattedName(e.text());
                break;
            }
        }

        i=0;
        for (Element e : userInfo) {
            if (i++ == id) {
                vcard.setOrganization(e.text());
                break;
            }
        }

        Ezvcard.write(vcard).version(VCardVersion.V3_0).go(file);
        InputStreamResource resource = new InputStreamResource(new FileInputStream("fileCard.vcf"));

        return ResponseEntity
                .ok()
                .header("Content-Disposition", "attachment; filename=fileCard.vcf")
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("text/vcf")).body(resource);

    }

}
