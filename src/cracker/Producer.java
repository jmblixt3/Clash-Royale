package cracker;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import javax.xml.bind.DatatypeConverter;

public class Producer implements Runnable {

    private BlockingQueue<Item> queue;
    private List<String> dictionary;
    private int flag;

    public Producer(BlockingQueue<Item> queue, List<String> dictionary, int flag) {
        this.queue = queue;
        this.dictionary = dictionary;
        // flag indicates which permutations to perform
        this.flag = flag;
    }

    public void run() {
        MessageDigest msgDigest = null;
        try {
            msgDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        switch (flag) {
        // no modification
        case 1:
            for (String word : dictionary) {
                hashWord(msgDigest, word);
            }
            break;
        // word repeated
        case 2:
            for (String word : dictionary) {
                word = word + word;
                hashWord(msgDigest, word);
            }
            break;
        // first letter upper case
        case 3:
            for (String word : dictionary) {
                word = setFirstCapital(word);
                hashWord(msgDigest, word);
            }
            break;
        // whole word upper case
        case 4:
            for (String word : dictionary) {
                word = setAllCapital(word);
                hashWord(msgDigest, word);
            }
            break;
        // whole word lower case
        case 5:
            for (String word : dictionary) {
                word = setAllLowerCase(word);
                hashWord(msgDigest, word);
            }
            break;
        // reverse word
        case 6:
            for (String word : dictionary) {
                String newWord = reverse(word);
                hashWord(msgDigest, newWord);
            }
            break;
        // append '!'
        case 7:
            for (String word : dictionary) {
                word = appendSpecial(word);
                hashWord(msgDigest, word);
            }
            break;
        // first letter upper case + append '!'
        case 8:
            for (String word : dictionary) {
                word = setFirstCapital(word);
                word = appendSpecial(word);
                hashWord(msgDigest, word);
            }
            break;
        // replace 'O' and 'o' with '0'
        case 9:
            for (String word : dictionary) {
                word = oToZero(word);
                hashWord(msgDigest, word);
            }
            break;
        // first letter upper case + replace 'O' and 'o' with '0'
        case 10:
            for (String word : dictionary) {
                word = setFirstCapital(word);
                word = oToZero(word);
                hashWord(msgDigest, word);
            }
            break;
        // replace letters with digits
        case 11:
            for (String word : dictionary) {
                word = lettersToDigits(word);
                hashWord(msgDigest, word);
            }
            break;
        // first letter upper case + replace letters with digits
        case 12:
            for (String word : dictionary) {
                word = setFirstCapital(word);
                word = lettersToDigits(word);
                hashWord(msgDigest, word);
            }
            break;
        // 0-200 added to beginning
        case 13:
            for (int i = 0; i <= 200; i++) {
                for (String word : dictionary) {
                    word = prependDigits(word, i);
                    hashWord(msgDigest, word);
                }
            }
            break;
        // 0-200 added to end
        case 14:
            for (int i = 0; i <= 200; i++) {
                for (String word : dictionary) {
                    word = appendDigits(word, i);
                    if (word.equals("flapjack44")) {
                        System.out.println("BINGO");
                    }
                    hashWord(msgDigest, word);
                }
            }
            break;
        // first letter upper case + 0-200 added to end
        case 15:
            for (int i = 0; i <= 200; i++) {
                for (String word : dictionary) {
                    word = setFirstCapital(word);
                    word = appendDigits(word, i);
                    hashWord(msgDigest, word);
                }
            }
            break;
        // 1900-2200 added to beginning
        case 16:
            for (int i = 1900; i <= 2200; i++) {
                for (String word : dictionary) {
                    word = prependDigits(word, i);
                    hashWord(msgDigest, word);
                }
            }
            break;
        // 1900-2200 added to end
        case 17:
            for (int i = 1900; i <= 2200; i++) {
                for (String word : dictionary) {
                    word = appendDigits(word, i);
                    hashWord(msgDigest, word);
                }
            }
            break;
        // each word joined with every other word
        case 18:
            for (String word : dictionary) {
                for (String otherWord : dictionary) {
                    String newWord = concat(word, otherWord);
                    hashWord(msgDigest, newWord);
                }
            }
            break;
        // each word joined with every other word + first letter upper case
        case 19:
            for (String word : dictionary) {
                for (String otherWord : dictionary) {
                    String newWord = concat(word, otherWord);
                    newWord = setFirstCapital(newWord);
                    hashWord(msgDigest, newWord);
                }
            }
            break;
        // each word joined with every other word + 0-200 added to end
        case 20:
            for (int i = 0; i <= 200; i++) {
                for (String word : dictionary) {
                    for (String otherWord : dictionary) {
                        String newWord = concat(word, otherWord);
                        newWord = appendDigits(newWord, i);
                        hashWord(msgDigest, newWord);
                    }
                }
            }
            break;
        }

        System.out.println("Producer " + flag + " stopped");
    }

    // adds int to end of string
    private String appendDigits(String word, int digit) {
        return word.replaceAll("$", Integer.toString(digit));
    }

    // adds '!' to end of string
    private String appendSpecial(String word) {
        return word.replaceAll("$", "!");
    }

    // adds int to start of string
    private String prependDigits(String word, int digit) {
        return word.replaceAll("^", Integer.toString(digit));
    }

    // first letter of string set to upper case
    private String setFirstCapital(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    // whole string set to upper case
    private String setAllCapital(String word) {
        return word.toUpperCase();
    }

    // whole string set to lower case
    private String setAllLowerCase(String word) {
        return word.toLowerCase();
    }

    // any 'o' and 'O' characters replaced with '0'
    private String oToZero(String word) {
        word = word.replaceAll("o|O", "0");
        return word;
    }

    // any 'a','A', 'e','E', 'i','I', 'o','O', replaced with 4 3 1 0 respectively
    private String lettersToDigits(String word) {
        word = word.replaceAll("a|A", "4");
        word = word.replaceAll("e|E", "3");
        word = word.replaceAll("i|I", "1");
        word = word.replaceAll("o|O", "0");
        return word;
    }

    // string reversed
    private String reverse(String word) {
        return new StringBuilder(word).reverse().toString();
    }

    // two strings joined together
    private String concat(String word, String otherWord) {
        return word + otherWord;
    }

    private void hashWord(MessageDigest msgDigest, String word) {
        try {
            byte[] digest = msgDigest.digest(word.getBytes("UTF-8"));
            String hex = DatatypeConverter.printHexBinary(digest);
            Item item = new Item(hex.toLowerCase(), word);
            // waits if queue is full
            queue.put(item);
        } catch (UnsupportedEncodingException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
