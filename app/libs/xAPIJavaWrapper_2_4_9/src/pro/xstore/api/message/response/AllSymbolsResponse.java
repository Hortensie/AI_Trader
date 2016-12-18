package pro.xstore.api.message.response;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import pro.xstore.api.message.error.APIReplyParseException;
import pro.xstore.api.message.records.SymbolRecord;

public class AllSymbolsResponse extends BaseResponse {

    private List<SymbolRecord> symbolRecords = new LinkedList<SymbolRecord>();

    @SuppressWarnings("rawtypes")
    public AllSymbolsResponse(String body) throws APIReplyParseException, APIErrorResponse {
        super(body);
        JSONArray symbolRecords = (JSONArray) this.getReturnData();
        for (Iterator it = symbolRecords.iterator(); it.hasNext();) {
            JSONObject e = (JSONObject) it.next();
            SymbolRecord symbolRecord = new SymbolRecord();
            symbolRecord.setFieldsFromJSONObject(e);
            this.symbolRecords.add(symbolRecord);
        }
    }

    public List<SymbolRecord> getSymbolRecords() {
        return symbolRecords;
    }

    @Override
    public String toString() {
        return "AllSymbolsResponse{" + "symbolRecords=" + symbolRecords + '}';
    }
}
