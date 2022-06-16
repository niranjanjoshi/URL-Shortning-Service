package com.URLShortner.URLShortnerService.repository;


import com.URLShortner.URLShortnerService.UrlShortnerServiceApplication;
import com.URLShortner.URLShortnerService.controller.UrlController;
import com.URLShortner.URLShortnerService.model.Url;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceInUseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = UrlShortnerServiceApplication.class)
@WebAppConfiguration
@ActiveProfiles("local")
@TestPropertySource(properties = { "amazon.dynamodb.endpoint=http://localhost:8000/", "amazon.aws.accesskey=test", "amazon.aws.secretkey=test" })
public class UrlRepositoryTest {

    private DynamoDBMapper dynamoDBMapper;
    @Mock
    private DynamoDBMapper dbMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @InjectMocks
    UrlRepository urlRepository;




    @Autowired
    UrlController urlController;

    private static final String EXPECTED_TITLE = "The Adventures of Tom Sawyer";

    @BeforeEach
    public void setup() {

        try {
            dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

            CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(Url.class);

            tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));

            amazonDynamoDB.createTable(tableRequest);
        } catch (ResourceInUseException e) {
            // Do nothing, table already created
        }

        dynamoDBMapper.batchDelete(urlRepository.findAll());
    }

}
