package com.travelservice.routemodule.services;

import com.travelservice.routemodule.NotificationModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationServiceClient {

    @Autowired
    NotificationServiceFeignClient notificationServiceFeignClient;

    public NotificationModel sendNotificationToAll(NotificationModel notificationModel){
        NotificationModel notificationModel1;
        try {
             notificationModel1 = this.notificationServiceFeignClient.sendNotificationToAll(notificationModel);

            return notificationModel1;
        }  catch (Exception ex) {


        }
        return notificationModel;
    }
}
