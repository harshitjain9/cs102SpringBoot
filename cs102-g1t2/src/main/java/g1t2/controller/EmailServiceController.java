package g1t2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailServerController {

    @Autowired
    private EmailServerService service;

    @PostMapping("/addEmailServerService")
    public EmailServer saveEmailServer(@RequestBody EmailServer emailServer){
        return service.saveEmailServer(emailServer);
    }

    @PostMapping("/updateEmailServerservice/")
    public void replaceEmailServer(@RequestBody EmailServer emailServer){
        service.replaceEmailServer(emailServer);
    }

    @GetMapping("/getByEmailServerId/{id}")
    public EmailServer getEmailServerById(@PathVariable int id) {
        return service.getEmailServer(id);
    }
}