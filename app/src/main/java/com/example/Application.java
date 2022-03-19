package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class Application {

   public static void main(String[] args) {
      SpringApplication.run(Application.class, args);
   }

   @Autowired
   private UserRepository userRepository;

   @PostConstruct
   public void initUsers() {
      List<User> users = Stream
            .of(User.builder().userName("john").password("$2a$10$yxMa7uWqfBlNpb6UTt.Rnumpvz1/8pgOCZRsWCD.aLAyL1M6F8b.O").email("john.doe@gmail.com").build(),
                  User.builder().userName("radha").password("$2a$10$zpdRB8eQgwsreyEX8svvr.QDG.pA.4Xq5epd/hL71TkvjMrm0Oz.G").email("radha.kumari@gmail.com").build(),
                  User.builder().userName("neha").password("$2a$10$7Ir5tKqveBcN6FzVZep8XOb/u.UDQT2ZXABZX/rhdjgQlHLa.yPMK").email("neha.parate@gmail.com").build(),
                  User.builder().userName("jane").password("$2a$10$YIVQrYBh9mODuR0pW1jUVua8lbqf9Bv1LmO/bw3WP8.fHwtEDc.Ba").email("jane.doe@gmail.com").build())
            .collect(Collectors.toList());
      userRepository.saveAll(users);
   }

   @Bean
   public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }
}
