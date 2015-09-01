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

import dr.yadcf.common.EngineValueLabel;
import dr.yadcf.common.TableAaDataServerSide;
import dr.yadcf.db.objectify.EntryDb;

public class EntrysTableServerSideSource extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4219756235170784173L;

	private static final Logger log = Logger.getLogger(EntrysTableServerSideSource.class.getName());
	
	private static List<EntryDb> entrys;
	
	static {
		entrys = populateList();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		int start = Integer.parseInt(req.getParameter("start"));
		int length = Integer.parseInt(req.getParameter("length"));
		String globalSearch = req.getParameter("search[value]");
		String sSearch_0 = req.getParameter("columns[0][search][value]");
		String sSearch_1 = req.getParameter("columns[1][search][value]");
		String sSearch_2 = req.getParameter("columns[2][search][value]");
		String sSearch_3 = req.getParameter("columns[3][search][value]");
		String sSearch_4 = req.getParameter("columns[4][search][value]");
		
		TableAaDataServerSide tableAaData = null;
		List<List<String>> aaData =  new ArrayList<List<String>>();

		List<String> rowData = null;
		
		for (int i=start; (i < entrys.size() && aaData.size() < length); i++) {
			
			rowData = new ArrayList<String>();
			
			if(globalSearch != null && !globalSearch.trim().equals("")) {
				if(!(   entrys.get(i).getEngine().toLowerCase().indexOf(globalSearch.toLowerCase()) != -1 ||
						entrys.get(i).getBrowser().toLowerCase().indexOf(globalSearch.toLowerCase())!= -1 ||
						entrys.get(i).getPlatform().toLowerCase().indexOf(globalSearch.toLowerCase())!= -1|| 
						entrys.get(i).getVersion().toLowerCase().indexOf(globalSearch.toLowerCase())!= -1||
						entrys.get(i).getGrade().toLowerCase().indexOf(globalSearch.toLowerCase())!= -1)) {
					continue;
				}
			}
			if(sSearch_0 != null && !sSearch_0.trim().equals("")) {
				if(entrys.get(i).getEngine().toLowerCase().indexOf(sSearch_0.toLowerCase()) == -1) {
					continue;
				}
			}
			if(sSearch_1 != null && !sSearch_1.trim().equals("")) {
				if(entrys.get(i).getBrowser().toLowerCase().indexOf(sSearch_1.toLowerCase()) == -1) {
					continue;
				}
			}
			if(sSearch_2 != null && !sSearch_2.trim().equals("")) {
				if(entrys.get(i).getPlatform().toLowerCase().indexOf(sSearch_2.toLowerCase()) == -1) {
					continue;
				}
			}
			if(sSearch_3 != null && !sSearch_3.trim().equals("")) {
				String[] minMax = sSearch_3.split("-yadcf_delim-");
				Date min = null;
				Date max = null;
				if(minMax.length > 0) {
					min = tryParseDate(minMax[0]);
				}
				if(minMax.length > 1) {
					max = tryParseDate(minMax[1]);
				}
				boolean inRange = false;
				Date val = tryParseDate(entrys.get(i).getVersion());
				
				System.out.println("from:"+min+", to:"+max + ", val:"+ val);
				
				if (min == null && max == null) {
					inRange = true;
				} else if (min == null && (val.before(max) || val.equals(max))) {
					inRange = true;
				} else if (min!=null && (min.before(val) || min.equals(val)) && null == max) {
					inRange = true;
				} else if (min!=null && (min.before(val) || min.equals(val)) && (max!=null && (val.before(max) || val.equals(max)))) {
					inRange = true;
				}
				if(!inRange) {
					continue;
				}
			}
			if(sSearch_4 != null && !sSearch_4.trim().equals("")) {
				String[] minMax = sSearch_4.split("-yadcf_delim-");
				Integer min = null;
				Integer max = null;
				if(minMax.length > 0) {
					min = tryParse(minMax[0]);
				}
				if(minMax.length > 1) {
					max = tryParse(minMax[1]);
				}
				boolean inRange = false;
				Integer val = tryParse(entrys.get(i).getGrade());
				
				System.out.println("min:"+min+", max:"+max + ", val:"+ val);
				
				if (min == null && max == null) {
					inRange = true;
				} else if (min == null && val <= max) {
					inRange = true;
				} else if (min <= val && null == max) {
					inRange = true;
				} else if (min <= val && val <= max) {
					inRange = true;
				}
				if(!inRange) {
					continue;
				}
			}
			rowData.add(entrys.get(i).getEngine());
			rowData.add(entrys.get(i).getBrowser());
			rowData.add(entrys.get(i).getPlatform());
			rowData.add(entrys.get(i).getVersion());
			rowData.add(entrys.get(i).getGrade());
			
			aaData.add(rowData);
		}
		
		Set <EngineValueLabel> col0Data = new HashSet<EngineValueLabel>();
		Set <String> col1Data = new HashSet<String>();
		Set <String> col2Data = new HashSet<String>();
//		Set <String> col3Data = new HashSet<String>();
		Set <String> col4Data = new HashSet<String>();
		for (EntryDb entry : entrys) {
			col0Data.add(new EngineValueLabel(entry.getEngine(), entry.getEngine() + " Eng'"));
			col1Data.add(entry.getBrowser());
			col2Data.add(entry.getPlatform());
		}
//		col0Data.add("engine");
//		col1Data.add("browser");
//		col2Data.add("platform");
//		col3Data.add("date");
//		col4Data.add("number");
		
		col4Data.add("0");
		col4Data.add("134");
		

		tableAaData = new TableAaDataServerSide(aaData);
		tableAaData.setRecordsTotal(entrys.size());
		tableAaData.setRecordsFiltered(aaData.size());
		tableAaData.setDraw(req.getParameter("draw"));
		
		tableAaData.setYadcf_data_0(new ArrayList<EngineValueLabel>(col0Data));
		tableAaData.setYadcf_data_1(new ArrayList<String>(col1Data));
		tableAaData.setYadcf_data_2(new ArrayList<String>(col2Data));
//		tableAaData.setYadcf_data_3(new ArrayList<String>(col3Data));
		tableAaData.setYadcf_data_4(new ArrayList<String>(col4Data));
		
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		log.info("Return value to Entrys table:\t"+ new Gson().toJson(tableAaData));
		resp.getWriter().write(new Gson().toJson(tableAaData));

	        
		
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
