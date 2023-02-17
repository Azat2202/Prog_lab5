package models;

public enum Country {
    UNITED_KINGDOM,
    FRANCE,
    CHINA,
    INDIA,
    SOUTH_KOREA;
    public static String names() {
        StringBuilder nameList = new StringBuilder();
        for (var forms : values()) {
            nameList.append(forms.name()).append("\n");
        }
        return nameList.substring(0, nameList.length()-1);
    }
}