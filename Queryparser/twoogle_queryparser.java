import org.apache.solr.search.QParser;
import org.apache.solr.common.params.DisMaxParams;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.apache.solr.search.ExtendedDismaxQParserPlugin;
import org.apache.solr.search.ExtendedDismaxQParser;
import org.apache.solr.common.params.SolrParams;
import org.apache.solr.request.SolrQueryRequest;
import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;
import com.memetix.mst.detect.Detect;
import de.mpg.mpiwg.berlin.mpdl.exception.ApplicationException;

public class twoogle_queryparser extends ExtendedDismaxQParserPlugin {
	  private static final String[] defined_weights = {"text_en", "text_ru", "text_de","text_fr","text_es", "hashtags","favorite_count", "retweet_count", "followers_count",
				"list_count" };
	  private static final String[] defined_weights1 = {"text_en", "text_ru", "text_de","text_fr","text_es", "hashtags","favorite_count", "retweet_count", "followers_count",
				"list_count"};
	  private static final String[] defined_weights2 = {"text_en", "text_ru", "text_de","text_fr","text_es","hashtags","favorite_count", "retweet_count", "followers_count",
			    "list_count"};
	  
	  public void initialise()
		{
			
			final String KEY = "474A4E72DB217E37031EC190ACB4159378A6917C";
			Translate.setKey(KEY);	
		}
	  
	  public String detectLang(String query) throws ApplicationException { 
		   String langCode = null; 
		   try {
		      Language detectedLanguage = Detect.execute(query); 
		      if (detectedLanguage != null) 
		        langCode = detectedLanguage.toString(); 
		    } catch (Exception e) { 
		      throw new ApplicationException(e); 
		    } 
		   return langCode; 
		 }
	  
