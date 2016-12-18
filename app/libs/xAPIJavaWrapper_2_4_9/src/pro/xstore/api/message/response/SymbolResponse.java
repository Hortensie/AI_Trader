package pro.xstore.api.message.response;

import org.json.simple.JSONObject;

import pro.xstore.api.message.error.APIReplyParseException;
import pro.xstore.api.message.records.SymbolRecord;

public class SymbolResponse extends BaseResponse {

    private SymbolRecord symbol;
    
    public SymbolResponse(String body) throws APIReplyParseException, APIErrorResponse {
        super(body);
        JSONObject ob = (JSONObject) this.getReturnData();
        symbol = new SymbolRecord();
        symbol.setFieldsFromJSONObject(ob);
    }

    public SymbolRecord getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return "SymbolResponse{" + "symbol=" + symbol + '}';
    }
}
