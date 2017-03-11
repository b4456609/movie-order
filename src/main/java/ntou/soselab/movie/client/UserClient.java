package ntou.soselab.movie.client;

import ntou.soselab.movie.client.dto.UserDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "user")
public interface UserClient {
    @RequestMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    UserDTO checkUser(@PathVariable("userId") String userId);
}
