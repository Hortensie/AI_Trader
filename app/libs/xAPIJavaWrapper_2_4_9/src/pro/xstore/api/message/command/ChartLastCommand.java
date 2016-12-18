package pro.xstore.api.message.command;

import org.json.simple.JSONObject;

import pro.xstore.api.message.error.APICommandConstructionException;

public class ChartLastCommand extends BaseCommand {

    public ChartLastCommand(JSONObject arguments) throws APICommandConstructionException {
        super(arguments);
    }

    @Override
    public String getCommandName() {
        return "getChartLastRequest";
    }

    @Override
    public String[] getRequiredArguments() throws APICommandConstructionException {
        return new String[] {"info"};
    }
}
