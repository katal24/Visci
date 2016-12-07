package visca;


import jssc.SerialPort;
import jssc.SerialPortException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;

//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dawid on 09.11.16.
 */
@RestController
public class Visca {

    static String commName = "COM1";
    static SerialPort serialPort;// = new SerialPort(commName);
    static private byte speed1 = 3;
    static private byte speed2 = 3;
    static ArrayList<String> macros;
    static private String response;
    static private String aa;
    public Visca(){

        serialPort = new SerialPort(commName);

        commName = "COM1";
        macros = new ArrayList<String>();
        try {
            serialPort.openPort();
            serialPort.setParams(9600, 8, 1, 0);
        } catch (SerialPortException e) {
            e.printStackTrace();
        }

    }

    public static byte[] readResponseHelp(SerialPort serialPort){
        try {
           return ResponseReader.readResponse(serialPort);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String bytesToString(byte[] bytes){
        StringBuilder sb = new StringBuilder();
        byte[] arrby = bytes;
        int n = arrby.length;
        int n2 = 0;
        while (n2<n){
            byte b = arrby[n2];
            sb.append(String.format("%02X ", Byte.valueOf(b)));
            ++n2;
        }
        return sb.toString();
    }

    @RequestMapping("/")
    public String index(){
        return "Welcome";
    }

    @RequestMapping("/home")
    public String home(){
        try {
            Home.move(serialPort);
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
        byte[] respons = readResponseHelp(serialPort);
        response = bytesToString(respons);
        return "{\"res\": \"" + response + "\"}";
    }

    @RequestMapping(value = "/up")
    @ResponseBody
    public String up(){
        System.out.print("############ " + aa);
        try {
            MoveUp.move(serialPort, speed1, speed2);
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
        byte[] respons = readResponseHelp(serialPort);
        response = bytesToString(respons);
        return "{\"res\": \"" + response + "\"}";
    }

    @RequestMapping("/down")
    public String down(){
        try {
            MoveDown.move(serialPort, speed1, speed2);
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
        byte[] respons = readResponseHelp(serialPort);
        response = bytesToString(respons);
        return "{\"res\": \"" + response + "\"}";
    }

    @RequestMapping("/left")
    public String left(){
        try {
            MoveLeft.move(serialPort, speed1, speed2);
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
        byte[] respons = readResponseHelp(serialPort);
        response = bytesToString(respons);
        return "{\"res\": \"" + response + "\"}";
    }

    @RequestMapping("/right")
    public String right(){
        try {
            MoveRight.move(serialPort, speed1, speed2);
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
        byte[] respons = readResponseHelp(serialPort);
        response = bytesToString(respons);
        return "{\"res\": \"" + response + "\"}";
    }

    @RequestMapping("/tele")
    public String tele(){
        try {
            ZoomTele.move(serialPort);
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
        byte[] respons = readResponseHelp(serialPort);
        response = bytesToString(respons);
        return "{\"res\": \"" + response + "\"}";
    }

    @RequestMapping("/wide")
    public String wide(){
        try {
            ZoomWide.move(serialPort);
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
        byte[] respons = readResponseHelp(serialPort);
        response = bytesToString(respons);
        return "{\"res\": \"" + response + "\"}";
    }


    @RequestMapping("/speed/sp1={sp1}&sp2={sp2}")
    public String wide(@PathVariable("sp1") byte sp1, @PathVariable("sp2") byte sp2){
             speed1 = sp1;
            speed2 = sp2;
        return "{\"res\": \"" + "Speed " + sp1 + " " + sp2 + "\"}";
    }

    private static void waitaMoment(int time){
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static String switchWord(String oneReq) throws SerialPortException {
        String[] word = oneReq.split(" ");
        byte[] respons;
        switch (word[0]) {
            case "home":
                Home.move(serialPort);
                respons = readResponseHelp(serialPort);
                response = bytesToString(respons);
              //  return "{\"res\": \"" + response + "\"}";
            System.out.println( "{\"res\": \"" + response + "\"}");

            //waitaMoment(5);
                break;
            case "right":
                MoveRight.move(serialPort, speed1, speed2);
                respons = readResponseHelp(serialPort);
                response = bytesToString(respons);
               // return "{\"res\": \"" + response + "\"}";
            System.out.println( "{\"res\": \"" + response + "\"}");

                //waitaMoment(5);
                break;
            case "left":
                MoveLeft.move(serialPort, speed1, speed2);
                respons = readResponseHelp(serialPort);
                response = bytesToString(respons);
             //   return "{\"res\": \"" + response + "\"}";
            System.out.println( "{\"res\": \"" + response + "\"}");

                //waitaMoment(5);
                break;
            case "up":
                MoveUp.move(serialPort, speed1, speed2);
                respons = readResponseHelp(serialPort);
                response = bytesToString(respons);
             //   return "{\"res\": \"" + response + "\"}";
            System.out.println( "{\"res\": \"" + response + "\"}");

                //waitaMoment(5);
                break;
            case "down":
                MoveDown.move(serialPort, speed1, speed2);
                respons = readResponseHelp(serialPort);
                response = bytesToString(respons);
           //     return "{\"res\": \"" + response + "\"}";
            System.out.println( "{\"res\": \"" + response + "\"}");

                //waitaMoment(5);
                break;
            case "tele":
                ZoomTele.move(serialPort);
                respons = readResponseHelp(serialPort);
                response = bytesToString(respons);
           //     return "{\"res\": \"" + response + "\"}";
            System.out.println( "{\"res\": \"" + response + "\"}");
                //waitaMoment(5);
                break;
            case "wide":
                ZoomWide.move(serialPort);
                respons = readResponseHelp(serialPort);
                response = bytesToString(respons);
           //     return "{\"res\": \"" + response + "\"}";
            System.out.println( "{\"res\": \"" + response + "\"}");

            //waitaMoment(5);
                break;
            case "speed":
                if (word.length == 3) {
                    System.out.println("Spped " + word[1] + " " + word[2]);
                    speed1 = Byte.parseByte(word[1]);
                    speed2 = Byte.parseByte(word[2]);
                } else {
                    System.out.println("No parameters");
                }
                //waitaMoment(5);
                break;
            case "wait":
                if(word.length == 2) {
                    System.out.println("Wait " + word[1]);
                    waitaMoment(Integer.parseInt(word[1]));
                } else {
                    System.out.println("Bad parameter");
                }
                break;
            default:
                System.out.println(word[0]);
                System.out.println("Bad request");
                break;
        }
        return oneReq;
    }

    public static void callMacro(String macro) throws SerialPortException {
        String[] reqs = macro.split(" ");
        reqs = Arrays.copyOfRange(reqs, 2, reqs.length);

        for(int i=0; i<reqs.length; i++){
            if(reqs[i].equals("speed")){
                switchWord(reqs[i] + " " + reqs[i+1] + " " + reqs[i+2]);
                i = i+2;
            } else if(reqs[i].equals("wait")){
                switchWord(reqs[i] + " " + reqs[i+1]);
                i++;
            } else {
                switchWord(reqs[i]);
            }
        }
    }

    public static boolean isInMacros(String maybeMacro){
        for(String macro : macros){
            if(maybeMacro.equals(macro.split(" ")[1])){
                return true;
            }
        }
        return false;
    }

    public static String getMacroFromMacros(String maybeMacro){
        for(String macro : macros) {
            if (maybeMacro.equals(macro.split(" ")[1])) {
                return macro;
            }
        }
        return null;
    }

    @RequestMapping("/makro/m={m}")
    public void makro(@PathVariable("m") String makro){
        System.out.println(makro);
        String[] words = makro.split(" ");

        if(!words[0].equals("=") && !isInMacros(words[0])) {
            try {
                switchWord(makro);
            } catch (SerialPortException e) {
                e.printStackTrace();
            }
        } else
            if(isInMacros(words[0])){ // wywolanie makra
            try {
                callMacro(getMacroFromMacros(words[0]));
            } catch (SerialPortException e) {
                e.printStackTrace();
            }
        } else {    // makro!!!
            System.out.println("nowe makro " + makro);
            macros.add(makro);
            // callMacro(s);
        }



//
//
//
//
//
//
//        try {
//            ZoomWide.move(serialPort);
//        } catch (SerialPortException e) {
//            e.printStackTrace();
//        }
//        byte[] respons = readResponseHelp(serialPort);
//        response = bytesToString(respons);
//        return "{\"res\": \"" + response + "\"}";
    }



}
