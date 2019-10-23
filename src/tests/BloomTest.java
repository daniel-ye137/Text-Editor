package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import org.junit.jupiter.api.Test;
import util.BloomFilter;

class BloomTest {
	Collection<String> input = new ArrayList<>();
	Random rand = new Random();

	void wordGenAllChars(int numWords) {
		for (int i = 0; i < numWords; i++) {
			int length = rand.nextInt(30) + 1;
			String out = "";
			for (int l = 0; l <= length; l++) {
				out += (char)(rand.nextInt(94) + 32);
			}
			input.add(out);
		}
	}

	@Test
	void insertOneTest() {
		input.add("Actual String");
		BloomFilter<String> bloom = new BloomFilter<>(input, 64, 5);
		assertTrue(bloom.mightContain("Actual String") == input.contains("Actual String"));
	}

	@Test
	void insertOneAndCheckFakeTest() {
		input.add("Actual String");
		BloomFilter<String> bloom = new BloomFilter<>(input, 64, 5);
		assertFalse(bloom.mightContain("Fake String"));
	}

	@Test
	void insert20Test() {
		int check = 0;
		wordGenAllChars(20);
		BloomFilter<String> bloom = new BloomFilter<>(input, 64, 5);
		for (String entry : input) {
			if (bloom.mightContain(entry)) {
				check++;
			}
		}
		assertTrue(check == 20);
	}

	@Test
	void check20Realand10FakeTest() {
		int check = 0;
		int fake = 0;
		wordGenAllChars(20);
		BloomFilter<String> bloom = new BloomFilter<>(input, 64, 5);
		wordGenAllChars(10);
		for (String entry : input) {
			if (bloom.mightContain(entry)) {
				check++;
			} else {
				fake++;
			}
		}
		assertTrue(check == 20 && fake == 10);
	}

	@Test
	void check150Realand50FakeTest() {
		int check = 0;
		int fake = 0;
		wordGenAllChars(150);
		BloomFilter<String> bloom = new BloomFilter<>(input, 300, 12);
		wordGenAllChars(50);
		for (String entry : input) {
			if (bloom.mightContain(entry)) {
				check++;
			} else {
				fake++;
			}
		}
		assertTrue(check == 150 && fake == 50);
	}

}
