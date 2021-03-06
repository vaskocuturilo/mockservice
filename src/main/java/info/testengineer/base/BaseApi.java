package info.testengineer.base;

import com.github.tomakehurst.wiremock.WireMockServer;
import info.testengineer.listener.LogListener;
import info.testengineer.mock.MockApiService;
import io.restassured.RestAssured;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static info.testengineer.webconfig.WebConfig.BASE_CONFIG;

/**
 * The type Base api.
 */

@Listeners(LogListener.class)
public class BaseApi {

    /**
     * Value mWireMockServer.
     */
    private WireMockServer mWireMockServer;

    /**
     * Value HTTP_PORT.
     */
    public static final int HTTP_PORT = 8091;

    /**
     * Default constructor.
     */
    public BaseApi() {
        super();
        //empty
        return;
    }

    /**
     * Before test.
     */
    @BeforeTest(alwaysRun = true)
    public void beforeTest() {
        mWireMockServer = new WireMockServer(options().port(HTTP_PORT));
        mWireMockServer.start();

        final MockApiService mockApiService = new MockApiService();
        mockApiService.startMockService();

        RestAssured.baseURI = BASE_CONFIG.getApiUrl();

    }

    /**
     * After test.
     */
    @AfterTest
    public void afterTest() {
        mWireMockServer.stop();
    }
}
