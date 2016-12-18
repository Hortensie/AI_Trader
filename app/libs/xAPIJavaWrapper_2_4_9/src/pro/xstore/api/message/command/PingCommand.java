package pro.xstore.api.message.command;

import org.json.simple.JSONObject;

import pro.xstore.api.message.error.APICommandConstructionException;

public class PingCommand extends BaseCommand {

    public PingCommand() throws APICommandConstructionException {
        super(new JSONObject());
    }

    @Override
    public String getCommandName() {
        return "ping";
    }

    @Override
    public String[] getRequiredArguments() throws APICommandConstructionException {
        return new String[]{};
    }
}