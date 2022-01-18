package com.example.servicetransgroup3.engine;

import com.example.servicetransgroup3.model.ServiceTrans;
import com.example.servicetransgroup3.service.ServiceTransImpl;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ServiceTransConsumer {
    final Logger logger = LoggerFactory.getLogger(ServiceTransConsumer.class);
    final String TOPIC = "u4k85isn-default";
    final String GROUP_ID = "SERVICE_TRAN_ID";

    @Autowired
    private ServiceTransImpl serviceTransImpl;

    @Autowired
    private Gson gson;

    @KafkaListener(topics = TOPIC + "_CREATE", groupId = GROUP_ID)
    public void createServices(String serviceTransJson) {
        ServiceTrans serviceTrans = gson.fromJson(serviceTransJson, ServiceTrans.class);
        logger.info(String.format("#### -> Consume created service tran -> %s", serviceTransJson));
        serviceTransImpl.createServiceTrans(serviceTrans);
    }
}
