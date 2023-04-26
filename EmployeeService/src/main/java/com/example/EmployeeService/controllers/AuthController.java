package com.example.EmployeeService.controllers;

import com.example.EmployeeService.entity.Employee;
import com.example.EmployeeService.exception.ApiException;
import com.example.EmployeeService.payload.JwtAuthRequest;
import com.example.EmployeeService.payload.JwtAuthResponse;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    @Autowired
    private EmployeeService employeeService;
    public AuthController(UserDetailsService userDetailsService, AuthenticationManager authenticationManager) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@NotNull @RequestBody JwtAuthRequest request) throws Exception {
        System.out.println("Inside the controller");
        this.authenticate(request.getUsername(),request.getPassword());
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
        System.out.println("Spring user details "+userDetails);
        String token = this.employeeService.generateToken(userDetails);
        System.out.println("Token"+token);
        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);
        response.setEmployee((Employee) userDetails);
        return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
    }
    @PostMapping("/token")
    public String getToken(@RequestBody JwtAuthRequest request) throws Exception{
        this.authenticate(request.getUsername(),request.getPassword());
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
        return employeeService.generateToken(userDetails);
    }
//
//    @PostMapping("/validate")
//    public Boolean validateToken(@RequestParam("token") String token,@RequestBody JwtAuthRequest request) throws Exception{
//        this.authenticate(request.getUsername(),request.getPassword());
//        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
//        return employeeService.validateToken(token,userDetails);
//    }



    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) throws Exception{
         employeeService.validToken(token);
         return "Token is Valid";
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
