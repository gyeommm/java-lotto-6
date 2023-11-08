package lotto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class CorrectLotto {
    private Lotto correct_lotto;
    private int bonus;

    public void printInputNumbersMessage(){
        System.out.println("당첨 번호를 입력해 주세요.");
    }

    private int countComma(String input_numbers){
        int count = 0;
        for(int i = 0; i < input_numbers.length(); i++){
            if(input_numbers.charAt(i) == ',')
                count++;
        }
        return count;
    }

    // 입력값이 없는 부분이 있는지 확인
    private void checkEmptyPart(int expect, int size){
        if(expect != size){
            throw new IllegalArgumentException("입력값이 없는 부분이 있습니다. 6개의 당첨 번호를 입력해 주세요.");
        }
    }

    // 입력값이 숫자인지 확인하여 변환
    private int IsInteger(String number){
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("입력값이 숫자가 아닙니다. 1~45 사이의 숫자를 입력해주세요.");
        }
    }

    private List<Integer> convertStringToInteger(String input_numbers){
        String[] numbers = input_numbers.split(",");
        checkEmptyPart(countComma(input_numbers) + 1, numbers.length);

        List<Integer> converted_numbers = new ArrayList<>();
        for(String number : numbers){
            int converted_number = IsInteger(number.strip());
            converted_numbers.add(converted_number);
        }

        return converted_numbers;
    }
    public void inputCorrectLotto(){
        while(true) {
            String input_numbers = readLine();
            try {
                List<Integer> converted_numbers = convertStringToInteger(input_numbers);
                correct_lotto = new Lotto(converted_numbers);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("[Error] " + e.getMessage());
            }
        }
    }

    public void printInputBonusMessage(){
        System.out.println("보너스 번호를 입력해 주세요.");
    }

    // 보너스 번호가 [1, 45] 구간에 있는지 확인
    private void IsNumberIn1to45(int number){
        if(number < 1 || number > 45){
            throw new IllegalArgumentException("보너스 번호는 1~45 사이의 숫자여야 합니다.");
        }
    }

    // 보너스 번호가 당첨 번호와 중복되는지 확인
    private void IsNumberDuplicate(int number){
        for(Integer correct_number : correct_lotto.getNumbers()){
            if(correct_number == number){
                throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
            }
        }
    }

    public void inputBonus(){
        while(true){
            String input_number = readLine();
            try {
                int converted_number = IsInteger(input_number.strip());
                IsNumberIn1to45(converted_number);
                IsNumberDuplicate(converted_number);
                bonus = converted_number;
                break;
            } catch(IllegalArgumentException e){
                System.out.println("[Error] " + e.getMessage());
            }
        }
    }

}
