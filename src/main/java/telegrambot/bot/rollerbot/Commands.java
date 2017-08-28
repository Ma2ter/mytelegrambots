package telegrambot.bot.rollerbot;

import telegrambot.utils.roll.DiceRoller;
import telegrambot.common.results.Result;
import telegrambot.common.results.RollResult;

/**
 * Created by atols on 25.07.2017.
 */
public class Commands {

    public static CommandsList getCommand(String input){
        for(CommandsList l : CommandsList.values()){
            if(!"".equals(l.getCmd())){
                String cmd = input.split(" ", 2)[0];
                if(cmd.equals(l.getCmd()) || cmd.startsWith(l.getCmd().concat("@")))
                    return l;
            }

        }
        return CommandsList.UNSUPPORTED;
    }

    enum CommandsList{

        START {
            private final String cmd = "/start";
            public String getCmd(){
                return cmd;
            }
            public Result run(String input){
                return new Result("Starting application");
            }
        },
        ROLL {
            {
                result = new RollResult();
            }

            @Override
            public String toString(){
                return result == null ? "null" : ((RollResult)result).getSolution();
            }

            private final String cmd = "/roll";

            public String getCmd(){
                return cmd;
            }

            public Result run(String input){
                input = input.replaceFirst(cmd.concat(".*? "), "");
                try {
                    result = DiceRoller.roll(input, false);
                    return result;
                } catch (Exception e) {
                    return new Result(e.getMessage());
                }
            }
        },
        ROLLI {

            {
                result = new RollResult();
            }

            @Override
            public String toString(){
                return result == null ? "null" : ((RollResult)result).getSolution();
            }

            private final String cmd = "/rolli";

            public String getCmd(){
                return cmd;
            }

            public Result run(String input){
                input = input.replaceFirst(cmd.concat(".*? "), "");
                try {
                    result = DiceRoller.roll(input, true);
                    return result;
                } catch (Exception e) {
                    return new Result(e.getMessage());
                }
            }
        },
        UNSUPPORTED {
            public Result run(String input){
                return new Result("Unsupported operation");
            }

            @Override
            public String getCmd() {
                return "";
            }
        };

        private String cmd;

        public void setCmd(String cmd){
            this.cmd = cmd;
        }

        public abstract Result run(String input);
        public abstract String getCmd();
        Result result = null;

    }

}
