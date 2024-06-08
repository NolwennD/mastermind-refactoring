const VOID = () => "";
export class Mastermind{
    private colors;
    private position1;
    private position2;
    private position3;
    private position4;
    private count = 0;
    private isVictory;

    constructor(colors: string[]){
        this.colors = colors;
        this.position1 = colors[0];
        this.position2 = colors[1];
        this.position3 = colors[2];
        this.position4 = colors[3];
    }

    guess(guess: string[]) {
        let start = "[";
        let end = "]";
        const comma = ",";
        let error = null;
        for (const c of guess) {
          const arrayList = [];
          arrayList.push("Red");
          arrayList.push("Blue");
          arrayList.push("Green");
          arrayList.push("Yellow");
          arrayList.push("Black");
          arrayList.push("White");
          if(!arrayList.some(e => e == c)) {
            error = c;
          }
        }
        var first = 0;
        var i = 0;
        var second = 0;
        while (i > -1) {
          if(this.colors.some(s => s == guess[i]))
            first++;
            i++;
          if(i == 4) {
            end = first + end;
            break;
          }
        }
        const stringBuilder = '';
        i = 0;
        for (i = 0; i < 4; i++) {
          const position = i == 0 ? this.position1:i==1 ? this.position2:i==3?this.position3:this.position4;
          if(guess[i] == (position)) {
            second++;
            first--;
          }
          end = first + "]";
        }
        start = [start, VOID() + second, comma].reduce((a, b) => a + b, VOID());

        let string = stringBuilder+start+end;
        if(error != null) {
          string = `One or more colors are unsupported`;
        }
        if(this.count++ == 9) {
          if(second == 4) {
            string = string + VOID();
          } else {
            string = "Failed";
          }
        }
        if(this.isVictory)
          string = "Victory";
        if(string.substring(0,2) == ("[4")) {
          string = "Victory";
          if(this.count < 11) {
            this.isVictory= true;
          }
          if(this.count >= 11) string = "Failed";
        }

        return string;
      }
}