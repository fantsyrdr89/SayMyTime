package armstrong.alexandra;
// using http://military.onlineclock.net/chart/ as reference for military time
import static armstrong.alexandra.Export.correction;
import static armstrong.alexandra.Export.question;
import static armstrong.alexandra.Import.stringInput;
import static java.lang.Character.getNumericValue;

class NotATime extends Exception{}

public class ChangeTime {
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws NotATime {
        boolean control = true;
        String[] time = null;
        String rawData = null;
        do{
            time = null;
            rawData = null;
            question();
            rawData = stringInput();
            if(rawData.charAt(rawData.length() - 1) == 'm') {
                time = splitInput(rawData);
                if (validStandardTime(time)){
                    control = false;
                }
            } else {
                if (validMilitaryTime(rawData)){
                    control = false;
                }
            }
        } while (control);
        if(time != null){
            standard(time);
        } else {
            military(rawData);
        }
        correction(sb.toString());
    }

    public static boolean validMilitaryTime(String time){
        try {
            if(time.length() != 4){
                throw new NotATime();
            }
            int hour = Integer.parseInt(time.substring(0,2));
            int minute = Integer.parseInt(time.substring(2,4));
            if (hour < 0 || hour > 23) {
                throw new NotATime();
            }
            if (minute < 0 || minute > 60) {
                throw new NotATime();
            }
            return true;
        }catch(Exception e){
            return false;
        }
    }
    public static boolean validStandardTime(String[] time){

        try {
            int hour = Integer.parseInt(time[0]);
            int minute = Integer.parseInt(time[1]);
            if (minute < 0 || minute > 60) {
                throw new NotATime();
            }
            if (hour <= 0 || hour > 12){
                throw new NotATime();
            }
            return true;
        } catch(Exception e){
            return false;
        }
    }

    public static String[] splitInput(String time) throws NotATime {
            try {
                String[] data = time.split("[:| ]");
                if (data.length == 1 || data.length == 3) {
                    return data;
                } else {
                    throw new NotATime();
                }
            } catch (Exception e) {
                return null;
            }

    }

    public static void military(String time){
        int hours = (Integer.parseInt(time))/100;
            if(hours > 20){
                wordSwitch((hours / 10) * 10);
                sb.append(" ");
                wordSwitch(hours % 10);
            } else if(hours == 0) {
                wordSwitch(0);
                sb.append(" ");
                wordSwitch(0);
            } else if(hours < 10){
                wordSwitch(0);
                sb.append(" ");
                wordSwitch(hours % 10);
            } else {
                wordSwitch(hours);
            }
        sb.append(" ");
        int minutes = (Integer.parseInt(time)) % 100;
            if(minutes == 0){
                sb.append("hundred");
            } else if(minutes < 10){
                wordSwitch(0);
                sb.append(" ");
                wordSwitch(minutes % 10);
            } else if(minutes <= 20 || minutes == 30 || minutes == 40 || minutes == 50){
                wordSwitch(minutes);
            } else {
                wordSwitch((minutes / 10) * 10);
                sb.append(" ");
                wordSwitch(minutes % 10);
            }
    }

    public static void standard(String[] time){
        int hours = Integer.parseInt(time[0]);
            wordSwitch(hours);
        sb.append(" ");
        int minutes = Integer.parseInt(time[1]);
            if(minutes == 0){
                sb.append("o'Clock");
            } else if(minutes < 10){
                sb.append("o'");
                wordSwitch(getNumericValue(time[1].charAt(1)));
            } else if(minutes <= 20 || minutes == 30 || minutes == 40 || minutes == 50){
                wordSwitch(minutes);
            } else {
                wordSwitch(getNumericValue(time[1].charAt(0))*10);
                sb.append(" ");
                wordSwitch(getNumericValue(time[1].charAt(1)));
            }
        sb.append(" " + time[2]);
    }

    public static void wordSwitch(int data){
        switch(data){
            case 0: sb.append("zero");break;
            case 1: sb.append("one");break;
            case 2: sb.append("two");break;
            case 3: sb.append("three");break;
            case 4: sb.append("four");break;
            case 5: sb.append("five");break;
            case 6: sb.append("six");break;
            case 7: sb.append("seven");break;
            case 8: sb.append("eight");break;
            case 9: sb.append("nine");break;
            case 10: sb.append("ten");break;
            case 11: sb.append("eleven");break;
            case 12: sb.append("twelve");break;
            case 13: sb.append("thirteen");break;
            case 14: sb.append("fourteen");break;
            case 15: sb.append("fifteen");break;
            case 16: sb.append("sixteen");break;
            case 17: sb.append("seventeen");break;
            case 18: sb.append("eighteen");break;
            case 19: sb.append("nineteen");break;
            case 20: sb.append("twenty");break;
            case 30: sb.append("thirty");break;
            case 40: sb.append("forty");break;
            case 50: sb.append("fifty");break;
        }
    }
}