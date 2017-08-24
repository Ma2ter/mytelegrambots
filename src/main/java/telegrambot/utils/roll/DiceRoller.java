package telegrambot.utils.roll;

import telegrambot.utils.Mather;

import java.util.*;

/**
 * Created by atols on 25.07.2017.
 */
public class DiceRoller {

    private final String VALIDATION_REGEXP = "^([-+/*]\\d+(\\.\\d+)?)*";
    private Map<Integer, List<String>> enclosedArgs = new HashMap<>();
    private List<String> params = new ArrayList<>();

    public static RollResult roll(String input, boolean additionalInfo) throws Exception{
        return new DiceRoller().resolveRoll(input, additionalInfo);
    }

    private int findNextRoundBracket(String s, int from){



        return 0;
    }


    private RollResult resolveRoll(String input, boolean additionalInfo) throws Exception {
        String result = input;
        StringBuilder message = new StringBuilder();
        RollResult.RollResultBuilder rollResultBuilder = new RollResult().new RollResultBuilder();
        rollResultBuilder.setQuery(input);
        for(int i = 0; i < result.length(); i++){
            if(result.charAt(i) == 'd') {
                int j = i;
                int minBorder = 1;
                int leftBorder = 0;
                int rightBorder = 0;
                while (--j > 0 && result.charAt(j - 1) >= '0' && result.charAt(j - 1) <= '9') ;
                Integer leftParam = Integer.parseInt((i - j - 1 > 0) ? result.substring(j, i) : String.valueOf(result.charAt(j)));
                leftBorder = j;
                int from = i + 1;
                j = from;
                while (++j < result.length() && result.charAt(j) >= '0' && result.charAt(j) <= '9') ;
                if (j < result.length() && result.charAt(j) == '-') {
                    minBorder = Integer.valueOf((j - from > 0) ? result.substring(from, j) : String.valueOf(result.charAt(j)));
                    from = j + 1;
                    while (++j < result.length() && result.charAt(j) >= '0' && result.charAt(j) <= '9') ;
                }
                Integer rightParam = Integer.valueOf((j - from > 0) ? result.substring(from, j) : String.valueOf(result.charAt(j)));
                rightBorder = j;
                Random randomizer = new Random();
                Integer sum = 0;
                message.append("Rolling ").append(leftParam).append("d").append(rightParam).append(System.lineSeparator());
                if(additionalInfo) message.append("{");
                if (leftParam > 0) {
                    for (int f = 0; f < leftParam - 1; f++) {
                        int roll = minBorder + randomizer.nextInt(rightParam - minBorder + 1);
                        sum += roll;
                        if(additionalInfo) message.append(roll).append("|");
                    }
                    int lastRoll = minBorder + randomizer.nextInt(rightParam - minBorder + 1);
                    sum += lastRoll;
                    if(additionalInfo) message.append(lastRoll);
                    i = 0;
                }
                result = result.replace(result.substring(leftBorder, rightBorder), sum.toString());
                if(additionalInfo)message.append("}").append(System.lineSeparator());
                message.append("Total: ").append(sum).append(System.lineSeparator());
                rollResultBuilder.setResult(String.valueOf(sum));
            }
        }
        rollResultBuilder.setSolution(message.append("After all rolls: ").append(result).append(System.lineSeparator()).append("SendResult: ").append(Mather.eval(result)).toString());
        return rollResultBuilder.build();
    }

}
