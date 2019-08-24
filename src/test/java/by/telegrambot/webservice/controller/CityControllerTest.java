package by.telegrambot.webservice.controller;/*
package by.telegrambot.webservice.controller;

import by.telegrambot.webservice.entity.City;
import by.telegrambot.webservice.service.CityService;
import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sun.nio.cs.Surrogate.is;

@RunWith(SpringRunner.class)
@WebMvcTest(CityController.class)
public class CityControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    CityService cityService;

    @Test
    public void testGetAllMethod()
            throws Exception {

        City city = new City();
        city.setName("Moscow");
        List<City> allcities = new ArrayList<>();
        allcities.add(city);
        given(cityService.findAll()).willReturn(allcities);

        mvc.perform(get("/city/allcities")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$", hasSize(1)));
    }
}
*/
