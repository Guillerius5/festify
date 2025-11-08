package com.wesovilabs.festify.persistence.jpa.repository;

import com.wesovilabs.festify.persistence.jpa.entity.AlbumEntity;
import com.wesovilabs.festify.persistence.jpa.entity.ArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumJpaRepository extends JpaRepository<AlbumEntity, Long> {

    List<AlbumEntity> findAllByArtistId(Long id);
}
