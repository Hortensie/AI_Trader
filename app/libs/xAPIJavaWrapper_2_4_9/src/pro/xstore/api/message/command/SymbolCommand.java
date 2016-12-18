package pro.xstore.api.message.command;

import org.json.simple.JSONObject;

import pro.xstore.api.message.error.APICommandConstructionException;

public class SymbolCommand extends BaseCommand {

    public SymbolCommand(JSONObject arguments) throws APICommandConstructionException {
        super(arguments);
    }

    @Override
    public String getCommandName() {
        return "getSymbol";
    }

    @Override
    public String[] getRequiredArguments() throws APICommandConstructionException {
        return new String[]{"symbol"};
    }
}
