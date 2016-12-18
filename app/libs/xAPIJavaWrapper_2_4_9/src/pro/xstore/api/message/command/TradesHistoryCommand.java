package pro.xstore.api.message.command;

import org.json.simple.JSONObject;

import pro.xstore.api.message.error.APICommandConstructionException;

public class TradesHistoryCommand extends BaseCommand {

    public TradesHistoryCommand(JSONObject arguments) throws APICommandConstructionException {
        super(arguments);
    }

    @Override
    public String getCommandName() {
        return "getTradesHistory";
    }

    @Override
    public String[] getRequiredArguments() throws APICommandConstructionException {
        return new String[]{"start", "end"};
    }
}
