package dk.cosby.roompersistancetest;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "books")
public class Book {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int bookID;

    @ColumnInfo(name = "name")
    private String BookName;

    public Book() {
    }


    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }
}
