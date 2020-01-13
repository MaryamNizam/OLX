package olx;

import org.slf4j.*;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;

public class ChatViewer {
	private Chat chat;
	private UserAccount sender;
	private UserAccount receiver;
	
	private TextIO textIO = TextIoFactory.getTextIO();
	
	public ChatViewer(UserAccount sender, UserAccount receiver) {
		this.sender = sender;
		this.receiver = receiver;
		
		OLX.terminal.println("You are entering chat window. Enter \"===\" as message to exit this window");
		Chat exists = sender.getChatWithUser(receiver);
		if(exists != null) {
			exists.viewChat();
		}
		
	}
	
	public void viewChat() {
		String input = textIO.newStringInputReader().read("Message");
		if(!input.equalsIgnoreCase("===")) {
			sender.sendMessage(receiver, input);
			//TRY CLERING SCREEN HERE.
			chat.viewChat();
			this.viewChat();
		}
	}
}
