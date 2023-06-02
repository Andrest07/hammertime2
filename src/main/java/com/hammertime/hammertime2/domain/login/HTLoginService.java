package com.hammertime.hammertime2.domain.login;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hammertime.hammertime2.domain.client.Client;
import com.hammertime.hammertime2.domain.client.ClientRepository;
import com.hammertime.hammertime2.domain.professional.Professional;
import com.hammertime.hammertime2.domain.professional.ProfessionalRepository;

import jakarta.servlet.http.HttpServletRequest;

public class HTLoginService implements UserDetailsService {
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ProfessionalRepository tradieRepository;
 
    public HTLoginService() {

    }
 
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        List<Client> clients = clientRepository.findAll();
        for (Client client : clients) {
            System.out.println("\n\nEmail: [" + client.getEmail() + "]");
        }

        


        // Check if the username is an email
        if (!isValidEmailAddress(username)) {
            throw new UsernameNotFoundException("Invalid email address");
        }

        Professional tradie = null;
        tradie = tradieRepository.findByEmail(username);
        Client client = null;
        client = clientRepository.findByEmail(username);

        // Check if the user is a tradie
        if (tradie != null) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_TRADIE"));
            // session.setAttribute("userId", tradie.getId());

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            request.getSession().setAttribute("userID", tradie.getId());

            return User.builder()
                    .username(tradie.getEmail())
                    .password(tradie.getPassword())
                    .authorities(authorities)
                    .build();
        }
        
        // Check if the user is a client
        if (client != null) {
            System.out.println("\n\n\n-------" + username + "\n\n\n");
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_CLIENT"));
            
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            request.getSession().setAttribute("userID", client.getId());

            return User.builder()
                    .username(client.getEmail())
                    .password(client.getPassword())
                    .authorities(authorities)
                    .build();
        }

        if(username.equals("admin@ht.com")){
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            //session.setAttribute("userId", 1);
            
            
            return User.builder()
                    .username("admin@ht.com")
                    .password("admin")
                    .authorities(authorities)
                    .build();
        }
 
        
 
        // If the user is not found, throw an exception
        throw new UsernameNotFoundException("User not found");
    }
 
    private boolean isValidEmailAddress(String email) {
        // A simple regex to check if the email is valid
        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}