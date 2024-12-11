package com.example.demo_web.repository;

import com.example.demo_web.model.HotelContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelContractRepository extends JpaRepository<HotelContract, Long>
{

}
