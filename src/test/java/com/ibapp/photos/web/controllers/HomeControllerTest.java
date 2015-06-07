package com.ibapp.photos.web.controllers;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


/**
 * Created by imran on 05/06/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {

    private final String instagramURI = "https://api.instagram.com/";

    private final String mediaFeedByTagCarURI = "v1/tags/car/media/recent";

    private final String accessToken = "?access_token=2093734493.f74ee4d.5a8b304f80df4fad80a09b02e7c38a69";

    private MockMvc mockMvc;

    private RestTemplate restTemplate = new RestTemplate();

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setup() {

        mockMvc = MockMvcBuilders
                .standaloneSetup(new HomeController())
                .build();
    }

    @Test
    public void testHomeController() throws Exception {

        String mediaFeedResponse = restTemplate
                .getForObject(instagramURI + mediaFeedByTagCarURI + accessToken, String.class);

        JsonNode rootNode = mapper.readTree(mediaFeedResponse);
        List<JsonNode> lowResImgNode = rootNode.findValues("low_resolution");

        List<String> imageLinks = mapper.readValue(lowResImgNode.toString(), List.class);

        List<String> limitedImageLinks = imageLinks.subList(0,20);

        assertThat(limitedImageLinks.size(), is(20));

        this.mockMvc
                .perform(get("/home"))
                .andExpect(status().isOk())
                .andExpect(view().name("homepage"));

    }
}
