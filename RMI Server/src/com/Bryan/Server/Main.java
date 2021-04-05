package com.Bryan.Server;

import com.Bryan.api.Connector;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Main {

    public static void main(String[] args) throws RemoteException {
        // Listener
        Registry reg = LocateRegistry.createRegistry(19128);
        ConnectorImpl server = new ConnectorImpl();
        Connector connect = (Connector)UnicastRemoteObject.exportObject(server, 0);
        reg.rebind("server", connect);

        // Running
        System.out.println("Server is running");
    }
}