	  @Override
	  public QParser createParser(String qstr, SolrParams localParams, SolrParams params, SolrQueryRequest req) {
		  String fquery = qstr, trans_en = "", trans_ru = "", trans_de = "",
					trans_fr = "", trans_es = "";
	    ModifiableSolrParams customParams = new ModifiableSolrParams();
	    initialise();
	    String lang="";
	    
	    try {
			lang=detectLang(qstr);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    if(lang.contains("en"))
	    {
	    	defined_weights[0]="text_en^6.5";
	    	defined_weights[1]= "text_ru^3.0";
	    	defined_weights[2]="text_de^3.0";
	    	defined_weights[3]="text_fr^3.0";
	    	defined_weights[4]="text_es^3.0";
	    	
	    	defined_weights1[0]="text_en^2.5";
	    	defined_weights1[1]= "text_ru^1.0";
	    	defined_weights1[2]="text_de^1.0";
	    	defined_weights1[3]="text_fr^1.0";
	    	defined_weights1[4]="text_es^1.0";
	    	
	    	defined_weights2[0]="text_en^4.0";
	    	defined_weights2[1]= "text_ru^2.5";
	    	defined_weights2[2]="text_de^2.5";
	    	defined_weights2[3]="text_fr^2.5";
	    	defined_weights2[4]="text_es^2.5";
	    	
	    	
	    	try {
				trans_de=Translate.execute(qstr, Language.ENGLISH,Language.GERMAN);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	try {
				trans_ru=Translate.execute(qstr, Language.ENGLISH,Language.RUSSIAN);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	try {
				trans_es=Translate.execute(qstr, Language.ENGLISH,Language.SPANISH);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	try {
				trans_fr=Translate.execute(qstr, Language.ENGLISH,Language.FRENCH);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	
	    	fquery = "text_en:\"\"" + qstr+ "\"\"" + " OR text_de:\"\"" + trans_de
					+ "\"\"" + " OR text_ru:\"\"" + trans_ru + "\"\"" + " OR text_es:\"\""
					+ trans_es + "\"\"" + " OR text_fr:\"\"" + trans_fr + "\"\"";
	    
	    
	    }
	    
	    
	    else if(lang.contains("ru"))
	    {
	    
	    	defined_weights[0]="text_en^3.0";
	    	defined_weights[1]= "text_ru^6.5";
	    	defined_weights[2]="text_de^3.0";
	    	defined_weights[3]="text_fr^3.0";
	    	defined_weights[4]="text_es^3.0";
	    	
	    	defined_weights1[0]="text_en^1.0";
	    	defined_weights1[1]= "text_ru^2.5";
	    	defined_weights1[2]="text_de^1.0";
	    	defined_weights1[3]="text_fr^1.0";
	    	defined_weights1[4]="text_es^1.0";
	    	
	    	defined_weights2[0]="text_en^2.5";
	    	defined_weights2[1]= "text_ru^4.0";
	    	defined_weights2[2]="text_de^2.5";
	    	defined_weights2[3]="text_fr^2.5";
	    	defined_weights2[4]="text_es^2.5";
	    	
	    	
	    	try {
				trans_de=Translate.execute(qstr, Language.RUSSIAN,Language.GERMAN);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	try {
				trans_en=Translate.execute(qstr, Language.RUSSIAN,Language.ENGLISH);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	try {
				trans_es=Translate.execute(qstr, Language.RUSSIAN,Language.SPANISH);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	try {
				trans_fr=Translate.execute(qstr, Language.RUSSIAN,Language.FRENCH);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	
	    	
	    	
	    
	    	fquery = "text_ru:\"\"" + qstr+ "\"\"" + " OR text_de:\"\"" + trans_de
			+ "\"\"" + " OR text_en:\"\"" + trans_en + "\"\"" + " OR text_es:\"\""
			+ trans_es + "\"\"" + " OR text_fr:\"\"" + trans_fr + "\"\"";
	    	
	    	
	    }
	    else if(lang.contains("de"))
	    {
	    	
		    
	    	defined_weights[0]="text_en^3.0";
	    	defined_weights[1]= "text_ru^6.5";
	    	defined_weights[2]="text_de^3.0";
	    	defined_weights[3]="text_fr^3.0";
	    	defined_weights[4]="text_es^3.0";
	    	
	    	defined_weights1[0]="text_en^1.0";
	    	defined_weights1[1]= "text_ru^2.5";
	    	defined_weights1[2]="text_de^1.0";
	    	defined_weights1[3]="text_fr^1.0";
	    	defined_weights1[4]="text_es^1.0";
	    	
	    	defined_weights2[0]="text_en^2.5";
	    	defined_weights2[1]= "text_ru^4.0";
	    	defined_weights2[2]="text_de^2.5";
	    	defined_weights2[3]="text_fr^2.5";
	    	defined_weights2[4]="text_es^2.5";
	    	
	    	
	    	try {
				trans_ru=Translate.execute(qstr, Language.GERMAN,Language.RUSSIAN);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	try {
				trans_en=Translate.execute(qstr, Language.GERMAN,Language.ENGLISH);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	try {
				trans_es=Translate.execute(qstr, Language.GERMAN,Language.SPANISH);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	try {
				trans_fr=Translate.execute(qstr, Language.GERMAN,Language.FRENCH);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	
	    	fquery = "text_de:\"\"" + qstr+ "\"\"" + " OR text_ru:\"\"" + trans_ru
	    			+ "\"\"" + " OR text_en:\"\"" + trans_en + "\"\"" + " OR text_es:\"\""
	    			+ trans_es + "\"\"" + " OR text_fr:\"\"" + trans_fr + "\"\"";
	    	
	    	
	    }
	    else if(lang.contains("fr"))
	    {
	    
	    	defined_weights[0]="text_en^3.0";
	    	defined_weights[1]= "text_ru^3.0";
	    	defined_weights[2]="text_de^3.0";
	    	defined_weights[3]="text_fr^6.5";
	    	defined_weights[4]="text_es^3.0";
	    	
	    	defined_weights1[0]="text_en^1.0";
	    	defined_weights1[1]= "text_ru^1.0";
	    	defined_weights1[2]="text_de^1.0";
	    	defined_weights1[3]="text_fr^2.5";
	    	defined_weights1[4]="text_es^1.0";
	    	
	    	defined_weights2[0]="text_en^2.5";
	    	defined_weights2[1]= "text_ru^2.5";
	    	defined_weights2[2]="text_de^2.5";
	    	defined_weights2[3]="text_fr^4.0";
	    	defined_weights2[4]="text_es^2.5";
	    	
	    	try {
				trans_ru=Translate.execute(qstr, Language.FRENCH,Language.RUSSIAN);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	try {
				trans_en=Translate.execute(qstr, Language.FRENCH,Language.ENGLISH);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	try {
				trans_es=Translate.execute(qstr, Language.FRENCH,Language.SPANISH);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	try {
				trans_de=Translate.execute(qstr, Language.FRENCH,Language.GERMAN);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	
	    	fquery = "text_fr:\"\"" + qstr+ "\"\"" + " OR text_ru:\"\"" + trans_ru
	    			+ "\"\"" + " OR text_en:\"\"" + trans_en + "\"\"" + " OR text_es:\"\""
	    			+ trans_es + "\"\"" + " OR text_de:\"\"" + trans_de + "\"\"";
	    	
	    	
	    	
	    	
	    }
	    else if(lang.contains("es"))
	    {
	    
	    	defined_weights[0]="text_en^3.0";
	    	defined_weights[1]= "text_ru^3.0";
	    	defined_weights[2]="text_de^3.0";
	    	defined_weights[3]="text_fr^3.0";
	    	defined_weights[4]="text_es^6.5";
	    	
	    	defined_weights1[0]="text_en^1.0";
	    	defined_weights1[1]= "text_ru^1.0";
	    	defined_weights1[2]="text_de^1.0";
	    	defined_weights1[3]="text_fr^1.0";
	    	defined_weights1[4]="text_es^2.5";
	    	
	    	defined_weights2[0]="text_en^2.5";
	    	defined_weights2[1]= "text_ru^2.5";
	    	defined_weights2[2]="text_de^2.5";
	    	defined_weights2[3]="text_fr^2.5";
	    	defined_weights2[4]="text_es^4.0";
	    	
	    	
	    	try {
				trans_ru=Translate.execute(qstr, Language.SPANISH,Language.RUSSIAN);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	try {
				trans_en=Translate.execute(qstr, Language.SPANISH,Language.ENGLISH);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	try {
				trans_fr=Translate.execute(qstr, Language.SPANISH,Language.FRENCH);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	try {
				trans_de=Translate.execute(qstr, Language.SPANISH,Language.GERMAN);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	
	    	fquery = "text_es:\"\"" + qstr+ "\"\"" + " OR text_ru:\"\"" + trans_ru
	    			+ "\"\"" + " OR text_en:\"\"" + trans_en + "\"\"" + " OR text_fr:\"\""
	    			+ trans_fr + "\"\"" + " OR text_de:\"\"" + trans_de + "\"\"";
	    		
	    	
	    }
	    else
	    {
	    	
	    	
	    	defined_weights[0]="text_en^3.0";
	    	defined_weights[1]= "text_ru^3.0";
	    	defined_weights[2]="text_de^3.0";
	    	defined_weights[3]="text_fr^3.0";
	    	defined_weights[4]="text_es^3.0";
	    	
	    	defined_weights1[0]="text_en^1.0";
	    	defined_weights1[1]= "text_ru^1.0";
	    	defined_weights1[2]="text_de^1.0";
	    	defined_weights1[3]="text_fr^1.0";
	    	defined_weights1[4]="text_es^1.0";
	    	
	    	defined_weights2[0]="text_en^2.5";
	    	defined_weights2[1]= "text_ru^2.5";
	    	defined_weights2[2]="text_de^2.5";
	    	defined_weights2[3]="text_fr^2.5";
	    	defined_weights2[4]="text_es^2.5";
	    	
	    	
	    	try {
				trans_ru=Translate.execute(qstr,Language.RUSSIAN);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	try {
				trans_en=Translate.execute(qstr,Language.ENGLISH);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	try {
				trans_fr=Translate.execute(qstr,Language.FRENCH);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	try {
				trans_de=Translate.execute(qstr,Language.GERMAN);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	
	    	fquery = "text_es:\"\"" + trans_es+ "\"\"" + " OR text_ru:\"\"" + trans_ru
	    			+ "\"\"" + " OR text_en:\"\"" + trans_en + "\"\"" + " OR text_fr:\"\""
	    			+ trans_fr + "\"\"" + " OR text_de:\"\"" + trans_de + "\"\"";
	    		
	   	
	    }
	    if(qstr.contains("#"))
	    {
	    	
	    	defined_weights[5]="hashtags^3.0";
	    	defined_weights1[5]="hashtags^1.5";
	    	defined_weights2[5]="hashtags^2.0";
	    	
	    	
	    	
	    }
	    else
	    {	
	    	defined_weights[5]="hashtags^1";
	    	defined_weights1[5]="hashtags^1";
	    	defined_weights2[5]="hashtags^1";
	     	
	    }
	    defined_weights[6] = "favorite_count^3.0";
	    defined_weights[7] = "retweet_count^3.0";
	    defined_weights[8] = "followers_count^3.0";
	    defined_weights[9] = "list_count^2.5";

	    defined_weights1[6] = "favorite_count^1.0";
	    defined_weights1[7] = "retweet_count^1.0";
	    defined_weights1[8] = "followers_count^1.0";
	    defined_weights1[9] = "list_count^1.0";

	    defined_weights2[6] = "favorite_count^2.5";
	    defined_weights2[7] = "retweet_count^2.5";
	    defined_weights2[8] = "followers_count^2.5";
	    defined_weights2[9] = "list_count^2.0";
	
	  
	    customParams.add(DisMaxParams.QF,defined_weights);
	    customParams.add(DisMaxParams.PF,defined_weights);
	    customParams.add(DisMaxParams.PF2,defined_weights1);
	    customParams.add(DisMaxParams.PF3,defined_weights2);
	    
	    params = SolrParams.wrapAppended(params, customParams);
	    return new ExtendedDismaxQParser(fquery, localParams, params, req);
	  }
	}
