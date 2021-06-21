package com.dinesh.microservice.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p.path("/get")
						.filters(f -> f.addRequestHeader("MyHeader", "MyURI").addRequestParameter("Param", "MyValue"))
						.uri("http://httpbin.org:80"))
				.route(p -> p.path("/students/**").uri("lb://microservice1"))
				.route(p -> p.path("/students-feign/**").uri("lb://microservice1"))
				.route(p -> p.path("/address/**").uri("lb://microservice2"))
				.route(p -> p.path("/dinesh/**").filters(f -> f.rewritePath("/dinesh/(?<segment>.*)", "/students-feign/${segment}"))
						.uri("lb://microservice1"))
				.build();
	}

}
