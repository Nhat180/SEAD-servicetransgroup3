package com.example.servicetransgroup3.controller;

import com.example.servicetransgroup3.model.ServiceTrans;
import com.example.servicetransgroup3.service.ServiceTransImpl;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllServiceTransByPage (
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "serviceTransId,asc") String[] sort
    ) {
        return new ResponseEntity<>(serviceTransImpl.getAllServiceTransByPage(page,size,sort), HttpStatus.OK);
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
