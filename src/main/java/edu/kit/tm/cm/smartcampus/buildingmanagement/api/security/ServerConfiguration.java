package edu.kit.tm.cm.smartcampus.buildingmanagement.api.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class ServerConfiguration {}
