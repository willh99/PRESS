package useData;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Copyright (C) 2018 willy
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 *
 * @author will
 */
public class Globals {
    private static InetAddress SERVER_HOST = null;
    private static int SERVER_PORT;
    private static int TIMEOUT;
    
    public static InetAddress getHostName()
    {
        return SERVER_HOST;
    }
    
    public static void setHostName(String addr)
    {
        try {
            SERVER_HOST = InetAddress.getByName(addr);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Globals.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static int getHostPort()
    {
        return SERVER_PORT;
    }
    
    public static void setHostPort(int p)
    {
        SERVER_PORT = p;
    }
    
    public static int getTimeout()
    {
        return TIMEOUT;
    }
    
    public static void setTimeout(int t)
    {
        if(t>=0)
            TIMEOUT = t;
    }
}
