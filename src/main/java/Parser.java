public class Parser {
    private String command;
    private String other;

    public Parser(String fullResponse) {
        int firstSpace = fullResponse.indexOf(' ');
        String command = (firstSpace == -1) ? fullResponse
                : fullResponse.substring(0, firstSpace);
        String other = (firstSpace == -1) ? ""
                : fullResponse.substring(firstSpace + 1);
    }

    public String getCommand() {
        return this.command;
    }

    public String getOther() {
        return this.other;
    }
}