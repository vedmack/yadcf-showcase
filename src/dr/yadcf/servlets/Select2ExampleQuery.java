package dr.yadcf.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dr.yadcf.common.TableAaDataServerSide;
import dr.yadcf.db.objectify.EntryDb;

public class Select2ExampleQuery extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4219756235170784173L;

	private static final Logger log = Logger.getLogger(Select2ExampleQuery.class.getName());
	
	private static List<EntryDb> entrys;
	
	static {
		entrys = populateList();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		String q = req.getParameter("q");
		String response = "{\"myData\":[{\"id\":\"aaa\",\"text\":\"AAA\"},{\"id\":\"bbb\",\"text\":\"BBB\"}]}";
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		//log.info("Return value to Entrys table:\t"+ new Gson().toJson(tableAaData));
		resp.getWriter().write(response);

	        
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("post");
		doGet(req,resp);
	}


	

	public static String generateRandomDate(){
		GregorianCalendar gc = new GregorianCalendar();

        int year = randBetween(2014, 2015);

        gc.set(Calendar.YEAR, year);

        int dayOfYear = randBetween(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));

        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);

        return (gc.get(Calendar.MONTH) + 1)+ "/"+gc.get(Calendar.DAY_OF_MONTH) + "/"+gc.get(Calendar.YEAR);
	}
	
	private static List<EntryDb> populateList(){
		List<EntryDb> retList = new ArrayList<EntryDb>();
		
      
		retList.add(new EntryDb("Trident","Internet Explorer 4.0","Win 95+",generateRandomDate(),"1"));
		retList.add(new EntryDb("Trident","Internet Explorer 5.0","Win 95+",generateRandomDate(),"2"));
		retList.add(new EntryDb("Trident","Internet Explorer 5.5","Win 95+",generateRandomDate(),"3"));
		retList.add(new EntryDb("Trident","Internet Explorer 6","Win 98+",generateRandomDate(),"4"));
		retList.add(new EntryDb("Trident","Internet Explorer 7","Win XP SP2+",generateRandomDate(),"5"));
		retList.add(new EntryDb("Trident","AOL browser (AOL desktop)","Win XP",generateRandomDate(),"4"));
		retList.add(new EntryDb("Gecko","Firefox 1.0","Win 98+ / OSX.2+",generateRandomDate(),"3"));
		retList.add(new EntryDb("Gecko","Firefox 1.5","Win 98+ / OSX.2+",generateRandomDate(),"2"));
		retList.add(new EntryDb("Gecko","Firefox 2.0","Win 98+ / OSX.2+",generateRandomDate(),"1"));
		retList.add(new EntryDb("Gecko","Firefox 3.0","Win 2k+ / OSX.3+",generateRandomDate(),"2"));
		retList.add(new EntryDb("Gecko","Camino 1.0","OSX.2+",generateRandomDate(),"11"));
		retList.add(new EntryDb("Gecko","Camino 1.5","OSX.3+",generateRandomDate(),"22"));
		retList.add(new EntryDb("Gecko","Netscape 7.2","Win 95+ / Mac OS 8.6-9.2",generateRandomDate(),"33"));
		retList.add(new EntryDb("Gecko","Netscape Browser 8","Win 98SE+",generateRandomDate(),"44"));
		retList.add(new EntryDb("Gecko","Netscape Navigator 9","Win 98+ / OSX.2+",generateRandomDate(),"55"));
		retList.add(new EntryDb("Gecko","Mozilla 1.0","Win 95+ / OSX.1+",generateRandomDate(),"44"));
		retList.add(new EntryDb("Gecko","Mozilla 1.1","Win 95+ / OSX.1+",generateRandomDate(),"33"));
		retList.add(new EntryDb("Gecko","Mozilla 1.2","Win 95+ / OSX.1+",generateRandomDate(),"22"));
		retList.add(new EntryDb("Gecko","Mozilla 1.3","Win 95+ / OSX.1+",generateRandomDate(),"11"));
		retList.add(new EntryDb("Gecko","Mozilla 1.4","Win 95+ / OSX.1+",generateRandomDate(),"0"));
		retList.add(new EntryDb("Gecko","Mozilla 1.5","Win 95+ / OSX.1+",generateRandomDate(),"0"));
		retList.add(new EntryDb("Gecko","Mozilla 1.6","Win 95+ / OSX.1+",generateRandomDate(),"111"));
		retList.add(new EntryDb("Gecko","Mozilla 1.7","Win 98+ / OSX.1+",generateRandomDate(),"22"));
		retList.add(new EntryDb("Gecko","Mozilla 1.8","Win 98+ / OSX.1+",generateRandomDate(),"33"));
		retList.add(new EntryDb("Gecko","Seamonkey 1.1","Win 98+ / OSX.2+",generateRandomDate(),"44"));
		retList.add(new EntryDb("Gecko","Epiphany 2.20","Gnome",generateRandomDate(),"33"));
		retList.add(new EntryDb("Webkit","Safari 1.2","OSX.3",generateRandomDate(),"22"));
		retList.add(new EntryDb("Webkit","Safari 1.3","OSX.3",generateRandomDate(),"111"));
		retList.add(new EntryDb("Webkit","Safari 2.0","OSX.4+",generateRandomDate(),"1"));
		retList.add(new EntryDb("Webkit","Safari 3.0","OSX.4+",generateRandomDate(),"2"));
		retList.add(new EntryDb("Webkit","OmniWeb 5.5","OSX.4+",generateRandomDate(),"3"));
		retList.add(new EntryDb("Webkit","iPod Touch / iPhone","iPod",generateRandomDate(),"4"));
		retList.add(new EntryDb("Webkit","S60","S60",generateRandomDate(),"5"));
		retList.add(new EntryDb("Presto","Opera 7.0","Win 95+ / OSX.1+",generateRandomDate(),"134"));
		retList.add(new EntryDb("Presto","Opera 7.5","Win 95+ / OSX.2+",generateRandomDate(),"25"));
		retList.add(new EntryDb("Presto","Opera 8.0","Win 95+ / OSX.2+",generateRandomDate(),"36"));
		retList.add(new EntryDb("Presto","Opera 8.5","Win 95+ / OSX.2+",generateRandomDate(),"67"));
		retList.add(new EntryDb("Presto","Opera 9.0","Win 95+ / OSX.3+",generateRandomDate(),"78"));
		retList.add(new EntryDb("Presto","Opera 9.2","Win 88+ / OSX.3+",generateRandomDate(),"79"));
		retList.add(new EntryDb("Presto","Opera 9.5","Win 88+ / OSX.3+",generateRandomDate(),"21"));
		retList.add(new EntryDb("Presto","Opera for Wii","Wii",generateRandomDate(),"321"));
		retList.add(new EntryDb("Presto","Nokia N800","N800",generateRandomDate(),"123"));
		retList.add(new EntryDb("Presto","Nintendo DS browser","Nintendo DS",generateRandomDate(),"123"));
		retList.add(new EntryDb("KHTML","Konqureror 3.1","KDE 3.1",generateRandomDate(),"77"));
		retList.add(new EntryDb("KHTML","Konqureror 3.3","KDE 3.3",generateRandomDate(),"88"));
		retList.add(new EntryDb("KHTML","Konqureror 3.5","KDE 3.5",generateRandomDate(),"99"));
		retList.add(new EntryDb("Tasman","Internet Explorer 4.5","Mac OS 8-9",generateRandomDate(),"88"));
		retList.add(new EntryDb("Tasman","Internet Explorer 5.1","Mac OS 7.6-9",generateRandomDate(),"66"));
		retList.add(new EntryDb("Tasman","Internet Explorer 5.2","Mac OS 8-X",generateRandomDate(),"777"));
		retList.add(new EntryDb("Misc","NetFront 3.1","Embedded devices",generateRandomDate(),"54"));
		retList.add(new EntryDb("Misc","NetFront 3.4","Embedded devices",generateRandomDate(),"36"));
		retList.add(new EntryDb("Misc","Dillo 0.8","Embedded devices",generateRandomDate(),"45"));
		retList.add(new EntryDb("Misc","Links","Text only",generateRandomDate(),"66"));
		retList.add(new EntryDb("Misc","Lynx","Text only",generateRandomDate(),"7"));
		retList.add(new EntryDb("Misc","IE Mobile","Windows Mobile 6",generateRandomDate(),"3"));
		retList.add(new EntryDb("Misc","PSP browser","PSP",generateRandomDate(),"44"));
		retList.add(new EntryDb("Other browsers","All others","-",generateRandomDate(),"88"));
	
		
		return retList;
	}
	
	public static Integer tryParse(String text) {
	  try {
	    return new Integer(text);
	  } catch (Exception e) {
	    return null;
	  }
	}
	
	public static Date tryParseLong(String text) {
	  try {
	    Long tmp = new Long(text);
	    return new Date(tmp);
	    
	  } catch (Exception e) {
	    return null;
	  }
	}
	
	public static Date tryParseDate(String text) {
	  try {
	    return new SimpleDateFormat("dd/MM/yyyy").parse(text);
	    
	  } catch (Exception e) {
	    return null;
	  }
	}

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
	
}
