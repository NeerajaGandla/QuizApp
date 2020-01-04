package com.neeraja.quizjava.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "categories")
public class Category {
    @PrimaryKey
    @NonNull
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "timestamp")
    private int timestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Categoy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
