package pro.xstore.api.message.command;

import org.json.simple.JSONObject;

import pro.xstore.api.message.error.APICommandConstructionException;

public class TradesCommand extends BaseCommand {

    public TradesCommand(JSONObject arguments) throws APICommandConstructionException {
        super(arguments);
    }

    @Override
    public String getCommandName() {
        return "getTrades";
    }

    @Override
    public String[] getRequiredArguments() throws APICommandConstructionException {
        return new String[] {"openedOnly"};
    }
}
