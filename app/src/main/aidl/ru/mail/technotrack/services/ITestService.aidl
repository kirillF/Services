// ITestService.aidl
package ru.mail.technotrack.services;


interface ITestService {
    String getString();
    oneway void getStringAsync();
    void bindActivity(IBinder callback);
}
