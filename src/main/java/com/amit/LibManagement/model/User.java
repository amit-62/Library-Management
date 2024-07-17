package com.amit.LibManagement.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Length;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length=30)
    private String name;

    @Column(nullable = false, unique = true, length = 15)
    private String phoneNo;

    @Column(unique = true, length = 50)
    private String email;

    private String address;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @OneToMany(mappedBy = "user")
    private List<Book> bookList;

    @OneToMany(mappedBy = "user")
    private List<Txn> txnList;


}
