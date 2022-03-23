package com.example.contactapp1911;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(
        entities = {Contact1.class},
        version = 1
)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ContactDao contactDao();
    private static AppDatabase instance;


    public static AppDatabase getInstance(Context context)
    {
        if(instance == null)
        {
            instance = Room.databaseBuilder(context, AppDatabase.class, "contacts1")
                    .build();
        }
        return instance;
    }
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE `contacts` (`id` INTEGER, `name` TEXT, `mobile` TEXT, `email` TEXT, `url` TEXT, PRIMARY KEY(`id`))");
        }
    };
    static final Migration MIGRATION_2_3 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE `contacts` ADD COLUMN url TEXT");
        }
    };
}
