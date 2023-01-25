package com.famly.controllers;


import com.famly.entity.AuthenticationRequest;
import com.famly.entity.AuthenticationResponse;
import com.famly.services.MyUserDetailsService;
import com.famly.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping(path = "/AuthService")
class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @RequestMapping({ "/hello" })
    public String firstPage() {
        return "Hello World";
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        System.out.println("Current user to log in ---- " + authenticationRequest.toString());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                            authenticationRequest.getPassword(),userDetails.getAuthorities())
            );
        }
        catch (LockedException e) {
            System.out.println("Locked exception exception ---- " + e.toString());
            throw new Exception("Incorrect username or password", e);
        }catch (BadCredentialsException e) {
            System.out.println("creds exception ---- " + e.toString());
            throw new Exception("Incorrect username or password", e);
        }
        catch (Exception e) {
            System.out.println("auth exception ---- " + e.toString());
            throw new Exception("Incorrect username or password", e);
        }

        System.out.println("authenticate endpoint request---- " + authenticationRequest.toString());

        System.out.println("authenticate endpoint ---- " + userDetails.toString());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> registerUserAndReturnAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);
        // TODO update the USer table with token and creds.
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
