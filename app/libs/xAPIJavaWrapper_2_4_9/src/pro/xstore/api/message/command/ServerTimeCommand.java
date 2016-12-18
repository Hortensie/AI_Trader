package pro.xstore.api.message.command;

import org.json.simple.JSONObject;

import pro.xstore.api.message.error.APICommandConstructionException;

public class ServerTimeCommand extends BaseCommand {

    public ServerTimeCommand() throws APICommandConstructionException{
        super(new JSONObject());
    }

    @Override
    public String getCommandName() {
        return "getServerTime";
    }

    @Override
    public String[] getRequiredArguments() throws APICommandConstructionException {
        return new String[]{};
    }
}