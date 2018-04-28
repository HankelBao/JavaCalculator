package JavaCalculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class MathExpression {
    public ArrayList<MathItem> mathItems = new ArrayList<MathItem>();
    public MathExpression(String expressionString) {
        int currentIndex = 0;
        int lastPhrasedIndex = 0;
        for (char detectionCharacter: expressionString.toCharArray()) {
            if (currentIndex != 0) {
                if ((detectionCharacter == '+') || detectionCharacter == '-') {
                    String itemString = expressionString.substring(lastPhrasedIndex, currentIndex);
                    lastPhrasedIndex = currentIndex;
                    this.createAndAddItem(itemString);
                }
            }
            currentIndex++;
        }
        String itemString = expressionString.substring(lastPhrasedIndex, expressionString.length());
        this.createAndAddItem(itemString);
    }
    public void createAndAddItem(String itemString) {
        MathItem item = new MathItem(itemString);
        this.addItem(item);
    }
    public void addItem(MathItem item) {
        this.mathItems.add(item);
    }
    public void printAllItems() {
        System.out.println("Here to print all items in an expression:");
        for (MathItem item: this.mathItems) {
            item.printLabelledItem();
        }
    }
    public void print() {
        for (MathItem item: this.mathItems) {
            item.printItem();
        }
    }
    public void combineSimilarTerms() {
        ArrayList<MathItem> newMathItems = new ArrayList<MathItem>();
        for(MathItem iterItem: this.mathItems) {
            MathItem similarTerm = this.findSimilarItemFromItems(iterItem, newMathItems);
            if (similarTerm != null) {
                similarTerm.combineItem(iterItem);
            } else {
                newMathItems.add(iterItem);
            }
        }
        this.mathItems = newMathItems;
        this.cleanZernCoefficientTerms();
    }
    public void sortBasedOnPower() {
        Comparator<MathItem> itemPowerComparator = this.mathItems.get(0).getPowerComparator();
        Collections.sort(this.mathItems, itemPowerComparator);
    }
    public int getMaxPower() {
        return this.mathItems.get(0).getPower();
    }
    public ArrayList<Double> extractCoefficients() {
        ArrayList<Double> coefficients = new ArrayList<Double>();
        for (int i=0; i<=this.getMaxPower(); i++) {
            coefficients.add(0.0);
        }
        for (MathItem item: this.mathItems) {
            coefficients.set(item.getPower(), item.coefficient);
        }
        return coefficients;
    }
    private void cleanZernCoefficientTerms() {
        for(int i=0; i<this.mathItems.size(); i++) {
            if (this.mathItems.get(i).isZeroCoefficient() == true) {
                if (!this.mathItems.get(i).variable.equals("")) {
                    this.mathItems.remove(i);
                }
            }
        }
    }
    private MathItem findSimilarItemFromItems(MathItem item, ArrayList<MathItem> mathItems) {
        for(MathItem iterItem: mathItems) {
            if (iterItem.isSimilarTerms(item) == true) {
                return iterItem;
            }
        }
        return null;
    }
}