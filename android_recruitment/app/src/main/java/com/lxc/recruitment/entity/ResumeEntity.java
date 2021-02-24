package com.lxc.recruitment.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "resume")
public class ResumeEntity {

    @ColumnInfo(name = "r_id")
    public Long id;
    @ColumnInfo(name = "p_id")
    public Long personalId;
    @ColumnInfo(name = "r_name")
    public String name;
    @ColumnInfo(name = "r_professional")
    public String professional;
    @ColumnInfo(name = "r_description")
    public String description;
    @ColumnInfo(name = "r_experience")
    public String experience;
    @ColumnInfo(name = "r_duration")
    public String duration;
    @ColumnInfo(name = "r_treatment")
    public String treatment;
    @ColumnInfo(name = "j_degree")
    public String degree;
}
