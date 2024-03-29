import ir.dispute.DisputeDictForm;
import ir.persistence.DictionaryPersistence;
import ir.support.SupportDictForm;
import ir.support.SupportFinderThread;

import java.io.FileOutputStream;
import java.io.PrintStream;

import edu.smu.tspell.wordnet.SynsetType;


public class DictionaryCreator {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PrintStream out = null;
		try{
		
		String seed="believe";
		SynsetType type=SynsetType.VERB;
		System.setProperty("wordnet.database.dir",
				"C:\\Program Files (x86)\\WordNet\\2.1\\dict\\");
		SupportDictForm s = new SupportDictForm(type,seed,false);
		s.startProcess(seed, SynsetType.VERB);

		Thread t1=new Thread(new SupportFinderThread(seed,type,false,false),"Thread1");
		Thread t2=new Thread(new SupportFinderThread(seed,type,false,false),"Thread2");
		Thread t3=new Thread(new SupportFinderThread(seed,type,true,false),"Thread3");
		t1.start();
		t2.start();
		t3.start();
		DisputeDictForm dd=new DisputeDictForm(type, seed);
		dd.statDisputeProcess();
		//System.out.println(DictionaryPersistence.hashDictionary.toString());
		System.out.println(DictionaryPersistence.hashDictionary.size());
		} catch(Exception ex){
			System.out.println(ex.getMessage());
		}finally{
			//out.close();
		}
	}
}