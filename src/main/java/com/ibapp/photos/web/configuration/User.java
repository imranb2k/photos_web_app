package com.ibapp.photos.web.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Created by imranbordiwala on 04/06/2015.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
