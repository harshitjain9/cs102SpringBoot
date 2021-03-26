package g1t2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import g1t2.entities.EmailServer;
import g1t2.service.EmailServerService;

@RestController
public class EmailServerController {

    @Autowired
    private EmailServerService service;

    @PostMapping("/createNewEmailServer")
    public EmailServer createNewEmailServer(@RequestBody EmailServer emailServer){
        return service.createNewEmailServer(emailServer);
    }

    @PostMapping("/updateEmailServer/")
    public void updateEmailServer(@RequestBody EmailServer emailServer){
        service.updateEmailServer(emailServer);
    }

    @GetMapping("/getEmailServer/{id}")
    public EmailServer getEmailServer(@PathVariable String id) {
        return service.getEmailServer(id);
    }
}