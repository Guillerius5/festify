package com.wesovilabs.festify.dto.response;

import java.time.Instant;
import java.time.LocalDate;

public record AlbumDetailResponse(String id, String artistId, String title, LocalDate RelaseDate, int tracks_count, int durationSeconds,
                                  Instant createAt, Instant updateAt) {}

