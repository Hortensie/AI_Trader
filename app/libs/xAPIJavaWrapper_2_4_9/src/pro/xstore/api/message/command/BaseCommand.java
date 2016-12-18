package pro.xstore.api.message.command;

import org.json.simple.JSONObject;



import pro.xstore.api.message.error.APICommandConstructionException;

/**
 * Abstract base class for each Command.
 */
public abstract class BaseCommand {

    protected String commandName;
    protected JSONObject arguments;

    /**
     * Constructor for base command.
     * @param arguments Command arguments (see xAPI Documentation)
     * @param prettyPrint
     * @throws APICommandConstructionException
     */
    public BaseCommand(JSONObject arguments) throws APICommandConstructionException {
        this.commandName = getCommandName();
        this.arguments = arguments;
        validateArguments();
    }
    
    /**
     * API inter-command timeout (if same command is executed more frequently than this number of milliseconds, RR-Socket disconnects)
     * @return inter-command timeout
     */
    public Long getTimeoutMillis(){
        return 250L;
    }

    /**
     * Returns command name
     * @return command name
     */
    public abstract String getCommandName();

    /**
     * Returns an array of required arguments
     * @return array of required arguments
     * @throws APICommandConstructionException
     */
    public abstract String[] getRequiredArguments() throws APICommandConstructionException;

    /**
     * Validates command arguments
     * @return are command arguments OK
     * @throws APICommandConstructionException
     */
    public boolean validateArguments() throws APICommandConstructionException {
        selfCheck();
        for (String argName : getRequiredArguments()) {
            if (!this.arguments.containsKey(argName)) {
                throw new APICommandConstructionException("Arguments of [" + commandName + "] Command must contain \"" + argName + "\" field!");
            }
        }
        return true;
    }

    /**
     * Creates a JSON String from this object
     * @return
     */
    @SuppressWarnings("unchecked")
    public String toJSONString() {
        JSONObject obj = new JSONObject();
        obj.put("command", commandName);
        obj.put("prettyPrint", false);
        obj.put("arguments", arguments);
        return obj.toJSONString();
    }

    /**
     * Checks if @commandName and @arguments are set
     * @throws APICommandConstructionException
     */
    private void selfCheck() throws APICommandConstructionException {
        if (commandName == null) {
            throw new APICommandConstructionException("commandName cannot be null");
        }
        if (arguments == null) {
            throw new APICommandConstructionException("arguments cannot be null");
        }
    }

	@Override
	public String toString() {
		return "BaseCommand [commandName=" + commandName + ", arguments="
				+ arguments + "]";
	}
}
