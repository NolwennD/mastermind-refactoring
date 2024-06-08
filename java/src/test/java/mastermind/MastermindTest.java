package mastermind;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class MastermindTest {
  
  @Test
  void shouldFailWithUnsupportedColor() {
    Mastermind mastermind = new Mastermind(fullRed());

    assertThat(mastermind.guess(List.of("Blue", "Blue", "Blue", "Brown"))).isEqualTo("One or more colors are unsupported");
  }

  @Test
  void shouldNotBeWellPlacedForRedSecretAndBlueGuess() {
    Mastermind mastermind = new Mastermind(fullRed());

    assertThat(mastermind.guess(List.of("Blue", "Blue", "Blue", "Blue"))).isEqualTo("[0,0]");
  }

  private List<String> fullRed() {
    return List.of("Red", "Red", "Red", "Red");
  }

  @Test
  void shouldSucceed() {
    Mastermind mastermind = new Mastermind(fullRed());
    var fullBlue = List.of("Blue", "Blue", "Blue", "Blue");
    mastermind.guess(fullBlue);
    mastermind.guess(fullBlue);
    mastermind.guess(fullBlue);
    mastermind.guess(fullBlue);
    mastermind.guess(fullBlue);
    mastermind.guess(fullBlue);
    mastermind.guess(fullBlue);
    mastermind.guess(fullBlue);
    mastermind.guess(fullBlue);

    assertThat(mastermind.guess(fullRed())).isEqualTo("Victory");
  }
  
  @Test
  void shouldBeSuccessfullAfterVictory() {
    Mastermind mastermind = new Mastermind(fullRed());
    var fullBlue = List.of("Blue", "Blue", "Blue", "Blue");
    mastermind.guess(fullBlue);
    mastermind.guess(fullRed());
    
    assertThat(mastermind.guess(fullRed())).isEqualTo("Victory");
  }
  
  @Test
  void shouldLooseAfterTenFailedAttempts() {
    Mastermind mastermind = new Mastermind(fullRed());
    
    var fullBlue = List.of("Blue", "Blue", "Blue", "Blue");
    mastermind.guess(fullBlue);
    mastermind.guess(fullBlue);
    mastermind.guess(fullBlue);
    mastermind.guess(fullBlue);
    mastermind.guess(fullBlue);
    mastermind.guess(fullBlue);
    mastermind.guess(fullBlue);
    mastermind.guess(fullBlue);
    mastermind.guess(fullBlue);
    
    assertThat(mastermind.guess(fullBlue)).isEqualTo("Failed");
  }
  
  @Test
  void shouldLooseAfterTenFailedAttemptsEvenIfNextAreSuccessfull() {
    Mastermind mastermind = new Mastermind(fullRed());
    
    var fullBlue = List.of("Blue", "Blue", "Blue", "Blue");
    mastermind.guess(fullBlue);
    mastermind.guess(fullBlue);
    mastermind.guess(fullBlue);
    mastermind.guess(fullBlue);
    mastermind.guess(fullBlue);
    mastermind.guess(fullBlue);
    mastermind.guess(fullBlue);
    mastermind.guess(fullBlue);
    mastermind.guess(fullBlue);
    mastermind.guess(fullBlue);
    
    assertThat(mastermind.guess(fullRed())).isEqualTo("Failed");
    assertThat(mastermind.guess(fullRed())).isEqualTo("Failed");
  }

  @Test
  void shouldNotBeWellPlacedForRedSecretAndRedBlueGuess() {
    Mastermind mastermind = new Mastermind(fullRed());

    assertThat(mastermind.guess(List.of("Red", "Blue", "Blue", "Blue"))).isEqualTo("[1,0]");
  }

  @Test
  void shouldNotBeWellPlacedForRedSecretAndRedBlueGuess2() {
    Mastermind mastermind = new Mastermind(List.of("Red", "Green", "Green", "Green"));

    assertThat(mastermind.guess(List.of("Blue", "Blue", "Red", "Blue"))).isEqualTo("[0,1]");
  }

  @Test
  void shouldNotBeWellPlacedForRedSecretAndRedBlueGuess4() {
    Mastermind mastermind = new Mastermind(List.of("Green", "Green", "Green", "Green"));

    assertThat(mastermind.guess(List.of("Green", "Green", "Red", "Blue"))).isEqualTo("[2,0]");
  }

  @Test
  void shouldNotBeWellPlacedForRedSecretAndRedBlueGuess5() {
    Mastermind mastermind = new Mastermind(List.of("Green", "Green", "Red", "Red"));

    assertThat(mastermind.guess(List.of("Red", "Red", "Blue", "Blue"))).isEqualTo("[0,2]");
  }

  @Test
  void shouldNotBeWellPlacedForRedSecretAndRedBlueGuess6() {
    List<String> secret = List.of("Green", "Blue", "Yellow", "Red");
    Mastermind mastermind = new Mastermind(secret);
    List<String> reversed = secret.reversed();

    assertThat(mastermind.guess(reversed)).isEqualTo("[0,4]");
  }
}
