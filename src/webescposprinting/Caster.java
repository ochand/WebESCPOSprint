package webescposprinting;


/**
 *
 * @author jesusespinoza
 */
import java.math.*;
import java.sql.*;
import java.text.*;
import java.util.Date;

public class Caster {
  private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
  private static final SimpleDateFormat SDFH = new SimpleDateFormat("HH:mm:ss");

  public static Date strToDate(String str) {
    Date date;
    try {
      date = SDF.parse(str);
    }
    catch (ParseException pE) {
      date = null;
    }
    catch (NullPointerException npE) {
      date = null;
    }
    return date;
  }

  public static java.sql.Date strToSQLDate(String str) {
    java.sql.Date date;
    try {
      date = new java.sql.Date(SDF.parse(str).getTime());
    }
    catch (ParseException pE) {
      date = null;
    }
    catch (NullPointerException npE) {
      date = null;
    }
    return date;
  }

    public static java.sql.Date dateToSQLDate(java.util.Date date) {
        String str = dateToStr(date);
        return strToSQLDate(str);
    }

    public static java.util.Date sqlDateToDate(java.sql.Date date) {
        String str = dateToStr(date);
        return strToDate(str);
    }



  public static String dateToStr(Date date) {
    String str;
    try {
      str = SDF.format(date);
    }
    catch (NullPointerException npE) {
      str = "";
    }
    return str;
  }

  public static String sqlDateToStr(java.sql.Date date) {
    String str;
    try {
      str = SDF.format(date);
    }
    catch (NullPointerException npE) {
      str = "";
    }
    return str;
  }
  
  
  public static Timestamp strToTimestamp(String str) {
    Timestamp timestamp;
    try {
      timestamp = new Timestamp(SDF.parse(str).getTime());
    }
    catch (ParseException pE) {
      timestamp = null;
    }
    catch (NullPointerException npE) {
      timestamp = null;
    }
    return timestamp;
  }

  public static String timestampToStr(Timestamp timestamp) {
    String str;
    try {
      str = SDF.format(timestamp);
    }
    catch (NullPointerException npE) {
      str = "";
    }
    return str;
  }

  public static java.sql.Date timestampToSQLDate(Timestamp timestamp) {
    java.sql.Date date;
    try {
      date = new java.sql.Date(timestamp.getTime());
    }
    catch (NullPointerException npE) {
      date = null;
    }
    return date;
  }

  public static Timestamp dateToTimestamp(Date date) {
    Timestamp timestamp;
    try {
      timestamp = new Timestamp(date.getTime());
    }
    catch (NullPointerException npE) {
      timestamp = null;
    }
    return timestamp;
  }

  public static Date timestampToDate(Timestamp timestamp) {
    Date date;
    try {
      date = new Date(timestamp.getTime());
    }
    catch (NullPointerException npE) {
      date = null;
    }
    return date;
  }

  public static Integer strToInteger(String str) {
   /* Integer integer;
    try {
      if (str != null && str.length() > 0) {
        while (str.substring(0, 1).compareTo("0") == 0) {
          str = str.substring(1);
        }
      }
      integer = Integer.decode(str);
    }
    catch (NumberFormatException nfE) {
      integer = null;
    }
    catch (NullPointerException npE) {
      integer = null;
    }
    return integer;*/

   try{
     return new Integer(str);
   }catch(Exception ex){
     return new Integer(0);
   }
  }

  public static String integerToStr(Integer integer) {
    String str;
    try {
      str = integer.toString();
    }
    catch (NullPointerException npE) {
      str = "";
    }
    return str;
  }
  
  public static String longToStr(Long long_) {
    String str;
    try {
      str = long_.toString();
    }
    catch (NullPointerException npE) {
      str = "";
    }
    return str;
  }

  public static Float strToFloat(String str) {
    Float fFloat;
    try {
      fFloat = Float.valueOf(str);
    }
    catch (NumberFormatException nfE) {
      fFloat = null;
    }
    catch (NullPointerException npE) {
      fFloat = null;
    }
    return fFloat;
  }

  public static String floatToStr(Float fFloat) {
    String str;
    try {
      str = fFloat.toString();
    }
    catch (NullPointerException npE) {
      str = "";
    }
    return str;
  }

  public static Double strToDouble(String str) {
    Double dDouble;
    try {
      dDouble = Double.valueOf(str);
    }
    catch (NumberFormatException nfE) {
      dDouble = new Double(0);
    }
    catch (NullPointerException npE) {
      dDouble = new Double(0);
    }
    return dDouble;
  }

  public static String doubleToStr(Double dDouble) {
    try {
      return dDouble.toString();
    }
    catch (Exception npE) {
      return "";
    }
  }

  public static BigDecimal strToBigDecimal(String str) {
    BigDecimal bigDecimal;
    try {
      bigDecimal = new BigDecimal(str);
    }
    catch (NumberFormatException nfE) {
      bigDecimal = null;
    }
    catch (NullPointerException npE) {
      bigDecimal = null;
    }
    return bigDecimal;
  }

  public static String bigDecimalToString(BigDecimal bigDecimal) {
    return bigDecimal.toString();
  }

  public static Boolean strToBoolean(String str) {
    return new Boolean(str);
  }

  public static String booleanToString(Boolean booleano) {
    String str;
    try {
      str = booleano.toString();
    }
    catch (NullPointerException npE) {
      str = "";
    }
    return str;
  }

  public static String booleanToString(boolean booleano) {
    return booleanToString(new Boolean(booleano));
  }

  public static String timeToString(Time time) {
    String str;
    try {
      str = time.toString();
    }
    catch (NullPointerException npE) {
      str = "";
    }
    return str;
  }

  public static Time strToTime(String hora) {

    Time time;

    try {
      time = new Time(SDFH.parse(hora).getTime());
    }
    catch (ParseException ex) {
      return null;
    }
    return time;
  }
    public static Double strToDoubleComa(String str) {
        Double dDouble;
        str.replaceAll(",", "");
        try {
            dDouble = Double.valueOf(str);
        } catch (NumberFormatException nfE) {
            dDouble = new Double(0);
        } catch (NullPointerException npE) {
            dDouble = new Double(0);
        }
        return dDouble;
    }
    
}
