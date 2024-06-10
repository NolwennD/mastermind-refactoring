<?php

namespace App\Mastermind;

function VOID()
{
    return "";
}

class Mastermind
{
    private $colors;
    private $position1;
    private $position2;
    private $position3;
    private $position4;
    private $count = 0;
    private $isVictory;


    public function __construct($colors)
    {
        $this->colors = $colors;
        $this->position1 = $colors[0];
        $this->position2 = $colors[1];
        $this->position3 = $colors[2];
        $this->position4 = $colors[3];

    }

public function guess($guess) {
$start = "[";
        $end = "]";
        $comma = ",";
        $error = null;
        foreach ($guess as $c) {
    $arrayList = [];
    $arrayList[] = "Red";
    $arrayList[] = "Blue";
    $arrayList[] = "Green";
    $arrayList[] = "Yellow";
    $arrayList[] = "Black";
    $arrayList[] = "White";
    if(!in_array($c, $arrayList)) {
        $error = $c;
    }
        }
        $first = 0;
        $i = 0;
        $second = 0;
        while ($i > -1) {
            if(in_array($guess[$i], $this->colors))
            $first++;
            $i++;
          if($i == 4) {
              $end = $first . $end;
              break;
          }
        }
        $stringBuilder = '';
        $i = 0;
        for ($i = 0; $i < 4; $i++) {
            $position = $i == 0 ? $this->position1 : (($i == 1 ? $this->position2 : $i == 3) ? $this->position3 : $this->position4);
            if($guess[$i] == ($position)) {
                $second++;
                $first--;
            }
            $end = $first . "]";
        }
        $start = array_reduce([$start, VOID() . $second, $comma], fn($a, $b) => $a.$b, VOID());

        $string = $stringBuilder.$start.$end;
        if($error) {
            $string = "One or more colors are unsupported";
        }
        if($this->count++ == 9) {
            if($second == 4) {
                $string = $string . VOID();
            } else {
                $string = "Failed";
            }
        }
        if($this->isVictory)
            $string = "Victory";
        if(substr($string,0,2) == ("[4")) {
            $string = "Victory";
            if($this->count < 11) {
                $this->isVictory= true;
            }
            if($this->count >= 11) {
                var_dump($this->count);die();
                $string = "Failed";
            }
        }

        return $string;
      }
}