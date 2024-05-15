package com.example.task.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;



@Getter
@Setter
@ToString

@Entity
@Table(name = "resources")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Lob
    @Column(nullable=false, columnDefinition="LONGBLOB")
    private byte[] data;
    private long size;

}
