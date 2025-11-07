package com.wesovilabs.festify.persistence.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "festivales")
public class FestivalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false, length = 160)
    @NotBlank
    private String title;

    @Column(name = "descripcion")
    private String about;

    @Column(nullable = false, length = 120)
    @NotBlank
    private String city;

    @Column(name = "fecha_inicio", nullable = false)
    @NotNull
    private LocalDate from;

    @Column(name = "fecha_fin", nullable = false)
    @NotNull
    private LocalDate to;

    @Column(name = "precio_min")
    private Double priceMin;

    @Column(name = "precio_max")
    private Double priceMax;

    // --- ctor vacío requerido por JPA
    protected FestivalEntity() {}

    public FestivalEntity(String titulo,  String ciudad, String about, LocalDate fechaInicio, LocalDate fechaFin,
                          Double precioMin, Double precioMax) {
        this.title = titulo;
        this.about = about;
        this.city = ciudad;
        this.from = fechaInicio;
        this.to = fechaFin;
        this.priceMin = precioMin;
        this.priceMax = precioMax;
    }

    public FestivalEntity(Long id, String title, String city, LocalDate from, LocalDate to, Double priceMin, Double priceMax) {
        this.id = id;
        this.title = title;
        this.city = city;
        this.from = from;
        this.to = to;
        this.priceMin = priceMin;
        this.priceMax = priceMax;
    }

    // --- getters/setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getTitle() { return title; } // PK: no cambies esto tras persistir
    public void setTitle(String titulo) { this.title = titulo; }

    public String getCity() { return city; }
    public void setCity(String ciudad) { this.city = ciudad; }

    public LocalDate getFrom() { return from; }
    public void setFrom(LocalDate fechaInicio) { this.from = fechaInicio; }

    public LocalDate getTo() { return to; }
    public void setTo(LocalDate fechaFin) { this.to = fechaFin; }

    public Double getPriceMin() { return priceMin; }
    public void setPriceMin(Double precioMin) { this.priceMin = precioMin; }

    public Double getPriceMax() { return priceMax; }
    public void setPriceMax(Double precioMax) { this.priceMax = precioMax; }

    // --- identidad basada solo en la PK
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FestivalEntity f)) return false;
        return Objects.equals(title, f.title);
    }
    @Override public int hashCode() { return Objects.hash(title); }
}
