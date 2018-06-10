package ru.job4j;

public class ParseString {

    class ValidStrException extends Exception{
    }

    String result = "";
    String result2 ="";
    String position = "";
    String tempstr = "";
    int pointer = 0;


    /*
     * Метод определяет валидность строки и распарсивает по скобкам
     * @return строка с номерами позиций строк
     */
    public String validStr(String str) {
        char tempCh;
        String inputStr ="";
        inputStr = str;
        try {
            if (pointer != str.length()) {
                tempCh = str.charAt(pointer);
                switch (tempCh) {
                    case '{':
                        pointer++;
                        tempstr += '{';
                        position += "[" + pointer + "]";
                        validStr(inputStr);
                        break;
                    case '}':
                        if (tempstr.length() == 0) {
                            throw new ParseString.ValidStrException();
                        } else {
                            if (tempstr.length() == 0) {
                                throw new ParseString.ValidStrException();
                            }
                            tempCh = tempstr.charAt(tempstr.length() - 1);
                            if (tempCh != '{') {
                                throw new ParseString.ValidStrException();
                            } else {
                                tempstr = tempstr.substring(0, tempstr.lastIndexOf("{"));
                                pointer++;
                                result += '{' + position.substring(position.lastIndexOf("[") + 1, position.length() - 1) + ',' + pointer + '}';
                                position = position.substring(0, position.lastIndexOf("["));
                                validStr(inputStr);
                                break;
                            }
                        }
                    case '[':
                        pointer++;
                        position += "[" + pointer + "]";
                        tempstr += '[';
                        validStr(inputStr);
                        break;
                    case ']':
                        if (tempstr.length() == 0) {
                            throw new ParseString.ValidStrException();
                        } else {
                            tempCh = tempstr.charAt(tempstr.length() - 1);
                            if (tempCh != '[') {
                                throw new ParseString.ValidStrException();
                            } else {
                                tempstr = tempstr.substring(0, tempstr.lastIndexOf("["));
                                pointer++;
                                result += '[' + position.substring(position.lastIndexOf("[") + 1, position.length() - 1) + ',' + pointer + ']';
                                position = position.substring(0, position.lastIndexOf("["));
                                validStr(inputStr);
                                break;
                            }
                        }
                    default:
                        pointer++;
                        validStr(inputStr);
                }
            } else {
                if (tempstr.length() != 0) {
                    throw new ParseString.ValidStrException();
                }
            }
        } catch (ParseString.ValidStrException vse) {
            result = "No Valid";
        }
        finally {
            return result;
        }

    }

    public static void main(String[] args) {
        String expr = "dfg[kf[fgjdf]ghdfg]g{dgd[]dsgg}er{kjh}";

        ParseString  parseString = new ParseString();

        System.out.println(String.format("%n Valid String task. %n Result:"));
        System.out.println(expr);
        System.out.println(parseString.validStr(expr));
    }

}
