package com.pankul.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "hotels")
public class HotelsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
    private Long id;

    @Column(name = "hotel_name")
    private String hotelName;

    @Column(name = "hotel_rating")
    private Double hotelRating;

    @Column(name = "city")
    private String city;

    @Column(name = "feature_1")
    private String feature1;

    @Column(name = "feature_2")
    private String feature2;

    @Column(name = "feature_3")
    private String feature3;

    @Column(name = "feature_4")
    private String feature4;

    @Column(name = "feature_5")
    private String feature5;

    @Column(name = "feature_6")
    private String feature6;

    @Column(name = "feature_7")
    private String feature7;

    @Column(name = "feature_8")
    private String feature8;

    @Column(name = "feature_9")
    private String feature9;

    @Column(name = "hotel_price")
    private Double hotelPrice;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Double getHotelRating() {
        return hotelRating;
    }

    public void setHotelRating(Double hotelRating) {
        this.hotelRating = hotelRating;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFeature1() {
        return feature1;
    }

    public void setFeature1(String feature1) {
        this.feature1 = feature1;
    }

    public String getFeature2() {
        return feature2;
    }

    public void setFeature2(String feature2) {
        this.feature2 = feature2;
    }

    public String getFeature3() {
        return feature3;
    }

    public void setFeature3(String feature3) {
        this.feature3 = feature3;
    }

    public String getFeature4() {
        return feature4;
    }

    public void setFeature4(String feature4) {
        this.feature4 = feature4;
    }

    public String getFeature5() {
        return feature5;
    }

    public void setFeature5(String feature5) {
        this.feature5 = feature5;
    }

    public String getFeature6() {
        return feature6;
    }

    public void setFeature6(String feature6) {
        this.feature6 = feature6;
    }

    public String getFeature7() {
        return feature7;
    }

    public void setFeature7(String feature7) {
        this.feature7 = feature7;
    }

    public String getFeature8() {
        return feature8;
    }

    public void setFeature8(String feature8) {
        this.feature8 = feature8;
    }

    public String getFeature9() {
        return feature9;
    }

    public void setFeature9(String feature9) {
        this.feature9 = feature9;
    }

    public Double getHotelPrice() {
        return hotelPrice;
    }

    public void setHotelPrice(Double hotelPrice) {
        this.hotelPrice = hotelPrice;
    }
}