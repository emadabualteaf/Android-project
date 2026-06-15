package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface HistoryDao {
    @Insert
    void insertRecord(HistoryRecord record);

    @Query("SELECT * FROM quiz_history ORDER BY id DESC")
    List<HistoryRecord> getAllHistory();
}