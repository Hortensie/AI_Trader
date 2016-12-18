package pro.xstore.api.streaming;

import pro.xstore.api.message.records.SBalanceRecord;
import pro.xstore.api.message.records.SCandleRecord;
import pro.xstore.api.message.records.SKeepAliveRecord;
import pro.xstore.api.message.records.SNewsRecord;
import pro.xstore.api.message.records.SProfitRecord;
import pro.xstore.api.message.records.STickRecord;
import pro.xstore.api.message.records.STradeRecord;
import pro.xstore.api.message.records.STradeStatusRecord;

public interface StreamingListenerInterface {
    public void receiveTradeRecord(STradeRecord tradeRecord);
    public void receiveTickRecord(STickRecord tickRecord);
    public void receiveBalanceRecord(SBalanceRecord balanceRecord);
    public void receiveNewsRecord(SNewsRecord newsRecord);
    public void receiveTradeStatusRecord(STradeStatusRecord tradeStatusRecord);
    public void receiveProfitRecord(SProfitRecord profitRecord);
    public void receiveKeepAliveRecord(SKeepAliveRecord keepAliveRecord);
    public void receiveCandleRecord(SCandleRecord candleRecord);
}