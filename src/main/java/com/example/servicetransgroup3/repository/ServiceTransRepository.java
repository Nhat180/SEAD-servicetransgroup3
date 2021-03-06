package com.example.servicetransgroup3.repository;

import com.example.servicetransgroup3.model.ServiceTrans;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceTransRepository extends JpaRepository<ServiceTrans, Long> {
    List<ServiceTrans> findAllByMechanic (String mechanic);
    List<ServiceTrans> findAllByMechanicId (Long mechanicId);

    @Query("SELECT st FROM ServiceTrans st WHERE CONCAT(st.serviceTransId, ' ') LIKE %?1%")
    public Page<ServiceTrans> search(String keyword, Pageable pageable);
}
