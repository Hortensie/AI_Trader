package pro.xstore.api.message.command;

import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import pro.xstore.api.message.codes.PERIOD_CODE;
import pro.xstore.api.message.codes.TRADE_OPERATION_CODE;
import pro.xstore.api.message.codes.TRADE_TRANSACTION_TYPE;
import pro.xstore.api.message.error.APICommandConstructionException;
import pro.xstore.api.message.error.APICommunicationException;
import pro.xstore.api.message.error.APIReplyParseException;
import pro.xstore.api.message.records.ChartLastInfoRecord;
import pro.xstore.api.message.records.ChartRangeInfoRecord;
import pro.xstore.api.message.records.RedirectRecord;
import pro.xstore.api.message.records.TradeTransInfoRecord;
import pro.xstore.api.message.response.APIErrorResponse;
import pro.xstore.api.message.response.AllSymbolsResponse;
import pro.xstore.api.message.response.CalendarResponse;
import pro.xstore.api.message.response.ChartResponse;
import pro.xstore.api.message.response.CommissionDefResponse;
import pro.xstore.api.message.response.CurrentUserDataResponse;
import pro.xstore.api.message.response.IbsHistoryResponse;
import pro.xstore.api.message.response.LoginResponse;
import pro.xstore.api.message.response.LogoutResponse;
import pro.xstore.api.message.response.MarginLevelResponse;
import pro.xstore.api.message.response.MarginTradeResponse;
import pro.xstore.api.message.response.NewsResponse;
import pro.xstore.api.message.response.PingResponse;
import pro.xstore.api.message.response.ProfitCalculationResponse;
import pro.xstore.api.message.response.ServerTimeResponse;
import pro.xstore.api.message.response.StepRulesResponse;
import pro.xstore.api.message.response.SymbolResponse;
import pro.xstore.api.message.response.TickPricesResponse;
import pro.xstore.api.message.response.TradeRecordsResponse;
import pro.xstore.api.message.response.TradeTransactionResponse;
import pro.xstore.api.message.response.TradeTransactionStatusResponse;
import pro.xstore.api.message.response.TradesHistoryResponse;
import pro.xstore.api.message.response.TradesResponse;
import pro.xstore.api.message.response.TradingHoursResponse;
import pro.xstore.api.message.response.VersionResponse;
import pro.xstore.api.sync.Credentials;
import pro.xstore.api.sync.Server;
import pro.xstore.api.sync.SyncAPIConnector;

/**
 * Main class for creating and executing commands.
 */
public class APICommandFactory {

	private static ThreadLocal<Integer> redirectCounter = new ThreadLocal<Integer>();
	
    public static LoginCommand createLoginCommand(Credentials credentials) throws APICommandConstructionException {
        JSONObject jsonObj = createLoginJsonObject(credentials);
        return new LoginCommand(jsonObj);
    }
    
    @SuppressWarnings("unchecked")
    private static JSONObject createLoginJsonObject(Credentials credentials) {
        JSONObject response = new JSONObject();
        if (credentials != null) {
	        response.put("userId", credentials.getLogin());
	        response.put("password", credentials.getPassword());
	        response.put("type", "java");
	        response.put("version", SyncAPIConnector.WRAPPER_VERSION);

	        String appId = credentials.getAppId(); 
	        if (appId != null) {
	        	response.put("appId", appId);
	        }

	        String appName = credentials.getAppName(); 
	        if (appName != null) {
	        	response.put("appName", appName);
	        }
        }
        return response;
    }

    @SuppressWarnings("unchecked")
    public static ChartLastCommand createChartLastCommand(ChartLastInfoRecord info) throws APICommandConstructionException {
        JSONObject args = new JSONObject();
        args.put("info", info.toJSONObject());
        return new ChartLastCommand(args);
    }

    public static LoginResponse executeLoginCommand(SyncAPIConnector connector, Credentials credentials) throws APICommandConstructionException, APICommunicationException, APIReplyParseException, APIErrorResponse, IOException {
    	LoginCommand lc = createLoginCommand(credentials);
    	LoginResponse response = executeLoginCommandHelper(connector, lc);
    	redirectCounter.set(0);
    	while(isRedirectNeeded(response)) {
    		checkIfTooManyRedirects();
    		redirectCounter.set(redirectCounter.get() + 1);
    		Server server = createServerFromLoginResponse(response);
    		System.out.println("Redirecting to: " + server);
    		connector.redirect(server);
    		response = executeLoginCommandHelper(connector, lc);
    	}
    	String streamSessionId = response.getStreamSessionId();
    	if(streamSessionId != null) {
    		connector.setStreamSessionId(streamSessionId);
    	}
    	return response;
    }

