package abd.p1.view;

import abd.p1.model.Generos;

public interface Observer {
	public void notifyNameCh(String s);
	
	public void notifyEdadCh(int n);
	
	public void notifySexCh(String g);
	
	public void notifyPrefCh(String p);
	
	public void notifyAddAf(String s);
	
	public void notifyAvaCh(byte[] img);
	
}
