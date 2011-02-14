package com.ensisa.test.login.client;


import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MyServiceAsync {
  @SuppressWarnings("rawtypes")
public void checkLogin(String userName, String password,AsyncCallback<User> callback);
}