package pro.xstore.api.message.command;

import org.json.simple.JSONObject;

import pro.xstore.api.message.error.APICommandConstructionException;

public class TradingHoursCommand extends BaseCommand {

    public TradingHoursCommand(JSONObject arguments) throws APICommandConstructionException {
        super(arguments);
    }

    @Override
    public String getCommandName() {
        return "getTradingHours";
    }

    @Override
    public String[] getRequiredArguments() throws APICommandConstructionException {
        return new String[]{"symbols"};
    }
}
