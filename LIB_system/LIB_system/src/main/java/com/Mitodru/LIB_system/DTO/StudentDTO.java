package com.Mitodru.LIB_system.DTO;


import lombok.Data;

@Data
public class StudentDTO {

    private Long studentId;   // ✅ SAME AS ENTITY
    private String name;
    private String rollNumber;
    private String email;
    private String phoneNumber;
    private String department;
}
