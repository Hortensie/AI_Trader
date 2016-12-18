package pro.xstore.api.sync;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Class with information about server address and connection parameters
 */
public class ServerData {

	public static enum ServerEnum {
		REAL, DEMO, UAT
	}
	
	private static final String XAPI_A = "xapia.x-station.eu";
	private static final String XAPI_B = "xapib.x-station.eu";
	
	private static final String[] XAPI_LIST = {XAPI_A, XAPI_B};
	private static final String[] XAPI_NAMES = {"A", "B"};

	private static final int[] PORTS_REAL = {5112, 5113};
	private static final int[] PORTS_DEMO = {5124, 5125};
	private static final int[] PORTS_UAT = {5116, 5117};

	protected static Map<String, String> xapiMap;
	protected static Map<String, String> xapiReversedMap;
	private static LinkedList<String> xapiList;
	private static String lastAddress;

	public ServerData()
    {
		setUpList();
    }

	public static ServerEnum getServerEnumByString(String serverName) {
		ServerEnum response = null;
		if (serverName != null) {
			if (serverName.equals("DEMO")) response = ServerEnum.DEMO;
			else if (serverName.equals("REAL")) response = ServerEnum.REAL;
			else if (serverName.equals("UAT")) response = ServerEnum.UAT;
		}
		return response;
	}

	private static void setUpList()
	{
		xapiMap = new HashMap<String, String>();
		xapiReversedMap = new HashMap<String, String>();
		xapiList = new LinkedList<String>();
		
		for (int i = 0; i < XAPI_LIST.length; i++) {
			String address = XAPI_LIST[i];
			String letter = XAPI_NAMES[i];
			
			xapiMap.put(letter, address);
			xapiReversedMap.put(address, letter);
			xapiList.add(address);
		}

		Collections.shuffle(xapiList);
		lastAddress = null;
	}
	
	public static String getNextAddress() {
		String response = null;
		if (lastAddress == null) {
			response = xapiList.getFirst();
		} else if (lastAddress.equals(xapiList.getLast())) {
			response = null;
		} else {
			int currentIndex = xapiList.indexOf(lastAddress);
			response = xapiList.get(currentIndex + 1);
		}
		lastAddress = response;
		return response;
	}
	
	public static Server getServerByEnum(ServerEnum server) {
    	if(xapiMap == null)
    	{
    		setUpList();
    	}

		Server response = null;

		switch (server) {
			case REAL:
			case DEMO:
			case UAT:
				String xapiAddress = lastAddress == null ? getNextAddress() : lastAddress;
				if (xapiAddress != null) {
					String letter = xapiReversedMap.get(xapiAddress);
					response = ServerData.getProductionServers().get(String.valueOf(server)+"_"+letter);
				}
				break;
		}
		return response;
	}
    
    /**
     * Static method which receives map of produciton servers
     */
	public static Map<String, Server> getProductionServers()
    {
		HashMap<String, Server> dict = new HashMap<String, Server>();
		
		dict = addServers(dict, PORTS_DEMO, true, "DEMO");
		dict = addServers(dict, PORTS_REAL, true, "REAL");
		dict = addServers(dict, PORTS_UAT, true, "UAT");
		
		return dict;
    }

    protected static HashMap<String, Server> addServers(HashMap<String, Server> dict, int[] portsArray, boolean isSsl, String desc)
    {
    	if(xapiMap == null)
    	{
    		setUpList();
    	}
    	
    	int mainPort = portsArray[0];
    	int streamingPort = portsArray[1];
    	for (String xapiKey : xapiMap.keySet())
    	{
    		String address = xapiMap.get(xapiKey);
    		String dictKey = desc+"_"+xapiKey;
    		String dictDesc = desc+" "+xapiKey;
    		dict.put(dictKey, new Server(address, mainPort, streamingPort, isSsl, dictDesc, true));
    	}
    	return dict;
    }
    
	public static String getLastAddress() {
		return lastAddress;
	}
}