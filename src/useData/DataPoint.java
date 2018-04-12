/*
 * Copyright (C) 2018 Will Horowitz
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
package useData;

import java.util.Date;

/**
 * Class to hold relevant information about a data point
 * Holds time of price data, name of associated region,
 * and the price at given the aforementioned parameters
 * @author will
 */
public class DataPoint {
    private final Date timeStamp;
    private final String name;
    private final double price;
    
    public DataPoint(Date tS, String n, double p){
        timeStamp=tS; name=n; price=p;
    }
    
    public Date getTimeStamp() {
        return timeStamp;
    }
    
    public String getName(){
        return name;
    }
    
    public double getPrice() {
        return price;
    }

    @Override
    public String toString(){
        return "Data Point[ timeStamp: "+timeStamp+" name: "+name+" price: "+price+"$/MWH ]";
    }
}
