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
package Tasks;

import java.io.IOException;
import java.util.Date;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import useData.ProcessData;

/**
 *
 * @author will
 */
public class DataFetch extends TimerTask {

    @Override
    public void run() 
    {
        try {
            ProcessData.downloadData();
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, "In DataFetch thread:  Successfully obtained data for: {0}\n", new Date());
        } catch (IOException ex) {
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, null, ex);
        }
    }
}