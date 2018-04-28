package JavaCalculator;

import java.util.Comparator;

class MathItem {
    public double coefficient=0.0;
    //TODO: String variable is not expressive
    public String variable="";
    public String itemString="";
    public MathItem(String itemString) {
        boolean negativeSign = false;
        if (itemString.toCharArray()[0] == '-')  {
            negativeSign = true;
        }
        if ((itemString.toCharArray()[0] == '+') || (itemString.toCharArray()[0] == '-')){
            itemString = itemString.substring(1, itemString.length());
        }
        int dividingIndex = this.findDividingIndex(itemString);
        if (dividingIndex == 0) {
            this.coefficient = 1.0;
        } else {
            String coefficientString = itemString.substring(0, dividingIndex);
            this.coefficient = Double.parseDouble(coefficientString);
        }
        if (negativeSign) {
            this.inverse();
        }
        this.variable = itemString.substring(dividingIndex, itemString.length());
        this.itemString = itemString;
    }
    public void inverse() {
        this.coefficient = -this.coefficient;
    }
    public void printLabelledItem() {
        System.out.print("c: "+this.coefficient+" ");
        System.out.println("v: "+this.variable+" ");
    }
    public void printItem() {
        if (this.coefficient < 0) {
            System.out.print(this.coefficient+this.variable);
        } else {
            System.out.print("+"+this.coefficient+this.variable);
        }
    }
    public boolean isSimilarTerms(MathItem item) {
        return this.variable.equals(item.variable);
    }
    public void combineItem(MathItem item) {
        if (this.isSimilarTerms(item) == true) {
            this.coefficient += item.coefficient;
        }
    }
    public boolean isZeroCoefficient() {
        return (this.coefficient == 0);
    }
    public int getPower() {
        //TODO: too simple implementation
        if (this.variable.equals("")) {
            return 0;
        }
        if (!this.variable.contains("^")) {
            return 1;
        }
        String powerString = this.variable.substring(this.variable.indexOf("^")+1, this.variable.length());
        return Integer.parseInt(powerString);
    }
    public Comparator<MathItem> getPowerComparator() {
        return new Comparator<MathItem>() {
            @Override
            public int compare(MathItem item1, MathItem item2) {
                return item2.getPower() - item1.getPower();
            }
        };
    }
    private int findDividingIndex(String str) {
        int currentIndex = 0;
        for(char character: str.toCharArray()) {
            if ((Character.isDigit(character) == false) && (character != '.')){
                return currentIndex;
            }
            currentIndex++;
        }
        return currentIndex;
    }
}