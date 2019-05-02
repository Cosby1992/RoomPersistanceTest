package dk.cosby.roompersistancetest;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface DaoBook {

    @Insert
    void insertOnlySingleBook (Book book);

    @Insert
    void insertMultipleBooks (List<Book> booksList);

    @Query("SELECT * FROM books WHERE id = :bookID")
    Book fetchOneBookbyBookId(int bookID);

    @Update
    void updateBook (Book book);

    @Delete
    void deleteBook (Book book);




}
