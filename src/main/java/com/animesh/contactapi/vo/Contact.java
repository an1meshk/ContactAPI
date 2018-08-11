package com.animesh.contactapi.vo;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Animesh Kumar on 13-04-2018.
 * <p>
 * Entity object to hold contact's complete details such as name, company name
 * profile image, email address, date of birth, work phone, personal phone
 * and address.
 */
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "The contact ID", hidden = true)
    private Integer id;
    @ApiModelProperty(notes = "contact name", example = "John", required = true)
    @NotEmpty
    private String name;
    @ApiModelProperty(notes = "contact company details", example = "Google", required = true)
    @NotEmpty
    private String company;
    @ApiModelProperty(notes = "contact profile image", dataType = "byte[]", example = "iVBORw0KGgoAAAANSUhEUgAAAB0AAAALCAYAAACDHIaJAAAABGdBTUEAALGPC/xhBQAAAAlwSFlzAAAOwwAADsMBx2+oZAAAAsNJREFUOE/FkFtIk3EYxicZRCcKtDzMKdLBZRYaapKVFVIGyxUprosIAqVFEQVehBehXiheGKSdyECyk2RlNrUELRFLMrU0agraZnNztbaZ5mHpr2//DSzqrqjn6vk/7+H3fp+M/yABTU+MI7vosgj+hQR07YI5pJ3IxzUxhtlslZIZDAaDuyTk9i6vF5qeoq+vD5tz1Bt4ZLOasdpHmHGNYxqyeFMpHzZhNM2+BTR22WKy8i9i6KxnmV8gKpUKeVAAO1I1HNceJkTyMUl7sE9JB3Q3s3ZFGIrQUOSKMM5frxeLKi8V4u/nR3RCEslbY4lJ3Cvy8rNnCAmRo1AoyMzOE8f/AL2EUYLKZDKKrzfw+skt4U8VXmPgVYPwlc16elp0nM4t4tNHC9ujglmzJY0J5zALpfrJgquYjAOsD1/Kyvj9DPV3M0fKrz5sY1DfKnaU1774GWpof4jMx4duO4wZn4qmhh4bTBrxlfyVOy+ZGLVSXJCL9kgW68KDSdp7iPdvWkTv815pUFL+sXSUcftorK2Ucl/U+zVkHNCwSiEnr/SGF+q/aBYqDbdbXXzu9XydrmsInH3i4qpHr8lUbyQocid2uwN1gpLYlAOMDPeL3gtVLQKamhiBMiGdtx2ewyt0zzAP9tPY1ITF5vRAlfNkHMw5x0DbPdHUZnFhe1cn/P32D+DQe4ZruijNPcrc+UvYlpzCCnkAgZGJTEs7inO0LPUPZEfyLoIC/FHGqqX0G9qMnQSHryZ+w3oiojejN9k90Kf1Orr07/lqt1BdXY19YoYp6Te6vcUxDq5RHkjeYHa622l6rKOusRWryUBNTS0jjk/cvX2Ty2XldPbo0WqSCY5KEb1uPWnQUXHzDmbbF/EW0D/VzOQIqk1RLA9dzR7VbpYsmk9B2T1v9Vf9Fahb01PjPHpQRUlJCa0db7zp7wTfASzT7ChN0+aGAAAAAElFTkSuQmCC")
    private byte[] profileImg;
    @ApiModelProperty(notes = "contact email address", example = "john@gmail.com", required = true)
    @NotEmpty
    @Email
    private String email;
    @ApiModelProperty(notes = "contact day of birth", example = "1990-04-15")
    private Date birthDate;
    @ApiModelProperty(notes = "contact work phone number", example = "2242011234", required = true)
    @NotEmpty
    private String workNumber;
    @ApiModelProperty(notes = "contact personal phone number", example = "8472011234", required = true)
    @NotEmpty
    private String personalNumber;
    @ApiModelProperty(notes = "contact address")
    private Address address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public byte[] getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(byte[] profileImg) {
        this.profileImg = profileImg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", profileImg=" + Arrays.toString(profileImg) +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", workNumber='" + workNumber + '\'' +
                ", personalNumber='" + personalNumber + '\'' +
                ", address=" + address +
                '}';
    }
}
