package telegrambot.utils.roll;

import telegrambot.common.results.RollResult;
import telegrambot.utils.Mather;

import java.util.*;

/**
 * Created by atols on 25.07.2017.
 * Вспомогательный класс. Описывает реализацию парсинга строки и симуляции бросков кубиков, считает результат и формирует результатный объект
 */
public class DiceRoller {


    /*Статичный метод для инициализации броска.
    input - строка в формате "(\d(d\d(-\d)?)? (+|-|*|/|^)*"
    additionalInfo - указывает, необходимо ли выводить полную информацию по всем броскам
        true  - Вывести полный перечень результатов бросков
        false - Ограничиться выводом решения и результата.
     */
    public static RollResult roll(String input, boolean additionalInfo) {
        try {
            return new DiceRoller().resolveRoll(input, additionalInfo);
        } catch (Exception e) {
            return new RollResult(e.getMessage());
        }
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
                //Определение числа "слева" от 'd'
                while (--j > 0 && result.charAt(j - 1) >= '0' && result.charAt(j - 1) <= '9') ;
                Integer leftParam = Integer.parseInt((i - j - 1 > 0) ? result.substring(j, i) : String.valueOf(result.charAt(j)));
                leftBorder = j;
                int from = i + 1;
                j = from;
                //Определение числа "справа" от 'd'
                while (++j < result.length() && result.charAt(j) >= '0' && result.charAt(j) <= '9') ;
                //Если наткнулись на минус - определяем минимальную и максимальную границы броска
                if (j < result.length() && result.charAt(j) == '-') {
                    //Записывем минимальную границу (число между 'd' и '-')
                    minBorder = Integer.valueOf((j - from > 0) ? result.substring(from, j) : String.valueOf(result.charAt(j)));
                    from = j + 1;
                    //Определение числа "справа" от '-'
                    while (++j < result.length() && result.charAt(j) >= '0' && result.charAt(j) <= '9') ;
                }
                //Записываем верхнюю границу
                Integer rightParam = Integer.valueOf((j - from > 0) ? result.substring(from, j) : String.valueOf(result.charAt(j)));
                rightBorder = j;
                Random randomizer = new Random();
                Integer sum = 0;
                message.append("Rolling ").append(leftParam).append("d").append(rightParam).append(System.lineSeparator());
                if(additionalInfo) message.append("{");
                //Если слева от 'd' положительное число - бросаем <leftParam> кубиков
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
                //В результатной строке осуществляем замену куска строки вида <\d>d<\d>(-<\d>)? на результат сложения всех бросков
                result = result.replaceFirst(result.substring(leftBorder, rightBorder), sum.toString());
                if(additionalInfo)message.append("}").append(System.lineSeparator());
                message.append("Total: ").append(sum).append(System.lineSeparator());
            }
        }
        Double matherResult = Mather.eval(result);
        rollResultBuilder.setResult(matherResult.toString());
        rollResultBuilder.setSolution(message.append("After all rolls: ").append(result).append(System.lineSeparator()).append("SendResult: ").append(matherResult.toString()).toString());
        return rollResultBuilder.build();
    }

}
