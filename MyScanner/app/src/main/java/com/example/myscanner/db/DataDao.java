package com.example.myscanner.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DataDao {

    @Query("SELECT * FROM Data")
    List<Data> DATA_LIST();

    @Insert
    void insertData(Data data);

}
