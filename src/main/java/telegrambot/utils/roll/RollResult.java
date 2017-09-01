package telegrambot.utils.roll;

/**
 * Created by atols on 27.07.2017.
 * Contains result of dice roll by DiceRoller.class
 *
 */
public class RollResult {

    //Запрос пользователя (пример <2d6 + 17>)
    private String query;
    //Описывает шаги, пройденные системой для поиска ответа на запрос
    private String solution;
    //Содержит фактический конечный результат выполнения программы
    private String result;


    //Constructor, getters, setters, Override methods
    public RollResult() {
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    /*
    Builder для касса RollResult. Содержит методы для упрощенного создания экземпляра RollResult
     */
    public class RollResultBuilder{

        private String builderQuery = "";
        private String builderSolution = "";
        private String builderResult = "";


        public RollResultBuilder setQuery(String builderQuery) {
            this.builderQuery = builderQuery;
            return this;
        }

        public RollResultBuilder setSolution(String builderSolution) {
            this.builderSolution = builderSolution;
            return this;
        }

        public RollResultBuilder setResult(String builderResult) {
            this.builderResult = builderResult;
            return this;
        }

        public RollResult build(){
            RollResult result = new RollResult();
            result.setQuery(builderQuery);
            result.setResult(builderResult);
            result.setSolution(builderSolution);
            return result;
        }
    }
}
