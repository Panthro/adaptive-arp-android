/**
 --| ADAPTIVE RUNTIME PLATFORM |----------------------------------------------------------------------------------------

 (C) Copyright 2013-2015 Carlos Lozano Diez t/a Adaptive.me <http://adaptive.me>.

 Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 . Unless required by appli-
 -cable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,  WITHOUT
 WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the  License  for the specific language governing
 permissions and limitations under the License.

 Original author:

 * Carlos Lozano Diez
 <http://github.com/carloslozano>
 <http://twitter.com/adaptivecoder>
 <mailto:carlos@adaptive.me>

 Contributors:

 * Ferran Vila Conesa
 <http://github.com/fnva>
 <http://twitter.com/ferran_vila>
 <mailto:ferran.vila.conesa@gmail.com>

 * See source code files for contributors.

 Release:

 * @version v2.0.3

-------------------------------------------| aut inveniam viam aut faciam |--------------------------------------------
 */

package me.adaptive.arp.impl;

import java.util.ArrayList;
import java.util.List;

import me.adaptive.arp.api.AppRegistryBridge;
import me.adaptive.arp.api.ILoggingLogLevel;
import me.adaptive.arp.api.INetworkStatus;
import me.adaptive.arp.api.INetworkStatusListener;

/**
 * Interface for Managing the Network status
 * Auto-generated implementation of INetworkStatus specification.
 */
public class NetworkStatusDelegate extends BaseCommunicationDelegate implements INetworkStatus {


    public static String APIService = "networkStatus";
    static LoggingDelegate Logger;
    public List<INetworkStatusListener> listeners = new ArrayList<INetworkStatusListener>();

    /**
     * Default Constructor.
     */
    public NetworkStatusDelegate() {
        super();
        Logger = ((LoggingDelegate) AppRegistryBridge.getInstance().getLoggingBridge().getDelegate());

    }

    /**
     * Add the listener for network status changes of the app
     *
     * @param listener Listener with the result
     * @since ARP1.0
     */
    public void addNetworkStatusListener(INetworkStatusListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
            Logger.log(ILoggingLogLevel.Debug, APIService, "addNetworkStatusListener: " + listener.toString() + " Added!");
        } else
            Logger.log(ILoggingLogLevel.Debug, APIService, "addNetworkStatusListener: " + listener.toString() + " is already added!");
    }

    /**
     * Un-registers an existing listener from receiving network status events.
     *
     * @param listener Listener with the result
     * @since ARP1.0
     */
    public void removeNetworkStatusListener(INetworkStatusListener listener) {
        if (listeners.contains(listener)) {
            listeners.remove(listener);
            Logger.log(ILoggingLogLevel.Debug, APIService, "removeNetworkStatusListener" + listener.toString() + " Removed!");
        } else
            Logger.log(ILoggingLogLevel.Debug, APIService, "removeNetworkStatusListener: " + listener.toString() + " is NOT registered");
    }

    /**
     * Removes all existing listeners from receiving network status events.
     *
     * @since ARP1.0
     */
    public void removeNetworkStatusListeners() {
        listeners.clear();
        Logger.log(ILoggingLogLevel.Debug, APIService, "removeNetworkStatusListeners: ALL NetworkStatusListeners have been removed!");
    }

}
/**
 ------------------------------------| Engineered with ♥ in Barcelona, Catalonia |--------------------------------------
 */
