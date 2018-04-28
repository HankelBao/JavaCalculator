package JavaCalculator;

class MathEquation {
    public MathExpression leftExpression;
    public MathExpression rightExpression;
    public MathEquation(String equationString) {
        String leftExpressionString = getLeftExpressionString(equationString);
        String rightExpressionString = getRightExpressionString(equationString);
        this.leftExpression = new MathExpression(leftExpressionString);
        this.rightExpression = new MathExpression(rightExpressionString);
    }
    public void moveAllItemsToLeft() {
        for (MathItem item: this.rightExpression.mathItems) {
            item.inverse();
            this.leftExpression.addItem(item);
        }
        this.rightExpression = new MathExpression("0");
    }
    public void combineSimilarTerms() {
        this.leftExpression.combineSimilarTerms();
        this.rightExpression.combineSimilarTerms();
    }
    public void sortBasedOnPower() {
        this.leftExpression.sortBasedOnPower();
        this.rightExpression.sortBasedOnPower();
    }
    public void print() {
        this.leftExpression.print();
        System.out.print("=");
        this.rightExpression.print();
        System.out.println("");
    }
    public void adjust() {
        this.moveAllItemsToLeft();
        this.combineSimilarTerms();
        this.sortBasedOnPower();
    }
    private String getLeftExpressionString(String equationString) {
        int indexOfEqual = equationString.indexOf('=');
        String leftExpressionString = equationString.substring(0,indexOfEqual);
        return leftExpressionString;
    }
    private String getRightExpressionString(String equationString) {
        int indexOfEqual = equationString.indexOf('=');
        String rightExpressionString = equationString.substring(indexOfEqual+1, equationString.length());
        return rightExpressionString;
    }
}