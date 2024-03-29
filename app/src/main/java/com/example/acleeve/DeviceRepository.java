package com.example.acleeve;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;

import java.util.List;

public class DeviceRepository {
    private DeviceDao deviceDao;
    private LiveData<List<Device>> allDevices;

    public DeviceRepository(Application application){
        DeviceDatabase database = DeviceDatabase.getInstance(application);
        deviceDao = database.deviceDao();
        allDevices = deviceDao.getAllDevices();
    }

    public LiveData<List<Device>> getAllDevices() {
        // Room automatically execute database operation in background thread
        // If datatype is Live Data
        return allDevices;
    }

    public void insert(Device device){
        new InsertDeviceAsyncTask(deviceDao).execute(device);
    }
    public void update(Device device){
        new UpdateDeviceAsyncTask(deviceDao).execute(device);
    }
    public void delete(Device device){
        new UpdateDeviceAsyncTask(deviceDao).execute(device);
    }

    public void deleteAllDevices(){
        new DeleteAllDevicesAsyncTask(deviceDao).execute();
    }

    private static class InsertDeviceAsyncTask extends AsyncTask<Device, Void, Void> {
        private DeviceDao deviceDao;

        private InsertDeviceAsyncTask(DeviceDao deviceDao){
            this.deviceDao = deviceDao;
        }

        @Override
        protected Void doInBackground(Device... devices){
            deviceDao.insert(devices[0]);
            return null;
        }
    }
    private static class UpdateDeviceAsyncTask extends AsyncTask<Device, Void, Void> {
        private DeviceDao deviceDao;

        private UpdateDeviceAsyncTask(DeviceDao deviceDao){
            this.deviceDao = deviceDao;
        }

        @Override
        protected Void doInBackground(Device... devices){
            deviceDao.update(devices[0]);
            return null;
        }
    }
    private static class DeleteDeviceAsyncTask extends AsyncTask<Device, Void, Void> {
        private DeviceDao deviceDao;

        private DeleteDeviceAsyncTask(DeviceDao deviceDao){
            this.deviceDao = deviceDao;
        }

        @Override
        protected Void doInBackground(Device... devices){
            deviceDao.delete(devices[0]);
            return null;
        }
    }
    private static class DeleteAllDevicesAsyncTask extends AsyncTask<Void, Void, Void> {
        private DeviceDao deviceDao;

        private DeleteAllDevicesAsyncTask(DeviceDao deviceDao){
            this.deviceDao = deviceDao;
        }

        @Override
        protected Void doInBackground(Void... voids){
            deviceDao.deleteAllDevices();
            return null;
        }
    }
}
