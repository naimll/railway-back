package com.travelservice.routemodule.services;

import com.travelservice.routemodule.NotificationModel;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notificationService", url = "http://localhost:18099")
public interface NotificationServiceFeignClient {
    @PostMapping(value = "/api/v1/notification/sendNotificationToAll")
    @Headers("Content-Type: application/json")
    public NotificationModel sendNotificationToAll(NotificationModel notification);
}
