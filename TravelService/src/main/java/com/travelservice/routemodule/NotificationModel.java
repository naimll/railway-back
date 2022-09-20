package com.travelservice.routemodule;

public class NotificationModel {
    public String subject;
    public String body;
    public String url;
    public NotificationModel(String subject,String body, String url){
        this.subject = subject;
        this.body = body;
        this.url = url;
    }
}
