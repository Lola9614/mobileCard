package com.example.mobilecardtwo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/mobileCard")
public class MoblieCardEndpoint {

    @GetMapping("/search")
    public ResponseEntity generateVCard(@RequestParam String searchText) throws IOException {
        Document doc = Jsoup.connect("https://adm.edu.p.lodz.pl/user/users.php?search="+searchText).get();

        Elements elements = doc.getElementsByClass("user-info");
        elements.forEach(element -> System.out.println(element));

        return ResponseEntity.ok().build();
    }

    @GetMapping("/generate"){

    }

}
