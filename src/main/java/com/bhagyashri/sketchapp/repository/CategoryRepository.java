package com.bhagyashri.sketchapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bhagyashri.sketchapp.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
