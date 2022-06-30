package com.googlesource.gerrit.plugins.examples.restapiservlet;

import static com.googlesource.gerrit.plugins.examples.restapiservlet.HelloResource.MY_KIND;

import com.google.gerrit.extensions.registration.DynamicMap;
import com.google.gerrit.extensions.restapi.RestApiModule;
import com.google.inject.AbstractModule;

public class Module extends AbstractModule {
    @Override
    protected void configure() {
        install(
            new RestApiModule() {
                @Override
                protected void configure() {
                    bind(HelloWorldCollection.class);
                    DynamicMap.mapOf(binder(), MY_KIND);
                }
            }
        );
    }
}
