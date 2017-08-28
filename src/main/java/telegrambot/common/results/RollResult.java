package telegrambot.common.results;

/**
 * Created by atols on 27.07.2017.
 */
public class RollResult extends Result {

    private String query;
    private String solution;
    private String result;

    public RollResult() {
    }

    public RollResult(String message) {
        super(message);
        this.solution = message;
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

    @Override
    public String toString() {
        return solution;
    }

    public class RollResultBuilder{

        private String builderQuery;
        private String builderSolution;
        private String builderResult;


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
            RollResult result = new RollResult("New");
            result.setQuery(builderQuery);
            result.setResult(builderResult);
            result.setSolution(builderSolution);
            return result;
        }
    }
}
