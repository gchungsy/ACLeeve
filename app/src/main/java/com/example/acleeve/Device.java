package com.example.acleeve;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Room documentation:
// developer.android.com/training/data-storage/room

// Room Annotation to create SQLite table for this object
@Entity(tableName = "device_table")
public class Device {

    @PrimaryKey(autoGenerate = true) // add and increment id automatically
    private int id;
    private String title;
    private String description;

    public String getTimestamp() {
        return timestamp;
    }

    private String timestamp;
    private String sidelabel;

    // Constructor
    public Device(String title, String description, String timestamp, String sidelabel) {
        this.title = title;
        this.description = description;
        this.timestamp = timestamp;
        this.sidelabel = sidelabel;
    }

    // Setter
    public void setId(int id) {
        this.id = id;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getSidelabel() {
        return sidelabel;
    }
}
