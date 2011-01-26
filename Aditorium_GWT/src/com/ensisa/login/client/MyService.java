package com.ensisa.login.client;


import com.google.gwt.user.client.rpc.RemoteService;

public interface MyService extends RemoteService {
    public User checkLogin(String userName, String password);
 
}