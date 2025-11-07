package com.wesovilabs.festify.service.impl;

import com.wesovilabs.festify.dto.request.FestivalRequest;
import com.wesovilabs.festify.dto.response.FestivalResponse;
import com.wesovilabs.festify.mapper.ArtistMapper;
import com.wesovilabs.festify.persistence.jpa.entity.ArtistEntity;
import com.wesovilabs.festify.persistence.jpa.entity.FestivalEntity;
import com.wesovilabs.festify.persistence.jpa.repository.FestivalJpaRepository;
import com.wesovilabs.festify.service.FestivalService;
import com.wesovilabs.festify.util.exception.FestivalNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FestivalServiceImpl implements FestivalService {

    private final FestivalJpaRepository festivalJpaRepository;

    @Autowired
    public FestivalServiceImpl(FestivalJpaRepository festivalJpaRepository) {
        this.festivalJpaRepository = festivalJpaRepository;
    }

    @Override
    public List<FestivalResponse> listFestivals(String sortBy, String dir) {
        Sort.Direction sortDirection = "desc".equalsIgnoreCase(dir) ? Sort.Direction.DESC : Sort.Direction.ASC;
        List<FestivalEntity> festivals = this.festivalJpaRepository.findAll(Sort.by(sortDirection, sortBy));
        return festivals.stream().map(festival ->
                new FestivalResponse(festival.getId(), festival.getTitle(), festival.getAbout(), festival.getCity(),
                        festival.getFrom(), festival.getTo(), festival.getPriceMin(), festival.getPriceMax())
        ).toList();

    }

    @Override
    public FestivalResponse createFestival(FestivalRequest request) {
        FestivalEntity entity = new FestivalEntity(
                request.title(), request.city(), request.about(), request.from(), request.to(), request.minPrice(), request.maxPrice());
        FestivalEntity entitySaved = this.festivalJpaRepository.save(entity);
        return new FestivalResponse(entitySaved.getId(), entitySaved.getTitle(), entitySaved.getAbout(), entitySaved.getCity(),
                entitySaved.getFrom(), entitySaved.getTo(), entitySaved.getPriceMin(), entitySaved.getPriceMax());
    }

    @Override
    public FestivalResponse updateFestival(Long id, FestivalRequest request) {
        FestivalEntity entity = new FestivalEntity(
                request.title(), request.city(), request.about(), request.from(), request.to(), request.minPrice(), request.maxPrice());
        entity.setId(id);

        if (!this.festivalJpaRepository.existsById(id)) {
            throw new FestivalNotFoundException("Festival no encontrado con id " + id);
        }
        FestivalEntity entitySaved = this.festivalJpaRepository.save(entity);
        return new FestivalResponse(entitySaved.getId(), entitySaved.getTitle(), entitySaved.getAbout(), entitySaved.getCity(),
                entitySaved.getFrom(), entitySaved.getTo(), entitySaved.getPriceMin(), entitySaved.getPriceMax());
    }

    @Override
    public FestivalResponse getFestival(Long id) {
        Optional<FestivalEntity> festival = this.festivalJpaRepository.findById(id);
        if (festival.isEmpty()) {
            throw new FestivalNotFoundException("Festival no encontrado con id " + id);
        }
        FestivalEntity result = festival.get();
        return new FestivalResponse(result.getId(), result.getTitle(), result.getAbout(), result.getCity(),
                result.getFrom(), result.getTo(), result.getPriceMin(), result.getPriceMax());
    }

    @Override
    public void deleteFestival(Long id) {
        if (!this.festivalJpaRepository.existsById(id)) {
            throw new FestivalNotFoundException("Festival no encontrado con id " + id);
        }
        this.festivalJpaRepository.deleteById(id);
    }
}
