package com.example.acleeve;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothClass;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Device.class}, version = 1, exportSchema = false)
public abstract class DeviceDatabase extends RoomDatabase {

        private static  DeviceDatabase instance; // always use same instance anywhere in app

        public abstract  DeviceDao deviceDao(); // Room subclasses our abstract class

        public static synchronized DeviceDatabase getInstance(Context context){
            if (instance == null){
                // create database
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        DeviceDatabase.class,
                        "device_database")
                        .fallbackToDestructiveMigration() // recreates database if version increase
                        .addCallback(roomCallback)
                        .build();
            }
            return instance;
        }

        private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
            @SuppressLint("NewApi")
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                new PopulateDbAsyncTask(instance).execute();
            }
        };

        @SuppressLint("NewApi")
        private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
            private DeviceDao deviceDao;

            private PopulateDbAsyncTask(DeviceDatabase db){
                this.deviceDao = db.deviceDao();
            }

            @Override
            protected Void doInBackground(Void... voids){
                deviceDao.insert(new Device("Device 1", "Serial #1", "1", "Left"));
                deviceDao.insert(new Device("Device 2", "Serial #2", "2", "Right"));
                return null;
            }
        }
}
