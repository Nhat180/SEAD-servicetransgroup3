package com.example.servicetransgroup3.controller;

import com.example.servicetransgroup3.model.ServiceTrans;
import com.example.servicetransgroup3.service.ServiceTransImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/servicetran")
public class ServiceTransController {
    @Autowired
    private ServiceTransImpl serviceTransImpl;

    @PostMapping
    public void createServiceTrans(@RequestBody ServiceTrans serviceTrans) {
        serviceTransImpl.createServiceTrans(serviceTrans);
    }

    @GetMapping("/{id}")
    public ServiceTrans getServiceTrans(@PathVariable(value = "id") Long id) {
        return serviceTransImpl.getServiceTrans(id);
    }

    @GetMapping("/getrequestjob")
    public List<ServiceTrans> getAllUnAcceptedJob (@RequestParam String mechanic) {
        return serviceTransImpl.getAllUnAcceptedJob(mechanic);
    }

    @GetMapping("/gettodojob")
    public List<ServiceTrans> getAllAcceptedJob (@RequestParam String mechanic) {
        return serviceTransImpl.getAllAcceptedJob(mechanic);
    }

}
