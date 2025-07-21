import java.util.*;

public class URLShortener {
    private static final String BASE_HOST = "http://short.ly/";
    private static final String CHAR_SET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 6;

    private Map<String, String> codeToUrl = new HashMap<>();
    private Map<String, String> urlToCode = new HashMap<>();
    private Random random = new Random();

    public String shorten(String longUrl) {
        if (urlToCode.containsKey(longUrl)) {
            return BASE_HOST + urlToCode.get(longUrl);
        }

        String code;
        do {
            code = generateCode();
        } while (codeToUrl.containsKey(code));

        codeToUrl.put(code, longUrl);
        urlToCode.put(longUrl, code);
        return BASE_HOST + code;
    }

    public String expand(String shortUrl) {
        String code = shortUrl.replace(BASE_HOST, "");
        return codeToUrl.getOrDefault(code, null);
    }

    private String generateCode() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < CODE_LENGTH; i++) {
            sb.append(CHAR_SET.charAt(random.nextInt(CHAR_SET.length())));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        URLShortener us = new URLShortener();
        String shortUrl = us.shorten("https://example.com/this-is-a-very-long-url");
        System.out.println("Shortened: " + shortUrl);
        System.out.println("Expanded: " + us.expand(shortUrl));
    }
}
