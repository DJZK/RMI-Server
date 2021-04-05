package com.Bryan.Server;

import com.Bryan.api.Connector;

/*
  value[] Parameters
  0 Modifier
  1 Element 1
  2 Element 2
  3 Sub Modifier

  ZK modifier
  0 Element
  1 Element
  2 Element
  3 Element
 */
public class ConnectorImpl implements Connector {
    public static String[] ZK = new String[4];

    @Override
    public String[] process(String[] value) {
        if (value[1].equals(ZK[0]) && value[3].equals("load")) {
            // Loading the Account
            System.out.println(ZK[0] + " logged in");
            DatabaseReader.loadUser(value[1]);
        } else if (value[0].equals("account")) {
            // Sending the return from Database
            DatabaseReader.checkAccount(value[1]);
        } else if (value[0].equals("report")) {
            // Report Generation
            DatabaseReader.genReport(value[1]);
            value[0] = ""; // Clears the Modifier
            System.out.println("Region " + ZK[2] + " report Generated");
        } else if (value[0].equals("login")) {
            System.out.println("Login attempt at");
        } else {
            // Error if client is possibly tampered
            ZK[0] = "Error";
        }
        return ZK;
    }
}
