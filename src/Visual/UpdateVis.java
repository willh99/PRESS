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
package Visual;

import javax.swing.JLabel;
import org.json.simple.JSONObject;
import useData.ParseJSON;

/**
 *
 * @author will
 */
public class UpdateVis {
    
    public static void updateSysStatus(JLabel label)
    {
        JSONObject obj = ParseJSON.getJSONObject("home_status.json");
        
        if(obj == null){
            label.setText("Controller Status: Disconnected");
            return;
        }
        
        boolean buy = (boolean) obj.get("Buy");
        boolean sell = (boolean) obj.get("Sell");
        
        if(buy && !sell){
            label.setText("Controller Status: Buy");
            return;
        }
        else if(!buy && sell){
            label.setText("Controler Status: Sell");
            return;
        }
                
    }
    
    public static void updateAlgorithmStatus(JLabel label)
    {
        JSONObject obj = ParseJSON.getJSONObject("algorithm_status.json");
        
        if(obj == null){
            label.setText("Algorthm Status: N/A");
            return;
        }
        
        boolean buy = (boolean) obj.get("Buy");
        boolean sell = (boolean) obj.get("Sell");
        
        if(buy && !sell){
            label.setText("Algorthm Status: Buy");
            return;
        }
        else if(!buy && sell){
            label.setText("Algorthm Status: Sell");
            return;
        }
        else
            label.setText("Algorthm Status: Halt");
    }
}
