package io.intertec.securitydemo.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Profile("form")
@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("login")
    public String getLoginView() {
      return "login";
    }

    @GetMapping("success")
    public String getSuccessfulLoginView() {
      return "success";
    }

    @GetMapping("authenticated")
    public String onlyAuthenticatedUsersPage() { return "authenticated";}
}