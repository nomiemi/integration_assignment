package assignment.controller;

import assignment.service.BitcoinPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BitcoinPriceController {

    @Autowired
    private BitcoinPriceService service;

    @GetMapping("/fetch")
    public ResponseEntity<String> fetchBitcoinPrice() {
        service.fetchAndSaveBitcoinPrice();
        return ResponseEntity.ok("Data fetched and saved");
    }
}
