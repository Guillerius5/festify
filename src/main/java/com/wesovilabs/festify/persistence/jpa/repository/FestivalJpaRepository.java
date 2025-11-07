package com.wesovilabs.festify.persistence.jpa.repository;

import com.wesovilabs.festify.persistence.jpa.entity.FestivalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FestivalJpaRepository extends JpaRepository<FestivalEntity, Long> {
}
