package ntou.soselab.order.client;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRule;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactFragment;
import ntou.soselab.order.client.dto.BookResultDTO;
import ntou.soselab.order.client.dto.BookShowDTO;
import ntou.soselab.order.client.dto.UserDTO;
import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserClientTest {

    @Rule
    public PactProviderRule mockProvider = new PactProviderRule("user", "localhost", 8085, this);

    @Autowired
    private UserClient userClient;

    private final String userId = "82b9d2a6-6db5-410a-8ec3-f8ac68c9ccad";
    private final String name = "Ben";
    private final String phone = "0912854015";

    @Test
    @PactVerification
    public void bookShow() throws Exception {
        UserDTO userDTO = userClient.checkUser(userId);
        assertThat(userDTO)
                .hasFieldOrPropertyWithValue("id", userId)
                .hasFieldOrPropertyWithValue("name", name)
                .hasFieldOrPropertyWithValue("phone", phone);
    }


    @Pact(consumer = "order")
    public PactFragment createFragment(PactDslWithProvider builder) {
        JSONObject result = new JSONObject()
                .put("id", userId)
                .put("phone", phone)
                .put("name", name);
        return builder
                .given(String.format("The user, Ben, is exists"))
                .uponReceiving("Get la la land movie detail")
                .path("/" + userId)
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(result)
                .toFragment();
    }

}