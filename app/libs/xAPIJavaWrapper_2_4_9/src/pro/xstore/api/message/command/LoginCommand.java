package pro.xstore.api.message.command;

import org.json.simple.JSONObject;

import pro.xstore.api.message.error.APICommandConstructionException;

public class LoginCommand extends BaseCommand {

    public LoginCommand(JSONObject arguments) throws APICommandConstructionException {
        super(arguments);
    }

    @Override
    public String getCommandName() {
        return "login";
    }

    @Override
    public String[] getRequiredArguments() throws APICommandConstructionException {
        return new String[] {"userId", "password"};
    }
}