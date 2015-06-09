package com.ibapp.photos.web.controllers;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Controller
public class HomeController {

    private final String instagramURI = "https://api.instagram.com/";

    private final String accessToken = "?access_token=2093734493.f74ee4d.5a8b304f80df4fad80a09b02e7c38a69";

    private RestTemplate restTemplate = new RestTemplate();

    private ObjectMapper mapper = new ObjectMapper();


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getMediaByTag(Model model, @RequestParam(value = "tag", required = false, defaultValue = "flowers")
                    String tag, @RequestParam(value = "numImg", required = false) Integer numImg) {

        String mediaFeedURI = instagramURI + "v1/tags/"+tag+"/media/recent" + accessToken;

        String mediaFeedResponse = null;

        try {
            mediaFeedResponse = restTemplate.getForObject(mediaFeedURI, String.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() != HttpStatus.NOT_FOUND)
                throw e;
        }

        List<String> imageLinks = null;

        try {

            JsonNode rootNode = mapper.readTree(mediaFeedResponse);
            List<JsonNode> lowResImgNode = rootNode.findValues("low_resolution");

            imageLinks = mapper.readValue(lowResImgNode.toString(), List.class);

        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        List<String> limitedImageLinks = null;

        if (numImg == null || numImg > 20) {
            limitedImageLinks = imageLinks.subList(0,20);
        } else {
            limitedImageLinks = imageLinks.subList(0,numImg);
        }

        model.addAttribute("imageLinks", limitedImageLinks);

        return "homepage";
    }

}