package com.bhagyashri.sketchapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bhagyashri.sketchapp.entity.Sketch;

@Repository
public interface SketchRepository extends JpaRepository<Sketch, Integer> {

}
