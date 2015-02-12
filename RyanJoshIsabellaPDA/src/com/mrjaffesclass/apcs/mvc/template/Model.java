package com.mrjaffesclass.apcs.mvc.template;

import com.mrjaffesclass.apcs.messenger.*;

/**
 * The model represents the data that the app uses.
 * @author Roger Jaffe
 * @version 1.0
 */
public class Model implements MessageHandler {

  // Messaging system for the MVC
  private final Messenger mvcMessaging;

  // Model's data variables

  /**
   * Model constructor: Create the data representation of the program
   * @param messages Messaging class instantiated by the Controller for 
   *   local messages between Model, View, and controller
   */
  public Model(Messenger messages) {
    mvcMessaging = messages;
  }
  
  /**
   * Initialize the model here and subscribe to any required messages
   */
  public void init() {
    mvcMessaging.subscribe("view:stringEntered", this);
  }
  
  private MessagePayload createPayload(String fieldText) {
    MessagePayload payload = new MessagePayload(fieldText);
    return payload;
  }
  
  @Override
  public void messageHandler(String messageName, Object messagePayload) {
    if (messagePayload != null) {
      System.out.println("MSG: received by model: "+messageName+" | "+messagePayload.toString());
    } else {
      System.out.println("MSG: received by model: "+messageName+" | No data sent");
    }
    MessagePayload payload = (MessagePayload)messagePayload;
    String field = payload.getFieldText();
    double a;
    int i;
    int j;
    if ("view:stringEntered".equals(messageName)){
        try{
            a = Double.parseDouble(field);
            i = ((int)a / 2) + 7;
            j = ((int)(a-7)*2);
            mvcMessaging.notify("model:returnPDA", createPayload(""+i+" to "+j));
        }
        catch (NumberFormatException e){
            mvcMessaging.notify("model:nonNum");
        }
    }
    
  }
 
}
    //mvcMessaging.notify("model:variable1Changed", variable1, true); }
