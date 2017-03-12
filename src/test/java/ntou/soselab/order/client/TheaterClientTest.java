package ntou.soselab.order.client;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRule;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactFragment;
import ntou.soselab.order.client.dto.BookResultDTO;
import ntou.soselab.order.client.dto.BookShowDTO;
import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class TheaterClientTest {

    @Rule
    public PactProviderRule mockProvider = new PactProviderRule("theater", "localhost", 8085, this);

    @Autowired
    private TheaterClient theaterClient;

    private final String showId = "5ef2bf0d-6dbf-4de8-a095-93690854da5b";

    @Test
    @PactVerification
    public void bookShow() throws Exception {
        BookResultDTO bookResultDTO = theaterClient.bookShow(BookShowDTO.builder()
                .showId(showId)
                .tickets(2)
                .build());
        assertThat(bookResultDTO.isSuccess()).isTrue();
    }


    @Pact(consumer = "order")
    public PactFragment createFragment(PactDslWithProvider builder) {
        JSONObject book = new JSONObject()
                .put("showId", showId)
                .put("tickets", 2);
        JSONObject result = new JSONObject()
                .put("success", true)
                .put("reason", "");
        return builder
                .given(String.format("The show id'%s' is exist and has 2 more seats", showId))
                .uponReceiving("Get la la land movie detail")
                .path("/book")
                .method("POST")
                .body(book)
                .willRespondWith()
                .status(200)
                .body(result)
                .toFragment();
    }

}