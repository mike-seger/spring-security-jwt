package com.net128.app.gw;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@EnableConfigurationProperties
@RequestMapping("/")
@SuppressWarnings("unused")
public class IndexController {
	@Value("${spring.application.name:gw}")
	String application;

	@Autowired
	GatewayRoutes gatewayRoutes;

	@GetMapping("/headers")
	public ResponseEntity<Mono<?>> headers(ServerWebExchange exchange) {
		return new ResponseEntity<>(Mono.just(exchange.getRequest().getHeaders()), HttpStatus.OK);
	}

	@GetMapping("/info")
	public ResponseEntity<GatewayInfo> info(ServerWebExchange exchange) {
		return new ResponseEntity<>(gatewayInfo(), HttpStatus.OK);
	}

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("gatewayInfo", gatewayInfo());
		return "index";
	}

	public GatewayInfo gatewayInfo() {
		return new GatewayInfo(
			application,
			gatewayRoutes.routes.stream().filter(
				route -> !route.uri.equals("no://op")).map(
				route ->
				new ServerInfo(
					route.id,
					route.predicates.stream().filter(p -> p.startsWith("Path="))
						.map(p -> p.replace("Path=", "")
							.replace("/**", "")
						).findFirst().orElse("") + "/"
				)
			).collect(Collectors.toList())
		);
	}
}

class GatewayInfo {
	public String application;
	public List<ServerInfo> serverInfos;

	public GatewayInfo(String application, List<ServerInfo> serverInfos) {
		this.application = application;
		this.serverInfos = serverInfos;
	}
}

@Component
@ConfigurationProperties(prefix="spring.cloud.gateway")
@Data
class GatewayRoutes {
	List<GatewayRoute> routes;
}

@Component
@ConfigurationProperties(prefix="spring.cloud.gateway.routes")
@Data
class GatewayRoute {
	String id;
	List<String> predicates;
	String uri;
}

class ServerInfo {
	public String name;
	public String root;
	public String actuator;
	public String info;
	public String api;

	public ServerInfo(String name, String root) {
		this.name = name;
		this.root = root;
		this.actuator = root + "actuator/";
		this.info = root + "info/";
		this.api = root + "api/";
	}
}
