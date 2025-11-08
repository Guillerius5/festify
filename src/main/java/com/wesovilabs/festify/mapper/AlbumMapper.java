package com.wesovilabs.festify.mapper;

import com.wesovilabs.festify.dto.response.AlbumDetailResponse;
import com.wesovilabs.festify.persistence.jpa.entity.AlbumEntity;
import org.springframework.stereotype.Component;

// Importa las clases necesarias
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Component
public class AlbumMapper {


    public AlbumDetailResponse toAlbumDetailResponse(AlbumEntity albumEntity) {

        return new AlbumDetailResponse(


                String.valueOf(albumEntity.getId()),
                String.valueOf(albumEntity.getArtistId()),
                albumEntity.getTitle(),
                albumEntity.getReleaseDate(),
                albumEntity.getTracks_count(), //
                albumEntity.getDurationSeconds(),

                safeToInstant(albumEntity.getCreatedAt()),
                safeToInstant(albumEntity.getUpdatedAt())
        );
    }

    /**
     * Método ayudante privado para convertir LocalDateTime a Instant.
     * Es "seguro" porque maneja los valores 'null' sin romperse.
     */
    private static Instant safeToInstant(LocalDateTime localDateTime) {

        if (localDateTime == null) {
            return null;
        }
        return localDateTime.toInstant(ZoneOffset.UTC);
    }
}
