
package olx;

import java.time.LocalDateTime;

import java.util.*;

public class Chat {
	private UserAccount[] participants = new UserAccount[2];
	private List<Message> messages;
	
	public Chat(UserAccount user1, UserAccount user2) {
		participants[0] = user1;
		participants[1] = user2;
		messages = new ArrayList<>();
	}

	public Chat(UserAccount user1, UserAccount user2, List<Message> messages) {
		participants[0] = user1;
		participants[1] = user2;
		this.messages = messages;
	}
	
	
	public void addMessage(String text, UserAccount sender) {
		if(!sender.equals(participants[0]) && !sender.equals(participants[1])) {
			throw new IllegalArgumentException("In Chat class: Sender is not one of participants");
		}
		messages.add(new Message(text, sender));
		for(int i = 0; i < 2; i++) {
			if(!sender.equals(participants[i])) {
				participants[i].notifyUserOfMessage();
			}
		}
	}
	
	public void viewChat() {
		for(Message message : messages) {
			message.viewMessage();
		}
	}
	public UserAccount[] getParticipants() {
		UserAccount[] temp = new UserAccount[2];
		for(int i =0; i<2;i++)
		{
			temp[i] = participants[i];
		}
		return temp;
		}
        
        public UserAccount getFirstParticipant() {
		
                return participants[0];
        }
        public UserAccount getSecoundParticipant() {
		
                return participants[1];
        }
        public String getLatestMessage()
        {
            String getmess=null;
 
            for(Message m1:messages)
            {
               getmess=m1.text;

              
            }
            return getmess;
        }
        public String getTimeOfLatestMessage()
        {
             String time=null;
             for(Message m1:messages)
            {
               time=m1.time.toString();

              
            }           
             return time;
        }
        
        
        boolean isParticipant(UserAccount user) {
        	if(participants[0].equals(user) || participants[1].equals(user)) {
        		return true;
        	}
        	return false;
        }
		
}
	
