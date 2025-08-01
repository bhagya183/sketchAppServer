package com.bhagyashri.sketchapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bhagyashri.sketchapp.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
