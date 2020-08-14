package com.integro.dbhs;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.integro.dbhs.adapters.NotificationAdapter;
import com.integro.dbhs.apis.ApiClients;
import com.integro.dbhs.apis.ApiServices;
import com.integro.dbhs.model.Notification;
import com.integro.dbhs.model.NotificationList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationActivity extends AppCompatActivity {

    private ArrayList<Notification> notificationArrayList;
    private RecyclerView rvNotifications;
    private NotificationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        rvNotifications = findViewById(R.id.rvNotifications);
        notificationArrayList = new ArrayList<>();
        rvNotifications.setLayoutManager( new LinearLayoutManager(getApplicationContext()));
        getNotifications();
    }

    public void getNotifications() {
        String date = "2020-02-26 23:16:07";
        Call<NotificationList> notificationListCall = ApiClients.getClients().create(ApiServices.class).getNotificationList(date);
        notificationListCall.enqueue(new Callback<NotificationList>() {
            @Override
            public void onResponse(Call<NotificationList> call, Response<NotificationList> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                if (response.body().getNotificationArrayList() == null) {
                    return;
                }
                int size = response.body().getNotificationArrayList().size();

                for (int i = 0; i < size; i++) {
                    notificationArrayList.add(response.body().getNotificationArrayList().get(i));
                }
                rvNotifications.setLayoutManager( new LinearLayoutManager(getApplicationContext()));
                adapter = new NotificationAdapter(getApplicationContext(), notificationArrayList);
                rvNotifications.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<NotificationList> call, Throwable t) {

            }
        });
    }
}
