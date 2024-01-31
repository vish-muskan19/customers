package com.example.Entity;


import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
	private User user;
    
    private String image;
        
    private String firstName;
    
    private String lastName;
    
    private String address;
    
    private String street;
    
    private String city;
    
    private String state;
    
    private String email;
    
    private String phone;
    
    private LocalDateTime createAt;

    // Getters and setters
}
