<?php

use App\Mastermind\Mastermind;

function fullRed()
{
    return ["Red", "Red", "Red", "Red"];
}

describe("Mastermind game", function()  {
    it("should fail with unsupported color", function() {
        $mastermind = new Mastermind(fullRed());

        expect($mastermind->guess(["Blue", "Blue", "Blue", "Brown"]))->toBe(
            "One or more colors are unsupported",
        );
    });
	it("should found nothing", function() {
        $mastermind = new Mastermind(fullRed());

        expect($mastermind->guess(["Blue", "Blue", "Blue", "Blue"]))->toBe("[0,0]");
    });
	it("should have one well placed color", function() {
        $mastermind = new Mastermind(fullRed());

        expect($mastermind->guess(["Red", "Blue", "Blue", "Blue"]))->toBe("[1,0]");
    });
	it("should have one existing color", function() {
        $mastermind = new Mastermind(["Red", "Green", "Green", "Green"]);

        expect($mastermind->guess(["Blue", "Blue", "Red", "Blue"]))->toBe("[0,1]");
    });
	it("should have two well placed colors", function() {
        $mastermind = new Mastermind(["Green", "Green", "Green", "Green"]);

        expect($mastermind->guess(["Green", "Green", "Red", "Blue"]))->toBe(
            "[2,0]",
        );
    });
	it("should found two colors", function() {
        $mastermind = new Mastermind(["Green", "Green", "Red", "Red"]);

        expect($mastermind->guess(["Red", "Red", "Blue", "Blue"]))->toBe("[0,2]");
    });
	it("should found four colors", function() {
        $secret = ["Green", "Blue", "Yellow", "Red"];
        $mastermind = new Mastermind($secret);
        $reversed = array_reverse($secret);

        expect($mastermind->guess($reversed))->toBe("[0,4]");
    });
	it("should win in ten rounds", function() {
        $mastermind = new Mastermind(fullRed());
        $fullBlue = ["Blue", "Blue", "Blue", "Blue"];

        $mastermind->guess($$fullBlue);
        $mastermind->guess($$fullBlue);
        $mastermind->guess($$fullBlue);
        $mastermind->guess($$fullBlue);
        $mastermind->guess($$fullBlue);
        $mastermind->guess($$fullBlue);
        $mastermind->guess($$fullBlue);
        $mastermind->guess($$fullBlue);
        $mastermind->guess($$fullBlue);

        expect($mastermind->guess(fullRed()))->toBe("Victory");
    });
	it("should win in two rounds", function() {
        $mastermind = new Mastermind(fullRed());
        $fullBlue = ["Blue", "Blue", "Blue", "Blue"];

        $mastermind->guess($fullBlue);
        $mastermind->guess(fullRed());

        expect($mastermind->guess(fullRed()))->toBe("Victory");
    });
	it("should failed at round ten", function() {
        $mastermind = new Mastermind(fullRed());
        $fullBlue = ["Blue", "Blue", "Blue", "Blue"];
        $mastermind->guess($fullBlue);
        $mastermind->guess($fullBlue);
        $mastermind->guess($fullBlue);
        $mastermind->guess($fullBlue);
        $mastermind->guess($fullBlue);
        $mastermind->guess($fullBlue);
        $mastermind->guess($fullBlue);
        $mastermind->guess($fullBlue);
        $mastermind->guess($fullBlue);

        expect($mastermind->guess($fullBlue))->toBe("Failed");
    });
	it("should failed even if an other guess is correct", function() {
        $mastermind = new Mastermind(fullRed());
        $fullBlue = ["Blue", "Blue", "Blue", "Blue"];
        $mastermind->guess($fullBlue);
        $mastermind->guess($fullBlue);
        $mastermind->guess($fullBlue);
        $mastermind->guess($fullBlue);
        $mastermind->guess($fullBlue);
        $mastermind->guess($fullBlue);
        $mastermind->guess($fullBlue);
        $mastermind->guess($fullBlue);
        $mastermind->guess($fullBlue);
        $mastermind->guess($fullBlue);

        expect($mastermind->guess(fullRed()))->toBe("Failed");
        expect($mastermind->guess(fullRed()))->toBe("Failed");
    });
});

