package pro.xstore.api.message.command;

import org.json.simple.JSONObject;

import pro.xstore.api.message.error.APICommandConstructionException;

public class TradeRecordsCommand extends BaseCommand {

    public TradeRecordsCommand(JSONObject arguments) throws APICommandConstructionException {
        super(arguments);
    }

    @Override
    public String getCommandName() {
        return "getTradeRecords";
    }

    @Override
    public String[] getRequiredArguments() throws APICommandConstructionException {
        return new String[]{"orders"};
    }
}
