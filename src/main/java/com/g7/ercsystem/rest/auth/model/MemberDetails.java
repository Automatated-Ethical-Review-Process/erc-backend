package com.g7.ercsystem.rest.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_details")
public class MemberDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long md_id;
    private String name;
    private String address;
    private String nic;

    @OneToOne(mappedBy = "memberDetails")
    private User user;

    public MemberDetails(String name, String address, String nic) {
        this.name = name;
        this.address = address;
        this.nic = nic;
    }
}
