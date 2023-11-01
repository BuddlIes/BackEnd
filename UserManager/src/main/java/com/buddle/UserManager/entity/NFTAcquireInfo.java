package com.buddle.UserManager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "NFTAcquire_info")
public class NFTAcquireInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long acquire_id;

    //    @ManyToOne
//    @JoinColumn(name = "user_number", referencedColumnName = "user_number")
//    private UserInfo userInfo;
//
//    @ManyToOne
//    @JoinColumn(name = "stamp_id", referencedColumnName = "stamp_id")
//    private StampInfo stampInfo;
    @Column private Long userNumber;
    @Column private Long nftId;

    @Column private LocalDateTime acquire_time;
}
