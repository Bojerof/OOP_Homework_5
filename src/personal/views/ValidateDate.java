package personal.views;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateDate {
    String patternName = "^\\S+$";
    String patternPhone = "^\\+7\\(\\d{3}\\)\\d{7}$";
    String language = "^[а-яА-ЯёЁ.,:;]+$";
    Pattern patternLanguage = Pattern.compile(language, Pattern.MULTILINE);

    Pattern pattern = Pattern.compile(patternName, Pattern.MULTILINE);
    Pattern phonePattern = Pattern.compile(patternPhone, Pattern.MULTILINE);

    public boolean checkName(String name){
        Matcher matcher = pattern.matcher(name);
        return !matcher.find();
    }
    public boolean checkPhone(String phone){
        Matcher matcher = phonePattern.matcher(phone);
        return !matcher.find();
    }
//    public boolean checkLanguage(String language){
//        Matcher matcher = patternLanguage.matcher(language);
//        return matcher.find();
//    }
}
