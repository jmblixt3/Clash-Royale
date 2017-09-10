package cracker;

public class Item {
    private String hashWord;
    private String plainWord;

    // object containing a plain text word and it's SHA-256 equivalent
    public Item(String hashWord, String plainWord) {
        this.hashWord = hashWord;
        this.plainWord = plainWord;
    }

    public String getHash() {
        return hashWord;
    }

    public String getPlain() {
        return plainWord;
    }
}
