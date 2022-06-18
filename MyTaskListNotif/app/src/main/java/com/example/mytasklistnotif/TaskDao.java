package com.example.mytasklistnotif;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Task task);

    @Query("SELECT * FROM table_task ORDER BY date ASC")
    LiveData<List<Task>> getAll();

    @Query("DELETE FROM table_task")
    void deleteAll();

    @Delete
    void delete(Task task);


}


//   @Query("INSERT INTO table_task(title,course, date, time, `desc`) VALUES(:title, :course, :date, :time, :desc)")
//   void allInsert(String title, String course, String date, String time, String desc);
//
//   @Query("UPDATE table_task SET title = :title, course = :course, date = :date, time = :time, `desc` = :desc WHERE id = :id ")
//   void update(int id, String title, String course, String date, String time, String desc);
//
//   @Query("SELECT * FROM table_task WHERE id = :id")
//   Task get(int id);




