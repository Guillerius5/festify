package com.wesovilabs.festify.web.controller;


import com.wesovilabs.festify.dto.response.AlbumDetailResponse;
import com.wesovilabs.festify.persistence.jpa.repository.AlbumJpaRepository;
import com.wesovilabs.festify.service.AlbumService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/artists/{artistId}/albums")

public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping
    public List<AlbumDetailResponse> getAllAlbumsByArtist(@PathVariable("artistId") Long artistId) {
        return albumService.getAllAlbumsByArtist(artistId);
    }
}
