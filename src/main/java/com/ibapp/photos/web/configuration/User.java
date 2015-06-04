package com.ibapp.photos.web.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Created by imranbordiwala on 04/06/2015.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    @JsonProperty("username")
    private String username;
    @JsonProperty("profile_picture")
    private String profile_picture;

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }
    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("profile_picture")
    public String getProfile_picture() {
        return profile_picture;
    }
    @JsonProperty("profile_picture")
    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }
}
