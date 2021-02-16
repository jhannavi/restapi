package com.meena.server.repository;

import com.meena.server.model.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {
    List<PhoneNumber> findAll();

    List<PhoneNumber> findByCustId (String custId);

    List<PhoneNumber> findAllBySimStatus (String simStatus);

    List<PhoneNumber> findByPhoneNumber (String phoneNumber);
}
