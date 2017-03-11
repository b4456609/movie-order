package ntou.soselab.movie.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "theater")
public interface TheaterClient {
    @RequestMapping(path = "/book", method = RequestMethod.POST)
    BookResultDTO bookShow(String showId);
}
