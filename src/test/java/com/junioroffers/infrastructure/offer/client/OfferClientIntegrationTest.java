package com.junioroffers.infrastructure.offer.client;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.http.Fault;
import com.junioroffers.config.ConfigTest;
import com.junioroffers.infrastructure.RemoteOfferClient;
import com.junioroffers.infrastructure.offer.dto.OfferDto;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wiremock.org.apache.http.HttpStatus;
import org.springframework.util.SocketUtils;

import java.util.Arrays;
import java.util.Collections;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.BDDAssertions.then;

class OfferClientIntegrationTest implements SampleOfferDto{

    int port = SocketUtils.findAvailableTcpPort();

    WireMockServer wireMockServer;

    RemoteOfferClient remoteOfferClient = new ConfigTest()
                    .remoteOfferTestClient("http://localhost:" + port + "/offers", 1000,1000);

    @BeforeEach
    void setup(){
        wireMockServer = new WireMockServer(options().port(port));
        wireMockServer.start();
        WireMock.configureFor(port);
    }

    @AfterEach
    void tearDown(){
        wireMockServer.stop();
    }

    @Test
    void shouldReturnTwoJobOffers(){
        WireMock.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                    .withStatus(HttpStatus.SC_OK)
                    .withHeader("Content-Type", "application/json")
                    .withBody(bodyWithTwoOffersJson())));

        then(remoteOfferClient.getOffers())
                .containsExactlyInAnyOrderElementsOf(Arrays.asList(cybersourceSoftwareEngineer(), cdqJuniorDevOpsEngineer()));
    }

    @Test
    void shouldFailWithConnectionResetByPeer(){
        WireMock.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                    .withStatus(HttpStatus.SC_OK)
                    .withHeader("Content-Type", "application/json")
                    .withFault(Fault.CONNECTION_RESET_BY_PEER)));

        then(remoteOfferClient.getOffers().size()).isEqualTo(0);
    }

    @Test
    void shouldFailWithEmptyResponse(){
        WireMock.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader("Content-Type", "application/json")
                        .withFault(Fault.EMPTY_RESPONSE)));

        then(remoteOfferClient.getOffers().size()).isEqualTo(0);
    }

    @Test
    void shouldFailWithMalformed(){
        WireMock.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader("Content-Type", "application/json")
                        .withFault(Fault.MALFORMED_RESPONSE_CHUNK)));

        then(remoteOfferClient.getOffers().size()).isEqualTo(0);
    }

    @Test
    void shouldFailWithRandom(){
        WireMock.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader("Content-Type", "application/json")
                        .withFault(Fault.RANDOM_DATA_THEN_CLOSE)));

        then(remoteOfferClient.getOffers().size()).isEqualTo(0);
    }

    @Test
    void shouldReturnOneJobOffer(){
        WireMock.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader("Content-Type", "application/json")
                        .withBody(bodyWithOneOfferJson())));

        then(remoteOfferClient.getOffers())
                .containsExactlyInAnyOrderElementsOf(Collections.singletonList(cybersourceSoftwareEngineer()));
    }

    @Test
    void shouldReturnZeroJobOffersWhenStatusIsNoContent(){
        WireMock.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.SC_NO_CONTENT)
                        .withHeader("Content-Type", "application/json")
                        .withBody(bodyWithOneOfferJson())));

        then(remoteOfferClient.getOffers().size()).isEqualTo(0);
    }

    @Test
    void should_return_zero_job_offers_when_status_is_ok() {
        WireMock.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader("Content-Type", "application/json")
                        .withBody(bodyWithZeroOffersJson())));

        then(remoteOfferClient.getOffers().size()).isEqualTo(0);
    }

    @Test
    void should_return_response_not_found_status_exception_when_http_service_returning_not_found_status() {
        WireMock.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(HttpStatus.SC_NOT_FOUND))
        );

        BDDAssertions.thenThrownBy(() ->
                remoteOfferClient.getOffers())
                .hasMessage("404 NOT_FOUND");
    }

    @Test
    void should_return_response_unauthorized_status_exception_when_http_service_returning_unauthorized_status() {
        WireMock.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(HttpStatus.SC_UNAUTHORIZED))
        );

        BDDAssertions.thenThrownBy(() ->
                remoteOfferClient.getOffers())
                .hasMessage("401 UNAUTHORIZED");
    }

    @Test
    void should_return_zero_job_offers_when_response_delay_is_1500_milis() {
        final String uri = "http://localhost:" + port + "/offers";
        RemoteOfferClient remoteOfferClient = new ConfigTest().remoteOfferTestClient(uri, 1000, 1000);
        WireMock.stubFor(WireMock.get("/offers")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.SC_OK)
                        .withHeader("Content-Type", "application/json")
                        .withBody(bodyWithTwoOffersJson())
                        .withFixedDelay(1500)));

        then(remoteOfferClient.getOffers().size()).isEqualTo(0);
    }

    private String bodyWithTwoOffersJson() {
        return "[{\n" +
                "    \"title\": \"Software Engineer - Mobile (m/f/d)\",\n" +
                "    \"company\": \"Cybersource\",\n" +
                "    \"salary\": \"4k - 8k PLN\",\n" +
                "    \"offerUrl\": \"https://nofluffjobs.com/pl/job/software-engineer-mobile-m-f-d-cybersource-poznan-entavdpn\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"title\": \"Junior DevOps Engineer\",\n" +
                "    \"company\": \"CDQ Poland\",\n" +
                "    \"salary\": \"8k - 14k PLN\",\n" +
                "    \"offerUrl\": \"https://nofluffjobs.com/pl/job/junior-devops-engineer-cdq-poland-wroclaw-gnymtxqd\"\n" +
                "  }]";
    }

    private String bodyWithOneOfferJson() {
        return "[{\n" +
                "    \"title\": \"Software Engineer - Mobile (m/f/d)\",\n" +
                "    \"company\": \"Cybersource\",\n" +
                "    \"salary\": \"4k - 8k PLN\",\n" +
                "    \"offerUrl\": \"https://nofluffjobs.com/pl/job/software-engineer-mobile-m-f-d-cybersource-poznan-entavdpn\"\n" +
                "  }]";
    }

    private String bodyWithZeroOffersJson() {
        return "[]";
    }

    private OfferDto cybersourceSoftwareEngineer() {
        return offerWithParameters("Cybersource", "4k - 8k PLN", "https://nofluffjobs.com/pl/job/software-engineer-mobile-m-f-d-cybersource-poznan-entavdpn", "Software Engineer - Mobile (m/f/d)");
    }

    private OfferDto cdqJuniorDevOpsEngineer() {
        return offerWithParameters("CDQ Poland", "8k - 14k PLN", "https://nofluffjobs.com/pl/job/junior-devops-engineer-cdq-poland-wroclaw-gnymtxqd", "Junior DevOps Engineer");
    }

}
