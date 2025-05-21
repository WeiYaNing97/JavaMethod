package com.example.test.ts.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Location {
    private double X;
    private double Y;
    private double H;
    private String ONTIME;
}