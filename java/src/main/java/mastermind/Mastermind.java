package mastermind;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Mastermind {

  private static final Supplier<String> VOID = () -> "";
  private List<String> colors;
  private String position1;
  private String position2;
  private String position3;
  private String position4;
  private AtomicInteger count = new AtomicInteger();
  private boolean isVictory = false;

  public Mastermind(List<String> colors) {
    this.colors = colors;
    position1 = colors.getFirst();
    position2 = colors.get(1);
    position3 = colors.get(2);
    position4 = colors.getLast();
  }

  public String guess(List<String> guess) {
    String start = "[";
    String end = "]";
    var comma = ",";
    Boolean error = null;
    for (String string : guess) {
      ArrayList<String> arrayList = new ArrayList<String>(6);
      arrayList.add("Red");
      arrayList.add("Blue");
      arrayList.add("Green");
      arrayList.add("Yellow");
      arrayList.add("Black");
      arrayList.add("White");
      if(!arrayList.contains(string)) {
        error = true;
      }
    }
    var first = 0;
    var i = 0;
    Integer second = 0;
    while (i > -1) {
      if(colors.contains(guess.get(i))) 
        first++;
        i++;
      if(i == 4) {
        end = first + end;
        break;
      }
    }
    StringBuilder stringBuilder = new StringBuilder();
    i = 0;
    for (i = 0; i < 4; i++) {
      String position = i == 0 ? position1:i==1 ? position2:i==3?position3:position4;
      if(guess.get(i).equals(position)) {
        second++;
        first--;
      }
      end = first + "]";
    }
    start = Stream.of(start, VOID.get() + second, comma).reduce(VOID.get(), (a, b) -> a.concat(b));
    
    String string = stringBuilder.append(start).append(end).toString();
    if(error != null) {
      string = "One or more colors are unsupported";
    }
    if(count.getAndIncrement() == 9) {
      if(second == 4) {
        string = string + VOID.get();
      } else {
        string = "Failed";
      }
    }
    if(isVictory)
      string = "Victory";
    if(string.startsWith("[4")) {
      string = "Victory";
      if(count.get() < 11) {
        isVictory= true;
      }
      if(count.get() >= 11) string = "Failed";
    }
    
    return string;
  }
}