    private static boolean isRedirectNeeded(LoginResponse lr) {
    	return lr != null && !lr.getStatus() && lr.isRedirectSet();
    }

    private static void checkIfTooManyRedirects() throws APICommunicationException {
		if (redirectCounter.get() >= SyncAPIConnector.MAX_REDIRECTS) {
			throw new APICommunicationException("too many redirects");
		}
    }

    private static Server createServerFromLoginResponse(LoginResponse lr) {
    	Server response = null;
    	if (lr != null) {
	    	RedirectRecord redir = lr.getRedirect();
	    	if (redir != null) {
				String address = redir.getAddress();
				int mainPort = redir.getMainPort();
				int streamingPort = redir.getStreamingPort();
				boolean secure = true;
				boolean partOfXapiList = false;
				String description = "redirected server " + redirectCounter.get();
				response = new Server(address, mainPort, streamingPort, secure, description, partOfXapiList);
	    	}
    	}
		return response;
    }
    
    private static LoginResponse executeLoginCommandHelper(SyncAPIConnector connector, LoginCommand lc) throws APICommunicationException, APIReplyParseException, APIErrorResponse {
    	String loginResult = connector.safeExecuteCommand(lc);
    	LoginResponse response = new LoginResponse(loginResult);
    	return response;
    }
    
    public static ChartResponse executeChartLastCommand(SyncAPIConnector connector, ChartLastInfoRecord info) throws APICommandConstructionException, APICommunicationException, APIReplyParseException, APIErrorResponse {
        return new ChartResponse(connector.safeExecuteCommand(createChartLastCommand(info)));
    }

    @SuppressWarnings("unchecked")
    public static ChartLastCommand createChartLastCommand(String symbol, PERIOD_CODE period, long start) throws APICommandConstructionException {
        JSONObject args = new JSONObject();
        args.put("info", (new ChartLastInfoRecord(symbol, period, start)).toJSONObject());
        return new ChartLastCommand(args);
    }

    @SuppressWarnings("unchecked")
    public static ChartRangeCommand createChartRangeCommand(ChartRangeInfoRecord info) throws APICommandConstructionException {
        JSONObject args = new JSONObject();
        args.put("info", info.toJSONObject());
        return new ChartRangeCommand(args);

    }

    public static VersionCommand createVersionCommand() throws APICommandConstructionException {
        return new VersionCommand();
    }
    
    public static PingCommand createPingCommand() throws APICommandConstructionException {
        return new PingCommand();
    }
    
    public static StepRulesCommand createStepRulesCommand() throws APICommandConstructionException {
        return new StepRulesCommand();
    }
    
    public static ProxyVersionCommand createProxyVersionCommand() throws APICommandConstructionException {
        return new ProxyVersionCommand();
    }
    
    @SuppressWarnings("unchecked")
    public static ChartRangeCommand createChartRangeCommand(String symbol, PERIOD_CODE period, Long start, Long end, Long ticks) throws APICommandConstructionException {
        JSONObject args = new JSONObject();
        args.put("info", (new ChartRangeInfoRecord(symbol, period, start, end, ticks)).toJSONObject());
        return new ChartRangeCommand(args);
    }

    public static CalendarCommand createCalendarCommand() throws APICommandConstructionException {
        return new CalendarCommand();
    }

    @SuppressWarnings("unchecked")
    public static CommissionDefCommand createCommissionDefCommand(String symbol, Double volume) throws APICommandConstructionException {
        JSONObject args = new JSONObject();
        args.put("symbol", symbol);
        args.put("volume", volume);
        return new CommissionDefCommand(args);
    }

    public static LogoutCommand createLogoutCommand() throws APICommandConstructionException {
        return new LogoutCommand();
    }

    public static MarginLevelCommand createMarginLevelCommand() throws APICommandConstructionException {
        return new MarginLevelCommand();
    }

    @SuppressWarnings("unchecked")
    public static MarginTradeCommand createMarginTradeCommand(String symbol, double volume) throws APICommandConstructionException {
        JSONObject args = new JSONObject();
        args.put("symbol", symbol);
        args.put("volume", volume);
        return new MarginTradeCommand(args);
    }

