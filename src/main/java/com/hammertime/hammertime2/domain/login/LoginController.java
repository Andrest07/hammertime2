package com.hammertime.hammertime2.domain.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hammertime.hammertime2.domain.client.Client;
import com.hammertime.hammertime2.domain.client.ClientRepository;
import com.hammertime.hammertime2.domain.professional.Professional;
import com.hammertime.hammertime2.domain.professional.ProfessionalRepository;

@RestController
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class LoginController {
    @Autowired
    ClientRepository clientRepository;
        
    @Autowired
    ProfessionalRepository tradieRepository;

    // @Autowired
    // private HTLoginService loginService;

    @GetMapping("/dataRequest/who")
    @ResponseBody
    public Long getUser(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            Client c = clientRepository.findByEmail(authentication.getName());
            Professional t = tradieRepository.findByEmail(authentication.getName());

            if(c != null){ return c.getId(); }
            if(t != null){ return t.getId(); }

            return null;
        } else {
            return null;
        }
    }

    @PostMapping("/dataRequest/signup")
    @Transactional
    public RedirectView processLogin(@RequestParam("user-type") String userType,
                                    @RequestParam("name") String name,
                                    @RequestParam("surname") String surname,
                                    @RequestParam(value = "business-name", required = false) String businessName,
                                    @RequestParam("email") String email,
                                    @RequestParam("address") String address,
                                    @RequestParam("phone") String phone,
                                    @RequestParam("password") String password,
                                    @RequestParam("confirm") String confirm,
                                    @RequestParam("accept-terms") boolean acceptTerms) {

        // Your existing code to save the user details
        if (userType.equals("clientD")) {
            Client client = new Client(name, surname, address, phone, email, password);
            clientRepository.save(client);

            
        } else {
            Professional professional = new Professional(name, surname, businessName, address, phone, email, password);
            tradieRepository.save(professional);
        }

        return new RedirectView("/login");
    }
}

 
// // Retrieve the saved client object
            // UserDetails userDetails = loginService.loadUserByUsername(client.getEmail());

            // // Authenticate the user
            // Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            // SecurityContextHolder.getContext().setAuthentication(authentication);

            // return new RedirectView("/client/home");






                        // // Retrieve the saved professional object
            // UserDetails userDetails = loginService.loadUserByUsername(professional.getEmail());

            // // Authenticate the user
            // Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            // SecurityContextHolder.getContext().setAuthentication(authentication);

            // return new RedirectView("/tradie/home");