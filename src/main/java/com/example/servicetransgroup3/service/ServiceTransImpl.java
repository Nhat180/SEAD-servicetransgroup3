package com.example.servicetransgroup3.service;

import com.example.servicetransgroup3.model.ServiceTrans;
import com.example.servicetransgroup3.repository.ServiceTransRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ServiceTransImpl {
    @Autowired
    private ServiceTransRepository serviceTransRepository;

    public ServiceTrans getServiceTrans(Long id) {
        ServiceTrans serviceTrans = new ServiceTrans();
        try {
            serviceTrans = this.serviceTransRepository.findById(id)
                    .orElseThrow(() -> new Exception("Service Transactional not found:: " + id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serviceTrans;
    }

    public List<ServiceTrans> getAllUnAcceptedJob(String currentMechanic) {
        List<ServiceTrans> serviceTransList = this.serviceTransRepository.findAllByMechanic(currentMechanic);
        for (int i = 0; i < serviceTransList.size(); i++) {
            if (serviceTransList.get(i).getStartDate() != null) {
                serviceTransList.remove(i);
            }
        }
        return serviceTransList;
    }

    public List<ServiceTrans> getAllAcceptedJob(String currentMechanic) {
        List<ServiceTrans> serviceTransList = this.serviceTransRepository.findAllByMechanic(currentMechanic);
        for (int i = 0; i < serviceTransList.size(); i++) {
            if (serviceTransList.get(i).getStartDate() == null) {
                serviceTransList.remove(i);
            }
        }
        return serviceTransList;
    }

}
