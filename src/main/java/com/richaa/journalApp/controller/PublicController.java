package com.richaa.journalApp.controller;

import lombok.extern.slf4j.Slf4j;
import com.richaa.journalApp.entity.User;
import com.richaa.journalApp.service.EmailService;
import com.richaa.journalApp.service.UserDetailsServiceImpl;
import com.richaa.journalApp.service.UserService;
import com.richaa.journalApp.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/health-check")
    public String healthcheck() {

        return "ok";
    }

    @PostMapping("/signup")
    public void signup(@RequestBody User user) {

        userService.saveNewUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
        String jwt = jwtUtil.generateToken(userDetails.getUsername());
        return  new ResponseEntity<>(jwt, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception occurred while createAuthenticationToken ", e);
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }

    }
//    @PostMapping("/send-sentiment")
//    public ResponseEntity<String> sendSentimentEmail(@RequestBody EmailRequest emailRequest) {
//        try {
//            String subject = "Your Sentiment Summary";
//            String body = "Hi " + emailRequest.getUserName() + ",\n\n"
//                    + "Sentiment Analysis Result: " + emailRequest.getSentimentAnalysis() + "\n\n"
//                    + "Thanks,\nJournal App Team";
//
//            emailService.sendEmail(emailRequest.getEmail(), subject, body);
//            return ResponseEntity.ok("Sentiment email sent");
//        } catch (Exception e) {
//            log.error("Failed to send sentiment email", e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email");
//        }
    }

