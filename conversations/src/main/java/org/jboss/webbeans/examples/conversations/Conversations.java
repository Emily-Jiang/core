package org.jboss.webbeans.examples.conversations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Current;
import javax.enterprise.inject.Named;
import javax.enterprise.inject.Produces;
import javax.faces.model.SelectItem;

import org.jboss.webbeans.conversation.ConversationIdGenerator;
import org.jboss.webbeans.conversation.ConversationInactivityTimeout;
import org.jboss.webbeans.conversation.ConversationManager;


@SessionScoped
@Named("conversations")
public class Conversations implements Serializable {

   @Current private Conversation conversation;
   @Current private ConversationIdGenerator id;
   @Current private ConversationManager conversationManager;
   private String cid;
     
   public Conversations() 
   {
   }
   
   @Produces
   @ConversationInactivityTimeout
   @Example
   public static long getConversationTimeoutInMilliseconds()
   {
      return 10000;
   }   

   public String end()
   {
      conversation.end();
      return "home";
   }
   
   public void begin()
   {
      conversation.begin();
   }
   
   public String beginAndRedirect()
   {
      conversation.begin();
      return "home";
   }
   
   public void noop()
   {   
   }
   
   public String noopAndRedirect()
   {   
      return "home";
   }
   
   public Iterable<Conversation> getConversationList() 
   {
      return conversationManager.getLongRunningConversations(); 
   }
   
   public List<SelectItem> getLongRunningConversations() 
   {
      List<SelectItem> longRunningConversations = new ArrayList<SelectItem>();
      for (Conversation conversation : conversationManager.getLongRunningConversations()) 
      {
         longRunningConversations.add(new SelectItem(conversation.getId(), conversation.getId()));
      }
      return longRunningConversations;
   }

   public void switchConversation() 
   {
      conversation.begin(cid);
   }
   
   public String getCid()
   {
      return cid;
   }

   public void setCid(String cid)
   {
      this.cid = cid;
   }
   
}