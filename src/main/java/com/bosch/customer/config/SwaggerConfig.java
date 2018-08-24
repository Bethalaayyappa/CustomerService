
package com.bosch.customer.config;

import static com.google.common.base.Predicates.or;
import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan(basePackages = { "com.bosch.customer.controller", "com.bosch.customer.entity" })
public class SwaggerConfig {

	@Bean
	public Docket petApi() {

		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).paths(Predicates.not(PathSelectors.regex("/error"))).build()
				.pathMapping("/").directModelSubstitute(LocalDate.class, String.class)
				.genericModelSubstitutes(ResponseEntity.class)
				.alternateTypeRules(newRule(
						typeResolver.resolve(DeferredResult.class,
								typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
						typeResolver.resolve(WildcardType.class)))
				.useDefaultResponseMessages(false).apiInfo(apiInfo())
				.tags(new Tag("Orders", "Rest API's For Order information"),
						new Tag("Cart", "Rest API's For Cart information"))
				.globalResponseMessage(RequestMethod.GET,
						newArrayList(new ResponseMessageBuilder().code(500).message("500 message")
								.responseModel(new ModelRef("Error")).build()))
				.securitySchemes(newArrayList(apiKey())).securityContexts(newArrayList(securityContext()))
				.enableUrlTemplating(true);
	}

	@Autowired
	private TypeResolver typeResolver;

	private List<SecurityScheme> apiKey() {
		List<SecurityScheme> schemeList = new ArrayList<>();
		schemeList.add(new ApiKey("Authorization", "SecurityToken", "header"));
		schemeList.add(new ApiKey("X-OrganizationKey", "OrganizationKey", "header"));
		return schemeList;
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(petstorePaths()).build();
	}

	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return newArrayList(new SecurityReference("Authorization", authorizationScopes));
	}

	private Predicate<String> petstorePaths() {
		return or(regex("/services/*"));
	}

	@Bean
	SecurityConfiguration security() {
		return new SecurityConfiguration("test-app-client-id", "test-app-client-secret", "test-app-realm", "mcms-web",
				"apiKey", ApiKeyVehicle.HEADER, "api_key", ",");
	}

	@Bean
	UiConfiguration uiConfig() {
		return new UiConfiguration("validatorUrl");
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("BOSCH Orders and Cart API ").description("API's for Orders & Cart ")
				.version("1.0").build();
	}

}
