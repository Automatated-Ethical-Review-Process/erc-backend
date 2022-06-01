package com.g7.ercsystem.rest.auth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*",maxAge = 3600)
public class TestController {

    @GetMapping(value = "/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin(){
        return "admin";
    }
    @GetMapping(value = "/applicant")
    @PreAuthorize("hasRole('APPLICANT')")
    public String applicant(){
        return "applicant";
    }
    @GetMapping(value = "/reviewer")
    @PreAuthorize("hasRole('REVIEWER')")
    public String reviewer(){
        return "reviewer";
    }
    @GetMapping(value = "/secretary")
    @PreAuthorize("hasRole('SECRETARY')")
    public String secretary(){
        return "secretary";
    }
    @GetMapping(value = "/clerk")
    @PreAuthorize("hasRole('CLERK')")
    public String clerk(){
        return "clerk";
    }
}
