package pro.xstore.api.message.command;

import org.json.simple.JSONObject;

import pro.xstore.api.message.error.APICommandConstructionException;

public class VersionCommand extends BaseCommand {

    public VersionCommand() throws APICommandConstructionException {
        super(new JSONObject());
    }

    @Override
    public String getCommandName() {
        return "getVersion";
    }

    @Override
    public String[] getRequiredArguments() throws APICommandConstructionException {
        return new String[]{};
    }
}