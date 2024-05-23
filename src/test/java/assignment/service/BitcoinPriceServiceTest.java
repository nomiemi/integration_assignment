package assignment.service;


import assignment.entity.BitcoinPrice;
import assignment.repository.BitcoinPriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static javax.management.Query.eq;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest
public class BitcoinPriceServiceTest{

    @InjectMocks
    private BitcoinPriceService service;

    @Mock
    private BitcoinPriceRepository repository;

    private MockRestServiceServer mockServer;
    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        restTemplate = new RestTemplate();
        mockServer = MockRestServiceServer.createServer(restTemplate);
        service = new BitcoinPriceService();
    }

    @Test
    void testFetchAndSaveBitcoinPrice() {
        String apiResponse = """
            {"bpi": {
                    "USD": {
                        "code": "USD",
                        "symbol": "&#36;",
                        "rate": "26,568.6550",
                        "description": "United States Dollar",
                        "rate_float": 26568.655
                    },
                    "GBP": {
                        "code": "GBP",
                        "symbol": "&pound;",
                        "rate": "20,866.2268",
                        "description": "British Pound Sterling",
                        "rate_float": 20866.2268
                    },
                    "EUR": {
                        "code": "EUR",
                        "symbol": "&euro;",
                        "rate": "24,466.7104",
                        "description": "Euro",
                        "rate_float": 24466.7104
                    }
                }
            }
            """;

        mockServer.expect(requestTo("https://api.coindesk.com/v1/bpi/currentprice.json"))
                .andRespond(withSuccess(apiResponse, MediaType.APPLICATION_JSON));

        service.fetchAndSaveBitcoinPrice();

        ArgumentCaptor<BitcoinPrice> captor = ArgumentCaptor.forClass(BitcoinPrice.class);
        verify(repository).saveAll(anyList());

        List<BitcoinPrice> savedPrice = captor.getAllValues();
        assertThat(savedPrice).isNotEmpty();
       assertThat(savedPrice.size()).isEqualTo(3);
    }
}
