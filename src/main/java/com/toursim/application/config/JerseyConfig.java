package com.toursim.application.config;

import com.toursim.application.city.CityController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig
{
    public JerseyConfig()
    {
        register(CityController.class);
    }
}
