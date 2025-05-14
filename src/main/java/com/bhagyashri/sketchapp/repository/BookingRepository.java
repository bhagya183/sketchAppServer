package com.bhagyashri.sketchapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bhagyashri.sketchapp.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

}
