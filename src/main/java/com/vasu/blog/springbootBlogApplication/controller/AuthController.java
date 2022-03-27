package com.vasu.blog.springbootBlogApplication.controller;
import com.vasu.blog.springbootBlogApplication.dtos.JWTAuthResponse;
import com.vasu.blog.springbootBlogApplication.dtos.LoginDto;
import com.vasu.blog.springbootBlogApplication.dtos.SignUpDto;
import com.vasu.blog.springbootBlogApplication.entity.Role;
import com.vasu.blog.springbootBlogApplication.entity.User;
import com.vasu.blog.springbootBlogApplication.repository.RoleRepository;
import com.vasu.blog.springbootBlogApplication.repository.UserRepository;
import com.vasu.blog.springbootBlogApplication.security.JwtTokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collections;

@Api(value = "Auth controller exposes signin and signup REST APIs")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider tokenProvider;

    @ApiOperation(value = "REST API to Register or Signup user to Blog App")
    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> authenticatedUser(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //get token from tokenprovider
        String token = tokenProvider.generateToken(authentication);

        return  ResponseEntity.ok(new JWTAuthResponse(token));
    }
    @ApiOperation(value = "REST API to Signin or Login user to Blog App")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){
        //add check for username exists in database or not
        if(userRepository.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity<>("Username is already taken!",HttpStatus.BAD_REQUEST);
        }
        //add check for email exists in database or not
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email is already taken!" ,HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);
        return new ResponseEntity<>("User registered successfully",HttpStatus.OK);

    }
}