    @SuppressWarnings("unchecked")
    public static ProfitCalculationCommand createProfitCalculationCommand(String symbol, Double volume, TRADE_OPERATION_CODE cmd, Double openPrice, Double closePrice) throws APICommandConstructionException {
        JSONObject args = new JSONObject();
        args.put("symbol", symbol);
        args.put("volume", volume);
        args.put("cmd", cmd.getCode());
        args.put("openPrice", openPrice);
        args.put("closePrice", closePrice);
        return new ProfitCalculationCommand(args);
    }

    @SuppressWarnings("unchecked")
    public static NewsCommand createNewsCommand(long start, long end) throws APICommandConstructionException {
        JSONObject args = new JSONObject();
        args.put("start", start);
        args.put("end", end);
        return new NewsCommand(args);
    }

    @SuppressWarnings("unchecked")
    public static IbHistoryCommand createIbHistoryCommand(long start, long end) throws APICommandConstructionException {
        JSONObject args = new JSONObject();
        args.put("start", start);
        args.put("end", end);
        return new IbHistoryCommand(args);
    }
    
    public static ServerTimeCommand createServerTimeCommand() throws APICommandConstructionException {
        return new ServerTimeCommand();
    }
    
    public static CurrentUserDataCommand createCurrentUserDataCommand() throws APICommandConstructionException {
        return new CurrentUserDataCommand();
    }

    public static AllSymbolsCommand createAllSymbolsCommand() throws APICommandConstructionException {
        return new AllSymbolsCommand();
    }

    @SuppressWarnings("unchecked")
    public static SymbolCommand createSymbolCommand(String symbol) throws APICommandConstructionException {
        JSONObject args = new JSONObject();
        args.put("symbol", symbol);
        return new SymbolCommand(args);
    }

    @SuppressWarnings("unchecked")
    public static TickPricesCommand createTickPricesCommand(Long level, List<String> symbols, Long timestamp) throws APICommandConstructionException {
        JSONObject args = new JSONObject();
        JSONArray arr = new JSONArray();
        arr.addAll(symbols);
        args.put("symbols", arr);
        args.put("timestamp", timestamp);
        args.put("level", level);
        return new TickPricesCommand(args);
    }

    @SuppressWarnings("unchecked")
    public static TradeRecordsCommand createTradeRecordsCommand(List<Long> orders) throws APICommandConstructionException {
        JSONObject args = new JSONObject();
        JSONArray arr = new JSONArray();
        arr.addAll(orders);
        args.put("orders", arr);
        return new TradeRecordsCommand(args);
    }

    @SuppressWarnings("unchecked")
    public static TradeTransactionCommand createTradeTransactionCommand(TradeTransInfoRecord tradeTransInfo) throws APICommandConstructionException {
        JSONObject args = new JSONObject();
        args.put("tradeTransInfo", tradeTransInfo.toJSONObject());
        return new TradeTransactionCommand(args);
    }

    @SuppressWarnings("unchecked")
    public static TradeTransactionCommand createTradeTransactionCommand(TRADE_OPERATION_CODE cmd, TRADE_TRANSACTION_TYPE type, Double price, Double sl, Double tp, String symbol, Double volume, Long order, String customComment, Long expiration) throws APICommandConstructionException {
        JSONObject args = new JSONObject();
        args.put("tradeTransInfo", new TradeTransInfoRecord(cmd, type, price, sl, tp, symbol, volume, order, customComment, expiration).toJSONObject());
        return new TradeTransactionCommand(args);
    }

    @SuppressWarnings("unchecked")
    public static TradeTransactionStatusCommand createTradeTransactionStatusCommand(long order) throws APICommandConstructionException {
        JSONObject args = new JSONObject();
        args.put("order", order);
        return new TradeTransactionStatusCommand(args);
    }

    @SuppressWarnings("unchecked")
    public static TradesCommand createTradesCommand(boolean openedOnly) throws APICommandConstructionException {
        JSONObject args = new JSONObject();
        args.put("openedOnly", openedOnly);
        return new TradesCommand(args);
    }

    @SuppressWarnings("unchecked")
    public static TradesHistoryCommand createTradesHistoryCommand(Long start, Long end) throws APICommandConstructionException {
        JSONObject args = new JSONObject();
        args.put("start", start);
        args.put("end", end);
        return new TradesHistoryCommand(args);
    }

    @SuppressWarnings("unchecked")
    public static TradingHoursCommand createTradingHoursCommand(List<String> symbols) throws APICommandConstructionException {
        JSONObject args = new JSONObject();
        JSONArray arr = new JSONArray();
        arr.addAll(symbols);
        args.put("symbols", arr);
        return new TradingHoursCommand(args);
    }

