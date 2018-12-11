package com.example.c1637.movie_assignment;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelp extends SQLiteOpenHelper
{
    public static final String ROWID = "ID";
    public static final int COL_ROWID = 0;

    public static final String TITLE = "TITLE";
    public static final int COL_TITLE = 1;

    public static final String DESCRIPTION = "DESCRIPTION";
    public static final int COL_DESCRIPTION = 2;

    public static final String GENRE = "GENRE";
    public static final int COL_GENRE = 3;

    public static final String PEOPLE = "PEOPLE";
    public static final int COL_PEOPLE = 4;

    public static final String LENGTH = "LENGTH";
    public static final int COL_LENGTH = 5;

    public static final String AGE = "AGE";
    public static final int COL_AGE = 6;

    public static final String LIST = "LIST";
    public static final int COL_LIST = 7;

    public static final String RATING = "RATING";
    public static final int COL_RATING = 8;

    public static final String[] ALL_ROWS = new String[] {ROWID, TITLE, DESCRIPTION, GENRE, PEOPLE, LENGTH, AGE, LIST, RATING};

    public static final String DATABASE_NAME = "MOVIES";
    public static final String TABLE_NAME = "MOVIE_INFO";



    public DBHelp(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "( ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT, DESCRIPTION TEXT, GENRE TEXT, PEOPLE TEXT, LENGTH TEXT, AGE TEXT, LIST INTEGER, RATING INTEGER)");
        PopulateData();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertDB(String title, String description, String director, String people)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE, title);
        contentValues.put(DESCRIPTION, description);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor DisplayAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = null;
        Cursor c = 	db.query(true, TABLE_NAME, ALL_ROWS,
                where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;

    }

   public Cursor DisplayList(long list)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + LIST + " = " + list ;
        Cursor  cursor = db.rawQuery(query,null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;

    }

    public void DeleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void PopulateData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO " + TABLE_NAME + "( TITLE, DESCRIPTION, GENRE, PEOPLE, LENGTH, AGE) VALUES "
                +"('Goodfellas', 'A young man grows up in the mob and works very hard to advance himself through the ranks. He enjoys his life of money and luxury, but is oblivious to the horror that he causes. A drug addiction and a few mistakes ultimately unravel his climb to the top.', 'Crime', 'Robert De Niro, Joe Pesci, Ray Liotta', '2 hours 28 minutes', '18'),"
        +"('Halloween', 'On a cold Halloween night in 1963, six year old Michael Myers brutally murdered his 17-year-old sister, Judith. He was sentenced and locked away for 15 years. But on October 30, 1978, while being transferred for a court date, a 21-year-old Michael Myers steals a car and escapes Smiths Grove. He returns to his quiet hometown of Haddonfield, Illinois, where he looks for his next victims.', 'Horror', 'Jamie Lee Curtis, Judy Greer, Nick Castle', '1 hour 44 minutes', '18'),"
        +"('Interstellar', 'In Earths future, a global crop blight and second Dust Bowl are slowly rendering the planet uninhabitable. Professor Brand, a brilliant NASA physicist, is working on plans to save mankind by transporting Earths population to a new home via a wormhole. ', 'Sci-Fi', 'Matthew McConaughey, Anne Hathaway, Michael Caine', '2 hours 49 minutes', '12'),"
        +"('Meet The Fockers', 'Now that Greg Focker is in with his soon-to-be in-laws, Jack and Dina Byrnes, it looks like smooth sailing for him and his fiancée, Pam. But thats before Pams parents meet Gregs parents, the Fockers. The hyper-relaxed Fockers and the tightly-wound Byrneses are woefully mismatched from the start, and no matter how hard Greg and Pam try, there is just no bringing their families together. This all adds up to a disastrously funny time of getting to know you.', 'Comedy', 'Ben Stiller, Robert Di Nero, Barbra Streisand, Dustin Hoffman', '1 hour 55 minutes', '12'),"
        +"('Shutter Island', 'The implausible escape of a brilliant murderess brings U.S. Marshal Teddy Daniels and his new partner to Ashecliffe Hospital, a fortress-like insane asylum located on a remote, windswept island. The woman appears to have vanished from a locked room, and there are hints of terrible deeds committed within the hospital walls. As the investigation deepens, Teddy realizes he will have to confront his own dark fears if he hopes to make it off the island alive.', 'Mystery', 'Leonardo DiCaprio, Mark Ruffalo, Emily Mortimer', '2 hours and 18 minut', '15'),"
        +"('The Green Mile', 'Paul Edgecomb walked the mile with a variety of cons. He had never encountered someone like John Coffey, a massive black man convicted of brutally killing a pair of young sisters. Coffey had the size and strength to kill anyone, but not the demeanor. Beyond his simple, naive nature and a deathly fear of the dark, Coffey seemed to possess a prodigious, supernatural gift. Paul began to question whether Coffey was truly guilty of murdering the two girls.', 'Drama', 'Tom Hanks, Bonnie Hunt, Michael Clarke Duncan', '3 hours 9 minutes', '18'),"
        +"('The Hangover', 'Two days before his wedding, Doug and three friends drive to Las Vegas for a wild and memorable stag party. In fact, when the three groomsmen wake up the next morning, they cannot remember a thing; nor can they find Doug. With little time to spare, the three hazy pals try to re-trace their steps and find Doug so they can get him back to Los Angeles in time to walk down the aisle.', 'Comedy', ' Zach Galifianakis, Bradley Cooper, Justin Bartha', '1 hour 48 minutes', '15'),"
        +"('The Incredibles 2', 'Helen is in the spotlight, leaving Bob at home with Violet and Dash to navigate the day-to-day heroics of normal life. Its a tough transistion for everyone, made tougher by the fact that the family is still unaware of baby Jack-Jacks emerging superpowers. When a new villain hatches a brilliant and dangerous plot, the family and Frozone must find a way to work together again—which is easier said than done, even when they’re all Incredible.', 'Family', 'Holly Hunter, Craig T. Nelson, Samuel L. Jackson', '2 hours 31 minutes', 'U'),"
        +"('The Prestige', 'Period thriller set in Edwardian London where two rival magicians, partners until the tragic death of an assistant during a show, feud bitterly after one of them performs the ultimate magic trick - teleportation. His rival tries desperately to uncover the secret of his routine, experimenting with dangerous new science as his quest takes him to the brink of insanity and jeopardises the lives of everyone around the pair.', 'Drama', 'Hugh Jackman, Christian Bale, Scarlette Johansson', '2 hours 10 minutes', '12'),"
        +"('The Grinch', 'The Grinch and his loyal dog, Max, live a solitary existence inside a cave on Mount Crumpet. His main source of aggravation comes during Christmastime when his neighbors in Whoville celebrate the holidays with a bang. When the Whos decide to make Christmas bigger and brighter, the disgruntled Grinch realizes there is one way to gain peace and quiet. With help from Max, the green grump hatches a scheme to pose as Santa Claus, steal Christmas and silence the Whos holiday cheer once and for all.', 'Animation', 'Benedict Cumberbatch, Cameron Seely, Rashida Jones', '1 hour 26 minutes', 'PG')");

    }

    public Cursor DisplayOne(long id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = ROWID + "=" + id;
        Cursor c = 	db.query(true, TABLE_NAME, ALL_ROWS,
                where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public boolean updateList(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LIST, 1);
        String where = ROWID + "=" + id;

        long result = db.update(TABLE_NAME, contentValues, where, null);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean DeleteList(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LIST, 0);
        String where = ROWID + "=" + id;

        long result = db.update(TABLE_NAME, contentValues, where, null);
        if(result == -1)
            return false;
        else
            return true;
    }

    public int CheckForList(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + ROWID + " = " + id + " AND " + LIST + " = " + 1 ;
        Cursor  cursor = db.rawQuery(query,null);
        int answer = cursor.getCount();
        return answer;

    }

    public boolean RateMovie(int rate, int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RATING, rate );
        String where = ROWID + "=" + id;

        long result = db.update(TABLE_NAME, contentValues, where, null);

        if(result == -1)
            return false;
        else
            return true;
    }

}
