package com.wesovilabs.festify.persistence.jpa.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table

public class AlbumEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Long artistId;

    @Column
    private String Title;

    @Column
    private LocalDate ReleaseDate;

    @Column
    private Integer tracks_count;

    @Column
    private Integer durationSeconds;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;



    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(Integer durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        ReleaseDate = releaseDate;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Integer getTracks_count() {
        return tracks_count;
    }

    public void setTracks_count(Integer tracks_count) {
        this.tracks_count = tracks_count;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