    public static ChartResponse executeChartLastCommand(SyncAPIConnector connector, String symbol, PERIOD_CODE period, Long start) throws APICommandConstructionException, APICommunicationException, APIReplyParseException, APIErrorResponse {
        return new ChartResponse(connector.safeExecuteCommand(createChartLastCommand(symbol, period, start)));
    }

    public static ChartResponse executeChartRangeCommand(SyncAPIConnector connector, ChartRangeInfoRecord info) throws APICommandConstructionException, APIReplyParseException, APIErrorResponse, APICommunicationException {
        return new ChartResponse(connector.safeExecuteCommand(createChartRangeCommand(info)));
    }

    public static ChartResponse executeChartRangeCommand(SyncAPIConnector connector, String symbol, PERIOD_CODE period, Long start, Long end, Long ticks) throws APICommandConstructionException, APIReplyParseException, APICommunicationException, APIErrorResponse {
        return new ChartResponse(connector.safeExecuteCommand(createChartRangeCommand(symbol, period, start, end, ticks)));
    }
    
    public static CalendarResponse executeCalendarCommand(SyncAPIConnector connector) throws APICommandConstructionException, APIReplyParseException, APICommunicationException, APIErrorResponse {
        return new CalendarResponse(connector.safeExecuteCommand(createCalendarCommand()));
    }

    public static CommissionDefResponse executeCommissionDefCommand(SyncAPIConnector connector, String symbol, double volume) throws APICommandConstructionException, APICommunicationException, APIReplyParseException, APIErrorResponse {
        return new CommissionDefResponse(connector.safeExecuteCommand(createCommissionDefCommand(symbol, volume)));
    }

    public static LogoutResponse executeLogoutCommand(SyncAPIConnector connector) throws APICommandConstructionException, APICommunicationException, APIReplyParseException, APIErrorResponse {
        return new LogoutResponse(connector.safeExecuteCommand(createLogoutCommand()));
    }

    public static MarginLevelResponse executeMarginLevelCommand(SyncAPIConnector connector) throws APICommandConstructionException, APIReplyParseException, APICommunicationException, APIErrorResponse {
        return new MarginLevelResponse(connector.safeExecuteCommand(createMarginLevelCommand()));
    }

    public static MarginTradeResponse executeMarginTradeCommand(SyncAPIConnector connector, String symbol, double volume) throws APICommandConstructionException, APIReplyParseException, APICommunicationException, APIErrorResponse {
        return new MarginTradeResponse(connector.safeExecuteCommand(createMarginTradeCommand(symbol, volume)));
    }

    public static ProfitCalculationResponse executeProfitCalculationCommand(SyncAPIConnector connector, String symbol, Double volume, TRADE_OPERATION_CODE cmd, Double openPrice, Double closePrice) throws APICommandConstructionException, APIReplyParseException, APIErrorResponse, APICommunicationException {
        return new ProfitCalculationResponse(connector.safeExecuteCommand(createProfitCalculationCommand(symbol, volume, cmd, openPrice, closePrice)));
    }

    public static IbsHistoryResponse executeIbHistoryCommand(SyncAPIConnector connector, long start, long end) throws APICommandConstructionException, APIReplyParseException, APIErrorResponse, APICommunicationException {
        return new IbsHistoryResponse(connector.safeExecuteCommand(createIbHistoryCommand(start, end)));
    }

    public static NewsResponse executeNewsCommand(SyncAPIConnector connector, long start, long end) throws APICommandConstructionException, APICommunicationException, APIReplyParseException, APIErrorResponse {
        return new NewsResponse(connector.safeExecuteCommand(createNewsCommand(start, end)));
    }

    public static ServerTimeResponse executeServerTimeCommand(SyncAPIConnector connector) throws APICommandConstructionException, APICommunicationException, APIReplyParseException, APIErrorResponse {
        return new ServerTimeResponse(connector.safeExecuteCommand(createServerTimeCommand()));
    }

    public static VersionResponse executeVersionCommand(SyncAPIConnector connector) throws APICommandConstructionException, APICommunicationException, APIReplyParseException, APIErrorResponse {
        return new VersionResponse(connector.safeExecuteCommand(createVersionCommand()));
    }

    public static PingResponse executePingCommand(SyncAPIConnector connector) throws APICommandConstructionException, APICommunicationException, APIReplyParseException, APIErrorResponse {
        return new PingResponse(connector.safeExecuteCommand(createPingCommand()));
    }

    public static StepRulesResponse executeStepRulesCommand(SyncAPIConnector connector) throws APICommandConstructionException, APICommunicationException, APIReplyParseException, APIErrorResponse {
        return new StepRulesResponse(connector.safeExecuteCommand(createStepRulesCommand()));
    }
    
