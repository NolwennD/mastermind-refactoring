import { describe, expect, it } from "vitest";
import { Mastermind } from "../src/mastermind";

const fullRed = () => ["Red", "Red", "Red", "Red"];

describe("Mastermind game", () => {
	it("should fail with unsupported color", () => {
		const mastermind = new Mastermind(fullRed());

		expect(mastermind.guess(["Blue", "Blue", "Blue", "Brown"])).toEqual(
			"One or more colors are unsupported",
		);
	});
	it("should found nothing", () => {
		const mastermind = new Mastermind(fullRed());

		expect(mastermind.guess(["Blue", "Blue", "Blue", "Blue"])).toEqual("[0,0]");
	});
	it("should have one well placed color", () => {
		const mastermind = new Mastermind(fullRed());

		expect(mastermind.guess(["Red", "Blue", "Blue", "Blue"])).toEqual("[1,0]");
	});
	it("should have one existing color", () => {
		const mastermind = new Mastermind(["Red", "Green", "Green", "Green"]);

		expect(mastermind.guess(["Blue", "Blue", "Red", "Blue"])).toEqual("[0,1]");
	});
	it("should have two well placed colors", () => {
		const mastermind = new Mastermind(["Green", "Green", "Green", "Green"]);

		expect(mastermind.guess(["Green", "Green", "Red", "Blue"])).toEqual(
			"[2,0]",
		);
	});
	it("should found two colors", () => {
		const mastermind = new Mastermind(["Green", "Green", "Red", "Red"]);

		expect(mastermind.guess(["Red", "Red", "Blue", "Blue"])).toEqual("[0,2]");
	});
	it("should found four colors", () => {
		const secret = ["Green", "Blue", "Yellow", "Red"];
		const mastermind = new Mastermind(secret);
		const reversed = secret.reverse();

		expect(mastermind.guess(reversed)).toEqual("[0,4]");
	});
	it("should win in ten rounds", () => {
		const mastermind = new Mastermind(fullRed());
		var fullBlue = ["Blue", "Blue", "Blue", "Blue"];

		mastermind.guess(fullBlue);
		mastermind.guess(fullBlue);
		mastermind.guess(fullBlue);
		mastermind.guess(fullBlue);
		mastermind.guess(fullBlue);
		mastermind.guess(fullBlue);
		mastermind.guess(fullBlue);
		mastermind.guess(fullBlue);
		mastermind.guess(fullBlue);

		expect(mastermind.guess(fullRed())).toEqual("Victory");
	});
	it("should win in two rounds", () => {
		const mastermind = new Mastermind(fullRed());
		var fullBlue = ["Blue", "Blue", "Blue", "Blue"];

		mastermind.guess(fullBlue);
		mastermind.guess(fullRed());

		expect(mastermind.guess(fullRed())).toEqual("Victory");
	});
	it("should failed at round ten", () => {
		const mastermind = new Mastermind(fullRed());
		var fullBlue = ["Blue", "Blue", "Blue", "Blue"];
		mastermind.guess(fullBlue);
		mastermind.guess(fullBlue);
		mastermind.guess(fullBlue);
		mastermind.guess(fullBlue);
		mastermind.guess(fullBlue);
		mastermind.guess(fullBlue);
		mastermind.guess(fullBlue);
		mastermind.guess(fullBlue);
		mastermind.guess(fullBlue);

		expect(mastermind.guess(fullBlue)).toEqual("Failed");
	});
	it("should failed even if an other guess is correct", () => {
		const mastermind = new Mastermind(fullRed());
		var fullBlue = ["Blue", "Blue", "Blue", "Blue"];
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

		expect(mastermind.guess(fullRed())).toEqual("Failed");
		expect(mastermind.guess(fullRed())).toEqual("Failed");
	});
});
