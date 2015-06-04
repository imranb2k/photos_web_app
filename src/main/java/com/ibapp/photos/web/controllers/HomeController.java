package com.ibapp.photos.web.controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibapp.photos.web.configuration.User;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAuthzResponse;
import org.apache.oltu.oauth2.client.response.OAuthClientResponse;
import org.apache.oltu.oauth2.common.OAuthProviderType;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by imranbordiwala on 02/06/2015.
 */

@Controller
public class HomeController {

    private RestTemplate restTemplate = new RestTemplate();

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHomePage() throws Exception{

        return "homepage";
    }

    @RequestMapping(value = "/home2", method = RequestMethod.GET)
    public String getHomePage2(Model model) throws Exception{

        String userUrl = "https://api.instagram.com/v1/users/self/?access_token=2093734493.f74ee4d.5a8b304f80df4fad80a09b02e7c38a69";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<String> responseEntity2 = restTemplate.getForEntity(userUrl,String.class);
        System.out.println(responseEntity2.getBody());

        ResponseEntity<User> responseEntity = restTemplate.getForEntity(userUrl, User.class);
        User user = restTemplate.getForObject(userUrl,User.class);
      //  System.out.println(user.getUsername());
        System.out.println("Response is " + responseEntity.getBody());

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        User user2 = mapper.readValue(responseEntity2.getBody(), User.class);
        System.out.println(user2.getUsername());

      //  System.out.println(user2.getUsername());


//        ResponseEntity<User> responseEntity2 = restTemplate.getForEntity(userUrl, User.class);

       // User user = responseEntity2.getBody();

        model.addAttribute("user", user);

        return "homepage";
    }

    private List<HttpMessageConverter<?>> getMessageConverters() {

        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(new MappingJackson2HttpMessageConverter());

        return converters;
    }

    @RequestMapping(value = "/submitHome", method = RequestMethod.POST)
    public String postHomePage() throws Exception{

        String tokenURL = "https://api.instagram.com/oauth/access_token?client_id=f74ee4d7248b47f88574713ffe48f252&client_secret=2407d606c6144f42a3ff592572cf56b3&grant_type=authorization_code&redirect_uri=http://localhost:8089/photosWebApp/home&code=6e9bd9e0d7164705abded9ca3f498b7f";

        return "redirect:" + tokenURL;
    }

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String signIn() throws Exception{
        OAuthClientRequest codeRequest = OAuthClientRequest
                .authorizationProvider(OAuthProviderType.INSTAGRAM)
                .setRedirectURI("http://localhost:8089/photosWebApp/home")
                .setClientId("f74ee4d7248b47f88574713ffe48f252").buildQueryMessage();

        System.out.println(codeRequest.getLocationUri());

        String codeURL = "https://api.instagram.com/oauth/authorize/?client_id=f74ee4d7248b47f88574713ffe48f252&redirect_uri=http://localhost:8089/photosWebApp/home&response_type=code";
        String tokenURL = "https://api.instagram.com/oauth/authorize/?client_id=f74ee4d7248b47f88574713ffe48f252&redirect_uri=http://localhost:8089/photosWebApp/home&response_type=token";

        return "redirect:" + tokenURL;
    }

}
