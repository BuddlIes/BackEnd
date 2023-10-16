package com.buddle.UserManager.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stamp_info")
public class StampInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long stamp_id;

    @Column private String stamp_name;
    @Column private Integer stamp_type;
    @Column private Integer login_number;
    @Column private Integer post_number;
    @Column private Integer comment_number;
    @Column private Integer do_volunteer_number;
    @Column private Integer had_volunteer_number;
    @Column private Integer review_number;


}


