package com.example.servicetransgroup3.controller;

import com.example.servicetransgroup3.model.ServiceTrans;
import com.example.servicetransgroup3.service.ServiceTransImpl;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka/servicetran")
public class ServiceTransKafkaController {
    static final Logger logger = LoggerFactory.getLogger(ServiceTransKafkaController.class);
    final String TOPIC = "SERVICE_TRAN";

    @Autowired
    private ServiceTransImpl serviceTransImpl;

    @Autowired
    private Gson gson;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping
    public void createServiceTrans(@RequestBody ServiceTrans serviceTrans) {
        String serviceTransJson = gson.toJson(serviceTrans);
        logger.info(String.format("#### -> Produce created service tran -> %s",serviceTransJson));
        kafkaTemplate.send(TOPIC + "_CREATE", serviceTransJson);
    }
}
