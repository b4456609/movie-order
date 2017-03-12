package ntou.soselab.order;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import soselab.easylearn.MCA.ProjectReader;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("mca")
public class MCATest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Value("${spring.application.name}")
    private String serviceName;

    // The package need to scan
    // private String packageName = "soselab.easyelarn";
    private String packageName = this.getClass().getPackage().getName();


    @Test
    public void test() {
        String mappingsJson = restTemplate.getForEntity("/mappings", String.class).getBody();
        String swaggerJson = restTemplate.getForEntity("/v2/api-docs", String.class).getBody();
        System.out.println(mappingsJson);
        System.out.println(swaggerJson);
        ProjectReader projectReader = new ProjectReader(serviceName, mappingsJson, swaggerJson, packageName);
        projectReader.execute();
    }
}
