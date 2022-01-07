package com.example.servicetransgroup3.service;

import com.example.servicetransgroup3.model.ServiceTrans;
import com.example.servicetransgroup3.repository.ServiceTransRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Transactional
@Service
public class ServiceTransImpl {
    @Autowired
    private ServiceTransRepository serviceTransRepository;

    public void createServiceTrans (ServiceTrans serviceTrans) {
        serviceTrans.setStartDate(null);
        serviceTrans.setEndDate(null);
        serviceTransRepository.save(serviceTrans);
    }

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


    public Map<String, Object> getAllServiceTransByPage(int page, int size, String[] sort) {
        Map<String, Object> res = new HashMap<>();
        try {
            List<ServiceTrans> serviceTransList = new ArrayList<>();
            List<Order> orders = new ArrayList<Order>();

            if (sort[0].contains(",")) {
                // will sort more than 2 fields
                // sortOrder="field, direction"
                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
                }
            } else {
                // sort=[field, direction]
                orders.add(new Order(getSortDirection(sort[1]), sort[0]));
            }


            Pageable paging = PageRequest.of(page,size,Sort.by(orders));

            Page<ServiceTrans> serviceTransPage;

            // paging based on the request of page and size from front-end
            serviceTransPage = serviceTransRepository.findAll(paging);

            serviceTransList = serviceTransPage.getContent(); // Assign paging content to list and then return to UI

            res.put("serviceTrans", serviceTransList);
            res.put("currentPage", serviceTransPage.getNumber());
            res.put("totalServiceTrans", serviceTransPage.getTotalElements());
            res.put("totalPages", serviceTransPage.getTotalPages());

            return res;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<ServiceTrans> getAllUnAcceptedJob(Long id) {
        List<ServiceTrans> allJob = this.serviceTransRepository.findAllByMechanicId(id);
        List<ServiceTrans> serviceTransList = new ArrayList<>();
        for (ServiceTrans serviceTrans : allJob) {
            if (serviceTrans.getStartDate() == null && serviceTrans.getEndDate() == null) {
                serviceTransList.add(serviceTrans);
            }
        }
        return serviceTransList;
    }

    public List<ServiceTrans> getAllAcceptedJob(Long id) {
        List<ServiceTrans> allJob = this.serviceTransRepository.findAllByMechanicId(id);
        List<ServiceTrans> serviceTransList = new ArrayList<>();
        for (ServiceTrans serviceTrans : allJob) {
            if (serviceTrans.getStartDate() != null && serviceTrans.getEndDate() == null) {
                serviceTransList.add(serviceTrans);
            }
        }
        return serviceTransList;
    }

    public void acceptJob(Long id) {
        ServiceTrans serviceTrans = getServiceTrans(id);
        serviceTrans.setStartDate(convertCurrentDateToString());
    }

    public void finishJob(Long id) {
        ServiceTrans serviceTrans = getServiceTrans(id);
        serviceTrans.setEndDate(convertCurrentDateToString());
    }

    public String convertCurrentDateToString() {
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        return simpleDateFormat.format(new Date());
    }


    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }

}
