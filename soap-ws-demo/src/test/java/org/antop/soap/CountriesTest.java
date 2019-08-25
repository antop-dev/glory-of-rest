package org.antop.soap;

import io.spring.guides.gs_producing_web_service.Country;
import io.spring.guides.gs_producing_web_service.Currency;
import io.spring.guides.gs_producing_web_service.GetCountryRequest;
import io.spring.guides.gs_producing_web_service.GetCountryResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CountriesTest extends WebServiceGatewaySupport {
    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        setDefaultUri("http://localhost:" + port + "/ws/countries");

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("io.spring.guides.gs_producing_web_service");

        setMarshaller(marshaller);
        setUnmarshaller(marshaller);
    }

    @Test
    public void getCountry() {
        String name = "Poland";
        GetCountryResponse response = getCountryRequest(name);

        Country country = response.getCountry();
        // verify
        assertNotNull(country);
        assertThat(country.getName(), equalTo(name));
        assertThat(country.getCapital(), equalTo("Warsaw"));
        assertThat(country.getCurrency(), is(Currency.PLN));
        assertThat(country.getPopulation(), equalTo(38186860));
    }

    @Test
    public void getCountryKorea() {
        String name = "Korea";
        GetCountryResponse response = getCountryRequest(name);
        // verify
        assertNull(response.getCountry());
    }

    public GetCountryResponse getCountryRequest(String name) {
        GetCountryRequest request = new GetCountryRequest();
        request.setName(name);
        return (GetCountryResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }


}
