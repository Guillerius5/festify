package com.wesovilabs.festify.persistence.jpa.repository;

import com.wesovilabs.festify.persistence.jpa.entity.ArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistJpaRepository extends JpaRepository<ArtistEntity, Long> {

    List<ArtistEntity> findByNameContainingIgnoreCase(String name);

}
