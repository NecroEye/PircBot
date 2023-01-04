import org.jibble.pircbot.IrcException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, IrcException, InterruptedException {




         IRCBot ircBot = new IRCBot();
         //connection checker
         ircBot.setVerbose(true);



         ircBot.connect("chat.freenode.net");

         ircBot.joinChannel("#notvirus3131");

        while (true) {
            Thread.sleep(1000);
        }


    }
}