package com.wesovilabs.festify.service;

import com.wesovilabs.festify.dto.response.AlbumDetailResponse;
import org.springframework.stereotype.Service;

import java.util.List;



public interface AlbumService {


List<AlbumDetailResponse> getAllAlbumsByArtist(Long id);
}
