package de.cegeka.dockerdemoapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;

@RestController
public class HostnameEndpoint {

    @Autowired
    Environment environment;

    @GetMapping("/hostname")
    public ResponseEntity<String> getHostname() throws Exception {
        String port = environment.getProperty("server.port");
        String hostname = InetAddress.getLocalHost().getHostName();
        return new ResponseEntity<>(hostname + " " + port, HttpStatus.OK);
    }

}