    public static String executeProxyVersionCommand(SyncAPIConnector connector) throws APICommunicationException, APICommandConstructionException {
    	return (String) connector.safeExecuteCommand(createProxyVersionCommand());
    }

    public static CurrentUserDataResponse executeCurrentUserDataCommand(SyncAPIConnector connector) throws APICommandConstructionException, APICommunicationException, APIReplyParseException, APIErrorResponse {
        return new CurrentUserDataResponse(connector.safeExecuteCommand(createCurrentUserDataCommand()));
    }

    public static AllSymbolsResponse executeAllSymbolsCommand(SyncAPIConnector connector) throws APICommandConstructionException, APIReplyParseException, APICommunicationException, APIErrorResponse {
        return new AllSymbolsResponse(connector.safeExecuteCommand(createAllSymbolsCommand()));
    }

    public static SymbolResponse executeSymbolCommand(SyncAPIConnector connector, String symbol) throws APICommandConstructionException, APIReplyParseException, APIErrorResponse, APICommunicationException {
        return new SymbolResponse(connector.safeExecuteCommand(createSymbolCommand(symbol)));
    }

    public static TickPricesResponse executeTickPricesCommand(SyncAPIConnector connector, Long level, List<String> symbols, Long timestamp) throws APICommandConstructionException, APICommunicationException, APIReplyParseException, APIErrorResponse {
        return new TickPricesResponse(connector.safeExecuteCommand(createTickPricesCommand(level, symbols, timestamp)));
    }

    public static TradeRecordsResponse executeTradeRecordsCommand(SyncAPIConnector connector, List<Long> orders) throws APICommandConstructionException, APIReplyParseException, APIErrorResponse, APICommunicationException {
        return new TradeRecordsResponse(connector.safeExecuteCommand(createTradeRecordsCommand(orders)));
    }

    public static TradeTransactionResponse executeTradeTransactionCommand(SyncAPIConnector connector, TradeTransInfoRecord tradeTransInfo) throws APICommandConstructionException, APIReplyParseException, APICommunicationException, APIErrorResponse {
        return new TradeTransactionResponse(connector.safeExecuteCommand(createTradeTransactionCommand(tradeTransInfo)));
    }

    public static TradeTransactionResponse executeTradeTransactionCommand(SyncAPIConnector connector, TradeTransactionCommand tradeTransactionCommand) throws APIReplyParseException, APICommunicationException, APIErrorResponse {
        return new TradeTransactionResponse(connector.safeExecuteCommand(tradeTransactionCommand));
    }

    public static TradeTransactionResponse executeTradeTransactionCommand(SyncAPIConnector connector, TRADE_OPERATION_CODE cmd, TRADE_TRANSACTION_TYPE type, Double price, Double sl, Double tp, String symbol, Double volume, Long order, String comment, Long expiration) throws APICommandConstructionException, APIReplyParseException, APIErrorResponse, APICommunicationException {
        return new TradeTransactionResponse(connector.safeExecuteCommand(createTradeTransactionCommand(cmd, type, price, sl, tp, symbol, volume, order, comment, expiration)));
    }

    public static TradeTransactionStatusResponse executeTradeTransactionStatusCommand(SyncAPIConnector connector, long order) throws APICommandConstructionException, APIReplyParseException, APICommunicationException, APIErrorResponse {
        return new TradeTransactionStatusResponse(connector.safeExecuteCommand(createTradeTransactionStatusCommand(order)));
    }

    public static TradesResponse executeTradesCommand(SyncAPIConnector connector, Boolean openedOnly) throws APICommandConstructionException, APIReplyParseException, APICommunicationException, APIErrorResponse {
        return new TradesResponse(connector.safeExecuteCommand(createTradesCommand(openedOnly)));
    }

    public static TradesHistoryResponse executeTradesHistoryCommand(SyncAPIConnector connector, Long start, Long end) throws APICommandConstructionException, APICommunicationException, APIReplyParseException, APIErrorResponse {
        return new TradesHistoryResponse(connector.safeExecuteCommand(createTradesHistoryCommand(start, end)));
    }

    public static TradingHoursResponse executeTradingHoursCommand(SyncAPIConnector connector, List<String> symbols) throws APICommandConstructionException, APICommunicationException, APIReplyParseException, APIErrorResponse {
        return new TradingHoursResponse(connector.safeExecuteCommand(createTradingHoursCommand(symbols)));
    }
}