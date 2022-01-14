package com.example.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

// import io.dekorate.docker.annotation.DockerBuild;
// import io.dekorate.kubernetes.annotation.Annotation;
// import io.dekorate.jib.annotation.JibBuild;
// import io.dekorate.kubernetes.annotation.KubernetesApplication;

@SpringBootApplication
// @EnableAspectJAutoProxy
public class AdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}

}
