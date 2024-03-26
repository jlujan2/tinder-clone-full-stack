package com.juank.tinderclone.dto;

import lombok.Data;

@Data
public class UserUpdateDTO {

    private String user_id;
    private String first_name;
    private Integer dob_day;
    private Integer dob_month;
    private Integer dob_year;
    private Boolean show_gender;
    private String gender_identity;
    private String gender_interest;
    private String url;
    private String about;
}
