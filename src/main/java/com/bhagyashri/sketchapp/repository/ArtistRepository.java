package com.bhagyashri.sketchapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bhagyashri.sketchapp.entity.Artist;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {

}
