package dk.cosby.roompersistancetest;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String DATABASE_NAME = "book_db";
    private BookDatabase bookDatabase;
    private Book tempBook;

    private TextView tv_text;

    int id = 120;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bookDatabase = Room.databaseBuilder(getApplicationContext(),
                BookDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();

        tv_text = (TextView) findViewById(R.id.text_from_load_save);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void saveDataToDB(View view) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                tempBook = new Book();
                tempBook.setBookID(++id);
                tempBook.setBookName("The Prestige");
                bookDatabase.daoBook().insertOnlySingleBook(tempBook);
            }
        }) .start();



    }

    public void loadDataFromDB(View view) {

        if(tempBook != null){

        new Thread(new Runnable() {
            @Override
            public void run() {
        tempBook = bookDatabase.daoBook().fetchOneBookbyBookId(id);
                System.out.println(tempBook.getBookName());

            }
        }) .start();

            tv_text.setText(tempBook.getBookName());
        }

    }
}
