package com.example.EmployeeService.controllers;

import com.example.EmployeeService.entity.Employee;
import com.example.EmployeeService.exception.ApiException;
import com.example.EmployeeService.payload.JwtAuthRequest;
import com.example.EmployeeService.payload.JwtAuthResponse;
import com.example.EmployeeService.security.JwtTokenHelper;
import com.example.EmployeeService.services.EmployeeService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtTokenHelper jwtTokenHelper;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    @Autowired
    private EmployeeService employeeService;
    public AuthController(JwtTokenHelper jwtTokenHelper, UserDetailsService userDetailsService, AuthenticationManager authenticationManager) {
        this.jwtTokenHelper = jwtTokenHelper;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@NotNull @RequestBody JwtAuthRequest request) throws Exception {
        System.out.println("Inside the controller");
        this.authenticate(request.getUsername(),request.getPassword());
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
        System.out.println("Spring user details "+userDetails);
        String token = this.jwtTokenHelper.generateToken(userDetails);
        System.out.println("Token"+token);
        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);
        response.setEmployee((Employee) userDetails);
        return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,password);
        try{
            this.authenticationManager.authenticate(authenticationToken);
        }catch (BadCredentialsException e){
            System.out.println("Invalid Credentials !!");
            throw new ApiException("Invalid Username or Password !!");
        }

    }
}
