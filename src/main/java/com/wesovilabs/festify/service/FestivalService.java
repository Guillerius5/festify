package com.wesovilabs.festify.service;

import com.wesovilabs.festify.dto.request.ArtistRequest;
import com.wesovilabs.festify.dto.request.FestivalRequest;
import com.wesovilabs.festify.dto.response.ArtistDetailResponse;
import com.wesovilabs.festify.dto.response.ArtistResumeResponse;
import com.wesovilabs.festify.dto.response.FestivalResponse;

import java.util.List;

public interface FestivalService {
    List<FestivalResponse> listFestivals(String sortBy, String sortDirection);

    FestivalResponse createFestival(FestivalRequest request);

    FestivalResponse updateFestival(Long id, FestivalRequest request);

    FestivalResponse getFestival(Long id);

    void deleteFestival(Long id);
}
