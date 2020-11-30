package com.example.taskyprojectnaya.MyUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

  /**
   * מחלקה שבודקת תקינות שדות כמו אימיל וסיסמא ועוד
   */
  public class MyValidations
  {
    private Pattern pattern;
    private Matcher matcher;
    private static final String PASSWORD_PATTERN = "((?=.[a-z])(?=.\\d)(?=.[A-Z])(?=.[@#$%!]).{8,40})";
    public MyValidations()
    {
      pattern = Pattern.compile(PASSWORD_PATTERN);
    }

    public boolean validatePasword(final String password) {

      matcher = pattern.matcher(password);
      return matcher.matches();




      if(!isPasswordValidMethod(newPassword.getText().toString())){
        System.out.println("Not Valid");

      }else{

        System.out.println("Valid");
      }



      // Validate password
      private boolean isPasswordValidMethod(String password) {

        String yourString = newPassword.getText().toString();

        System.out.println("yourString is =" + yourString);

        boolean isValid = false;

        // ^[_A-Za-z0-9-\+]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})$
        // ^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$

        String expression = "^(?=.*[A-Za-z])(?=.*\\\\d)(?=.*[$@$!%*#?&])[A-Za-z\\\\d$@$!%*#?&]{8,}$";
        CharSequence inputStr = password;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
          System.out.println("if");
          isValid = true;
        }else{
          System.out.println("else");
        }
        return isValid;
      }

    }

  }

