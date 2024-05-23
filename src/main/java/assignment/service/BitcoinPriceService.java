package assignment.service;

import assignment.repository.BitcoinPriceRepository;
import assignment.entity.BitcoinPrice;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class BitcoinPriceService {

    @Autowired
    private BitcoinPriceRepository repository;

    public void fetchAndSaveBitcoinPrice() {
        String url = "https://api.coindesk.com/v1/bpi/currentprice.json";
        var  restTemplate = new RestTemplate();
        var  response = restTemplate.getForObject(url, String.class);
        repository.saveAll(BitcoinPriceService.parseBpiJson(response));

    }


    private static List<BitcoinPrice> parseBpiJson(String json) {
        var bpiList = new ArrayList<BitcoinPrice>();
        try {
            var mapper = new ObjectMapper();
            var root = mapper.readTree(json);
            var bpiNode = root.path("bpi");

            Iterator<JsonNode> elements = bpiNode.elements();
            while (elements.hasNext()) {
                var currencyNode = elements.next();
                var bpi = new BitcoinPrice();
                bpi.setCode(currencyNode.path("code").asText());
                bpi.setDescription(currencyNode.path("description").asText());
                bpi.setRateFloat((float) currencyNode.path("rate_float").asDouble());
                bpiList.add(bpi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bpiList;
    }
}
