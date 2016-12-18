package pro.xstore.api.sync;

import java.util.LinkedList;
import java.util.Map;

import pro.xstore.api.message.command.APICommandFactory;
import pro.xstore.api.message.records.STickRecord;
import pro.xstore.api.message.records.STradeRecord;
import pro.xstore.api.message.records.TickRecord;
import pro.xstore.api.message.response.LoginResponse;
import pro.xstore.api.message.response.TickPricesResponse;
import pro.xstore.api.streaming.StreamingListener;
import pro.xstore.api.sync.ServerData.ServerEnum;

public class Example {

	public void runExample(ServerEnum server, Credentials credentials) throws Exception {
		try {
			SyncAPIConnector connector = new SyncAPIConnector(server);
			LoginResponse loginResponse = APICommandFactory.executeLoginCommand(connector, credentials);
			System.out.println(loginResponse);
			if (loginResponse != null && loginResponse.getStatus())
			{
				StreamingListener sl = new StreamingListener() {
					@Override
					public void receiveTradeRecord(STradeRecord tradeRecord) {
						System.out.println("Stream trade record: " + tradeRecord);
					}
	
					@Override
					public void receiveTickRecord(STickRecord tickRecord) {
						System.out.print("Stream tick record: " + tickRecord);
					}
				};

				LinkedList<String> list = new LinkedList<String>();
				String symbol = "DE30";
				list.add(symbol);

				TickPricesResponse resp = APICommandFactory.executeTickPricesCommand(connector, 0L, list, 0L);
				for (TickRecord tr : resp.getTicks()) {
					System.out.println("TickPrices result: "+tr.getSymbol() + " - ask: " + tr.getAsk());
				}

				connector.connectStream(sl);
				System.out.println("Stream connected.");
				
				connector.subscribePrice(symbol);
				connector.subscribeTrades();
	
				Thread.sleep(10000);

				connector.unsubscribePrice(symbol);
				connector.unsubscribeTrades();
			
				connector.disconnectStream();
				System.out.println("Stream disconnected.");
				
				Thread.sleep(5000);
				
				connector.connectStream(sl);
				System.out.println("Stream connected again.");
				connector.disconnectStream();
				System.out.println("Stream disconnected again.");
				System.exit(0);
			}
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}

	protected Map<String,Server> getAvailableServers() {
		return ServerData.getProductionServers();
	}
}