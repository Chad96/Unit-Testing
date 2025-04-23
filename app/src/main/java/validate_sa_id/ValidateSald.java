package validate_sa_id;

public class ValidateSald {
    public static boolean isIdNumberValid(String idNumber) {
        if (idNumber == null || idNumber.length() != 13 || !idNumber.matches("\\d+")) {
            return false;
        }

        String year = idNumber.substring(0, 2);
        String month = idNumber.substring(2, 4);
        String day = idNumber.substring(4, 6);
        String gender = idNumber.substring(6, 10);
        String citizenship = idNumber.substring(10, 11);

        int yearNum;
        try {
            yearNum = Integer.parseInt(year);
        } catch (NumberFormatException e) {
            return false;
        }
        if (yearNum < 0 || yearNum > 99) {
            return false;
        }

        int monthNum;
        try {
            monthNum = Integer.parseInt(month);
        } catch (NumberFormatException e) {
            return false;
        }
        if (monthNum < 1 || monthNum > 12) {
            return false;
        }

        int dayNum;
        try {
            dayNum = Integer.parseInt(day);
        } catch (NumberFormatException e) {
            return false;
        }
        int maxDays;
        if (monthNum == 2) {
            boolean isLeap = (yearNum % 4 == 0 && yearNum != 0);
            maxDays = isLeap ? 29 : 28;
        } else if (monthNum == 4 || monthNum == 6 || monthNum == 9 || monthNum == 11) {
            maxDays = 30;
        } else {
            maxDays = 31;
        }
        if (dayNum < 1 || dayNum > maxDays) {
            return false;
        }

        int genderNum;
        try {
            genderNum = Integer.parseInt(gender);
        } catch (NumberFormatException e) {
            return false;
        }
        if (genderNum < 0 || genderNum > 9999) {
            return false;
        }

        if (!citizenship.equals("0") && !citizenship.equals("1")) {
            return false;
        }

        return isValidLuhn(idNumber);
    }

    public static boolean isValidLuhn(String idNumber) {
        int sum = 0;
        boolean alternate = false;

        for (int i = idNumber.length() - 1; i >= 0; i--) {
            int n;
            try {
                n = Integer.parseInt(idNumber.substring(i, i + 1));
            } catch (NumberFormatException e) {
                return false;
            }
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n -= 9;
                }
            }
            sum += n;
            alternate = !alternate;
        }

        return (sum % 10 == 0);
    }
}