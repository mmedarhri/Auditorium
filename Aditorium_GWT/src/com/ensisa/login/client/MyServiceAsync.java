package com.ensisa.login.client;


import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MyServiceAsync {
  public void checkLogin(String userName, String password,AsyncCallback callback);
}