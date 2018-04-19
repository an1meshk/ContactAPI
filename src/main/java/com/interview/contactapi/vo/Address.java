package com.interview.contactapi.vo;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * Created by Animesh Kumar on 13-04-2018.
 *
 * Entity object to hold contact's address details such as street location,
 * city, state, pincode
 */
public class Address implements Serializable {
    @ApiModelProperty(notes = "contact street location", example = "Corey Court")
    private String street;
    @ApiModelProperty(notes = "contact city", example = "Chicago", required = true)
    @NotEmpty
    private String city;
    @ApiModelProperty(notes = "contact state", example = "IL", required = true)
    @NotEmpty
    private String state;
    @ApiModelProperty(notes = "contact pincode", example = "60089")
    private String pincode;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", pincode=" +  pincode+
                '}';
    }
}
