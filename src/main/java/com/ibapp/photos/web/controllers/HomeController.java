package com.ibapp.photos.web.controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.ibapp.photos.web.configuration.User;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAuthzResponse;
import org.apache.oltu.oauth2.client.response.OAuthClientResponse;
import org.apache.oltu.oauth2.common.OAuthProviderType;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by imranbordiwala on 02/06/2015.
 */

@Controller
public class HomeController {

    private RestTemplate restTemplate = new RestTemplate();

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHomePage() throws Exception {

        return "homepage";
    }

    @RequestMapping(value = "/home2", method = RequestMethod.GET)
    public String getHomePage2(Model model) throws Exception {

        String mediaFeedUrl = "https://api.instagram.com/v1/users/self/feed?access_token=2093734493.f74ee4d.5a8b304f80df4fad80a09b02e7c38a69";

        ResponseEntity<String> responseEntity2 = restTemplate.getForEntity(mediaFeedUrl, String.class);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode rootNode = mapper.readTree(responseEntity2.getBody());
        List<JsonNode> codeNode = rootNode.findValues("low_resolution");

        List<User> users = mapper.readValue(codeNode.toString(), List.class);

        model.addAttribute("user", users);


        return "homepage";
    }

    @RequestMapping(value = "/home3", method = RequestMethod.GET)
    public String getHomePage3(Model model, @RequestParam("count") String count) throws Exception {

        String mediaFeedUrl = "https://api.instagram.com/v1/users/self/feed?count="+count+"&access_token=2093734493.f74ee4d.5a8b304f80df4fad80a09b02e7c38a69";

        ResponseEntity<String> responseEntity2 = restTemplate.getForEntity(mediaFeedUrl, String.class);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode rootNode = mapper.readTree(responseEntity2.getBody());
        List<JsonNode> codeNode = rootNode.findValues("low_resolution");

        List<User> users = mapper.readValue(codeNode.toString(), List.class);

        model.addAttribute("user", users);

        return "homepage";
    }

    @RequestMapping(value = "/home4", method = RequestMethod.GET)
    public String getHomePage4(Model model, @RequestParam("tag") String tag) throws Exception {

        String mediaFeedUrl = "https://api.instagram.com/v1/tags/"+tag+"/media/recent?access_token=2093734493.f74ee4d.5a8b304f80df4fad80a09b02e7c38a69";

        ResponseEntity<String> responseEntity2 = restTemplate.getForEntity(mediaFeedUrl, String.class);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode rootNode = mapper.readTree(responseEntity2.getBody());
        List<JsonNode> codeNode = rootNode.findValues("low_resolution");

        List<User> users = mapper.readValue(codeNode.toString(), List.class);

        model.addAttribute("user", users);

        return "homepage";
    }

    @RequestMapping(value = "/submitHome", method = RequestMethod.POST)
    public String postHomePage() throws Exception {

        String tokenURL = "https://api.instagram.com/oauth/access_token?client_id=f74ee4d7248b47f88574713ffe48f252&client_secret=2407d606c6144f42a3ff592572cf56b3&grant_type=authorization_code&redirect_uri=http://localhost:8089/photosWebApp/home&code=6e9bd9e0d7164705abded9ca3f498b7f";

        return "redirect:" + tokenURL;
    }

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String signIn() throws Exception {

        String codeURL = "https://api.instagram.com/oauth/authorize/?client_id=f74ee4d7248b47f88574713ffe48f252&redirect_uri=http://localhost:8089/photosWebApp/home&response_type=code";
        String tokenURL = "https://api.instagram.com/oauth/authorize/?client_id=f74ee4d7248b47f88574713ffe48f252&redirect_uri=http://localhost:8089/photosWebApp/home&response_type=token";

        return "redirect:" + tokenURL;
    }

}
