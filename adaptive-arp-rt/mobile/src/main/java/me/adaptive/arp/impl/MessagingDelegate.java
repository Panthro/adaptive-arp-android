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

import android.content.Intent;
import android.net.Uri;
import android.telephony.PhoneNumberUtils;

import me.adaptive.arp.api.*;

/**
   Interface for Managing the Messaging operations
   Auto-generated implementation of IMessaging specification.
*/
public class MessagingDelegate extends BasePIMDelegate implements IMessaging {


    public String APIService = "messaging";
    static LoggingDelegate Logger;

     /**
        Default Constructor.
     */
     public MessagingDelegate() {
          super();
         Logger = ((LoggingDelegate)AppRegistryBridge.getInstance().getLoggingBridge().getDelegate());

     }

     /**
        Send text SMS

        @param number   to send
        @param text     to send
        @param callback with the result
        @since ARP1.0
     */
     public void sendSMS(final String number, final String text, final IMessagingCallback callback) {
         AppContextDelegate.getExecutorService().submit(new Runnable() {
             public void run() {
                 boolean result = false;

                 try {

                     if (PhoneNumberUtils.isWellFormedSmsAddress(number)) {
                         Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                         sendIntent.setData(Uri.parse("sms:" + number));
                         sendIntent.putExtra("sms_body", text);
                         AppContextDelegate.getMainActivity().startActivity(sendIntent);

                         result = true;
                     }
                 } catch (Exception ex) {
                     Logger.log(ILoggingLogLevel.ERROR, APIService,
                             "sendMessageSMS: Error [" + text + "] to phone ["
                                     + number + "]" + ex.getLocalizedMessage()
                     );
                     callback.onError(IMessagingCallbackError.Unknown);
                 }
                 callback.onResult(result);
             }

         });
     }

}
/**
------------------------------------| Engineered with ♥ in Barcelona, Catalonia |--------------------------------------
*/
