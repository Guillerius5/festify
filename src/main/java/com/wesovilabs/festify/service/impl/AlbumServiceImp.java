package com.wesovilabs.festify.service.impl;

import com.wesovilabs.festify.dto.response.AlbumDetailResponse;
import com.wesovilabs.festify.mapper.AlbumMapper;
import com.wesovilabs.festify.persistence.jpa.repository.AlbumJpaRepository;
import com.wesovilabs.festify.persistence.jpa.repository.ArtistJpaRepository;
import com.wesovilabs.festify.service.AlbumService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class AlbumServiceImp implements AlbumService {


  private final AlbumJpaRepository albumJpaRepository;
  private final AlbumMapper albumMapper;

    public AlbumServiceImp(AlbumJpaRepository albumJpaRepository, AlbumMapper albumMapper) {
        this.albumJpaRepository = albumJpaRepository;
        this.albumMapper = albumMapper;
    }

@Override
    public List<AlbumDetailResponse> getAllAlbumsByArtist(Long id) {
        return albumJpaRepository.findAllByArtistId(id).stream()
                .map(albumMapper::toAlbumDetailResponse)
                .collect(Collectors.toList());
    }
    }

