package com.wesovilabs.festify.service.impl;

import com.wesovilabs.festify.dto.request.ArtistRequest;
import com.wesovilabs.festify.dto.response.ArtistDetailResponse;
import com.wesovilabs.festify.dto.response.ArtistResumeResponse;
import com.wesovilabs.festify.mapper.ArtistMapper;
import com.wesovilabs.festify.persistence.jpa.entity.ArtistEntity;
import com.wesovilabs.festify.persistence.jpa.repository.ArtistJpaRepository;
import com.wesovilabs.festify.service.ArtistService;
import com.wesovilabs.festify.util.exception.ArtistNotFoundException;
import com.wesovilabs.festify.util.exception.InvalidIdException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    final private ArtistJpaRepository artistJpaRepository;

    @Autowired
    public ArtistServiceImpl(ArtistJpaRepository artistJpaRepository) {
        this.artistJpaRepository = artistJpaRepository;
    }

    @Override
    public List<ArtistResumeResponse> listArtists() {
        Pageable pageable =
                PageRequest.of(0, 5,
                        Sort.by("price").descending().and(Sort.by("name"))
                );
        Example<ArtistEntity> example = Example.of(new ArtistEntity());
        List<ArtistEntity> artists =  this.artistJpaRepository.findAll(example);
        return artists.stream().map(ArtistMapper::mapArtistToArtistResume).toList();
    }

    @Override
    public ArtistResumeResponse createArtist(ArtistRequest request) {
        // dto.request -> persistence.entity
        ArtistEntity artist = ArtistMapper.mapArtistRequestToArtistEntity(request);

        // repository (bbdd)
        ArtistEntity result = this.artistJpaRepository.save(artist);

        // persistence.entity -> dto.response
        return ArtistMapper.mapArtistToArtistResume(result);
    }

    @Override
    public ArtistDetailResponse updateArtist(String strId, ArtistRequest request) {
        Long id = parseArtistId(strId);
        ArtistEntity artist = ArtistMapper.mapArtistRequestToArtistEntity(request);
        artist.setId(id);
        logger.info("Update artist with id {}", artist.getId());
        if (!this.artistJpaRepository.existsById(id)){
            throw new ArtistNotFoundException("Artista no encontrado con id " + strId);
        }
        ArtistEntity result = this.artistJpaRepository.save(artist);
        return ArtistMapper.mapArtistToArtistDetail(result);
    }

    @Override
    public ArtistDetailResponse getArtistById(String strId) {
        Long id = parseArtistId(strId);
        Optional<ArtistEntity> artist = this.artistJpaRepository.findById(id);
        if (artist.isEmpty()){
            throw new ArtistNotFoundException("Artista no encontrado con id " + strId);
        }
        return ArtistMapper.mapArtistToArtistDetail(artist.get());
    }

    @Override
    public void deleteArtist(String strId) {
        Long id = parseArtistId(strId);
        if (!this.artistJpaRepository.existsById(id)){
            throw new ArtistNotFoundException("Artista no encontrado con id " + strId);
        }
        this.artistJpaRepository.deleteById(id);
    }

    private Long parseArtistId(String strId) {
        try {
            strId = strId.trim().replace("ART-","");
            Long id = Integer.valueOf(strId).longValue();
            return id;
        }catch (NumberFormatException e) {
            throw new InvalidIdException("Invalid artist id " + strId);
        }
    }

}
