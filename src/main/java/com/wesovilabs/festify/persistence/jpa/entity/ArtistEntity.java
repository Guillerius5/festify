package com.wesovilabs.festify.persistence.jpa.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="albums")
public class ArtistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(length = 2, nullable = false)
    private String country;
    @Column(columnDefinition = "text[]", nullable = false)
    @org.hibernate.annotations.JdbcTypeCode(org.hibernate.type.SqlTypes.ARRAY)
    private List<String> genres = new ArrayList<>();

    private Integer listeners;
    @Column(nullable = false)
    private String status = "Activo";

    @Column
    private String biography;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private Timestamp updatedAt;

    public ArtistEntity() {
    }

    public ArtistEntity(Long id, String name, List<String> genres, String country, Integer listeners, String status, String biography) {
        this.id = id;
        this.name = name;
        this.genres = genres;
        this.country = country;
        this.listeners = listeners;
        this.status = status;
        this.biography = biography;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getListeners() {
        return listeners;
    }

    public void setListeners(Integer listeners) {
        this.listeners = listeners;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public  void setBiography(String biography) {
        this.biography = biography;
    }
    public String getBiography() {
        return biography;
    }
}
